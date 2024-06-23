package com.example.d5_mvc_jsp_quiz.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/contact-us")
public class ContactUsController {
  @GetMapping(value = "") //localhost:8080/about
  public String aboutModelView(Model model) {
    return "contact-us";
  }


}
