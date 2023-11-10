package com.example.d3_spring_annotation;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AnnotationDemo {
    public static void main(String[] args){
        // With annotation + XML bean configuration
//        ApplicationContext ac = new ClassPathXmlApplicationContext("playerBean.xml");
        // With pure annotations
//        ApplicationContext ac = new AnnotationConfigApplicationContext(PlayerConfig.class);
        // With pure XML configurations
        ApplicationContext ac = new ClassPathXmlApplicationContext("pureXMLPlayerBean.xml");
        Player player = ac.getBean("player", Player.class);

        TennisCoach tc = player.getTennisCoach();
        GolfCoach gc = player.getGolfCoach();
        FootballCoach fc = player.getFootballCoach();

        tc.getDailyWorkOutSchedule();
        gc.getDailyWorkOutSchedule();
        fc.getDailyWorkOutSchedule();
    }

}
