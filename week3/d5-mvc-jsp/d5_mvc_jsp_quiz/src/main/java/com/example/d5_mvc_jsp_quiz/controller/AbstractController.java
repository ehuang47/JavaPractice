package com.example.d5_mvc_jsp_quiz.controller;

import com.example.d5_mvc_jsp_quiz.exception.type.MethodNotAllowedException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AbstractController {
  @RequestMapping()
  public String handleAllUnsupportedRequests(){
    throw new MethodNotAllowedException();
  }
}
