package com.example.d5_mvc_jsp_quiz.repository.quiz;

import com.example.d5_mvc_jsp_quiz.domain.Quiz;
import com.example.d5_mvc_jsp_quiz.repository.ObjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class QuizRepository implements ObjectRepository<Quiz> {
  private JdbcTemplate jdbcTemplate;
  private QuizRepositoryRowMapper rowMapper;
  private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

  @Autowired
  public QuizRepository(JdbcTemplate jdbcTemplate, QuizRepositoryRowMapper rowMapper, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
    this.rowMapper = rowMapper;
    this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
  }

  @Override
  public Long save(Quiz quiz) {
    return 1L;
  }

  @Override
  public Optional<Quiz> findById(Long id) {
//    String query = "SELECT * FROM week3_quiz WHERE quiz_id = ?";
//    Quiz quiz = jdbcTemplate.queryForObject(query, rowMapper, id);
    String query = "SELECT * FROM week3_quiz WHERE quiz_id = :quizId";
    MapSqlParameterSource parameterSource = new MapSqlParameterSource();
    parameterSource.addValue("quizId", id);
    Quiz quiz = namedParameterJdbcTemplate.queryForObject(query, parameterSource, rowMapper);
    return Optional.ofNullable(quiz);
  }

  @Override
  public List<Quiz> findAll() {
    String query = "SELECT * FROM week3_quiz";
    return jdbcTemplate.query(query, rowMapper);
  }
}
