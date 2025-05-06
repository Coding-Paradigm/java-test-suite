package com.github.coding_paradigm.benchmarks;

import java.lang.invoke.MethodHandles;
import java.lang.invoke.VarHandle;
import java.lang.reflect.Field;
import java.util.concurrent.TimeUnit;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

@State(Scope.Thread)
@BenchmarkMode(Mode.AverageTime)
@Fork(1)
@Warmup(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
public class BM_002_SetField {

  public static class TestClass {

    static final TestClass INSTANCE = new TestClass();

    static final Field STATIC_FIELD, FIELD;
    static final VarHandle STATIC_FIELD_HANDLE, FIELD_HANDLE;

    static {
      try {
        STATIC_FIELD = TestClass.class.getField("staticField");
        STATIC_FIELD_HANDLE = MethodHandles.lookup().unreflectVarHandle(STATIC_FIELD);
        FIELD = TestClass.class.getField("field");
        FIELD_HANDLE = MethodHandles.lookup().unreflectVarHandle(FIELD);
      } catch (ReflectiveOperationException e) {
        throw new RuntimeException(e);
      }
    }

    public static int staticField = 0;
    public int field = 0;
  }

  @Benchmark
  @OperationsPerInvocation(1000)
  public void staticField(Blackhole bh) {
    TestClass.staticField = 42;
  }

  @Benchmark
  @OperationsPerInvocation(1000)
  public void staticFieldReflection() throws IllegalAccessException {
    TestClass.STATIC_FIELD.setInt(null, 42);
  }

  @Benchmark
  @OperationsPerInvocation(1000)
  public void staticFieldVH() {
    TestClass.STATIC_FIELD_HANDLE.set((int) 42);
  }

  @Benchmark
  @OperationsPerInvocation(1000)
  public void field() {
    TestClass.INSTANCE.field = 42;
  }

  @Benchmark
  @OperationsPerInvocation(1000)
  public void fieldReflection() throws IllegalAccessException {
    TestClass.FIELD.setInt(TestClass.INSTANCE, 42);
  }

  @Benchmark
  @OperationsPerInvocation(1000)
  public void fieldVH() {
    TestClass.FIELD_HANDLE.set(TestClass.INSTANCE, 42);
  }
}
