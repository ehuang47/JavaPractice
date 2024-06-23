package com.example.d5_mvc_jsp_quiz.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ContactUsController {
  @GetMapping(value = "/contact-us") //localhost:8080/about
  public String aboutModelView(Model model) {
    return "contact-us";
  }


}
