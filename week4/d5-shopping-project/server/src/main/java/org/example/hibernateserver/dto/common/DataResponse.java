package org.example.hibernateserver.dto.common;

public record DataResponse<T>(Boolean success, String message, T data) {
  public static <T> DataResponse<T> successWithData(T data) {
    return new DataResponse<>(true, "OK", data);
  }

  public static <T> DataResponse<T> successWithMessage(String message) {
    return new DataResponse<>(true, message, null);
  }

  public static <T> DataResponse<T> failure(String message) {
    return new DataResponse<>(false, message, null);
  }
}
