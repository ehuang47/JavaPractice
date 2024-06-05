package com.example.d5_mvc_jsp_quiz.repository.quizResult;

import com.example.d5_mvc_jsp_quiz.domain.QuizResultChoice;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class QuizResultChoiceRepositoryRowMapper implements RowMapper<QuizResultChoice> {
  @Override
  public QuizResultChoice mapRow(ResultSet rs, int rowNum) throws SQLException {
    return new QuizResultChoice(
      rs.getLong("id"),
      rs.getLong("quiz_result_id"),
      rs.getLong("question_id"),
      rs.getLong("choice_id")
    );
  }
}
