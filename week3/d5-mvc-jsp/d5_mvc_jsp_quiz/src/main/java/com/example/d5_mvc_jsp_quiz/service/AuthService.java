package com.example.d5_mvc_jsp_quiz.service;

import com.example.d5_mvc_jsp_quiz.domain.User;
import com.example.d5_mvc_jsp_quiz.exception.type.InvalidArgumentException;
import com.example.d5_mvc_jsp_quiz.exception.type.InvalidCredentialsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthService {
  private final UserService userService;

  @Autowired
  public AuthService(UserService userService) {
    this.userService = userService;
  }

  public User validateLogin(User user){
    if(user.getUsername() == null) {
      throw new InvalidArgumentException("Username is required.");
    } else if (user.getPassword() == null){
      throw new InvalidArgumentException("Password is required.");
    }

    Optional<User> validatedUser = userService.findByUsernameOrEmail(user.getUsername(), null);
    if (validatedUser.isEmpty()) {
      throw new InvalidCredentialsException("Invalid username or password.");
    } else {
      return validatedUser.get();
    }
  }

  public void validateRegistration(User user) {
    List<User> usersByUsername = userService.findAllByUsernameOrEmail(user.getUsername(), null);
    if (!usersByUsername.isEmpty()) {
      throw new InvalidArgumentException("Username already exists.");
    }
    List<User> usersByEmail = userService.findAllByUsernameOrEmail(null, user.getEmail());
    if (!usersByEmail.isEmpty()) {
      throw new InvalidArgumentException("Email already exists.");
    }
    userService.save(user);
  }
}
