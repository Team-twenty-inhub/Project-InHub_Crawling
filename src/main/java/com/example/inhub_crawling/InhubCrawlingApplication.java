package com.example.inhub_crawling;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class InhubCrawlingApplication {

    public static void main(String[] args) {
        SpringApplication.run(InhubCrawlingApplication.class, args);
    }

}
