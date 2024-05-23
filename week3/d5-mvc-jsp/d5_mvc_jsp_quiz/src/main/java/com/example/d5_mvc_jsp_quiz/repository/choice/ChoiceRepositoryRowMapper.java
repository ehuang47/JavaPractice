package com.example.d5_mvc_jsp_quiz.repository.choice;

import com.example.d5_mvc_jsp_quiz.domain.Choice;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.SQLException;

@Component
public class ChoiceRepositoryRowMapper implements RowMapper<Choice> {
  @Override
  public Choice mapRow(java.sql.ResultSet rs, int i) throws SQLException {
    Choice choice = new Choice();
    choice.setId(rs.getLong("choice_id"));
    choice.setQuestionId(rs.getLong("question_id"));
    choice.setDescription(rs.getString("description"));
    return choice;
  }
}
