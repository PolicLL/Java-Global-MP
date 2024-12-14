// Write your answer here, and then test your code.
// Your job is to implement the transformValues() method.

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

class Answer4
{

  // Change these boolean values to control whether you see
  // the expected answer and/or hints.
  static boolean showExpectedResult = false;
  static boolean showHints = false;

  // Transform an array of numbers into a comma-delimited list
  // using functional programming.
  static String transformValues(int[] numbers) {

    List<String> values = List.of("213", " 21");

    System.out.println(String.join(",", values.toArray(new String[0])));
    System.out.println(String.join(",", values.toArray(new String[0])));

    AtomicReference<String> result = new AtomicReference<>(String.valueOf(numbers[0]));

    Arrays.stream(numbers).skip(1).forEach(
        value -> result.updateAndGet(current -> current + ", " + value)
    );

    return result.get();
  }

}
