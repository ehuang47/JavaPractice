package com.example.d5_mvc_jsp_quiz.controller;

import com.example.d5_mvc_jsp_quiz.domain.Quiz;
import com.example.d5_mvc_jsp_quiz.domain.QuizResult;
import com.example.d5_mvc_jsp_quiz.domain.QuizResultChoice;
import com.example.d5_mvc_jsp_quiz.service.QuizResultService;
import com.example.d5_mvc_jsp_quiz.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/quiz-result")
public class QuizResultController {
  private final QuizResultService quizResultService;
  private final QuizService quizService;

  @Autowired
  public QuizResultController(QuizResultService quizResultService, QuizService quizService) {
    this.quizResultService = quizResultService;
    this.quizService = quizService;
  }

  @PostMapping("")
  public String submitQuiz(@RequestParam Map<String, String> body, Model model) {
//    System.out.println(body);
    QuizResult submission = quizResultService.bodyMapper(body);
//    System.out.println(submission);

    Long quizResultId = quizResultService.save(submission);
    QuizResult savedQuizResult = quizResultService.findById(quizResultId);
    List<QuizResultChoice> savedChoiceList = quizResultService.findAllByQuizResultId(quizResultId);

    Quiz quiz = quizService.findById(submission.getQuizId());
    model.addAttribute("quizCategory", quiz.getCategory());
    // use question service to get all correct choices according to the questions here
    model.addAttribute("correctChoiceList", "");
    model.addAttribute("savedQuizResult", savedQuizResult);
    model.addAttribute("savedChoiceList", savedChoiceList);
    return "quiz-result";
  }
}
