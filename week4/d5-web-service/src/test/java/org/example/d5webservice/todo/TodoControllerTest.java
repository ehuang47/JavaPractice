package org.example.d5webservice.todo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TodoController.class)
public class TodoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RestTemplate restTemplate;

    private final String API_BASE_URL = "https://jsonplaceholder.typicode.com/todos";

    @BeforeEach
    void setUp() {
        // Common setup if needed
    }

    @Test
    @DisplayName("GET /todo?userId={id} - Success")
    void findAllTodoByUserId_WhenTodosExist_ReturnsTodos() throws Exception {
        // Given
        Long userId = 1L;
        TodoDto[] todoArray = {
            new TodoDto(userId, 1L, "delectus aut autem", false),
            new TodoDto(userId, 2L, "quis ut nam facilis", false)
        };

        String expectedUrl = API_BASE_URL + "?userId=" + userId;
        when(restTemplate.getForEntity(expectedUrl, TodoDto[].class))
                .thenReturn(new ResponseEntity<>(todoArray, HttpStatus.OK));

        // When/Then
        mockMvc.perform(get("/todo").param("userId", userId.toString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data[0].id").value(1))
                .andExpect(jsonPath("$.data[0].title").value("delectus aut autem"))
                .andExpect(jsonPath("$.data[0].completed").value(false))
                .andExpect(jsonPath("$.data[1].id").value(2));
    }

    @Test
    @DisplayName("GET /todo?userId={id} - Empty Response")
    void findAllTodoByUserId_WhenNoTodos_ReturnsEmptyList() throws Exception {
        // Given
        Long userId = 999L;
        TodoDto[] emptyArray = {};

        String expectedUrl = API_BASE_URL + "?userId=" + userId;
        when(restTemplate.getForEntity(expectedUrl, TodoDto[].class))
                .thenReturn(new ResponseEntity<>(emptyArray, HttpStatus.OK));

        // When/Then
        mockMvc.perform(get("/todo").param("userId", userId.toString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data").isArray())
                .andExpect(jsonPath("$.data").isEmpty());
    }

    @Test
    @DisplayName("GET /todo?userId={id} - Null Response Body")
    void findAllTodoByUserId_WhenNullResponseBody_ReturnsEmptyList() throws Exception {
        // Given
        Long userId = 1L;

        String expectedUrl = API_BASE_URL + "?userId=" + userId;
        when(restTemplate.getForEntity(expectedUrl, TodoDto[].class))
                .thenReturn(new ResponseEntity<>(null, HttpStatus.OK));

        // When/Then
        mockMvc.perform(get("/todo").param("userId", userId.toString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data").isArray())
                .andExpect(jsonPath("$.data").isEmpty());
    }

    @Test
    @DisplayName("GET /todo - Without userId Parameter")
    void findAllTodoByUserId_WithoutUserId_HandlesNullParameter() throws Exception {
        // Given
        TodoDto[] todoArray = {
            new TodoDto(1L, 1L, "Task 1", false),
            new TodoDto(2L, 2L, "Task 2", true)
        };

        String expectedUrl = API_BASE_URL + "?userId=null";
        when(restTemplate.getForEntity(expectedUrl, TodoDto[].class))
                .thenReturn(new ResponseEntity<>(todoArray, HttpStatus.OK));

        // When/Then
        mockMvc.perform(get("/todo"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data").isArray());
    }

    @Test
    @DisplayName("GET /todo?userId={id} - API Error")
    void findAllTodoByUserId_WhenApiError_ReturnsFailure() throws Exception {
        // Given
        Long userId = 1L;
        String errorMessage = "API service unavailable";

        String expectedUrl = API_BASE_URL + "?userId=" + userId;
        when(restTemplate.getForEntity(expectedUrl, TodoDto[].class))
                .thenThrow(new HttpClientErrorException(HttpStatus.SERVICE_UNAVAILABLE, errorMessage));

        // When/Then
        mockMvc.perform(get("/todo").param("userId", userId.toString()))
                .andExpect(status().isOk())  // Controller returns 200 with error info
                .andExpect(jsonPath("$.success").value(false))
                .andExpect(jsonPath("$.message").isString());
    }

    @Test
    @DisplayName("GET /todo?userId={id} - General Exception")
    void findAllTodoByUserId_WhenGeneralException_ReturnsFailure() throws Exception {
        // Given
        Long userId = 1L;
        String errorMessage = "Unexpected error occurred";

        String expectedUrl = API_BASE_URL + "?userId=" + userId;
        when(restTemplate.getForEntity(expectedUrl, TodoDto[].class))
                .thenThrow(new RuntimeException(errorMessage));

        // When/Then
        mockMvc.perform(get("/todo").param("userId", userId.toString()))
                .andExpect(status().isOk())  // Controller returns 200 with error info
                .andExpect(jsonPath("$.success").value(false))
                .andExpect(jsonPath("$.message").value(errorMessage));
    }
}
