package com.example.d5_mvc_jsp_quiz.service;

import com.example.d5_mvc_jsp_quiz.domain.Choice;
import com.example.d5_mvc_jsp_quiz.domain.Question;
import com.example.d5_mvc_jsp_quiz.exception.EntityType;
import com.example.d5_mvc_jsp_quiz.exception.type.InvalidArgumentException;
import com.example.d5_mvc_jsp_quiz.exception.type.NoResultsException;
import com.example.d5_mvc_jsp_quiz.repository.choice.ChoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class ChoiceService extends EntityService<Choice, Long> {
  private final ChoiceRepository choiceRepository;

  @Autowired
  public ChoiceService(ChoiceRepository choiceRepository) {
    super(choiceRepository);
    this.choiceRepository = choiceRepository;
  }
  public Map<Long, List<Choice>> findAllByQuestionList(List<Question> questionList, int questionCount) {
    List<Long> idList = questionList.stream().map(Question::getId).toList();
    List<Choice> choiceList = choiceRepository.findAllByQuestionList(idList);
    /*
    Object questionIdsObj = filters.get("questionIdList");
    if (questionIdsObj instanceof List<?> questionIdsList) {
      if (!questionIdsList.isEmpty() && questionIdsList.get(0) instanceof Long) {
        @SuppressWarnings("unchecked")
        List<Long> questionIds = (List<Long>) questionIdsList;
      }
    }

    public List<User> findAll(Map<String, Object> filters) {
    StringBuilder query = new StringBuilder("SELECT * FROM users WHERE 1=1");
    MapSqlParameterSource parameters = new MapSqlParameterSource();

    filters.forEach((key, value) -> {
        query.append(" AND ").append(key).append(" = :").append(key);
        parameters.addValue(key, value);
    });

    return namedParameterJdbcTemplate.query(query.toString(), parameters, userRowMapper);
}
     */

    if (choiceList.isEmpty()) {
      throw new NoResultsException(EntityType.CHOICE, "Question is not finalized.");
    } else if (choiceList.size() != questionCount * 3) { // each Q has 3
      throw new InvalidArgumentException("Not enough choices exist for this question.");
    }

    Map<Long, List<Choice>> choicesByQuestion = new LinkedHashMap<>();
    for (Choice c : choiceList) {
      choicesByQuestion.computeIfAbsent(c.getQuestionId(), x -> new ArrayList<>()).add(c);
    }

    return choicesByQuestion;
  }
}
