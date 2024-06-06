package com.example.d5_mvc_jsp_quiz.service;

import com.example.d5_mvc_jsp_quiz.domain.Question;
import com.example.d5_mvc_jsp_quiz.repository.question.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService implements ObjectService<Question> {
  private final QuestionRepository questionRepository;

  @Autowired
  public QuestionService(QuestionRepository questionRepository) {
    this.questionRepository = questionRepository;
  }

  @Override
  public Long save(Question question) {
    return null;
  }

  @Override
  public Question findById(Long id) {
    return null;
  }

  public List<Question> findAllByQuestionList(List<Long> questionIdList) {
    return questionRepository.findAllByQuestionList(questionIdList);
  }

  @Override
  public List<Question> findAll() {
    return null;
  }
}
