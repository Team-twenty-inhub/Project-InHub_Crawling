package com.example.inhub_crawling.crawling.controller;

import com.example.inhub_crawling.crawling.dto.CrawledJobDto;
import com.example.inhub_crawling.crawling.service.CrawlingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/crawling")
@RequiredArgsConstructor
public class CrawlingController {
    private final CrawlingService crawlingService;
    @GetMapping("/")
    public List<CrawledJobDto> crawlAndSaveJobs() {
        List<CrawledJobDto> crawledJobs = crawlingService.crawlJobs();

        return crawledJobs;
    }
}
