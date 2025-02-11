package com.example.d5_mvc_jsp_quiz.service;

import com.example.d5_mvc_jsp_quiz.domain.Question;
import com.example.d5_mvc_jsp_quiz.domain.Quiz;
import com.example.d5_mvc_jsp_quiz.exception.EntityType;
import com.example.d5_mvc_jsp_quiz.exception.type.EntityNotFoundException;
import com.example.d5_mvc_jsp_quiz.repository.quiz.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuizService extends EntityService<Quiz,Long> {
  private final QuizRepository quizRepository;
  private final QuestionService questionService;

  @Autowired
  public QuizService(QuizRepository quizRepository,
                     QuestionService questionService) {
    super(quizRepository);
    this.quizRepository = quizRepository;
    this.questionService = questionService;
  }


  public Quiz findByIdWithLimitQuestionsAndChoices(Long id, int questionCount) {
    Quiz quiz = quizRepository.findById(id)
      .orElseThrow(() -> new EntityNotFoundException(EntityType.QUIZ, id));

    List<Question> questionList = questionService.findAllQuestionsByQuizIdWithLimit(quiz.getId(), questionCount);
    questionList = questionService.populateQuestionListChoices(questionList);

    quiz.setQuestionList(questionList);

    return quiz;
  }
}
