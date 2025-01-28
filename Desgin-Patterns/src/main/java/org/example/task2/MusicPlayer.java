package org.example.task2;

import java.util.List;

public class MusicPlayer implements Player {
  private final List<String> tracks;
  private int currentTrackIndex;
  private boolean isPlaying;
  private boolean isRepeatOn;

  public MusicPlayer(List<String> tracks) {
    if (tracks == null || tracks.isEmpty()) {
      throw new IllegalArgumentException("Track list cannot be null or empty.");
    }
    this.tracks = tracks;
    this.currentTrackIndex = 0;
    this.isPlaying = false;
    this.isRepeatOn = false;
  }

  @Override
  public void playOrPause() {
    if (isPlaying) {
      System.out.println("Pausing: " + tracks.get(currentTrackIndex));
      isPlaying = false;
    } else {
      System.out.println("Playing: " + tracks.get(currentTrackIndex));
      isPlaying = true;
    }
  }

  @Override
  public void stop() {
    if (isPlaying) {
      System.out.println("Stopping: " + tracks.get(currentTrackIndex));
      isPlaying = false;
      currentTrackIndex = 0;
    } else {
      System.out.println("Already stopped");
    }
  }

  @Override
  public void next() {
    if (currentTrackIndex < tracks.size() - 1) {
      currentTrackIndex++;
      System.out.println("Playing next track: " + tracks.get(currentTrackIndex));
      isPlaying = true;
    } else if (isRepeatOn) {
      currentTrackIndex = 0;
      System.out.println("Replaying first track: " + tracks.get(currentTrackIndex));
      isPlaying = true;
    } else {
      System.out.println("Already on the last track");
    }
  }

  @Override
  public void previous() {
    if (currentTrackIndex > 0) {
      currentTrackIndex--;
      System.out.println("Playing previous track: " + tracks.get(currentTrackIndex));
      isPlaying = true;
    } else {
      System.out.println("Already on the first track");
    }
  }

  @Override
  public void repeatOnOrOff() {
    isRepeatOn = !isRepeatOn;
    System.out.println(isRepeatOn ? "Repeat on" : "Repeat off");
  }

  public static void main(String[] args) {
    List<String> trackList = List.of("Track 1", "Track 2", "Track 3");
    MusicPlayer player = new MusicPlayer(trackList);

    player.playOrPause(); // Playing: Track 1
    player.next();        // Playing next track: Track 2
    player.next();        // Playing next track: Track 3
    player.next();        // Already on the last track
    player.repeatOnOrOff(); // Repeat on
    player.next();        // Replaying first track: Track 1
    player.previous();    // Already on the first track
    player.stop();        // Stopping: Track 1
    player.stop();        // Already stopped
  }
}
