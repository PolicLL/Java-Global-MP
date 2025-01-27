package org.example.task1;

public class InsufficientRightsException extends RuntimeException {

  public InsufficientRightsException(User user, String accessedPath) {
    super(String.format("User %s does not have sufficient rights to access the path: %s",
        user.username(), accessedPath));
  }
}
