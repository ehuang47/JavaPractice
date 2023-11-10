package com.example.d3_spring_annotation;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"com.example.d3_spring_annotation"})
public class PlayerConfig {
    @Bean
    public GolfCoach golfCoach() { return new GolfCoach();}
    @Bean
    public FootballCoach footballCoach() { return new FootballCoach();}
}
