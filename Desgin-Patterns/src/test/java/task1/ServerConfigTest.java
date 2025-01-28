package task1;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static utils.TestUtils.clearFileContent;

import java.io.FileWriter;
import java.io.IOException;
import org.example.task1.config.ServerConfig;
import org.example.task1.model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ServerConfigTest {

  private static final String TEST_CONFIG_FILE = "test_config.properties";

  @AfterEach
  void tearDown() {
    clearFileContent(TEST_CONFIG_FILE);
  }

  @Test
  @Order(1)
  void testInvalidFilePath_throwsException() {
    String invalidPath = "invalid_config.properties";
    RuntimeException exception = assertThrows(RuntimeException.class, () -> ServerConfig.getInstance(invalidPath));
    System.out.println(exception.getMessage());
    assertTrue(exception.getMessage().contains("Failed to load configuration file"));
  }

  @Test
  @Order(2)
  void testMissingDefaultAccessLevel_throwsException() throws IOException {
    RuntimeException exception = assertThrows(RuntimeException.class, () -> ServerConfig.getInstance(TEST_CONFIG_FILE));
    assertTrue(exception.getMessage().contains("Missing required configuration: default.access.level"));
  }


  @Test
  void testGetInstance_withCustomFilePath() throws IOException {
    writeToFile();

    ServerConfig config = ServerConfig.getInstance(TEST_CONFIG_FILE);
    assertNotNull(config);
  }

  @Test
  void testGetAccessLevel_existingUser() throws IOException {
    writeToFile();

    ServerConfig config = ServerConfig.getInstance(TEST_CONFIG_FILE);
    User john = new User("john");
    String accessLevel = config.getAccessLevel(john);
    assertEquals("admin", accessLevel, "Access level for existing user should match");
  }

  @Test
  void testGetAccessLevel_defaultAccessLevel() throws IOException {
    writeToFile();

    ServerConfig config = ServerConfig.getInstance(TEST_CONFIG_FILE);
    User unknown = new User("unknown");
    String accessLevel = config.getAccessLevel(unknown);
    assertEquals("guest", accessLevel, "Access level for unknown user should be the default");
  }

  private void writeToFile() throws IOException {
    FileWriter writer = new FileWriter(TEST_CONFIG_FILE);
    writer.write("default.access.level=guest\n");
    writer.write("user.john.access.level=admin\n");
    writer.close();
  }
}
