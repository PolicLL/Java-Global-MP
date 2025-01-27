package utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class TestUtils {

  public static void clearFileContent(String filePath) {
    try (FileWriter writer = new FileWriter(new File(filePath), false)) {
      writer.write(""); // Writing an empty string to the file
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

}
