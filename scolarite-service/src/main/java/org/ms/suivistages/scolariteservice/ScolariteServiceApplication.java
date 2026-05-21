package org.ms.suivistages.scolariteservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ScolariteServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(ScolariteServiceApplication.class, args);
    }
}
