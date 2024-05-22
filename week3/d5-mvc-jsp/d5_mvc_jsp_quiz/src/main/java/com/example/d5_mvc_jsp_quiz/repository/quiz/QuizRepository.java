package com.example.d5_mvc_jsp_quiz.repository.quiz;

import com.example.d5_mvc_jsp_quiz.domain.Choice;
import com.example.d5_mvc_jsp_quiz.domain.Question;
import com.example.d5_mvc_jsp_quiz.domain.Quiz;
import com.example.d5_mvc_jsp_quiz.repository.ObjectRepository;
import com.example.d5_mvc_jsp_quiz.repository.user.UserRepositoryRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class QuizRepository implements ObjectRepository<Quiz> {
  private static final List<Quiz> quizzes;
  private static final Quiz EMPTY_QUIZ = new Quiz(-1, "category", new ArrayList<>());

  static {
    quizzes = new ArrayList<>();
    Quiz quiz = new Quiz(1, "Test", null);
    ArrayList<Question> questionList = new ArrayList<>();
    for (int i = 0; i < 10; i++) {
      Question q = new Question(i, 1, i * 4, "Question " + i, null);
      questionList.add(q);
      ArrayList<Choice> choiceList = new ArrayList<>();
      for (int j = 0; j < 4; j++) {
        choiceList.add(new Choice((i * 4) + j, i, "Choice " + j));
      }
      q.setChoiceList(choiceList);
    }
    quiz.setQuestionList(questionList);
    quizzes.add(quiz);
  }

  private JdbcTemplate jdbcTemplate;
  private UserRepositoryRowMapper rowMapper;
  private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

  @Autowired
  public QuizRepository(JdbcTemplate jdbcTemplate, UserRepositoryRowMapper rowMapper, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
    this.rowMapper = rowMapper;
    this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
  }

  @Override
  public void save(Quiz quiz) {

  }

  @Override
  public Quiz findById(int id) {
    Optional<Quiz> quiz = quizzes.stream()
      .filter(q -> q.getId() == id)
      .findFirst();

    return quiz.orElse(EMPTY_QUIZ);
  }

  @Override
  public List<Quiz> findAll() {
    return null;
  }
}
