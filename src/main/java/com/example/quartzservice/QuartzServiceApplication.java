package com.example.quartzservice;

import com.example.quartzservice.response.BaseResponse;
import org.mybatis.spring.annotation.MapperScan;
import org.quartz.Scheduler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.example.quartzservice.mapper")
@SpringBootApplication(scanBasePackages = {"com.example.quartzservice"})
public class QuartzServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(QuartzServiceApplication.class, args);
    }

}
