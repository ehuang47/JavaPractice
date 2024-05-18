package com.example.d5_mvc_jsp_quiz.controller;

import com.example.d5_mvc_jsp_quiz.domain.Choice;
import com.example.d5_mvc_jsp_quiz.domain.Question;
import com.example.d5_mvc_jsp_quiz.domain.Quiz;
import com.example.d5_mvc_jsp_quiz.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;

@Controller
public class QuizController {
    @GetMapping(value="/quiz") //localhost:8080/quiz
    public String quizModelView(Model model) {
        Quiz quiz = new Quiz(1, "Test", null);
        ArrayList<Question> questionList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Question q = new Question(i, 1, i*4, "Question " + i, null);
            questionList.add(q);
            ArrayList<Choice> choiceList = new ArrayList<>();
            for (int j = 0; j < 4; j++) {
                choiceList.add(new Choice((i*4)+j, i, "Choice " + j));
            }
            q.setChoiceList(choiceList);
        }
        ArrayList<Question> selectedQuestionList = new ArrayList<>();
        quiz.setQuestionList(questionList);
        model.addAttribute("quiz", quiz);
        return "quiz";
    }
}
