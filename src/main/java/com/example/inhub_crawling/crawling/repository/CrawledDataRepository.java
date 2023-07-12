package com.example.inhub_crawling.crawling.repository;

import com.example.inhub_crawling.crawling.entity.CrawledJob;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface CrawledDataRepository extends JpaRepository<CrawledJob, Long> {
    List<CrawledJob> findByCreatedAtBefore(LocalDateTime date);
}
