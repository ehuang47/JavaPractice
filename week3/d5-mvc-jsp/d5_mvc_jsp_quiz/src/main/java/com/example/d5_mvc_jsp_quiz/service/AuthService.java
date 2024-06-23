package com.example.d5_mvc_jsp_quiz.service;

import com.example.d5_mvc_jsp_quiz.domain.User;
import com.example.d5_mvc_jsp_quiz.exception.EntityNotFoundException;
import com.example.d5_mvc_jsp_quiz.exception.InvalidArgumentException;
import com.example.d5_mvc_jsp_quiz.exception.InvalidCredentialsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthService {
  private final UserService userService;

  @Autowired
  public AuthService(UserService userService) {
    this.userService = userService;
  }

  public User validateLogin(String username, String password) {
    try {
      User user = userService.findByUsernameOrEmail(username, null);
      if (!user.getPassword().equals(password)) {
        throw new InvalidCredentialsException("Invalid username or password.");
      }
      return user;
    } catch (EntityNotFoundException e) {
      throw new InvalidCredentialsException("Invalid username or password.");
    }
  }

  public void validateRegistration(String email, String username,
                                   String password, String firstName,
                                   String lastName) {
    List<User> usersByUsername = userService.findAllByUsernameOrEmail(username, null);
    if (!usersByUsername.isEmpty()) {
      throw new InvalidArgumentException("Username already exists.");
    }
    List<User> usersByEmail = userService.findAllByUsernameOrEmail(null, email);
    if (!usersByEmail.isEmpty()) {
      throw new InvalidArgumentException("Email already exists.");
    }
    User user = new User();
    user.setFirstName(firstName);
    user.setLastName(lastName);
    user.setEmail(email);
    user.setUsername(username);
    user.setPassword(password);
    userService.save(user);
  }
}
