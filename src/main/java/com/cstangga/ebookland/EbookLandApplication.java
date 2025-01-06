package com.cstangga.ebookland;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing // @CreatedDate, @LastModifiedDate 사용에 필요
public class EbookLandApplication {

    public static void main(String[] args) {
        SpringApplication.run(EbookLandApplication.class, args);
    }

}
