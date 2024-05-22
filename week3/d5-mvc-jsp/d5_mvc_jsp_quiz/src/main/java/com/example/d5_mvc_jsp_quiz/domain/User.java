package com.example.d5_mvc_jsp_quiz.domain;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User {
  private Long id;
  private String username;
  private String password;
  private String email;
  private int role; // 0-user, 1-admin
  private String firstName;
  private String lastName;
}
