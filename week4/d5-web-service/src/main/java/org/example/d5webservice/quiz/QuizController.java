package org.example.d5webservice.quiz;

import org.example.d5webservice.common.DataResponse;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/quiz")
public class QuizController {
  private final SessionFactory sessionFactory;
  public QuizController(SessionFactory sessionFactory){
    this.sessionFactory = sessionFactory;
  }

  @GetMapping("")
  @Transactional(readOnly = true)
  public DataResponse<List<QuizDto>> findAllQuizByUserId(@RequestParam(required = false) Long userId) {
    try {
      String jpql = "SELECT q FROM Quiz q WHERE q.userId = :userId";
      List<Quiz> quizzes = sessionFactory.getCurrentSession()
        .createQuery(jpql, Quiz.class)
        .setParameter("userId", userId)
        .getResultList();
      return DataResponse.successWithData(quizzes.stream()
        .map(quiz -> new QuizDto(
          quiz.getId(),
          quiz.getName(),
          quiz.getUserId()))
        .toList());
    } catch (Exception e) {
      return DataResponse.failure(e.getMessage());
    }
  }
}
