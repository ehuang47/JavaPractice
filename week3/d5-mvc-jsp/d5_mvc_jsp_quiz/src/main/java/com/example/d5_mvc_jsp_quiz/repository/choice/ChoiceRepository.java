package com.example.d5_mvc_jsp_quiz.repository.choice;

import com.example.d5_mvc_jsp_quiz.domain.Choice;
import com.example.d5_mvc_jsp_quiz.repository.EntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ChoiceRepository implements EntityRepository<Choice, Long> {
  private JdbcTemplate jdbcTemplate;
  private ChoiceRepositoryRowMapper rowMapper;
  private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

  @Autowired
  public ChoiceRepository(JdbcTemplate jdbcTemplate,
                          ChoiceRepositoryRowMapper rowMapper,
                          NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
    this.rowMapper = rowMapper;
    this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
  }

  @Override
  public Optional<Choice> findById(Long id) {
    String query = "SELECT * FROM week3_choice WHERE choice_id = :choiceId";
    MapSqlParameterSource parameterSource = new MapSqlParameterSource();
    parameterSource.addValue("choiceId", id);
    Choice choice = namedParameterJdbcTemplate.queryForObject(query, parameterSource, rowMapper);
    return Optional.ofNullable(choice);
  }

//  todo: switch to using common findall
  public List<Choice> findAllByQuestionList(List<Long> questionIdList) {
    String query = """
      SELECT * FROM week3_choice 
      WHERE question_id IN (:questionIdList) 
      ORDER BY question_id, choice_id""";
    MapSqlParameterSource parameterSource = new MapSqlParameterSource();
    parameterSource.addValue("questionIdList", questionIdList);
    return namedParameterJdbcTemplate.query(query, parameterSource, rowMapper);
  }

  @Override
  public List<Choice> findAll(){
    String query = "SELECT * FROM week3_choice";
    return jdbcTemplate.query(query, rowMapper);
  }

}
