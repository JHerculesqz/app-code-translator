package com.firelord;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan("com.firelord")
@ServletComponentScan
@EnableScheduling
public class QuaApplication {
    public static void main(String[] args) {
        SpringApplication.run(QuaApplication.class, args);
    }
}
