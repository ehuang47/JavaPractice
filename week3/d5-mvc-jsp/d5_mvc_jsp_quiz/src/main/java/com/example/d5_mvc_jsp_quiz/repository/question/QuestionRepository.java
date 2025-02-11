package com.example.d5_mvc_jsp_quiz.repository.question;

import com.example.d5_mvc_jsp_quiz.domain.Question;
import com.example.d5_mvc_jsp_quiz.repository.EntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class QuestionRepository implements EntityRepository<Question, Long> {
  private JdbcTemplate jdbcTemplate;
  private QuestionRepositoryRowMapper rowMapper;
  private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

  {}
  @Autowired
  public QuestionRepository(JdbcTemplate jdbcTemplate,
                            QuestionRepositoryRowMapper rowMapper,
                            NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
    this.rowMapper = rowMapper;
    this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
  }

  @Override
  public Optional<Question> findById(Long id) {
    String query = "SELECT * FROM week3_question WHERE question_id = :questionId";
    MapSqlParameterSource parameterSource = new MapSqlParameterSource();
    parameterSource.addValue("questionId", id);
    Question question = namedParameterJdbcTemplate.queryForObject(query, parameterSource, rowMapper);
    return Optional.ofNullable(question);
  }

  public List<Question> findAllByQuizId(Long quizId) {
    String query = "SELECT * FROM week3_question WHERE quiz_id = :quizId";
    MapSqlParameterSource parameterSource = new MapSqlParameterSource();
    parameterSource.addValue("quizId", quizId);
    return namedParameterJdbcTemplate.query(query, parameterSource, rowMapper);
  }

  public List<Question> findAllByQuestionIdList(List<Long> questionIdList) {
    String query = """
      SELECT * FROM week3_question 
      WHERE question_id IN (:questionIdList)""";
    MapSqlParameterSource parameterSource = new MapSqlParameterSource()
      .addValue("questionIdList", questionIdList);
    return namedParameterJdbcTemplate.query(query, parameterSource, rowMapper);
  }

  //  todo: switch to using common findall
  @Override
  public List<Question> findAll() {
    String query = "SELECT * FROM week3_question";
    return jdbcTemplate.query(query, rowMapper);
  }
//  todo: fix findall dynamic filtering query
  @Override
  public List<Question> findAll(Map<String, Object> filters) {
    Long quizId = (Long) filters.get("quizId");
    @SuppressWarnings("unchecked")
    List<Long> questionIdList = (List<Long>) filters.get("questionIdList");
    String query = """
      SELECT * FROM week3_question 
      WHERE question_id IN (:questionIdList)
      AND quiz_id = :quizId""";
    MapSqlParameterSource parameterSource = new MapSqlParameterSource()
      .addValue("questionIdList", questionIdList);
    return namedParameterJdbcTemplate.query(query, parameterSource, rowMapper);
  }
}
