package com.example.inhub_crawling.crawling.component;

import com.example.inhub_crawling.crawling.service.CrawlingService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class CrawlingScheduler {
    private final CrawlingService crawlingService;

    @Scheduled(cron = "0 0 6 * * *")
    public void saveJobsData() {
        crawlingService.removeOutdatedJobs();  // 14일 이전 데이터 삭제
        crawlingService.crawlAndSaveJobs();    // 크롤링
        crawlingService.removeDuplicateJobs(); // 중복 데이터 제거
    }
}

