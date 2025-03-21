package task1;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import org.example.task1.SessionManager;
import org.example.task1.config.ServerConfig;
import org.example.task1.exception.InsufficientRightsException;
import org.example.task1.model.AccessLevel;
import org.example.task1.model.Session;
import org.example.task1.model.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SessionManagerTest {

  private static final String TEMP_CONFIG_FILE = "test_config_part_two.properties";

  @BeforeAll
  static void setup() {
    ServerConfig.resetInstance();
  }

  @BeforeEach
  void setUp() throws IOException {
    File configFile = new File(TEMP_CONFIG_FILE);
    if (configFile.exists()) {
      configFile.delete();
    }

    try (FileWriter writer = new FileWriter(configFile)) {
      writer.write("default.access.level=guest\n");
      writer.write("user.john_doe.access.level=admin\n");
    }
  }

  @Test
  void testCreateSession_success() {
    User user = new User("john_doe");
    SessionManager sessionManager = new SessionManager();

    Session session = sessionManager.createSession(user, TEMP_CONFIG_FILE);

    assertNotNull(session);
    assertEquals(user, session.user());
  }

  @Test
  void testCreateSession_insufficientRights_throwsException() {
    User user = new User("unknownUser");
    SessionManager sessionManager = new SessionManager();

    InsufficientRightsException exception = assertThrows(InsufficientRightsException.class, () -> {
      sessionManager.createSession(user, TEMP_CONFIG_FILE, AccessLevel.MANAGER);
    });

    assertNotNull(exception);
    assertEquals(user, exception.getUser());
    assertEquals(TEMP_CONFIG_FILE, exception.getAccessedPath());
  }
}
