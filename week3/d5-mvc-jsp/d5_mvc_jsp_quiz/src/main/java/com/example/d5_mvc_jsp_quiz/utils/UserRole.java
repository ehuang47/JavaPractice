package com.example.d5_mvc_jsp_quiz.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserRole {
  USER(0), ADMIN(1);

  private final int value;
}
