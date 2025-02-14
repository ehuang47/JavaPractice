package com.example.d5_mvc_jsp_quiz.repository.user;

import com.example.d5_mvc_jsp_quiz.domain.User;
import com.example.d5_mvc_jsp_quiz.repository.EntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
public class UserRepository implements EntityRepository<User, Long> {
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
    String query = """
      INSERT INTO week3_user (first_name, last_name, email, username, password) 
      VALUES (:firstName, :lastName, :email, :username, :password )""";

    MapSqlParameterSource parameterSource = new MapSqlParameterSource()
      .addValue("firstName", user.getFirstName())
      .addValue("lastName", user.getLastName())
      .addValue("email", user.getEmail())
      .addValue("username", user.getUsername())
      .addValue("password", user.getPassword());

    KeyHolder keyHolder = new GeneratedKeyHolder();
    namedParameterJdbcTemplate.update(query, parameterSource, keyHolder, new String[]{"quiz_result_choice_id"});
    return Objects.requireNonNull(keyHolder.getKey()).longValue();
  }

  @Override
  public Optional<User> findById(Long id) {
    String query = "SELECT * FROM week3_user WHERE user_id=:userId";
    MapSqlParameterSource parameterSource = new MapSqlParameterSource();
    parameterSource.addValue("userId", id);
    User user = namedParameterJdbcTemplate.queryForObject(query, parameterSource, rowMapper);
    return Optional.ofNullable(user);
  }

  public Optional<User> findByUsernameOrEmail(String username, String email) {
    StringBuilder queryBuilder = new StringBuilder();
    queryBuilder.append("SELECT * FROM week3_user WHERE ");
    MapSqlParameterSource parameterSource = new MapSqlParameterSource();

    if (username != null) {
      queryBuilder.append("username LIKE :username");
      parameterSource.addValue("username", username);
    } else if (email != null) {
      queryBuilder.append("email LIKE :email");
      parameterSource.addValue("email", email);
    }

    try {
      User user = namedParameterJdbcTemplate.queryForObject(queryBuilder.toString(), parameterSource, rowMapper);
      return Optional.ofNullable(user);
      } catch (EmptyResultDataAccessException e) {
      return Optional.empty();
    }
  }

  public List<User> findAllByUsernameOrEmail(String username, String email) {
    StringBuilder queryBuilder = new StringBuilder("SELECT * FROM week3_user WHERE ");
    MapSqlParameterSource parameterSource = new MapSqlParameterSource();

    if (username != null) {
      queryBuilder.append("username LIKE :username");
      parameterSource.addValue("username", username);
    } else if (email != null) {
      queryBuilder.append("email LIKE :email");
      parameterSource.addValue("email", email);
    }

    return namedParameterJdbcTemplate.query(queryBuilder.toString(), parameterSource, rowMapper);
  }

  @Override
  public List<User> findAll() {
    String query = "SELECT * FROM week3_user";
    return jdbcTemplate.query(query, rowMapper);
  }
}
