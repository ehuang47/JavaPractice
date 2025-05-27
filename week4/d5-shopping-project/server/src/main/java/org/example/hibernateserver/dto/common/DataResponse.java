package org.example.hibernateserver.dto.common;

public record DataResponse<T>(Boolean success, String message, T data) {
}
