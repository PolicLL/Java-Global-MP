package org.example.task1;

public class SessionManager  {

  private AccessChecker access = AccessChecker.getInstance();

  public Session createSession(User user, String accessedPath) {
    if (access.mayAccess(user, accessedPath)) {
      return new Session(user);
    } else {
      throw new InsufficientRightsException(user, accessedPath);
    }
  }

}
