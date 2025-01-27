package org.example.task1;

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

  private final ServerConfig config = ServerConfig.getInstance("");

  private AccessChecker() {}

  public boolean mayAccess(User user, String path) {
    String userLevel = config.getAccessLevel(user);
    return true;
  }

}
