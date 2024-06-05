package com.example.d5_mvc_jsp_quiz.service;

import com.example.d5_mvc_jsp_quiz.domain.Choice;
import com.example.d5_mvc_jsp_quiz.domain.Question;
import com.example.d5_mvc_jsp_quiz.domain.Quiz;
import com.example.d5_mvc_jsp_quiz.exception.EntityNotFoundException;
import com.example.d5_mvc_jsp_quiz.exception.EntityType;
import com.example.d5_mvc_jsp_quiz.exception.InvalidArgumentException;
import com.example.d5_mvc_jsp_quiz.exception.NoResultsException;
import com.example.d5_mvc_jsp_quiz.repository.choice.ChoiceRepository;
import com.example.d5_mvc_jsp_quiz.repository.question.QuestionRepository;
import com.example.d5_mvc_jsp_quiz.repository.quiz.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class QuizService implements ObjectService<Quiz> {
  private final QuizRepository quizRepository;
  private final QuestionRepository questionRepository;
  private final ChoiceRepository choiceRepository;

  @Autowired
  public QuizService(QuizRepository quizRepository,
                     QuestionRepository questionRepository,
                     ChoiceRepository choiceRepository) {
    this.quizRepository = quizRepository;
    this.questionRepository = questionRepository;
    this.choiceRepository = choiceRepository;
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

    List<Question> questionList = questionRepository.findAllByQuizId(quiz.getId());

    if (questionList.isEmpty()) {
      throw new NoResultsException(EntityType.QUESTION, "Quiz is not finalized.");
    } else if (questionList.size() < questionCount) {
      throw new InvalidArgumentException("Not enough questions exist for this quiz.");
    } else {
      Collections.shuffle(questionList);
      questionList = questionList.stream()
        .limit(questionCount)
        .collect(Collectors.toList());
    }

    List<Long> idList = questionList.stream().map(Question::getId).toList();
    List<Choice> choiceList = choiceRepository.findAllByQuestionList(idList);

    if (choiceList.isEmpty()) {
      throw new NoResultsException(EntityType.CHOICE, "Question is not finalized.");
    } else if (choiceList.size() != questionCount * 3) { // each Q has 3
      throw new InvalidArgumentException("Not enough choices exist for this question.");
    } else {
      // maintain choice list sorted by questionId
      Map<Long, List<Choice>> choicesByQuestion = new LinkedHashMap<>();
      for (Choice c : choiceList) {
        choicesByQuestion.computeIfAbsent(c.getQuestionId(), x -> new ArrayList<>()).add(c);
      }
      for (Question q : questionList) {
        q.setChoiceList(choicesByQuestion.get(q.getId()));
      }
    }

    quiz.setQuestionList(questionList);

    return quiz;
  }

  @Override
  public List<Quiz> findAll() {
    return quizRepository.findAll();
  }
}
