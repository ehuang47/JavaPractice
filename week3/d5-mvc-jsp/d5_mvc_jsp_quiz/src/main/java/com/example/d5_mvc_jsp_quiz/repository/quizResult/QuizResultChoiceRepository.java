package com.example.d5_mvc_jsp_quiz.repository.quizResult;

import com.example.d5_mvc_jsp_quiz.domain.QuizResultChoice;
import com.example.d5_mvc_jsp_quiz.repository.ObjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class QuizResultChoiceRepository implements ObjectRepository<QuizResultChoice> {
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
  public void save(QuizResultChoice quizResultChoice) {
    String query = """
      INSERT INTO week3_quiz_result_choice (quiz_result_id, question_id, choice_id) VALUES 
      (:quizResultId, :questionId, :choiceId )""";

    MapSqlParameterSource parameterSource = new MapSqlParameterSource()
      .addValue("quizResultId", quizResultChoice.getQuizResultId())
      .addValue("questionId", quizResultChoice.getQuestionId())
      .addValue("choiceId", quizResultChoice.getChoiceId());
    namedParameterJdbcTemplate.update(query, parameterSource);
  }

  @Override
  public Optional<QuizResultChoice> findById(Long id) {
    return Optional.empty();
  }

  @Override
  public List<QuizResultChoice> findAll() {
    return null;
  }
}
