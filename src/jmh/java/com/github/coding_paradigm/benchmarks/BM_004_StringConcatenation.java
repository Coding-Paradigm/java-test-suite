package com.github.coding_paradigm.benchmarks;

import java.util.concurrent.TimeUnit;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

@State(Scope.Thread)
@BenchmarkMode(Mode.AverageTime)
@Fork(1)
@Warmup(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
public class BM_004_StringConcatenation {

  @Param({"1", "10", "100"})
  private int length;

  private String[] strings;

  @Setup
  public void setup() {
    var strings = new String[length];
    for (int i = 0; i < length; i++) {
      strings[i] = "str" + i;
    }
    this.strings = strings;
  }

  @Benchmark
  public void plus(Blackhole bh) {
    var result = "";
    for (var str : this.strings) {
      result = result + str;
    }
    bh.consume(result);
  }

  @Benchmark
  public void concat(Blackhole bh) {
    var result = "";
    for (var str : this.strings) {
      result = result.concat(str);
    }
    bh.consume(result);
  }

  @Benchmark
  public void builder(Blackhole bh) {
    var sb = new StringBuilder();
    for (var str : this.strings) {
      sb.append(str);
    }
    bh.consume(sb.toString());
  }

  @Benchmark
  public void join(Blackhole bh) {
    bh.consume(String.join("", strings));
  }
}
