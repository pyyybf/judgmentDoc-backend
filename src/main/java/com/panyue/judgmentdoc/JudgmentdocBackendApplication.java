package com.panyue.judgmentdoc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class JudgmentdocBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(JudgmentdocBackendApplication.class, args);
    }

}
