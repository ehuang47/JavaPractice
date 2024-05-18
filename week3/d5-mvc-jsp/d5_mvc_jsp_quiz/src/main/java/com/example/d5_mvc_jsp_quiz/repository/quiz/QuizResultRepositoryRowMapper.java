package com.example.d5_mvc_jsp_quiz.repository.quiz;

import com.example.d5_mvc_jsp_quiz.domain.QuizResult;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class QuizResultRepositoryRowMapper implements RowMapper<QuizResult> {
    @Override
    public QuizResult mapRow(java.sql.ResultSet resultSet, int i) throws java.sql.SQLException {
        return new QuizResult(
                resultSet.getInt("id"),
                resultSet.getInt("user_id"),
                resultSet.getInt("quiz_id"),
                resultSet.getString("start_time"),
                resultSet.getString("end_time")
        );
    }
}
