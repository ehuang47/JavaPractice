package com.example.d5_mvc_jsp_quiz.repository.message;

import com.example.d5_mvc_jsp_quiz.domain.Message;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class MessageRepositoryRowMapper implements RowMapper<Message> {
  @Override
  public Message mapRow(ResultSet rs, int rowNum) throws SQLException {
    Message message = new Message();
    message.setId(rs.getLong("message_id"));
    message.setEmail(rs.getString("email"));
    message.setDateSubmitted(rs.getString("date_submitted"));
    message.setSubject(rs.getString("subject"));
    message.setMessage(rs.getString("message"));
    return message;
  }
}
