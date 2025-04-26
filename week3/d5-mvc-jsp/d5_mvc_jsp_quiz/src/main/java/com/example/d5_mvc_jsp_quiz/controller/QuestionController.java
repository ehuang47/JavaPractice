package com.example.d5_mvc_jsp_quiz.controller;

import com.example.d5_mvc_jsp_quiz.domain.Question;
import com.example.d5_mvc_jsp_quiz.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/question")
public class QuestionController extends AbstractController {
  private final QuestionService questionService;

  @Autowired
  public QuestionController(QuestionService questionService) {
    this.questionService = questionService;
  }

  @GetMapping("/management")
  public String getQuestionManagement(Model model){
    final List<Question> questionList = questionService.findAll();
    model.addAttribute("questionList", questionList);
    return "admin/question-management";
  }
}
