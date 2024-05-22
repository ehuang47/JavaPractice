package com.example.d5_mvc_jsp_quiz.service;

import com.example.d5_mvc_jsp_quiz.domain.User;
import com.example.d5_mvc_jsp_quiz.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements ObjectService<User> {
  private final UserRepository userRepository;

  @Autowired
  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public void save(User user) {
    userRepository.save(user);
  }

  @Override
  public User findById(int id) {
    return userRepository.findById(id);
  }

  @Override
  public List<User> findAll() {
    return userRepository.findAll();
  }
}
