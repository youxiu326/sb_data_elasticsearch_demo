package com.youxiu326;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableElasticsearchRepositories
//@EntityScan("com.youxiu326.entity")
//@EnableJpaRepositories("com.youxiu326.dao")
public class SbDataElasticsearchDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SbDataElasticsearchDemoApplication.class, args);
    }

}
