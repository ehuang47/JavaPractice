package com.example.d5_mvc_jsp_quiz.repository.quizResult;

import com.example.d5_mvc_jsp_quiz.domain.QuizResult;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class QuizResultRepositoryRowMapper implements RowMapper<QuizResult> {
  @Override
  public QuizResult mapRow(java.sql.ResultSet resultSet, int i) throws java.sql.SQLException {
    return new QuizResult(
      resultSet.getLong("quiz_result_id"),
      resultSet.getLong("quiz_id"),
      resultSet.getString("date_started"),
      resultSet.getString("date_submitted"),
      null
    );
  }
}
