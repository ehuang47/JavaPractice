package com.example.d5_mvc_jsp_quiz.controller;

import com.example.d5_mvc_jsp_quiz.domain.Quiz;
import com.example.d5_mvc_jsp_quiz.domain.QuizResult;
import com.example.d5_mvc_jsp_quiz.service.QuizResultService;
import com.example.d5_mvc_jsp_quiz.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/home")
public class HomeController {
  private final QuizService quizService;
  private final QuizResultService quizResultService;

  @Autowired
  public HomeController(QuizService quizService, QuizResultService quizResultService) {
    this.quizService = quizService;
    this.quizResultService = quizResultService;
  }

  @GetMapping("")
  public String getHomeView(Model model) {
    List<Quiz> quizList = quizService.findAll();
    model.addAttribute("quizList", quizList);

    List<QuizResult> quizResultList = quizResultService.findAll();
    model.addAttribute("quizResultList", quizResultList);

    Map<Long, String> quizIdToCategory = new LinkedHashMap<>();
    for (Quiz q : quizList) {
      quizIdToCategory.put(q.getId(), q.getCategory());
    }
    model.addAttribute("quizIdToCategory", quizIdToCategory);
    return "home";
  }
}
