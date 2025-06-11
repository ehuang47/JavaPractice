package org.example.d5webservice.todo;

import org.example.d5webservice.DataResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/todo")
public class TodoController {
  private final RestTemplate restTemplate;
  @Autowired
  public TodoController(RestTemplate restTemplate) {
    this.restTemplate = restTemplate;
  }

  @GetMapping("")
  public DataResponse<List<TodoDto>> findAllTodoByUserId(@RequestParam(required = false) Long userId) {
    try {
      final String todoEndpoint = String.format("https://jsonplaceholder.typicode.com/todos?userId=%s", userId);
      ResponseEntity<TodoDto[]> response = restTemplate.getForEntity(todoEndpoint, TodoDto[].class);
      List<TodoDto> todos = response.getBody() != null
        ? List.of(response.getBody())
        : List.of();
      return DataResponse.successWithData(todos);
    } catch (Exception e) {
      return DataResponse.failure(e.getMessage());
    }
  }
}
