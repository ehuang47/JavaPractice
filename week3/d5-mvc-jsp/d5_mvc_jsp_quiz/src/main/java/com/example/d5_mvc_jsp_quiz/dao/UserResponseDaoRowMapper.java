package com.example.d5_mvc_jsp_quiz.dao;

import com.example.d5_mvc_jsp_quiz.domain.UserResponse;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class UserResponseDaoRowMapper implements RowMapper<UserResponse> {
    @Override
    public UserResponse mapRow(ResultSet rs, int rowNum) throws SQLException {
        UserResponse userResponse = new UserResponse();
        userResponse.setUserId(rs.getInt("user_id"));
        userResponse.setId(rs.getInt("id"));
        userResponse.setQuestionId(rs.getInt("question_id"));
        userResponse.setQuizResultId(rs.getInt("quiz_result_id"));
        userResponse.setSelectedChoiceId(rs.getInt("selected_choice_id"));
        return userResponse;
    }
}
