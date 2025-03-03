package com.example.d5_mvc_jsp_quiz.repository.quizResult;

import com.example.d5_mvc_jsp_quiz.domain.QuizResultSummary;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class QuizResultSummaryRowMapper implements RowMapper<QuizResultSummary> {

  @Override
  public QuizResultSummary mapRow(java.sql.ResultSet resultSet, int i) throws java.sql.SQLException {
    QuizResultSummary quizResultSummary = new QuizResultSummary();
    quizResultSummary.setId(resultSet.getLong("quiz_result_id"));
    quizResultSummary.setQuizId(resultSet.getLong("quiz_id"));
    quizResultSummary.setDateStarted(resultSet.getString("date_started"));
    quizResultSummary.setDateSubmitted(resultSet.getString("date_submitted"));
    quizResultSummary.setUserId(resultSet.getLong("user_id"));
    quizResultSummary.setFirstName(resultSet.getString("first_name"));
    quizResultSummary.setLastName(resultSet.getString("last_name"));
    quizResultSummary.setCategory(resultSet.getString("category"));

    return quizResultSummary;
  }
}
