package org.example;

import org.example.task1.config.ServerConfig;
import org.example.task1.model.User;

public class Main {
  public static void main(String[] args) {

    String filePath = "src/main/resources/config.properties";
    ServerConfig serverConfig = ServerConfig.getInstance(filePath);

    User admin = new User("admin");
    User john = new User("john_doe");
    User unknown = new User("unknown_user");

    System.out.println(admin.username() + " access level: " + serverConfig.getAccessLevel(admin));
    System.out.println(john.username() + " access level: " + serverConfig.getAccessLevel(john));
    System.out.println(unknown.username() + " access level: " + serverConfig.getAccessLevel(unknown));
  }
}
