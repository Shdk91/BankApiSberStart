package com.example.bankapisberstart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class BankApiSberStartApplication {

    public static void main(String[] args) {
        SpringApplication.run(BankApiSberStartApplication.class, args);
    }

}
