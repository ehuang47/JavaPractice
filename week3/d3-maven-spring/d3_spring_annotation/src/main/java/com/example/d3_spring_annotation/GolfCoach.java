package com.example.d3_spring_annotation;

import org.springframework.stereotype.Component;

@Component("golfCoach")
public class GolfCoach implements Coach{
    @Override
    public void getDailyWorkOutSchedule() {
        System.out.println("Some schedule for golf");
    }
}
