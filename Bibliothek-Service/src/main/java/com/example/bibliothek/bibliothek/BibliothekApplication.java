package com.example.bibliothek.bibliothek;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class BibliothekApplication {

    public static void main(String[] args) {
        SpringApplication.run(BibliothekApplication.class, args);
    }

}
