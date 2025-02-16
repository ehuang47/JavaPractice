package com.example.d5_mvc_jsp_quiz.controller;

import com.example.d5_mvc_jsp_quiz.domain.User;
import com.example.d5_mvc_jsp_quiz.service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {
  private final AuthService authService;

  @Autowired
  public AuthController(AuthService authService) {
    this.authService = authService;
  }

  @GetMapping(value = "/login") //localhost:8080/user
  public String getLoginView(HttpServletRequest request) {
    return "login";
  }


  @PostMapping(value = "/login")
  public String login(@ModelAttribute User user, HttpServletRequest request) {

    User validatedUser = authService.validateLogin(user);

    HttpSession oldSession = request.getSession(false);
    // invalidate old session if it exists
    if (oldSession != null) oldSession.invalidate();

    // generate new session
    HttpSession newSession = request.getSession(true);

    // store user details in session
    newSession.setAttribute("userId", validatedUser.getId());
    newSession.setAttribute("userRole", validatedUser.getRole());

    return "redirect:/home";
  }

  @GetMapping("/logout")
  public String logout(HttpServletRequest request) {
    HttpSession oldSession = request.getSession(false);
    if (oldSession != null) oldSession.invalidate();
    return "redirect:/login";
  }

  @GetMapping(value = "/register") //localhost:8080/user
  public String getRegisterView() {
    return "register";
  }

  @PostMapping(value = "/register")
  public String register(@ModelAttribute User body) {
    authService.validateRegistration(body);
    return "redirect:/login";
  }
}
