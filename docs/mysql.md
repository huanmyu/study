# 使用MySQL容器，避免安装
- 运行MySQL容器
```
docker rm -f study-mysql  //删除存在的MySQL容器
docker run --name study-mysql -p 127.0.0.1:33060:3306 -e MYSQL_ROOT_PASSWORD=123456 -d mysql:5.7  //运行MySQL容器
```
- 进入容器并运行命令
```
docker exec -it study-mysql mysql -uroot -p
Enter password:123456

// 本机有MySQL也可以
mysql -h127.0.0.1 -P33060 -uroot -p
Enter password:123456
```
- 创建数据库
```
CREATE DATABASE study DEFAULT CHARACTER SET utf8 DEFAULT COLLATE utf8_general_ci;
```
- spring.jpa.hibernate.ddl-auto属性值的意义
hibernate.hbm2ddl.auto Automatically validates or exports schema DDL to the database when the SessionFactory is created. 
With create-drop, the database schema will be dropped when the SessionFactory is closed explicitly.
So the list of possible options are,
validate: validate the schema, makes no changes to the database.
update: update the schema.
create: creates the schema, destroying previous data.
create-drop: drop the schema when the SessionFactory is closed explicitly, typically when the application is stopped.
