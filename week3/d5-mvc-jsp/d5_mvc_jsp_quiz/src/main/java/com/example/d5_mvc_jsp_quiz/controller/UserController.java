package com.example.d5_mvc_jsp_quiz.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    @GetMapping(value="/user") //localhost:8080/user
    public String userModelView() {
        return "user";
    }
}
