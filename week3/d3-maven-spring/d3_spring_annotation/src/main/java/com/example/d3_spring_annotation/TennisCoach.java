package com.example.d3_spring_annotation;

import org.springframework.stereotype.Component;

@Component("tennisCoach")
public class TennisCoach implements Coach{
    @Override
    public void getDailyWorkOutSchedule() {
        System.out.println("Some schedule for tennis");
    }
}
