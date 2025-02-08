package com.example.d5_mvc_jsp_quiz.service;

import com.example.d5_mvc_jsp_quiz.domain.User;
import com.example.d5_mvc_jsp_quiz.exception.EntityType;
import com.example.d5_mvc_jsp_quiz.exception.type.EntityNotFoundException;
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
  public Long save(User user) {
    return userRepository.save(user);
  }

  @Override
  public User findById(Long id) {
    return userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(EntityType.USER, id));
  }

  public User findByUsernameOrEmail(String username, String email) {
    return userRepository.findByUsernameOrEmail(username, email)
      .orElseThrow(() -> new EntityNotFoundException(EntityType.USER,
        username != null ? username : email));
  }

  public List<User> findAllByUsernameOrEmail(String username, String email) {
    return userRepository.findAllByUsernameOrEmail(username, email);
  }


  @Override
  public List<User> findAll() {
    return userRepository.findAll();
  }
}
