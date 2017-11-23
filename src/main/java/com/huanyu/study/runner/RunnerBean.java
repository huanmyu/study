package com.huanyu.study.runner;

import org.springframework.boot.*;
import org.springframework.stereotype.*;

@Component
public class RunnerBean implements CommandLineRunner {

    public void run(String... args) {
        System.out.println("This is runner bean");
    }

}
