package com.vr.rental;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
@EnableFeignClients
@EnableCaching
public class VehicleRentalApplication {

    public static void main(String[] args) {
        SpringApplication.run(VehicleRentalApplication.class, args);
    }

}
