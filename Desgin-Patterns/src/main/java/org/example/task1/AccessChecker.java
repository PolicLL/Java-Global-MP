package org.example.task1;

import java.util.Objects;
import org.example.task1.config.ServerConfig;
import org.example.task1.model.AccessLevel;
import org.example.task1.model.User;

public class AccessChecker {

  private static volatile AccessChecker instance;

  public static AccessChecker getInstance() {
    if (instance == null) {
      synchronized (AccessChecker.class) {
        if(instance == null)
          instance = new AccessChecker();
      }
    }
    return instance;
  }
  private AccessChecker() {}

  public boolean mayAccess(User user, String path) {
    return mayAccess(user, path, AccessLevel.USER);
  }

  public boolean mayAccess(User user, String path, AccessLevel required) {
    String userAccessLevelString = getUserAccessLevel(user, path);

    if (userAccessLevelString == null || userAccessLevelString.isEmpty()) {
      return false;
    }

    return AccessLevel.fromString(userAccessLevelString).canAccess(required);
  }

  private String getUserAccessLevel(User user, String path) {
    return ServerConfig.getInstance(path).getAccessLevel(user);
  }



}
