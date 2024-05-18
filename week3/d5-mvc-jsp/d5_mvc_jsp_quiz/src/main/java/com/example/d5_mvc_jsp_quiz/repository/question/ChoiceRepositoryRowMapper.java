package com.example.d5_mvc_jsp_quiz.repository.question;

import com.example.d5_mvc_jsp_quiz.domain.Choice;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class ChoiceRepositoryRowMapper implements RowMapper<Choice> {
    @Override
    public Choice mapRow(java.sql.ResultSet resultSet, int i) throws java.sql.SQLException {
        return new Choice(
                resultSet.getInt("id"),
                resultSet.getInt("question_id"),
                resultSet.getString("description")
        );
    }
}
