package com.dd.edu;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@ComponentScan(basePackages = {"com.dd"})

public class EduApplication {
    public static void main(String[] args) {
        SpringApplication.run(com.dd.edu.EduApplication.class,args);
    }
}
