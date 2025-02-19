package com.example.myjobtracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.example.myjobtracker"})
public class MyJobTrackerApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyJobTrackerApplication.class, args);
    }

}
