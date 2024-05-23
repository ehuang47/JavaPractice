package com.example.d5_mvc_jsp_quiz.repository.question;

import com.example.d5_mvc_jsp_quiz.domain.Question;
import com.example.d5_mvc_jsp_quiz.repository.ObjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class QuestionRepository implements ObjectRepository<Question> {
  private JdbcTemplate jdbcTemplate;
  private QuestionRepositoryRowMapper rowMapper;
  private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

  @Autowired
  public QuestionRepository(JdbcTemplate jdbcTemplate,
                            QuestionRepositoryRowMapper rowMapper,
                            NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
    this.rowMapper = rowMapper;
    this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
  }

  @Override
  public void save(Question question) {
  }

  @Override
  public Optional<Question> findById(Long id) {
    String query = "SELECT * FROM week3_question WHERE question_id = :questionId";
    MapSqlParameterSource parameterSource = new MapSqlParameterSource();
    parameterSource.addValue("questionId", id);
    Question question = namedParameterJdbcTemplate.queryForObject(query, parameterSource, rowMapper);
    return Optional.ofNullable(question);
  }

  public List<Question> findAllByQuiz(Long quizId) {
    String query = "SELECT * FROM week3_question WHERE quiz_id = :quizId";
    MapSqlParameterSource parameterSource = new MapSqlParameterSource();
    parameterSource.addValue("quizId", quizId);
    return namedParameterJdbcTemplate.query(query, parameterSource, rowMapper);
  }

  @Override
  public List<Question> findAll() {
    return null;
  }
}
