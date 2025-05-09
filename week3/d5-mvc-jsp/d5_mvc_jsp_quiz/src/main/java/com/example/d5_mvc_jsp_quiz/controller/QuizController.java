package com.example.d5_mvc_jsp_quiz.controller;

import com.example.d5_mvc_jsp_quiz.domain.Quiz;
import com.example.d5_mvc_jsp_quiz.service.QuizService;
import com.example.d5_mvc_jsp_quiz.utils.TimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/quiz")
public class QuizController extends AbstractController {
  private final QuizService quizService;

  @Autowired
  public QuizController(QuizService quizService) {
    this.quizService = quizService;
  }

  @GetMapping("/{id}") //localhost:8080/quiz/1
  public String quizModelView(@PathVariable("id") Long id, Model model) {
    Quiz quiz = this.quizService.findByIdWithLimitQuestionsAndChoices(id, 10);
    String dateStarted = TimeUtils.getCurrentTimestamp();
    model.addAttribute("quiz", quiz);
    model.addAttribute("dateStarted", dateStarted);
    return "quiz";
  }
}
