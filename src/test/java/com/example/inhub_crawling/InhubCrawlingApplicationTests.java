//package com.example.inhub_crawling;
//
//import com.google.gson.Gson;
//import com.google.gson.GsonBuilder;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.chrome.ChromeOptions;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.io.FileWriter;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Scanner;
//
//@SpringBootTest
//class InhubCrawlingApplicationTests {
//
//    @Test
//    @DisplayName("크롤링 테스트")
//    void t001() throws Exception {
//
//        String keyword = "자바";
//
//        int pageNum = 1;
//
//        ChromeOptions options = new ChromeOptions();
////        options.addArguments("--headless"); // 브라우저 창을 표시하지 않고 백그라운드에서 실행
//
//        WebDriver driver = new ChromeDriver(options);
//
//        List<CrawledJob> crawledJobs = new ArrayList<>();
//
//
//            String url = "https://www.jobkorea.co.kr/Search/?stext=" + keyword + "&tabType=recruit&Page_No=";
//
//            driver.get(url);
//
//            try {
//                Thread.sleep(2000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//
//            WebElement html = driver.findElement(By.cssSelector("div.list-default"));
//            WebElement list = html.findElement(By.tagName("ul"));
//            java.util.List<WebElement> jobs = list.findElements(By.tagName("li"));
//
//            for (WebElement job : jobs) {
//                String company = job.findElement(By.cssSelector("a.name")).getText().trim();
//                String detail = job.findElement(By.cssSelector("a.title")).getText().trim();
//                String jobUrl = job.findElement(By.tagName("a")).getAttribute("href");
//                String experience = job.findElement(By.cssSelector("span.exp")).getText().trim();
//                String location = job.findElement(By.cssSelector("span.loc.long")).getText();
//                String apply = job.findElement(By.cssSelector("div.post-list-apply")).getText().trim();
//
//                CrawledJob crawledJob = new CrawledJob(company, detail, jobUrl, experience, location, apply);
//                crawledJobs.add(crawledJob);
//            }
//
//
//        driver.quit();
//
//        // 출력
//        for (CrawledJob job : crawledJobs) {
//            System.out.println("회사: " + job.getCompany());
//            System.out.println("세부 정보: " + job.getDetail());
//            System.out.println("URL: " + job.getJobUrl());
//            System.out.println("경력: " + job.getExperience());
//            System.out.println("지역: " + job.getLocation());
//            System.out.println("지원 정보: " + job.getApply());
//            System.out.println();
//        }
//
//        // JSON 파일로 저장
//        Gson gson = new GsonBuilder().setPrettyPrinting().create();
//        String json = gson.toJson(crawledJobs);
//
//        try (FileWriter writer = new FileWriter("output.json")) {
//            writer.write(json);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Data
//    @AllArgsConstructor
//    private static class CrawledJob {
//        private String company;
//        private String detail;
//        private String jobUrl;
//        private String experience;
//        private String location;
//        private String apply;
//    }
//
//}
