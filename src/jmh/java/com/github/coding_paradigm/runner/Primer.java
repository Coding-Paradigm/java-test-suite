package com.github.coding_paradigm.runner;

import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

public class Primer {

    public static void main(String[] args) throws RunnerException {
        if (args.length == 0) {
            throw new IllegalStateException("No benchmark classes provided.");
        }
        var options = new OptionsBuilder()
                .include(".*" + args[0] + ".*")
                .timeUnit(TimeUnit.NANOSECONDS)
                .build();
        new Runner(options).run();
    }

}
