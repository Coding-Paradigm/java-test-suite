package com.github.coding_paradigm.benchmarks;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@State(Scope.Thread)
@BenchmarkMode(Mode.AverageTime)
@Fork(1)
@Warmup(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
public class BM_005_Contains {

    private static final List<String> WORDS = List.of(
            "apple", "banana", "orange", "grape", "kiwi", "mango", "peach", "pear",
            "pineapple", "strawberry", "watermelon", "blueberry", "raspberry",
            "blackberry", "cantaloupe", "honeydew", "papaya", "passionfruit",
            "pomegranate", "tangerine"
    );

    @Param({"1", "4", "8"})
    private int amount;

    private List<String> integersList;
    private Set<String> integersSet;

    @Setup
    public void setup() {
        List<String> copy = new ArrayList<>(WORDS);
        Collections.shuffle(copy);
        copy = copy.subList(0, amount);

        this.integersList = List.copyOf(copy);
        this.integersSet = Set.copyOf(copy);
    }

    @Benchmark
    public void listContains(Blackhole bh) {
        for (var word : WORDS) {
            bh.consume(this.integersList.contains(word));
        }
    }

    @Benchmark
    public void setContains(Blackhole bh) {
        for (var word : WORDS) {
            bh.consume(this.integersSet.contains(word));
        }
    }

}
