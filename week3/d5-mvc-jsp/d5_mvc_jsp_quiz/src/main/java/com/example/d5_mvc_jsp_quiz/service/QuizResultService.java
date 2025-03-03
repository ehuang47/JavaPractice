package com.example.d5_mvc_jsp_quiz.service;

import com.example.d5_mvc_jsp_quiz.domain.QuizResult;
import com.example.d5_mvc_jsp_quiz.domain.QuizResultChoice;
import com.example.d5_mvc_jsp_quiz.domain.QuizResultSummary;
import com.example.d5_mvc_jsp_quiz.exception.type.InvalidArgumentException;
import com.example.d5_mvc_jsp_quiz.repository.quizResult.QuizResultChoiceRepository;
import com.example.d5_mvc_jsp_quiz.repository.quizResult.QuizResultRepository;
import com.example.d5_mvc_jsp_quiz.utils.TimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class QuizResultService extends EntityService<QuizResult, Long> {
  private final QuizResultRepository quizResultRepository;
  private final QuizResultChoiceRepository quizResultChoiceRepository;

  @Autowired
  public QuizResultService(QuizResultRepository quizResultRepository,
                           QuizResultChoiceRepository quizResultChoiceRepository) {
    super(quizResultRepository);
    this.quizResultRepository = quizResultRepository;
    this.quizResultChoiceRepository = quizResultChoiceRepository;
  }

  @Override
  public Long save(QuizResult quizResult) {
    Long quizResultId = super.save(quizResult);
    quizResultChoiceRepository.batchSave(quizResultId, quizResult.getQuizResultChoiceList());
    return quizResultId;
  }

  public QuizResult bodyMapper(Map<String, String> body, Long userId) {
    if (body.size() != 12) {
      throw new InvalidArgumentException("Missing required answers for all choices.");
    }

    QuizResult submission = new QuizResult();
    submission.setUserId(userId);
    String QUIZ_ID = "quizId";
    submission.setQuizId(Long.valueOf(body.get(QUIZ_ID)));
    String DATE_STARTED = "dateStarted";
    submission.setDateStarted(body.get(DATE_STARTED));
    String dateSubmitted = TimeUtils.getCurrentTimestamp();
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

  public List<QuizResult> findAllByUserId(Long userId) {
    return quizResultRepository.findAllByUserId(userId);
  }

public List<QuizResultSummary> findAllForManagement(String columnToOrder,
                                                    String ascending,
                                                    String columnToFilter,
                                                    String filterValue) {

    if (columnToOrder == null) {
      columnToOrder = "date_submitted";
//      throw new InvalidArgumentException("Column to order is required.");
    }
    if (ascending == null) {
      ascending = "ASC";
//      throw new InvalidArgumentException("Sort order is required.");
    } else if (!ascending.equals("ASC") && !ascending.equals("DESC")) {
      throw new InvalidArgumentException("Sort must be ASC or DESC.");
    }

    final List<String> validColumnNamesSort = List.of("date_submitted", "name");
    if (!validColumnNamesSort.contains(columnToOrder)) {
      throw new InvalidArgumentException();
    }

    final List<String> validColumnNamesFilter = List.of("category", "user_id");
    if (columnToFilter != null && !validColumnNamesFilter.contains(columnToFilter)) {
      throw new InvalidArgumentException();
    }

    final boolean isAscending = ascending.equals("ASC");
    List<QuizResultSummary>  quizResultSummaries = quizResultRepository.findAllForManagement(columnToOrder, isAscending, columnToFilter, filterValue);
    Map<Long, Integer> scoreMap = quizResultRepository.findAllScores();
    quizResultSummaries.forEach(quizResultSummary ->
      quizResultSummary.setScore(scoreMap.get(quizResultSummary.getId()))
    );
    return quizResultSummaries;
  }
}
