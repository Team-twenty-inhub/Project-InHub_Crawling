package com.example.inhub_crawling.crawling.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CrawledJobDto {
    private String company;
    private String detail;
    private String jobUrl;
    private String experience;
    private String location;
    private String apply;
}
