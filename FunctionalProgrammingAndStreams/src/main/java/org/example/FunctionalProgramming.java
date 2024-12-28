package org.example;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import java.util.stream.Collector;
import java.util.stream.Stream;

public class FunctionalProgramming {

  public static Stream<Prop> generate(int n) {
    AtomicInteger value = new AtomicInteger();
    return Stream.generate(() -> new Prop(UUID.randomUUID(), "", value.getAndIncrement())).limit(n);
  }

  public static Stream<UUID> toIds(List<List<Prop>> propListList) {
    return propListList.stream().flatMap(propList -> propList.stream().map(Prop::id));
  }

  public static int countEvens(Stream<Integer> numbers) {
    return (int) numbers.filter(value -> value % 2 == 0).count();
  }

  public static Stream<Character> firstWord(Stream<Character> characters) {
    return characters.dropWhile(Character::isWhitespace)
        .takeWhile(c -> !Character.isWhitespace(c));
  }

  public static Stream<String> nonNullProperties(Stream<Prop> props) {
    return null;
  }

  public static List<Prop> sortProps(Stream<Prop> props) {
    return null;
  }

  public static List<Prop> filterNonNullName(Stream<Prop> props) {
    return null;
  }

  public static List<Prop> filterUniqueIds(Stream<Prop> props) {
    return null;
  }

  public static String highestValueName(Stream<Prop> props) {
    return null;
  }

  public static Map<String, List<UUID>> nameConflicts(Stream<Prop> props) {
    return null;
  }


  public static Map<String, Integer> statefulCollectors(Stream<Prop> props) {
    return null;
  }

  public static Map<String, Integer> collectorChaining(Stream<Integer> numbers) {
    return null;
  }

  public static Map<String, String> customAggregation(Stream<Prop> props) {
    return null;
  }

  public static <T> Function<T, T> fold(Stream<Function<T, T>> functions) {
    return null;
  }

  public static <T, RT, AT, RF, AF, R> Collector<T, ?, R> partitioningCollector(
      Predicate<? super T> predicate,
      Collector<? super T, AT, RT> collTrue,
      Collector<? super T, AF, RF> collFalse,
      BiFunction<RT, RF, R> constructor
  ) {
    return null;
  }

  public static Map<String, Integer> advancedCollectors(Stream<Integer> numbers) {
    return null;
  }

}
