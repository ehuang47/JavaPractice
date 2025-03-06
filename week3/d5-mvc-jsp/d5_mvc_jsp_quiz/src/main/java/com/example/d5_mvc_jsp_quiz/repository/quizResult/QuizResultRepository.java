package com.example.d5_mvc_jsp_quiz.repository.quizResult;

import com.example.d5_mvc_jsp_quiz.domain.QuizResult;
import com.example.d5_mvc_jsp_quiz.domain.QuizResultSummary;
import com.example.d5_mvc_jsp_quiz.repository.EntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@Repository
public class QuizResultRepository implements EntityRepository<QuizResult, Long> {
  private JdbcTemplate jdbcTemplate;
  private QuizResultRepositoryRowMapper rowMapper;
  private QuizResultSummaryRowMapper quizResultSummaryRowMapper;
  private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

  @Autowired
  public QuizResultRepository(JdbcTemplate jdbcTemplate,
                              QuizResultRepositoryRowMapper rowMapper,
                              QuizResultSummaryRowMapper quizResultSummaryRowMapper,
                              NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
    this.rowMapper = rowMapper;
    this.quizResultSummaryRowMapper = quizResultSummaryRowMapper;
    this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
  }

  @Override
  public Long save(QuizResult quizResult) {
    String query = """
      INSERT INTO week3_quiz_result (quiz_id, date_started, date_submitted, user_id) VALUES 
      (:quizId, :dateStarted, :dateSubmitted, :userId )""";

    MapSqlParameterSource parameterSource = new MapSqlParameterSource()
      .addValue("quizId", quizResult.getQuizId())
      .addValue("dateStarted", quizResult.getDateStarted())
      .addValue("dateSubmitted", quizResult.getDateSubmitted())
      .addValue("userId", quizResult.getUserId());

    KeyHolder keyHolder = new GeneratedKeyHolder();
    namedParameterJdbcTemplate.update(query, parameterSource, keyHolder, new String[]{"quiz_result_id"});
    return Objects.requireNonNull(keyHolder.getKey()).longValue();
  }

  @Override
  public Optional<QuizResult> findById(Long id) {
    String query = "SELECT * FROM week3_quiz_result WHERE quiz_result_id = :quizResultId";
    MapSqlParameterSource parameterSource = new MapSqlParameterSource()
      .addValue("quizResultId", id);
    QuizResult quizResult = namedParameterJdbcTemplate.queryForObject(query, parameterSource, rowMapper);
    return Optional.ofNullable(quizResult);
  }

  public List<QuizResult> findAllByUserId(Long userId) {
    String query = "SELECT * FROM week3_quiz_result WHERE user_id = :userId";
    MapSqlParameterSource parameterSource = new MapSqlParameterSource()
      .addValue("userId", userId);
    return namedParameterJdbcTemplate.query(query, parameterSource, rowMapper);
  }

  public List<QuizResultSummary> findAllForManagement(String columnToOrder,
                                                      boolean ascending,
                                                      String columnToFilter,
                                                      String filterValue) {
    StringBuilder queryBuilder = new StringBuilder();
    queryBuilder.append("SELECT r.*, u.first_name, u.last_name, q.category ")
      .append("FROM week3_quiz_result r ")
      .append("JOIN week3_user u on r.user_id = u.user_id ")
      .append("JOIN week3_quiz q on r.quiz_id = q.quiz_id ");

    if (columnToFilter != null && (filterValue != null && !filterValue.isEmpty())) {
      queryBuilder.append("WHERE ")
        .append(columnToFilter.equals("user_id") ? "u.user_id" : columnToFilter)
        .append("= :filterValue ");
    }
    if (columnToOrder.equals("name")){
      queryBuilder.append("ORDER BY first_name ")
        .append(ascending ? "ASC" : "DESC")
        .append(", last_name ")
        .append(ascending ? "ASC" : "DESC");
    } else {
      queryBuilder.append("ORDER BY ")
        .append(columnToOrder)
        .append(" ")
        .append(ascending ? "ASC" : "DESC");
    }
    MapSqlParameterSource parameterSource = new MapSqlParameterSource()
      .addValue("filterValue", filterValue);
    return namedParameterJdbcTemplate.query(queryBuilder.toString(), parameterSource, quizResultSummaryRowMapper);
  }

  public Map<Long, Integer> findAllScores() {
    final String query = """
      SELECT quiz_result_id, 
        SUM(case when q.correct_choice_id = rc.choice_id then 1 else 0 end) as score
      FROM week3_quiz_result_choice rc
      JOIN week3_question q
      ON rc.question_id = q.question_id
      GROUP BY quiz_result_id;
      """;

    Map<Long, Integer> scoreMap = new HashMap<>();
    jdbcTemplate.query(query, (rs) -> {
      scoreMap.put(rs.getLong("quiz_result_id"), rs.getInt("score"));
    });

    return scoreMap;
  }
}
