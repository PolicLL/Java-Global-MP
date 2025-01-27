package org.example.task1;

import org.example.task1.exception.InsufficientRightsException;
import org.example.task1.model.AccessLevel;
import org.example.task1.model.Session;
import org.example.task1.model.User;

public class SessionManager  {

  private final AccessChecker access = AccessChecker.getInstance();

  public Session createSession(User user, String accessedPath) {
    if (access.mayAccess(user, accessedPath)) {
      return new Session(user);
    } else {
      throw new InsufficientRightsException(user, accessedPath);
    }
  }


  public void createSession(User user, String accessedPath,  AccessLevel requiredAccessLevel) {
    if (access.mayAccess(user, accessedPath, requiredAccessLevel)) {
      new Session(user);
    } else {
      throw new InsufficientRightsException(user, accessedPath);
    }
  }
}
