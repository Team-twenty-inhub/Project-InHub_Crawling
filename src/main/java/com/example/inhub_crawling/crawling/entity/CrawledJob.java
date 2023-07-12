package com.example.inhub_crawling.crawling.entity;

import com.example.inhub_crawling.crawling.dto.CrawledJobDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class CrawledJob {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String company;
    private String detail;
    private String jobUrl;
    private String experience;
    private String location;
    private String apply;

    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdAt;

    public static CrawledJob toSaveEntity(CrawledJobDto crawledJobDto) {
        CrawledJob build = CrawledJob.builder()
                .company(crawledJobDto.getCompany())
                .detail(crawledJobDto.getDetail())
                .jobUrl(crawledJobDto.getJobUrl())
                .experience(crawledJobDto.getExperience())
                .location(crawledJobDto.getLocation())
                .apply(crawledJobDto.getApply())
                .build();

        return build;
    }
    public CrawledJob (String company, String detail, String jobUrl, String experience, String location, String apply) {
        this.company = company;
        this.detail = detail;
        this.jobUrl = jobUrl;
        this.experience = experience;
        this.location = location;
        this.apply = apply;
    }

}
