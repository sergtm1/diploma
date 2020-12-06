package com.diploma.ekg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = "com.diploma.ekg.entity")
@EnableJpaRepositories(basePackages = "com.diploma.ekg.repository")
public class EkgApplication {

    public static void main(String[] args) {
        SpringApplication.run(EkgApplication.class, args);
    }
}
