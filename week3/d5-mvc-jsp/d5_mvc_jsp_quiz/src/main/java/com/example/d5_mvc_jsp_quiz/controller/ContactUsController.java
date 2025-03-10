package com.example.d5_mvc_jsp_quiz.controller;

import com.example.d5_mvc_jsp_quiz.domain.Message;
import com.example.d5_mvc_jsp_quiz.service.MessageService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/contact-us")
public class ContactUsController extends AbstractController  {
  private final MessageService messageService;
  @Autowired
  public ContactUsController(MessageService messageService){
    this.messageService = messageService;
  }

  @GetMapping //localhost:8080/contact-us
  public String contactUsModelView(Model model, HttpServletRequest request) {
    HttpSession session = request.getSession(false);
    boolean isLoggedIn = session != null && session.getAttribute("userId") != null;
    if (isLoggedIn) {
      int userRole = (int) session.getAttribute("userRole");
      model.addAttribute("userRole", userRole);
    }
    return "contact-us";
  }

  @GetMapping(value="/management")
  public String adminViewAllContactSubmissions(Model model){
    List<Message> messageList = messageService.findAll();
    model.addAttribute("messageList", messageList);
    return "admin/contact-us";
  }

  @PostMapping
  public String submitContactUs(@ModelAttribute Message contactMessage) {
    Message submittedMessage = messageService.bodyMapper(contactMessage);
    messageService.save(submittedMessage);
    return "redirect:/home";
  }
}
