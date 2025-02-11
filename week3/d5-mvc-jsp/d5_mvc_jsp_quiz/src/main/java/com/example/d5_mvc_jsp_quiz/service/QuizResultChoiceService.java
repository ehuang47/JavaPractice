package com.example.d5_mvc_jsp_quiz.service;

import com.example.d5_mvc_jsp_quiz.domain.QuizResultChoice;
import com.example.d5_mvc_jsp_quiz.repository.quizResult.QuizResultChoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuizResultChoiceService extends EntityService<QuizResultChoice, Long> {
  private final QuizResultChoiceRepository quizResultChoiceRepository;

  @Autowired
  public QuizResultChoiceService(QuizResultChoiceRepository quizResultChoiceRepository) {
    super(quizResultChoiceRepository);
    this.quizResultChoiceRepository = quizResultChoiceRepository;
  }

  public List<QuizResultChoice> findAllByQuizResultId(Long id) {
    return quizResultChoiceRepository.findAllByQuizResultId(id);
  }

}
