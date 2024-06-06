package com.example.d5_mvc_jsp_quiz.service;

import com.example.d5_mvc_jsp_quiz.domain.QuizResultChoice;
import com.example.d5_mvc_jsp_quiz.repository.quizResult.QuizResultChoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuizResultChoiceService implements ObjectService<QuizResultChoice> {
  private final QuizResultChoiceRepository quizResultChoiceRepository;

  @Autowired
  public QuizResultChoiceService(QuizResultChoiceRepository quizResultChoiceRepository) {
    this.quizResultChoiceRepository = quizResultChoiceRepository;
  }

  @Override
  public Long save(QuizResultChoice quizResultChoice) {
    return null;
  }

  @Override
  public QuizResultChoice findById(Long id) {
    return null;
  }

  @Override
  public List<QuizResultChoice> findAll() {
    return null;
  }

  public List<QuizResultChoice> findAllByQuizResultId(Long id) {
    return quizResultChoiceRepository.findAllByQuizResultId(id);
  }

}
