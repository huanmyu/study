package com.huanyu.study.service;

import java.io.StringWriter;
import java.util.Arrays;
import java.util.UUID;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.StringRedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.huanyu.study.entity.Permission;
import com.huanyu.study.entity.Role;
import com.huanyu.study.entity.User;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import javax.mail.internet.MimeMessage;

@Component("userService")
public class UserServiceImpl implements UserService {

    private final static int expireTime = 48 * 60 * 60;

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final PermissionRepository permissionRepository;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private SimpleMailMessage templateMessage;

    @Autowired
    private VelocityEngine velocityEngine;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository,
            PermissionRepository permissionRepository) {
        super();
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.permissionRepository = permissionRepository;
    }

    @Override
    public User register(String email) {
        Assert.notNull(email, "email must not be null");
        String permissionName = "study";
        Permission permission = permissionRepository.findByName(permissionName);
        if (permission == null) {
            permission = new Permission("study");
            permissionRepository.save(permission);
        }

        String roleName = "admin";
        Role role = roleRepository.findByName(roleName);
        if (role == null) {
            role = new Role("admin", Arrays.asList(permission));
            roleRepository.save(role);
        }

        User user = userRepository.findByEmail(email);
        if (user == null) {
            Boolean isActived = false;
            user = new User(email, isActived, role);
            userRepository.save(user);
        }

        String activeCode = UUID.randomUUID().toString();
        System.out.println("actvie code ===> " + activeCode);
        ValueOperations<String, String> ops = stringRedisTemplate.opsForValue();
        ops.set(activeCode, user.getId().toString());
        stringRedisTemplate.execute(new RedisCallback<Object>() {
            public Object doInRedis(RedisConnection connection) throws DataAccessException {
                return ((StringRedisConnection)connection).expire(activeCode, expireTime);
            }
        });

        Boolean sendResult = SendActiveEmail(user, activeCode);
        System.out.println("send email result" + sendResult);
        return user;
    }

    @Override
    public User checkActive(String activeCode) {
        ValueOperations<String, String> ops = stringRedisTemplate.opsForValue();
        Long userId = Long.parseLong(ops.get(activeCode));
        return userRepository.findOne(userId);
    }

    private Boolean SendSimpleActiveEmail(User user, String activeCode) {
         UriComponents activeUri = UriComponentsBuilder.fromPath("/users/active/{code}").build().expand(activeCode).encode();
         SimpleMailMessage msg = new SimpleMailMessage();
         msg.setSubject("欢迎注册Continuous Study平台");
         msg.setTo(user.getEmail());
         msg.setText("Dear User, thank you for register. You active url is " +
         activeUri.toUriString());
         try {
            mailSender.send(msg);
            return true;
         } catch (MailException ex) {
             System.err.println(ex.getMessage());
             return false;
         }
     }

    private Boolean SendActiveEmail(User user, String activeCode) {
        MimeMessagePreparator preparator = new MimeMessagePreparator() {
            @Override
            public void prepare(MimeMessage mimeMessage) throws Exception {
                MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
                message.setTo(user.getEmail());
                message.setFrom("continuous_study@163.com");
                message.setSubject("欢迎注册Continuous Study平台");
                UriComponents activeUri = UriComponentsBuilder.fromPath("/users/active/{code}").build().expand(activeCode).encode();
                String activeUrl = "http://127.0.0.1:8080" + activeUri.toUriString();
                VelocityContext context = new VelocityContext();
                context.put("activeUrl", activeUrl);
                StringWriter sw = new StringWriter();
                velocityEngine.mergeTemplate("templates/mailer/active-user.vm", "utf-8", context, sw);
                System.out.println(sw.toString());
                message.setText(sw.toString(), true);
            }
        };
        try {
            mailSender.send(preparator);
            return true;
        } catch (MailException ex) {
            System.err.println(ex.getMessage());
            return false;
        }
    }

}
