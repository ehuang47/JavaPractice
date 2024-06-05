package com.example.d5_mvc_jsp_quiz.repository.user;

import com.example.d5_mvc_jsp_quiz.domain.User;
import com.example.d5_mvc_jsp_quiz.repository.ObjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class UserRepository implements ObjectRepository<User> {
  private static final List<User> users;
  private static final User EMPTY_USER = new User(-1L, "", "", "", 0, "", "");

  static {
    users = new ArrayList<>();
    for (Long i = 0L; i < 3; i++) {
      users.add(new User(i, "user" + i, "pass" + i, String.format("user%s@t.com", i), 0, "user" + i, "lastName" + i));
    }
    users.add(new User(999L, "admin", "pass", "admin@t.com", 1, "admin", "istrator"));
  }

  private JdbcTemplate jdbcTemplate;
  private UserRepositoryRowMapper rowMapper;
  private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

  @Autowired
  public UserRepository(JdbcTemplate jdbcTemplate, UserRepositoryRowMapper rowMapper, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
    this.rowMapper = rowMapper;
    this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
  }

  @Override
  public Long save(User user) {
    return 1L;
  }

  @Override
  public Optional<User> findById(Long id) {
    Optional<User> user = users.stream()
      .filter(a -> a.getId().equals(id))
      .findFirst();

    return user;
  }

  @Override
  public List<User> findAll() {
    return users;
  }
}
