package com.example.d5_mvc_jsp_quiz.repository.quizResult;

import com.example.d5_mvc_jsp_quiz.domain.QuizResult;
import com.example.d5_mvc_jsp_quiz.repository.ObjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class QuizResultRepository implements ObjectRepository<QuizResult> {
  private JdbcTemplate jdbcTemplate;
  private QuizResultRepositoryRowMapper rowMapper;
  private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

  @Autowired
  public QuizResultRepository(JdbcTemplate jdbcTemplate,
                              QuizResultRepositoryRowMapper rowMapper,
                              NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
    this.rowMapper = rowMapper;
    this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
  }

  @Override
  public Long save(QuizResult quizResult) {
    String query = """
      INSERT INTO week3_quiz_result (quiz_id, date_started, date_submitted) VALUES 
      (:quizId, :dateStarted, :dateSubmitted )""";

    MapSqlParameterSource parameterSource = new MapSqlParameterSource()
      .addValue("quizId", quizResult.getQuizId())
      .addValue("dateStarted", quizResult.getDateStarted())
      .addValue("dateSubmitted", quizResult.getDateSubmitted());

    KeyHolder keyHolder = new GeneratedKeyHolder();
    namedParameterJdbcTemplate.update(query, parameterSource, keyHolder, new String[]{"quiz_result_id"});
    return keyHolder.getKey().longValue();
  }

  @Override
  public Optional<QuizResult> findById(Long id) {
    String query = "SELECT * FROM week3_quiz_result WHERE quiz_result_id = :quizResultId";
    MapSqlParameterSource parameterSource = new MapSqlParameterSource()
      .addValue("quizResultId", id);
    QuizResult quizResult = namedParameterJdbcTemplate.queryForObject(query, parameterSource, rowMapper);
    return Optional.ofNullable(quizResult);
  }

  @Override
  public List<QuizResult> findAll() {
    return null;
  }
}
