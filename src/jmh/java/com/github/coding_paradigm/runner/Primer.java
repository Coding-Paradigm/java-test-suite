package com.github.coding_paradigm.runner;

import java.io.File;
import java.util.concurrent.TimeUnit;
import org.openjdk.jmh.results.format.ResultFormatType;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.OptionsBuilder;

public class Primer {

  public static void main(String[] args) throws RunnerException {
    if (args.length == 0) {
      throw new IllegalStateException("No benchmark classes provided.");
    }
    new File("build/reports/benchmark").mkdirs();
    var options =
        new OptionsBuilder()
            .include(".*" + args[0] + ".*")
            .timeUnit(TimeUnit.NANOSECONDS)
            .result("build/reports/benchmark/" + args[0] + ".json")
            .resultFormat(ResultFormatType.JSON)
            .build();
    new Runner(options).run();
  }
}
