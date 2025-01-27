package org.example.task1;

import java.util.Objects;
import org.example.task1.config.ServerConfig;
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
    return !Objects.equals(ServerConfig.getInstance(path).getAccessLevel(user), "");
  }

}
