package org.example.hibernateserver.dto.common;


import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public class RequestContext {
  private final Long userId;
  private boolean isAdmin;

  public static RequestContext forUser(Long userId) {
    if (userId == null) throw new IllegalArgumentException("User ID required");
    return new RequestContext(userId, false);
  }
  public static RequestContext forAdmin(Long userId) {
    if (userId == null) throw new IllegalArgumentException("User ID required");
    return new RequestContext(userId, true);
  }

  public boolean isAuthenticated() {
    return userId != null;
  }
}
