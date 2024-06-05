package com.example.d5_mvc_jsp_quiz.controller;

import com.example.d5_mvc_jsp_quiz.service.QuizResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
@RequestMapping("/quiz-result")
public class QuizResultController {
  private final QuizResultService quizResultService;

  @Autowired
  public QuizResultController(QuizResultService quizResultService) {
    this.quizResultService = quizResultService;
  }

  @PostMapping("")
  public String submitQuiz(@RequestParam Map<String, String> body) {
    // use service.saveQuizResults, return the result
    System.out.println(body);
    return "quiz-result";
  }
}
