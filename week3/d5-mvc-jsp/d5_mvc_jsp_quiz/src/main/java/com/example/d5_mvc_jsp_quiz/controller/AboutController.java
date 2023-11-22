package com.example.d5_mvc_jsp_quiz.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

//q: does it make sense to create a controller for each page? or should we base controllers off of functionality? or maybe database tables
//a: it makes sense to create a controller for each page
//q: a web app can have tens or hundreds of pages, im not sure you'd create a controller for each?
//a: you would create a controller for each page
//q: what if you have a page that displays a list of users, and you want to be able to click on a user and see their details?


@Controller
public class AboutController {
    @GetMapping(value="/about") //localhost:8080/about
    public String aboutModelView(Model model) {
        model.addAttribute("interests", "some value for interests");
        model.addAttribute("title", "About Me");
        return "about";
    }


}
