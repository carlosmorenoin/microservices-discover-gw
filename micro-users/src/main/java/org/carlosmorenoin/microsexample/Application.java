package org.carlosmorenoin.microsexample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Import;

import org.carlosmorenoin.microsexample.core.UserService;

@SpringBootApplication
@EnableDiscoveryClient
@Import(UserService.class)
public class Application {

    public static void main(String[] args) {
        System.setProperty("spring.config.name", "micro-users");
        SpringApplication.run(Application.class);
    }
}
