package com.example.d5_mvc_jsp_quiz.repository.question;

import com.example.d5_mvc_jsp_quiz.domain.Question;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class QuestionRepositoryRowMapper implements RowMapper<Question> {
  @Override
  public Question mapRow(ResultSet rs, int rowNum) throws SQLException {
    Question question = new Question();
    question.setId(rs.getLong("question_id"));
    question.setQuizId(rs.getLong("quiz_id"));
    question.setCorrectChoiceId(rs.getLong("correct_choice_id"));
    question.setDescription(rs.getString("description"));
    question.setChoiceList(null);
    question.setActive(rs.getInt("active_status") == 1);
    return question;
  }
}
