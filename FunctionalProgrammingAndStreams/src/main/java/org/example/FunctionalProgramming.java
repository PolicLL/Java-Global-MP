package org.example;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collector;
import java.util.stream.Collectors;
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
    return props.flatMap(value -> Stream.of(
        value.id() != null ? String.valueOf(value.id()) : null,
        value.name() != null && !value.name().trim().isEmpty() ? value.name() : null,
        String.valueOf(value.value())
    ).filter(Objects::nonNull));
  }

  public static List<Prop> sortProps(Stream<Prop> props) {
    return props
        .sorted(Comparator.comparingInt(Prop::value)
            .thenComparing(Prop::name))
        .toList();
  }


  public static List<Prop> filterNonNullName(Stream<Prop> props) {
    return props.filter(value -> value.name() != null).toList();
  }

  public static List<Prop> filterUniqueIds(Stream<Prop> props) {
    return props
        .collect(Collectors.toMap(Prop::id, prop -> prop, (existing, replacement) -> existing))
        .values()
        .stream()
        .toList();
  }

  public static Optional<Prop> highestValueName(Stream<Prop> props) {
    return props.max(Comparator.comparing(Prop::value));
  }

  public static Map<String, List<UUID>> nameConflicts(Stream<Prop> props) {
    return props.collect(Collectors.groupingBy(
        Prop::name,
        Collectors.mapping(Prop::id, Collectors.toList())
    ))
        .entrySet()
        .stream()
        .filter(entity -> entity.getValue().size() > 1)
        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
  }


  public static Map<String, Integer> statefulCollectors(Stream<Prop> props) {
    return props.collect(Collectors.groupingBy(
        Prop::name, Collectors.summingInt(Prop::value)
    ));
  }

  public static Map<String, Integer> collectorChaining(Stream<Integer> numbers) {
    return numbers.collect(Collectors.groupingBy(
        n -> (n % 2 == 0) ? "even" : "odd",
        Collectors.summingInt(Integer::intValue)
    ));
  }

  public static Map<String, String> customAggregation(Stream<Prop> props) {
    return props.collect(Collectors.teeing(
        Collectors.maxBy(Comparator.comparing(Prop::value)),
        Collectors.minBy(Comparator.comparing(Prop::value)),
        (max, min) -> Map.of(
            "max", Objects.requireNonNull(max.map(Prop::name).orElse(null)),
            "min", Objects.requireNonNull(min.map(Prop::name).orElse(null))
        )
    ));
  }

  public static <T> Function<T, T> fold(Stream<Function<T, T>> functions) {
    return functions.reduce(Function.identity(), Function::andThen);
  }


  public static <T, RT, AT, RF, AF, R> Collector<T, ?, R> partitioningCollector(
      Predicate<? super T> predicate,
      Collector<? super T, AT, RT> collTrue,
      Collector<? super T, AF, RF> collFalse,
      BiFunction<RT, RF, R> constructor) {

    return Collector.of(
        // initialize empty accumulators
        () -> new Pair<>(
            collTrue.supplier().get(),
            collFalse.supplier().get()
        ),
        // check every element using predicate (in this case checking nullability)
        (pair, element) -> {
          if (predicate.test(element)) {
            collTrue.accumulator().accept(pair.first, element);
          } else {
            collFalse.accumulator().accept(pair.second, element);
          }
        },
        (pair1, pair2) -> new Pair<>(
            collTrue.combiner().apply(pair1.first, pair2.first),
            collFalse.combiner().apply(pair1.second, pair2.second)
        ),
        pair -> constructor.apply(
            collTrue.finisher().apply(pair.first),
            collFalse.finisher().apply(pair.second)
        )
    );
  }
    record Pair<A, B>(A first, B second) {

  }

  public static Map<String, Integer> sumAndCountNulls(Stream<Integer> stream) {
    return stream.collect(partitioningCollector(
        Objects::nonNull,
        Collectors.summingInt(Integer::intValue),
        Collectors.counting(),
        (sum, count) -> Map.of("sum", sum, "nullCount", count.intValue())
    ));
  }


}
