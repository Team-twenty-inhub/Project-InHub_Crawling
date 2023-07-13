package com.example.inhub_crawling.crawling.controller;

import com.example.inhub_crawling.crawling.dto.CrawledJobDto;
import com.example.inhub_crawling.crawling.entity.CrawledJob;
import com.example.inhub_crawling.crawling.repository.CrawledDataRepository;
import com.example.inhub_crawling.crawling.service.CrawlingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/crawling")
@RequiredArgsConstructor
public class CrawlingController {
    private final CrawlingService crawlingService;
    private final CrawledDataRepository crawledDataRepository;

    @GetMapping("/saveTest")
    public List<CrawledJobDto> save() {
        return crawlingService.crawlAndSaveJobs();
    }

    @GetMapping("/job-infos")
    public List<CrawledJobDto> getCrawledJobs() {
        List<CrawledJob> crawledJobEntities = crawledDataRepository.findAll();
        List<CrawledJobDto> crawledJobs = new ArrayList<>();

        for (CrawledJob crawledJob : crawledJobEntities) {
            CrawledJobDto crawledJobDto = new CrawledJobDto();
            crawledJobDto.setCompany(crawledJob.getCompany());
            crawledJobDto.setDetail(crawledJob.getDetail());
            crawledJobDto.setJobUrl(crawledJob.getJobUrl());
            crawledJobDto.setExperience(crawledJob.getExperience());
            crawledJobDto.setLocation(crawledJob.getLocation());
            crawledJobDto.setApply(crawledJob.getApply());

            crawledJobs.add(crawledJobDto);
        }

        return crawledJobs;
    }
}
