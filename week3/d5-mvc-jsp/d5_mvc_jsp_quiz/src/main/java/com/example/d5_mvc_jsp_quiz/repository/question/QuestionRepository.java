package com.example.d5_mvc_jsp_quiz.repository.question;

import com.example.d5_mvc_jsp_quiz.domain.Choice;
import com.example.d5_mvc_jsp_quiz.domain.Question;
import lombok.Getter;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class QuestionRepository {
    @Getter
    private static final Question question;
    private static final List<Choice> choices;

    static {
        choices = new ArrayList<>();
        choices.add(new Choice(1, 1,"42"));
        choices.add(new Choice(2, 2,"correct answer"));
        choices.add(new Choice(3, 3,"yes"));
        question = new Question(
                1,
                1,
                1,
                "What is the correct answer?",
                choices);
    }

    // need a method to select 5 random questions
    // create a method to query all choices per question and return the list (using choice dao row mapper)
}
