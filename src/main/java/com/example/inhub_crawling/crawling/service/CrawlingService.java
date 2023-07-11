package com.example.inhub_crawling.crawling.service;

import com.example.inhub_crawling.crawling.dto.CrawledJobDto;
import com.example.inhub_crawling.crawling.entity.CrawledJob;
import com.example.inhub_crawling.crawling.repository.CrawledDataRepository;
import lombok.RequiredArgsConstructor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CrawlingService {
    private final CrawledDataRepository crawledDataRepository;

    //    @Value("${custom.}")
//    private int page
    @Transactional
    public List<CrawledJobDto> crawlJobs() {
        String keyword = "자바";
        int startPage = 1;
        int endPage = 5;

        ChromeOptions options = new ChromeOptions();
//        options.addArguments("--headless"); // 브라우저 창을 표시하지 않고 백그라운드에서 실행

        WebDriver driver = new ChromeDriver(options);

        List<CrawledJobDto> crawledJobs = new ArrayList<>();

        for (int pageNum = startPage; pageNum <= endPage; pageNum++) {
            String url = "https://www.jobkorea.co.kr/Search/?stext=" + keyword + "&tabType=recruit&Page_No=" + pageNum;

            driver.get(url);

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            WebElement html = driver.findElement(By.cssSelector("div.list-default"));
            WebElement list = html.findElement(By.tagName("ul"));
            List<WebElement> jobs = list.findElements(By.tagName("li"));

            for (WebElement job : jobs) {
                String company = job.findElement(By.cssSelector("a.name")).getText().trim();
                String detail = job.findElement(By.cssSelector("a.title")).getText().trim();
                String jobUrl = job.findElement(By.tagName("a")).getAttribute("href");
                String experience = job.findElement(By.cssSelector("span.exp")).getText().trim();
                String location = job.findElement(By.cssSelector("span.loc.long")).getText();
                String apply = job.findElement(By.cssSelector("div.post-list-apply")).getText().trim();


                CrawledJob crawledJob = new CrawledJob(company, detail, jobUrl, experience, location, apply);
                CrawledJob saved = crawledDataRepository.save(crawledJob);

                crawledJobs.add(new CrawledJobDto(company, detail, jobUrl, experience, location, apply));

                System.out.println(">>>>>>>>>>>>>>>>" + saved.getJobUrl());
            }
        }

        driver.quit();

        // 크롤링한 데이터를 JPA를 통해 DB에 저장
        List<CrawledJob> entities = crawledJobs.stream()
                .map(CrawledJob::toSaveEntity)
                .collect(Collectors.toList());
        crawledDataRepository.saveAll(entities);

        System.out.println(entities);

        return crawledJobs;
    }
}