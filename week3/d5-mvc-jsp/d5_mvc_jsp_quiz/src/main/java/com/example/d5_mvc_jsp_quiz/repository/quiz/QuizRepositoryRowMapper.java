package com.example.d5_mvc_jsp_quiz.repository.quiz;

import com.example.d5_mvc_jsp_quiz.domain.Quiz;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class QuizRepositoryRowMapper implements RowMapper<Quiz> {
  @Override
  public Quiz mapRow(ResultSet rs, int rowNum) throws SQLException {
    Quiz quiz = new Quiz();
    quiz.setId(rs.getLong("quiz_id"));
    quiz.setCategory(rs.getString("category"));
    quiz.setQuestionList(null);
    return quiz;
  }
}
