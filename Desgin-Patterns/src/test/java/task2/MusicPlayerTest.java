package task2;

import org.example.task2.MusicPlayer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class MusicPlayerTest {

  private MusicPlayer player;

  @BeforeEach
  void setUp() {
    player = new MusicPlayer(List.of("Track 1", "Track 2", "Track 3"));
  }

  @Test
  void testPlayOrPause() {
    assertEquals("Playing: Track 1", captureOutput(player::playOrPause));
    assertEquals("Pausing: Track 1", captureOutput(player::playOrPause));
  }

  @Test
  void testStop() {
    assertEquals("Already stopped", captureOutput(player::stop));
    player.playOrPause();
    assertEquals("Stopping: Track 1", captureOutput(player::stop));
    assertEquals("Already stopped", captureOutput(player::stop));
  }

  @Test
  void testNext() {
    assertEquals("Playing next track: Track 2", captureOutput(player::next));
    assertEquals("Playing next track: Track 3", captureOutput(player::next));
    assertEquals("Already on the last track", captureOutput(player::next));
    player.repeatOnOrOff();
    assertEquals("Replaying first track: Track 1", captureOutput(player::next));
  }

  @Test
  void testPrevious() {
    assertEquals("Already on the first track", captureOutput(player::previous));
    player.next();
    assertEquals("Playing previous track: Track 1", captureOutput(player::previous));
  }

  @Test
  void testRepeatOnOrOff() {
    assertEquals("Repeat on", captureOutput(player::repeatOnOrOff));
    assertEquals("Repeat off", captureOutput(player::repeatOnOrOff));
  }

  // Helper method to capture system output
  private String captureOutput(Runnable action) {
    java.io.ByteArrayOutputStream out = new java.io.ByteArrayOutputStream();
    System.setOut(new java.io.PrintStream(out));
    action.run();
    System.setOut(System.out);
    return out.toString().trim();
  }
}
