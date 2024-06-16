package com.example.d5_mvc_jsp_quiz.service;

import com.example.d5_mvc_jsp_quiz.domain.Question;
import com.example.d5_mvc_jsp_quiz.domain.Quiz;
import com.example.d5_mvc_jsp_quiz.exception.EntityNotFoundException;
import com.example.d5_mvc_jsp_quiz.exception.EntityType;
import com.example.d5_mvc_jsp_quiz.repository.quiz.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuizService implements ObjectService<Quiz> {
  private final QuizRepository quizRepository;
  private final QuestionService questionService;
  private final ChoiceService choiceService;

  @Autowired
  public QuizService(QuizRepository quizRepository,
                     QuestionService questionService,
                     ChoiceService choiceService) {
    this.quizRepository = quizRepository;
    this.questionService = questionService;
    this.choiceService = choiceService;
  }

  @Override
  public Long save(Quiz quiz) {
    return quizRepository.save(quiz);
  }

  @Override
  public Quiz findById(Long id) {
    return quizRepository.findById(id)
      .orElseThrow(() -> new EntityNotFoundException(EntityType.QUIZ, id));
  }

  public Quiz findByIdWithLimitQuestionsAndChoices(Long id, int questionCount) {
    Quiz quiz = quizRepository.findById(id)
      .orElseThrow(() -> new EntityNotFoundException(EntityType.QUIZ, id));

    List<Question> questionList = questionService.findAllQuestionsByQuizIdWithLimit(quiz.getId(), questionCount);
    questionList = questionService.populateQuestionListChoices(questionList);

    quiz.setQuestionList(questionList);

    return quiz;
  }

  @Override
  public List<Quiz> findAll() {
    return quizRepository.findAll();
  }
}
