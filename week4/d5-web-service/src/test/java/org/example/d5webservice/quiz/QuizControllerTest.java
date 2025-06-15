package org.example.d5webservice.quiz;

import org.example.d5webservice.common.DataResponse;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(QuizController.class)
class QuizControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SessionFactory sessionFactory;

    @Mock
    private Session session;

    @Mock
    private Transaction transaction;

    @Mock
    private Query<Quiz> query;

    @BeforeEach
    void setUp() {
        // Common stubs
        when(sessionFactory.getCurrentSession()).thenReturn(session);
        when(session.beginTransaction()).thenReturn(transaction);
    }

    @Test
    @DisplayName("GET /quiz?userId={id} - Success")
    void findAllQuizByUserId_WhenQuizzesExist_ReturnsQuizzes() throws Exception {
        // Given
        Long userId = 1L;
        Quiz quiz = Quiz.builder()
                .id(1L)
                .name("Java Basics")
                .userId(userId)
                .build();

        when(session.createQuery(anyString(), eq(Quiz.class))).thenReturn(query);
        when(query.setParameter("userId", userId)).thenReturn(query);
        when(query.getResultList()).thenReturn(List.of(quiz));

        // When/Then
        mockMvc.perform(get("/quiz").param("userId", userId.toString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data[0].id").value(1))
                .andExpect(jsonPath("$.data[0].name").value("Java Basics"))
                .andExpect(jsonPath("$.data[0].userId").value(userId));
    }

    @Test
    @DisplayName("GET /quiz?userId={id} - Empty List")
    void findAllQuizByUserId_WhenNoQuizzes_ReturnsEmptyList() throws Exception {
        // Given
        Long userId = 999L;

        when(session.createQuery(anyString(), eq(Quiz.class))).thenReturn(query);
        when(query.setParameter("userId", userId)).thenReturn(query);
        when(query.getResultList()).thenReturn(Collections.emptyList());

        // When/Then
        mockMvc.perform(get("/quiz").param("userId", userId.toString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data").isArray())
                .andExpect(jsonPath("$.data").isEmpty());
    }

    @Test
    @DisplayName("GET /quiz?userId={id} - Exception Handling")
    void findAllQuizByUserId_WhenExceptionOccurs_ReturnsFailure() throws Exception {
        // Given
        Long userId = 1L;
        String errorMessage = "Database connection failed";

        when(session.createQuery(anyString(), eq(Quiz.class))).thenReturn(query);
        when(query.setParameter("userId", userId)).thenReturn(query);
        when(query.getResultList()).thenThrow(new RuntimeException(errorMessage));

        // When/Then
        mockMvc.perform(get("/quiz").param("userId", userId.toString()))
                .andExpect(status().isOk())  // Controller returns 200 with error message
                .andExpect(jsonPath("$.success").value(false))
                .andExpect(jsonPath("$.message").value(errorMessage));
    }

    @Test
    @DisplayName("GET /quiz - Without userId Parameter")
    void findAllQuizByUserId_WithoutUserId_HandlesNullParameter() throws Exception {
        // Given
        when(session.createQuery(anyString(), eq(Quiz.class))).thenReturn(query);
        when(query.setParameter("userId", null)).thenReturn(query);
        when(query.getResultList()).thenReturn(Collections.emptyList());

        // When/Then
        mockMvc.perform(get("/quiz"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data").isArray())
                .andExpect(jsonPath("$.data").isEmpty());
    }
}
