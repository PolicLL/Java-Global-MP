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

  @Test
  void testNonNullProperties() {
    Stream<Prop> props = Stream.of(new Prop(UUID.randomUUID(), "", 10),
        new Prop(null, "Banana",0), new Prop(UUID.randomUUID(),"Apple", 30));
    Stream<String> nonNullProperties = nonNullProperties(props);
    List<String> properties = nonNullProperties.toList();
    System.out.println(properties);
    assertEquals(7, properties.size(), "There should be seven non-null properties.");
    assertTrue(properties.contains("Apple") && properties.contains("Banana"), "Properties should include 'Apple' and 'Banana'.");
  }

  @Test
  void testSortProps() {
    Prop p1 = new Prop(UUID.randomUUID(), "Ana",30);
    Prop p2 = new Prop(UUID.randomUUID(), "Bena", 30);
    Prop p3 = new Prop(UUID.randomUUID(), "Tina", 20);
    Stream<Prop> props = Stream.of(p1, p2, p3);
    List<Prop> sortedList = sortProps(props);
    System.out.println(sortedList);
    assertNotNull(sortedList, "Sorted list should not be null.");
    assertEquals(p3.value(), sortedList.get(0).value());
    assertEquals(p1.value(), sortedList.get(1).value());
    assertEquals(p2.value(), sortedList.get(2).value());
  }

  @Test
  void testFilterNonNullName() {
    Prop p1 = new Prop(UUID.randomUUID(), "A",10);
    Prop p2 = new Prop(UUID.randomUUID(), null,20);
    Prop p3 = new Prop(UUID.randomUUID(), "C", 30);
    Stream<Prop> props = Stream.of(p1, p2, p3);
    List<Prop> filteredList = filterNonNullName(props);
    assertNotNull(filteredList, "Filtered list should not be null.");
    assertEquals(2, filteredList.size(), "There should be two Props with non-null names.");
    assertTrue(filteredList.stream().anyMatch(prop -> 10 == prop.value()));
    assertTrue(filteredList.stream().anyMatch(prop -> 30 == prop.value()));
  }

  @Test
  void testUniqueIds() {
    UUID id = UUID.randomUUID();
    Prop p1 = new Prop(id, "A",10);
    Prop p2 = new Prop(UUID.randomUUID(), null,20);
    Prop p3 = new Prop(id, "C", 30);
    Stream<Prop> props = Stream.of(p1, p2, p3);
    List<Prop> filtered = filterUniqueIds(props);
    System.out.println(filtered);
    assertEquals(2, filtered.size(), "Only two unique IDs should be present.");
    assertTrue(filtered.contains(p1));
    assertTrue(filtered.contains(p2));
  }

  @Test
  void testHighestValueName() {
    Prop p1 = new Prop(UUID.randomUUID(), "A",10);
    Prop p2 = new Prop(UUID.randomUUID(), null,20);
    Prop p3 = new Prop(UUID.randomUUID(), "C", 30);
    Stream<Prop> props = Stream.of(p1, p2, p3);
    Prop maxProp = highestValueName(props).get();
    assertEquals(p3.name(), maxProp.name());
  }

  @Test
  void testNameConflicts() {
    Prop p1 = new Prop(UUID.randomUUID(), "Name1", 10);
    Prop p2 = new Prop(UUID.randomUUID(),  "Name2",20);
    Prop p3 = new Prop(UUID.randomUUID(),  "Name1", 30);
    Prop p4 = new Prop(UUID.randomUUID(),  "Name3", 30);
    Prop p5 = new Prop(UUID.randomUUID(),  "Name3", 30);
    Prop p6 = new Prop(UUID.randomUUID(),  "Name4", 30);
    Stream<Prop> props = Stream.of(p1, p2, p3, p4, p5, p6);
    Map<String, List<UUID>> conflicts = nameConflicts(props);
    assertEquals(2, conflicts.size());
    assertTrue(conflicts.containsKey(p1.name()));
    assertEquals(2, conflicts.get(p1.name()).size());
  }

  @Test
  void testStatefulCollectors() {
    Prop p1 = new Prop(UUID.randomUUID(), "Name1", 10);
    Prop p2 = new Prop(UUID.randomUUID(),  "Name2",20);
    Prop p3 = new Prop(UUID.randomUUID(),  "Name1", 30);
    Prop p4 = new Prop(UUID.randomUUID(),  "Name3", 30);
    Prop p5 = new Prop(UUID.randomUUID(),  "Name3", 30);
    Prop p6 = new Prop(UUID.randomUUID(),  "Name4", 30);
    Map<String, Integer> resultMap = statefulCollectors(Stream.of(p1, p2, p3, p4, p5, p6));
    assertEquals(4, resultMap.size());
    assertTrue(resultMap.get(p1.name()) == 40 && resultMap.get(p6.name()) == 30);
  }

  @Test
  void testCollectorChaining() {
    Stream<Integer> numbers = Stream.of(1, 2, 3, 4, 5, 6, 7);
    Map<String, Integer> resultMap = collectorChaining(numbers);
    assertEquals(2, resultMap.size());
    assertTrue(resultMap.get("odd") == 16 && resultMap.get("even") == 12);
  }
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
