package com.example.d5_mvc_jsp_quiz.service;

import com.example.d5_mvc_jsp_quiz.domain.Quiz;
import com.example.d5_mvc_jsp_quiz.repository.quiz.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuizService implements ObjectService<Quiz> {
  private final QuizRepository quizRepository;

  @Autowired
  public QuizService(QuizRepository quizRepository) {
    this.quizRepository = quizRepository;
  }

  @Override
  public void save(Quiz quiz) {
    quizRepository.save(quiz);
  }

  @Override
  public Quiz findById(Long id) {
    return quizRepository.findById(id);
  }

  @Override
  public List<Quiz> findAll() {
    return null;
  }
}
