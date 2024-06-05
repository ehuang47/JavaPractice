package com.example.d5_mvc_jsp_quiz.service;

import com.example.d5_mvc_jsp_quiz.domain.QuizResult;
import com.example.d5_mvc_jsp_quiz.domain.QuizResultChoice;
import com.example.d5_mvc_jsp_quiz.repository.choice.ChoiceRepository;
import com.example.d5_mvc_jsp_quiz.repository.question.QuestionRepository;
import com.example.d5_mvc_jsp_quiz.repository.quiz.QuizRepository;
import com.example.d5_mvc_jsp_quiz.repository.quizResult.QuizResultChoiceRepository;
import com.example.d5_mvc_jsp_quiz.repository.quizResult.QuizResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class QuizResultService implements ObjectService<QuizResult> {
  private final QuizRepository quizRepository;
  private final QuestionRepository questionRepository;
  private final ChoiceRepository choiceRepository;
  private final QuizResultRepository quizResultRepository;
  private final QuizResultChoiceRepository quizResultChoiceRepository;

  @Autowired
  public QuizResultService(QuizRepository quizRepository,
                           QuestionRepository questionRepository,
                           ChoiceRepository choiceRepository,
                           QuizResultRepository quizResultRepository,
                           QuizResultChoiceRepository quizResultChoiceRepository) {
    this.quizRepository = quizRepository;
    this.questionRepository = questionRepository;
    this.choiceRepository = choiceRepository;
    this.quizResultRepository = quizResultRepository;
    this.quizResultChoiceRepository = quizResultChoiceRepository;
  }

  @Override
  public void save(QuizResult quizResult) {
    // how would @Valid help here?
    // expect a quiz, 10 quiz questions, 10 answers
    // fails if can't find any of these
    System.out.println(quizResult);
    String dateSubmitted = ZonedDateTime.now(ZoneOffset.UTC).format(DateTimeFormatter.ISO_INSTANT);
    System.out.println(dateSubmitted);
    quizResult.setDateSubmitted(dateSubmitted);
    quizResultRepository.save(quizResult);

    // somehow get the form to submit the properly matching payload, to loop and transform
    for (QuizResultChoice c : quizResult.getQuizResultChoiceList()) {
      QuizResultChoice choice = new QuizResultChoice();
      choice.setQuizResultId(quizResult.getId());
      choice.setQuestionId(1L);
      choice.setChoiceId(1L);
      quizResultChoiceRepository.save(choice);
    }
    // look for the list of questions according to this quiz
    // for each question, grab all the correct choices
    // compare each and return the final score, list of correct and not correct

  }

  @Override
  public QuizResult findById(Long id) {
    return null;
  }

  @Override
  public List<QuizResult> findAll() {
    return null;
  }
}
