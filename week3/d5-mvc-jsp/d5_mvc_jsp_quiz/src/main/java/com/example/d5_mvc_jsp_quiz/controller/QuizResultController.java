package com.example.d5_mvc_jsp_quiz.controller;

import com.example.d5_mvc_jsp_quiz.domain.Question;
import com.example.d5_mvc_jsp_quiz.domain.Quiz;
import com.example.d5_mvc_jsp_quiz.domain.QuizResult;
import com.example.d5_mvc_jsp_quiz.domain.QuizResultChoice;
import com.example.d5_mvc_jsp_quiz.service.QuestionService;
import com.example.d5_mvc_jsp_quiz.service.QuizResultChoiceService;
import com.example.d5_mvc_jsp_quiz.service.QuizResultService;
import com.example.d5_mvc_jsp_quiz.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.Duration;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/quiz-result")
public class QuizResultController {
  private final QuizResultService quizResultService;
  private final QuizResultChoiceService quizResultChoiceService;
  private final QuizService quizService;
  private final QuestionService questionService;

  @Autowired
  public QuizResultController(QuizResultService quizResultService,
                              QuizService quizService,
                              QuizResultChoiceService quizResultChoiceService,
                              QuestionService questionService
  ) {
    this.quizResultService = quizResultService;
    this.quizService = quizService;
    this.quizResultChoiceService = quizResultChoiceService;
    this.questionService = questionService;
  }

  @PostMapping("")
  public String submitQuiz(@RequestParam Map<String, String> body, Model model) {
    QuizResult submission = quizResultService.bodyMapper(body);
    Quiz quiz = quizService.findById(submission.getQuizId());
    model.addAttribute("quizCategory", quiz.getCategory());

    Long quizResultId = quizResultService.save(submission);
    QuizResult savedQuizResult = quizResultService.findById(quizResultId);
    ZonedDateTime start = Instant.parse(savedQuizResult.getDateStarted()).atZone(ZoneId.of("UTC"));
    ZonedDateTime end = Instant.parse(savedQuizResult.getDateSubmitted()).atZone(ZoneId.of("UTC"));
    Duration duration = Duration.between(start, end);
    model.addAttribute("quizDurationMinutes", duration.toMinutes());
    model.addAttribute("quizDurationSeconds", duration.toSeconds());

    List<QuizResultChoice> savedChoiceList = quizResultChoiceService.findAllByQuizResultId(quizResultId);
    model.addAttribute("savedChoiceList", savedChoiceList);

    List<Long> questionIdList = savedChoiceList.stream()
      .map(QuizResultChoice::getQuestionId)
      .collect(Collectors.toList());
    List<Question> questionList = questionService.findAllByQuestionList(questionIdList);
    Map<Long, Question> questionIdToQuestion = new LinkedHashMap<>();
    for (Question q : questionList) {
      questionIdToQuestion.put(q.getId(), q);
    }
    model.addAttribute("questionIdToQuestion", questionIdToQuestion);


    System.out.println(savedChoiceList);
    System.out.println(questionIdToQuestion.get(savedChoiceList.get(0).getQuestionId()));
    return "quiz-result";
  }
}
