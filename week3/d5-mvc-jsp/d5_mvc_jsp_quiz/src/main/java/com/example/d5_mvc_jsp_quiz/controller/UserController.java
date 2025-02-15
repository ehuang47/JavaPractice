package com.example.d5_mvc_jsp_quiz.controller;

import com.example.d5_mvc_jsp_quiz.domain.User;
import com.example.d5_mvc_jsp_quiz.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController extends AbstractController {
  private final UserService userService;

  @Autowired
  public UserController(UserService userService) {
    this.userService = userService;
  }
  @GetMapping("/management") //localhost:8080/user-management
  public String getUserView(Model model) {
    List<User> users = userService.findAll();
    model.addAttribute("users", users);
    return "admin/user-management";
  }

  @PatchMapping("/management")
  @ResponseBody
  public ResponseEntity<String> updateActiveStatus(@RequestBody User body) {
    userService.setActive(body);
    return ResponseEntity.ok("{\"reload\": true}");
  }
}
