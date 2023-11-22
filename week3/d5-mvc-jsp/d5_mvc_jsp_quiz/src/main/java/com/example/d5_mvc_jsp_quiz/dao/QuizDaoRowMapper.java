package com.example.d5_mvc_jsp_quiz.dao;

import com.example.d5_mvc_jsp_quiz.domain.Quiz;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class QuizDaoRowMapper implements RowMapper<QuizDao> {
    @Override
    public QuizDao mapRow(ResultSet rs, int rowNum) throws SQLException {
        Quiz quiz = new Quiz();
        quiz.setId(rs.getInt("id"));
        quiz.setCategory(rs.getString("category"));
        quiz.setQuestionList(null);
        return null;
    }
}
