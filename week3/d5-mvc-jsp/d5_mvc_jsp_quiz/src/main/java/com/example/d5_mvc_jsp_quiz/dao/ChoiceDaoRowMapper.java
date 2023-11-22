package com.example.d5_mvc_jsp_quiz.dao;

import com.example.d5_mvc_jsp_quiz.domain.Choice;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ChoiceDaoRowMapper implements RowMapper<Choice> {
    @Override
    public Choice mapRow(java.sql.ResultSet resultSet, int i) throws java.sql.SQLException {
        return new Choice(
                resultSet.getInt("id"),
                resultSet.getInt("question_id"),
                resultSet.getString("description")
        );
    }
}
