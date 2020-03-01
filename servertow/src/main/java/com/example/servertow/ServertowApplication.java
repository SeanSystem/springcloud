package com.example.servertow;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class ServertowApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServertowApplication.class, args);
    }

}
