package org.example.d5webservice.todo;

public record TodoDto (
  Long userId,
  Long id,
  String title,
  Boolean completed
){ }
