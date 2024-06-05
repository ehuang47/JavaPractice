package com.example.d5_mvc_jsp_quiz.repository.quizResult;

import com.example.d5_mvc_jsp_quiz.domain.QuizResult;
import com.example.d5_mvc_jsp_quiz.repository.ObjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
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
  public void save(QuizResult quizResult) {
    String query = """
      INSERT INTO week3_quiz_result (quiz_id, date_started, date_submitted) VALUES 
      (:quizId, :dateStarted, :dateSubmitted )""";

    MapSqlParameterSource parameterSource = new MapSqlParameterSource()
      .addValue("quizId", quizResult.getQuizId())
      .addValue("dateStarted", quizResult.getDateStarted())
      .addValue("dateSubmitted", quizResult.getDateSubmitted());
    namedParameterJdbcTemplate.update(query, parameterSource);
  }

  @Override
  public Optional<QuizResult> findById(Long id) {
    return Optional.empty();
  }

  @Override
  public List<QuizResult> findAll() {
    return null;
  }
}
