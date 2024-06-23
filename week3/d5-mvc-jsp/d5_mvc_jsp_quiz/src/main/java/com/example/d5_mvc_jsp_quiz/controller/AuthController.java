package com.example.d5_mvc_jsp_quiz.controller;

import com.example.d5_mvc_jsp_quiz.domain.User;
import com.example.d5_mvc_jsp_quiz.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class AuthController {
  private final AuthService authService;

  @Autowired
  public AuthController(AuthService authService) {
    this.authService = authService;
  }

  @GetMapping(value = "/login") //localhost:8080/user
  public String getLoginView() {
    return "login";
  }

  @PostMapping(value = "/login")
  public String login(@RequestParam Map<String, String> body) {
    System.out.println(body);
    System.out.println(body.get("username"));

    User user = authService.validateLogin(body.get("username"), body.get("password"));
    System.out.println(user);

    return "redirect:/home";
  }

  @GetMapping(value = "/register") //localhost:8080/user
  public String getRegisterView() {
    return "register";
  }

  @PostMapping(value = "/register")
  public String register(@RequestParam Map<String, String> body) {
    authService.validateRegistration(body.get("email"),
      body.get("username"),
      body.get("password"),
      body.get("firstName"),
      body.get("lastName"));
    return "redirect:/login";
  }
}
