package com.example.d5_mvc_jsp_quiz.service;

import com.example.d5_mvc_jsp_quiz.domain.Choice;
import com.example.d5_mvc_jsp_quiz.domain.Question;
import com.example.d5_mvc_jsp_quiz.exception.EntityType;
import com.example.d5_mvc_jsp_quiz.exception.InvalidArgumentException;
import com.example.d5_mvc_jsp_quiz.exception.NoResultsException;
import com.example.d5_mvc_jsp_quiz.repository.choice.ChoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class ChoiceService implements ObjectService<Choice> {
  private final ChoiceRepository choiceRepository;

  @Autowired
  public ChoiceService(ChoiceRepository choiceRepository) {
    this.choiceRepository = choiceRepository;
  }

  @Override
  public Long save(Choice choice) {
    return null;
  }

  @Override
  public Choice findById(Long id) {
    return null;
  }

  @Override
  public List<Choice> findAll() {
    return null;
  }

  public Map<Long, List<Choice>> findAllByQuestionList(List<Question> questionList, int questionCount) {
    List<Long> idList = questionList.stream().map(Question::getId).toList();
    List<Choice> choiceList = choiceRepository.findAllByQuestionList(idList);

    if (choiceList.isEmpty()) {
      throw new NoResultsException(EntityType.CHOICE, "Question is not finalized.");
    } else if (choiceList.size() != questionCount * 3) { // each Q has 3
      throw new InvalidArgumentException("Not enough choices exist for this question.");
    }

    Map<Long, List<Choice>> choicesByQuestion = new LinkedHashMap<>();
    for (Choice c : choiceList) {
      choicesByQuestion.computeIfAbsent(c.getQuestionId(), x -> new ArrayList<>()).add(c);
    }

    return choicesByQuestion;
  }
}
