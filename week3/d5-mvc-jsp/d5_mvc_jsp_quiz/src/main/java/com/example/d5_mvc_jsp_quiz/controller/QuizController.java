package com.example.d5_mvc_jsp_quiz.controller;

import com.example.d5_mvc_jsp_quiz.domain.Quiz;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class QuizController {
    @GetMapping(value="/quiz") //localhost:8080/quiz
    public String quizModelView() {
        System.out.println("hitting /quiz");
//        Quiz quiz = new Quiz(1, "Test", null);
//        model.addAttribute("quiz", quiz);
        return "quiz";
    }
}
