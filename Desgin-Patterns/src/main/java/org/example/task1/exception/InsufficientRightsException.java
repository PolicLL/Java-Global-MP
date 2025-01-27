package org.example.task1.exception;

import org.example.task1.model.User;

public class InsufficientRightsException extends RuntimeException {

  private final User user;

  private final String accessedPath;

  public InsufficientRightsException(User user, String accessedPath) {
    super(String.format("User %s does not have sufficient rights to access the path: %s",
        user.username(), accessedPath));

    this.user = user;
    this.accessedPath = accessedPath;
  }

  public User getUser() {
    return user;
  }

  public String getAccessedPath() {
    return accessedPath;
  }
}
