package com.naic;


import com.naic.common.Dscsq;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class NaicApplication {

    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(NaicApplication.class);
    }
    public static void main(String[] args) {
        SpringApplication.run(NaicApplication.class,args);
    }
}
