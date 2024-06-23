package com.example.d5_mvc_jsp_quiz.service;

import com.example.d5_mvc_jsp_quiz.domain.QuizResult;
import com.example.d5_mvc_jsp_quiz.domain.QuizResultChoice;
import com.example.d5_mvc_jsp_quiz.exception.EntityNotFoundException;
import com.example.d5_mvc_jsp_quiz.exception.EntityType;
import com.example.d5_mvc_jsp_quiz.exception.InvalidArgumentException;
import com.example.d5_mvc_jsp_quiz.repository.quizResult.QuizResultChoiceRepository;
import com.example.d5_mvc_jsp_quiz.repository.quizResult.QuizResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class QuizResultService implements ObjectService<QuizResult> {
  private final QuizResultRepository quizResultRepository;
  private final QuizResultChoiceRepository quizResultChoiceRepository;
  private final String DATE_STARTED = "dateStarted";
  private final String QUIZ_ID = "quizId";

  @Autowired
  public QuizResultService(QuizResultRepository quizResultRepository,
                           QuizResultChoiceRepository quizResultChoiceRepository) {
    this.quizResultRepository = quizResultRepository;
    this.quizResultChoiceRepository = quizResultChoiceRepository;
  }

  @Override
  public Long save(QuizResult quizResult) {
    // how would @Valid help here?
    Long quizResultId = quizResultRepository.save(quizResult);
//    System.out.println(quizResultId);
    List<Long> quizResultChoiceIdList = quizResultChoiceRepository
      .batchSave(quizResultId, quizResult.getQuizResultChoiceList());
//    System.out.println(quizResultChoiceIdList);
    return quizResultId;
  }

  public QuizResult bodyMapper(Map<String, String> body) {
    if (body.size() != 12) {
      throw new InvalidArgumentException("Missing required answers for all choices.");
    }

    QuizResult submission = new QuizResult();
    submission.setQuizId(Long.valueOf(body.get(QUIZ_ID)));
    submission.setDateStarted(body.get(DATE_STARTED));
    String dateSubmitted = ZonedDateTime.now(ZoneOffset.UTC).format(DateTimeFormatter.ISO_INSTANT);
    submission.setDateSubmitted(dateSubmitted);

    List<QuizResultChoice> choiceList = new ArrayList<QuizResultChoice>();
    for (Map.Entry<String, String> entry : body.entrySet()) {
      String k = entry.getKey();
      if (k.equals(QUIZ_ID) || k.equals(DATE_STARTED)) {
        continue;
      }
      Long v = Long.valueOf(entry.getValue());
      QuizResultChoice resultChoice = new QuizResultChoice();
      String[] keyParts = k.split("-");
      Long questionId = Long.valueOf(keyParts[keyParts.length - 1]);
      resultChoice.setChoiceId(v);
      resultChoice.setQuestionId(questionId);
      choiceList.add(resultChoice);
    }
    submission.setQuizResultChoiceList(choiceList);

    return submission;
  }

  @Override
  public QuizResult findById(Long id) {
    return quizResultRepository.findById(id)
      .orElseThrow(() -> new EntityNotFoundException(EntityType.QUIZ_RESULT, id));
  }

  @Override
  public List<QuizResult> findAll() {
    return quizResultRepository.findAll();
  }
}
