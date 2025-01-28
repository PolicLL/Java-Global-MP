package org.example.task1.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import org.example.task1.model.User;

public class ServerConfig {

  private static volatile ServerConfig instance;
  private static final String DEFAULT_FILE_PATH = "src/main/resources/config.properties";

  private static final Properties properties = new Properties();

  private ServerConfig(String configFilePath) {
    loadConfig(configFilePath);
  }

  public static ServerConfig getInstance(String configFilePath) {
    if (instance == null) {
      synchronized (ServerConfig.class) {
        if (instance == null) {
          instance = new ServerConfig(configFilePath);
        }
      }
    }

    return instance;
  }

  public static void resetInstance() {
    instance = null;
  }

  public static ServerConfig getInstance() {
    return getInstance(DEFAULT_FILE_PATH);
  }

  private static void loadConfig(String configFilePath) {
    try (FileInputStream fileInputStream = new FileInputStream(configFilePath)) {
      properties.load(fileInputStream);
      validateConfig();
    } catch (IOException e) {
      throw new RuntimeException("Failed to load configuration file: " + configFilePath, e);
    }
  }

  private static void validateConfig() {
    if (!properties.containsKey("default.access.level")) {
      throw new RuntimeException("Missing required configuration: default.access.level");
    }
  }

  public String getAccessLevel(User user) {
    return properties.getProperty("user." + user.username() + ".access.level",
        properties.getProperty("default.access.level"));
  }
}
