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
    public String save() {
        crawlingService.removeOutdatedJobs();  // 14일 이전 데이터 삭제
        crawlingService.crawlAndSaveJobs();    // 크롤링
        crawlingService.removeDuplicateJobs(); // 중복 데이터 제거

        return "성공";
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
