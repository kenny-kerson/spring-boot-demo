package com.kenny.gscaching;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
// 캐싱기능을 활성화 시킨다 : @EnableCaching
public class GsCachingApplication {

    public static void main(String[] args) {
        SpringApplication.run(GsCachingApplication.class, args);
    }

}
