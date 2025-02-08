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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.Duration;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
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

  private String getLocalTime(ZonedDateTime time) {
    ZoneId userTimeZone = ZoneId.of("America/Chicago");

    // Convert start and end times to the user's time zone
    ZonedDateTime userTime = time.withZoneSameInstant(userTimeZone);

    // Define the desired format
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy HH:mm");

    // Format the times
    return userTime.format(formatter);
  }

  private void buildQuizResult(QuizResult savedQuizResult,
                               QuizResultChoiceService quizResultChoiceService,
                               QuestionService questionService,
                               Long quizResultId,
                               Model model) {
    ZonedDateTime start = Instant.parse(savedQuizResult.getDateStarted()).atZone(ZoneId.of("UTC"));
    ZonedDateTime end = Instant.parse(savedQuizResult.getDateSubmitted()).atZone(ZoneId.of("UTC"));
    Duration duration = Duration.between(start, end);
    model.addAttribute("startTime", getLocalTime(start));
    model.addAttribute("endTime", getLocalTime(end));
    model.addAttribute("quizDurationMinutes", duration.toMinutes());
    model.addAttribute("quizDurationSeconds", duration.toSeconds());

    List<QuizResultChoice> savedChoiceList = quizResultChoiceService.findAllByQuizResultId(quizResultId);
    Map<Long, Long> questionIdToSelectedChoiceId = new LinkedHashMap<>();
    for (QuizResultChoice c : savedChoiceList) {
      questionIdToSelectedChoiceId.put(c.getQuestionId(), c.getChoiceId());
    }
    model.addAttribute("questionIdToSelectedChoiceId", questionIdToSelectedChoiceId);

    List<Long> questionIdList = savedChoiceList.stream()
      .map(QuizResultChoice::getQuestionId)
      .collect(Collectors.toList());
    List<Question> questionList = questionService.findAllByQuestionIdListWithChoices(questionIdList);
    questionList = questionService.populateQuestionListChoices(questionList);
    model.addAttribute("questionList", questionList);

    int scoreCount = 0;
    for (Question q : questionList) {
      if (q.getCorrectChoiceId().equals(questionIdToSelectedChoiceId.get(q.getId()))) {
        scoreCount++;
      }
    }
    final int PASSING_SCORE = 3;
    model.addAttribute("result", scoreCount > PASSING_SCORE ? "Pass" : "Fail");
  }

  @GetMapping("/{id}")
  public String getQuizResult(@PathVariable("id") Long id, Model model) {
    QuizResult savedQuizResult = quizResultService.findById(id);
    Quiz quiz = quizService.findById(savedQuizResult.getQuizId());
    model.addAttribute("quizCategory", quiz.getCategory());
    buildQuizResult(savedQuizResult, quizResultChoiceService, questionService, id, model);

    return "quiz-result";
  }

  @PostMapping("")
  public String submitQuiz(@RequestParam Map<String, String> body, Model model) {
    QuizResult submission = quizResultService.bodyMapper(body);
    Quiz quiz = quizService.findById(submission.getQuizId());
    model.addAttribute("quizCategory", quiz.getCategory());

    Long quizResultId = quizResultService.save(submission);
    QuizResult savedQuizResult = quizResultService.findById(quizResultId);
    buildQuizResult(savedQuizResult, quizResultChoiceService, questionService, quizResultId, model);

//    System.out.println(savedChoiceList);
//    System.out.println(questionIdToQuestion.get(savedChoiceList.get(0).getQuestionId()));
    return "quiz-result";
  }
}
