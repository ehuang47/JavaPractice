package com.example.d5_mvc_jsp_quiz.service;

import com.example.d5_mvc_jsp_quiz.domain.User;
import com.example.d5_mvc_jsp_quiz.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService extends EntityService<User, Long> {
  private final UserRepository userRepository;

  @Autowired
  public UserService(UserRepository userRepository) {
    super(userRepository);
    this.userRepository = userRepository;
  }

  public Optional<User> findByUsernameOrEmail(String username, String email) {
    return userRepository.findByUsernameOrEmail(username, email);
  }

  public List<User> findAllByUsernameOrEmail(String username, String email) {
    return userRepository.findAllByUsernameOrEmail(username, email);
  }

  public void setActive(User user) {
    userRepository.setActive(user);
  }

  public List<User> findAllByRole(int role){
    return userRepository.findAllByRole(role);
  }
}
