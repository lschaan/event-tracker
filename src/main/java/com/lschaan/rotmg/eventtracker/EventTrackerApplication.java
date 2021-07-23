package com.lschaan.rotmg.eventtracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class EventTrackerApplication {

    public static void main(String[] args) {
        SpringApplication.run(EventTrackerApplication.class, args);
    }

}