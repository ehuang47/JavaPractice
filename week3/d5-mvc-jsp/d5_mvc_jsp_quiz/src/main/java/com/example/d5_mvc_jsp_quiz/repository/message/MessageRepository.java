package com.example.d5_mvc_jsp_quiz.repository.message;

import com.example.d5_mvc_jsp_quiz.domain.Message;
import com.example.d5_mvc_jsp_quiz.repository.EntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;

@Repository
public class MessageRepository implements EntityRepository<Message, Long> {
  private JdbcTemplate jdbcTemplate;
  private MessageRepositoryRowMapper rowMapper;

  private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

  @Autowired
  public MessageRepository(JdbcTemplate jdbcTemplate,
                           MessageRepositoryRowMapper rowMapper,
                           NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
    this.rowMapper = rowMapper;
    this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
  }

  @Override
  public List<Message> findAll() {
    String query = "SELECT * FROM week3_message ORDER BY date_submitted DESC";
    return jdbcTemplate.query(query, rowMapper);
  }

  @Override
  public Long save(Message message) {
    String query = """
      INSERT INTO week3_message (email, date_submitted, subject, message) VALUES 
      (:email, :dateSubmitted, :subject, :message)""";

    MapSqlParameterSource parameterSource = new MapSqlParameterSource()
      .addValue("email", message.getEmail())
      .addValue("dateSubmitted", message.getDateSubmitted())
      .addValue("subject", message.getSubject())
      .addValue("message", message.getMessage());

    KeyHolder keyHolder = new GeneratedKeyHolder();
    namedParameterJdbcTemplate.update(query, parameterSource, keyHolder, new String[]{"message_id"});
    return Objects.requireNonNull(keyHolder.getKey()).longValue();
  }
}
