import static org.example.FunctionalProgramming.advancedCollectors;
import static org.example.FunctionalProgramming.collectorChaining;
import static org.example.FunctionalProgramming.countEvens;
import static org.example.FunctionalProgramming.customAggregation;
import static org.example.FunctionalProgramming.filterNonNullName;
import static org.example.FunctionalProgramming.filterUniqueIds;
import static org.example.FunctionalProgramming.firstWord;
import static org.example.FunctionalProgramming.fold;
import static org.example.FunctionalProgramming.generate;
import static org.example.FunctionalProgramming.highestValueName;
import static org.example.FunctionalProgramming.nameConflicts;
import static org.example.FunctionalProgramming.nonNullProperties;
import static org.example.FunctionalProgramming.sortProps;
import static org.example.FunctionalProgramming.statefulCollectors;
import static org.example.FunctionalProgramming.toIds;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.example.Prop;
import org.junit.jupiter.api.Test;

public class FunctionalProgrammingTest {

  @Test
  void testGenerate() {
    int n = 5;

    Stream<Prop> resultStream = generate(n);
    assertEquals(n, resultStream.count(), "The stream should contain exactly " + n + " elements.");

    resultStream = generate(n);
    Set<UUID> ids = resultStream.map(Prop::id).collect(Collectors.toSet());
    assertEquals(n, ids.size(), "All ids should be unique.");

    resultStream = generate(n);
    List<Integer> values = resultStream.map(Prop::value).toList();
    for (int i = 0; i < n; i++) {
      assertEquals(i, (int) values.get(i), "Values should be incremental starting from 0.");
    }
  }

  @Test
  void testToIds() {
    List<List<Prop>> propListList = Arrays.asList(
        Arrays.asList(new Prop(UUID.randomUUID(), "", 0), new Prop(UUID.randomUUID(), "",1)),
        Arrays.asList(new Prop(UUID.randomUUID(), "", 2), new Prop(UUID.randomUUID(), "",3))
    );
    Stream<UUID> uuidStream = toIds(propListList);

    List<UUID> uuidList = uuidStream.toList();
    System.out.println(uuidList);
    assertEquals(4, uuidList.size(), "The stream should have all IDs from all nested lists.");
  }

  @Test
  void testCountEvens() {
    Stream<Integer> numbers = Stream.of(1, 2, 3, 4, 5, 6);
    int evens = countEvens(numbers);
    assertEquals(3, evens, "There should be three even numbers.");
  }

  @Test
  void testFirstWord() {
    Stream<Character> characters = Stream.of(' ', 'H', 'e', 'l', 'l', 'o', ' ', 'W', 'o', 'r', 'l', 'd');
    Stream<Character> firstWord = firstWord(characters);
    String word = firstWord.map(String::valueOf).collect(Collectors.joining());
    assertEquals("Hello", word, "The first word should be 'Hello'.");
  }
//
//  @Test
//  void testNonNullProperties() {
//    Stream<Prop> props = Stream.of(new Prop(UUID.randomUUID(), 10),
//        new Prop(UUID.randomUUID(), 20), new Prop(UUID.randomUUID(), 30));
//    Stream<String> nonNullProperties = nonNullProperties(props);
//    assert nonNullProperties != null;
//    List<String> properties = nonNullProperties.toList();
//    assertEquals(2, properties.size(), "There should be two non-null properties.");
//    assertTrue(properties.contains("Apple") && properties.contains("Banana"), "Properties should include 'Apple' and 'Banana'.");
//  }
//
//  @Test
//  void testSortProps() {
//    Prop p1 = new Prop(UUID.randomUUID(), 30);
//    Prop p2 = new Prop(UUID.randomUUID(), 10);
//    Prop p3 = new Prop(UUID.randomUUID(), 20);
//    Stream<Prop> props = Stream.of(p1, p2, p3);
//    List<Prop> sortedList = sortProps(props);
//    assertNotNull(sortedList, "Sorted list should not be null.");
//    assertEquals(p2.value(), sortedList.get(0).value(), "Apple should come first based on assumed sorting criteria.");
//    assertEquals(p3.value(), sortedList.get(1).value(), "Banana should come second.");
//    assertEquals(p1.value(), sortedList.get(2).value(), "Orange should come last.");
//  }
//
//  @Test
//  void testFilterNonNullName() {
//    Prop p1 = new Prop(UUID.randomUUID(), 10);
//    Prop p2 = new Prop(UUID.randomUUID(), 20);
//    Prop p3 = new Prop(UUID.randomUUID(), 30);
//    Stream<Prop> props = Stream.of(p1, p2, p3);
//    List<Prop> filteredList = filterNonNullName(props);
//    assertNotNull(filteredList, "Filtered list should not be null.");
//    assertEquals(2, filteredList.size(), "There should be two Props with non-null names.");
//    assertTrue(filteredList.stream().anyMatch(prop -> 10 == prop.value()));
//    assertTrue(filteredList.stream().anyMatch(prop -> 20 == prop.value()));
//  }
//
//  @Test
//  void testUniqueIds() {
//    UUID id = UUID.randomUUID();
//    Prop p1 = new Prop(id, 10);
//    Prop p2 = new Prop(id, 20);
//    Prop p3 = new Prop(UUID.randomUUID(), 30);
//    Stream<Prop> props = Stream.of(p1, p2, p3);
//    List<Prop> filtered = filterUniqueIds(props);
//    assert filtered != null;
//    assertEquals(2, filtered.size(), "Only two unique IDs should be present.");
//  }
//
//  @Test
//  void testHighestValueName() {
//    Prop p1 = new Prop(UUID.randomUUID(), 10);
//    Prop p2 = new Prop(UUID.randomUUID(), 40);
//    Stream<Prop> props = Stream.of(p1, p2);
//    String name = highestValueName(props);
//    assertEquals("B", name, "The name of the highest value prop should be B.");
//  }
//
//  @Test
//  void testNameConflicts() {
//    Prop p1 = new Prop(UUID.randomUUID(), 10);
//    Prop p2 = new Prop(UUID.randomUUID(),  20);
//    Prop p3 = new Prop(UUID.randomUUID(),  30);
//    Stream<Prop> props = Stream.of(p1, p2, p3);
//    Map<String, List<UUID>> conflicts = nameConflicts(props);
//    assert conflicts != null;
//    assertEquals(1, conflicts.size(), "There should be one name conflict.");
//    assertTrue(conflicts.containsKey("A"), "The conflict should be on the name A.");
//    assertEquals(2, conflicts.get("A").size(), "There should be two IDs under name A.");
//  }
//
//  @Test
//  void testStatefulCollectors() {
//    Stream<Prop> props = Stream.of(new Prop(UUID.randomUUID(), 10), new Prop(UUID.randomUUID(), 20), new Prop(UUID.randomUUID(), 30));
//    Map<String, Integer> resultMap = statefulCollectors(props);
//    assert resultMap != null;
//    assertEquals(2, resultMap.size());
//    assertTrue(resultMap.get("Apple") == 40 && resultMap.get("Banana") == 20);
//  }
//
//  @Test
//  void testCollectorChaining() {
//    Stream<Integer> numbers = Stream.of(1, 2, 3, 4, 5);
//    Map<String, Integer> resultMap = collectorChaining(numbers);
//    assert resultMap != null;
//    assertEquals(2, resultMap.size());
//    assertTrue(resultMap.get("odd") == 9 && resultMap.get("even") == 6);
//  }
//
//  @Test
//  void testCustomAggregation() {
//    Stream<Prop> props = Stream.of(new Prop(UUID.randomUUID(), 10),
//        new Prop(UUID.randomUUID(), 50), new Prop(UUID.randomUUID(), 20));
//    Map<String, String> resultMap = customAggregation(props);
//    assertEquals("Banana", resultMap.get("highest"));
//    assertEquals("Apple", resultMap.get("lowest"));
//  }
//
//  @Test
//  void testFold() {
//    Stream<Function<String, String>> functions = Stream.of(String::trim, s -> s.replace('b', 'c'));
//    Function<String, String> f = fold(functions);
//    assert f != null;
//    assertEquals("cat", f.apply("  bat"));
//  }
//
//  @Test
//  void testAdvancedCollectors() {
//    Stream<Integer> numbers = Stream.of(1, 2, null, 4, null, 6);
//    Map<String, Integer> result = advancedCollectors(numbers);
//    assert result != null;
//    assertEquals(2, result.size());
//    assertTrue(result.get("sum") == 13 && result.get("nullCount") == 2);
//  }


}
