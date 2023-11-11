package com.example.d5_mvc_jsp_quiz.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AboutController {
    @GetMapping(value="/about") //localhost:8080/about
    public String aboutModelView(Model model) {
        model.addAttribute("interests", "some value for interests");
        model.addAttribute("title", "About Me");
        return "about";
    }
}
