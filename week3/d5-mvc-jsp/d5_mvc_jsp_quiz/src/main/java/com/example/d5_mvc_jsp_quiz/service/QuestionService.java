package com.example.d5_mvc_jsp_quiz.service;

import com.example.d5_mvc_jsp_quiz.domain.Choice;
import com.example.d5_mvc_jsp_quiz.domain.Question;
import com.example.d5_mvc_jsp_quiz.exception.EntityType;
import com.example.d5_mvc_jsp_quiz.exception.type.InvalidArgumentException;
import com.example.d5_mvc_jsp_quiz.exception.type.NoResultsException;
import com.example.d5_mvc_jsp_quiz.repository.question.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class QuestionService implements ObjectService<Question> {
  private final QuestionRepository questionRepository;
  private final ChoiceService choiceService;

  @Autowired
  public QuestionService(QuestionRepository questionRepository,
                         ChoiceService choiceService) {
    this.questionRepository = questionRepository;
    this.choiceService = choiceService;
  }

  @Override
  public Long save(Question question) {
    return null;
  }

  @Override
  public Question findById(Long id) {
    return null;
  }

  public List<Question> findAllByQuestionIdListWithChoices(List<Long> questionIdList) {
    return questionRepository.findAllByQuestionIdList(questionIdList);
  }

  public List<Question> populateQuestionListChoices(List<Question> questionList) {
    Map<Long, List<Choice>> choicesByQuestion = choiceService.findAllByQuestionList(questionList, questionList.size());

    for (Question q : questionList) {
      q.setChoiceList(choicesByQuestion.get(q.getId()));
    }

    return questionList;
  }

  public List<Question> findAllQuestionsByQuizIdWithLimit(Long quizId, int limit) {
    List<Question> questionList = questionRepository.findAllByQuizId(quizId);
    if (questionList.isEmpty()) {
      throw new NoResultsException(EntityType.QUESTION, "Quiz is not finalized.");
    } else if (questionList.size() < limit) {
      throw new InvalidArgumentException("Not enough questions exist for this quiz.");
    } else {
      Collections.shuffle(questionList);
      questionList = questionList.stream()
        .limit(limit)
        .collect(Collectors.toList());
    }

    return questionList;
  }

  @Override
  public List<Question> findAll() {
    return null;
  }
}
