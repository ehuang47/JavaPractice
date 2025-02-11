package com.example.d5_mvc_jsp_quiz.repository.quizResult;

import com.example.d5_mvc_jsp_quiz.domain.QuizResultChoice;
import com.example.d5_mvc_jsp_quiz.repository.EntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class QuizResultChoiceRepository implements EntityRepository<QuizResultChoice, Long> {
  private JdbcTemplate jdbcTemplate;
  private QuizResultChoiceRepositoryRowMapper rowMapper;
  private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

  @Autowired
  public QuizResultChoiceRepository(JdbcTemplate jdbcTemplate,
                                    QuizResultChoiceRepositoryRowMapper rowMapper,
                                    NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
    this.rowMapper = rowMapper;
    this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
  }

  @Override
  public Long save(QuizResultChoice quizResultChoice) {
    String query = """
      INSERT INTO week3_quiz_result_choice (quiz_result_id, question_id, choice_id) VALUES 
      (:quizResultId, :questionId, :choiceId )""";

    MapSqlParameterSource parameterSource = new MapSqlParameterSource()
      .addValue("quizResultId", quizResultChoice.getQuizResultId())
      .addValue("questionId", quizResultChoice.getQuestionId())
      .addValue("choiceId", quizResultChoice.getChoiceId());

    KeyHolder keyHolder = new GeneratedKeyHolder();
    namedParameterJdbcTemplate.update(query, parameterSource, keyHolder, new String[]{"quiz_result_choice_id"});
    return keyHolder.getKey().longValue();
  }

  public List<Long> batchSave(Long quizResultId, List<QuizResultChoice> quizResultChoiceList) {
    StringBuilder queryBuilder = new StringBuilder();
    queryBuilder.append("INSERT INTO week3_quiz_result_choice (quiz_result_id, question_id, choice_id) VALUES ");
    MapSqlParameterSource parameterSource = new MapSqlParameterSource()
      .addValue("quizResultId", quizResultId);

    for (QuizResultChoice c : quizResultChoiceList) {
      String questionId = String.format("questionId%s", c.getQuestionId());
      String choiceId = String.format("choiceId%s", c.getChoiceId());
      queryBuilder.append("(:quizResultId, :")
        .append(questionId)
        .append(",:")
        .append(choiceId).append("),");
      parameterSource.addValue(questionId, c.getQuestionId())
        .addValue(choiceId, c.getChoiceId());
    }

    String builderResult = queryBuilder.toString();
    String query = builderResult.substring(0, builderResult.length() - 1);

    KeyHolder keyHolder = new GeneratedKeyHolder();
    namedParameterJdbcTemplate.update(query, parameterSource, keyHolder, new String[]{"quiz_result_choice_id"});
    return keyHolder.getKeyList().stream()
      .map(map -> ((BigInteger) map.get("GENERATED_KEY")).longValue())
      .collect(Collectors.toList());
  }

  public List<QuizResultChoice> findAllByQuizResultId(Long quizResultId) {
    String query = "SELECT * FROM week3_quiz_result_choice WHERE quiz_result_id = :quizResultId";
    MapSqlParameterSource parameterSource = new MapSqlParameterSource()
      .addValue("quizResultId", quizResultId);
    return namedParameterJdbcTemplate.query(query, parameterSource, rowMapper);
  }
}
