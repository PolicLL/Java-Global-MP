package org.example.task1;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ServerConfig {

  private static volatile ServerConfig instance;

  private final Properties properties = new Properties();

  private static final String DEFAULT_FILE_PATH = "values.txt";

  // Get using custom path

  public static ServerConfig getInstance(String configFilePath) {
    if (instance == null) {
      synchronized (ServerConfig.class) {
        if(instance == null)
          instance = new ServerConfig(configFilePath);
      }
    }
    return instance;
  }

  private ServerConfig(String configFilePath) {
    try (FileInputStream fileInputStream = new FileInputStream(configFilePath)) {
      // Load configuration from the specified file
      properties.load(fileInputStream);
      validateConfig();
    } catch (IOException e) {
      throw new RuntimeException("Failed to load configuration file: " + configFilePath, e);
    }
  }

  // Get using default path

  public static ServerConfig getInstance() {
    if (instance == null) {
      synchronized (ServerConfig.class) {
        if(instance == null)
          instance = new ServerConfig();
      }
    }
    return instance;
  }

  private ServerConfig() {
    try (FileInputStream fileInputStream = new FileInputStream(DEFAULT_FILE_PATH)) {
      // Load configuration from the specified file
      properties.load(fileInputStream);
      validateConfig();
    } catch (IOException e) {
      throw new RuntimeException("Failed to load configuration file: " + DEFAULT_FILE_PATH, e);
    }
  }

  private void validateConfig() {
    if (!properties.containsKey("default.access.level")) {
      throw new RuntimeException("Missing required configuration: default.access.level");
    }
  }

  public String getAccessLevel(User user) {
    return properties.getProperty("user." + user.username() + ".access.level",
        properties.getProperty("default.access.level"));
  }


}
