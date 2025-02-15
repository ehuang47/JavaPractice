package com.example.d5_mvc_jsp_quiz.controller;

import com.example.d5_mvc_jsp_quiz.exception.type.EntityNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FallbackController {

  @GetMapping("/**")
  public String handleUnsupportedGetRequests(){

    return "redirect:/login";
  }

  @RequestMapping("/**")
  public String handleAllUnsupportedRequests(){
    throw new EntityNotFoundException();
  }
}
