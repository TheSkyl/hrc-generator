package com.cebrains.hrc.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.cebrains.hrc"})
public class HRCRestApplication {

    public static void main(String[] args) {
        SpringApplication.run(HRCRestApplication.class, args);
    }
}
