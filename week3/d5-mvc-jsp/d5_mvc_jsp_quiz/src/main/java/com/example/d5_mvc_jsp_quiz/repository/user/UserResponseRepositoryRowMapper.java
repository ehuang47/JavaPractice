package com.example.d5_mvc_jsp_quiz.repository.user;

import com.example.d5_mvc_jsp_quiz.domain.UserResponse;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class UserResponseRepositoryRowMapper implements RowMapper<UserResponse> {
  @Override
  public UserResponse mapRow(ResultSet rs, int rowNum) throws SQLException {
    UserResponse userResponse = new UserResponse();
    userResponse.setUserId(rs.getLong("user_id"));
    userResponse.setId(rs.getLong("id"));
    userResponse.setQuestionId(rs.getLong("question_id"));
    userResponse.setQuizResultId(rs.getLong("quiz_result_id"));
    userResponse.setSelectedChoiceId(rs.getLong("selected_choice_id"));
    return userResponse;
  }
}
