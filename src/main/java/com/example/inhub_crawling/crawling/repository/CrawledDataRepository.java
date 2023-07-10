package com.example.inhub_crawling.crawling.repository;

import com.example.inhub_crawling.crawling.entity.CrawledJob;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CrawledDataRepository extends JpaRepository<CrawledJob, Long> {
}
