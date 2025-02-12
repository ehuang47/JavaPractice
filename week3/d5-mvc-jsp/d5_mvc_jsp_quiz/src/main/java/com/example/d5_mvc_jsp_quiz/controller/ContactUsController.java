package com.example.d5_mvc_jsp_quiz.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/contact-us")
public class ContactUsController extends AbstractController  {
  @GetMapping(value = "") //localhost:8080/contact-us
  public String aboutModelView(Model model, HttpServletRequest request) {
    HttpSession session = request.getSession(false);
    
    boolean isLoggedIn = session != null && session.getAttribute("userId") != null;
    
    model.addAttribute("showHome", isLoggedIn);
    model.addAttribute("showLogout", isLoggedIn);
    model.addAttribute("showRegister", !isLoggedIn);
    model.addAttribute("showLogin", !isLoggedIn);

    return "contact-us";
  }
}
