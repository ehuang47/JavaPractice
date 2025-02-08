package com.example.d5_mvc_jsp_quiz.repository.user;

import com.example.d5_mvc_jsp_quiz.domain.User;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class UserRepositoryRowMapper implements RowMapper<User> {
  @Override
  public User mapRow(ResultSet rs, int rowNum) throws SQLException {
    User user = new User();
    user.setId(rs.getLong("user_id"));
    user.setRole(rs.getInt("role"));
    user.setEmail(rs.getString("email"));
    user.setFirstName(rs.getString("first_name"));
    user.setLastName(rs.getString("last_name"));
    user.setUsername(rs.getString("username"));
    user.setPassword(rs.getString("password"));
    return user;
  }
}
