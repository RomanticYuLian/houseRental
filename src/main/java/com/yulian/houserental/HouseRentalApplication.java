package com.yulian.houserental;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.yulian.houserental.mapper")
@SpringBootApplication
public class HouseRentalApplication {
    public static void main(String[] args) {
        SpringApplication.run(HouseRentalApplication.class, args);
    }
}
