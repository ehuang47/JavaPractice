package com.example.d3_spring_annotation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class Player {
    private TennisCoach tennisCoach; //dep1, manually injected
    private GolfCoach golfCoach; //dep2, setter injected
//    @Autowired
    private FootballCoach footballCoach; //dep3, field injected
//    @Autowired
//    public Player(TennisCoach tennisCoach){
//        this.tennisCoach = tennisCoach;
//    }

    @Autowired
    public Player(TennisCoach tennisCoach, FootballCoach footballCoach){
        this.tennisCoach = tennisCoach;
        this.footballCoach = footballCoach;
    }

    public TennisCoach getTennisCoach() {
        return tennisCoach;
    }
    public void setTennisCoach(TennisCoach tennisCoach) {
        this.tennisCoach = tennisCoach;
    }
    public GolfCoach getGolfCoach() {
        return golfCoach;
    }
    @Autowired
    public void setGolfCoach(GolfCoach golfCoach) {
        this.golfCoach = golfCoach;
    }
    public FootballCoach getFootballCoach() {
        return footballCoach;
    }
    public void setFootballCoach(FootballCoach footballCoach) {
        this.footballCoach = footballCoach;
    }
}
