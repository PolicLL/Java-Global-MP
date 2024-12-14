import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;

class Answer1 {

  // Change these boolean values to control whether you see
  // the expected answer and/or hints
  static boolean showExpectedResult = false;
  static boolean showHints = false;

  // Create constants representing the four available math functions
  public static final String ADD = "ADD";
  public static final String SUBTRACT = "SUBTRACT";
  public static final String MULTIPLY = "MULTIPLY";
  public static final String DIVIDE = "DIVIDE";

  // Do mathematical calculations using lambda expressions
  public static Map<String, Float> calculate(float value1, float value2) {

    // Your code goes here
    // Create 4 instances of the BiFunction interface referencing lambda expressions

    List<BiFunction<Float, Float, Float>> functions = List.of(
        Float::sum, (a, b) -> a - b, (a, b) -> a * b, (a, b) -> a / b
    );


    // Map object for holding the results
    Map<String, Float> results = new HashMap<>();
    results.put(ADD, functions.get(0).apply(value1, value2));
    results.put(SUBTRACT, functions.get(1).apply(value1, value2));
    results.put(MULTIPLY, functions.get(2).apply(value1, value2));
    results.put(DIVIDE, functions.get(3).apply(value1, value2));

    // Populate the map here with the results of the 4 math operations

    return results;
  }

}
