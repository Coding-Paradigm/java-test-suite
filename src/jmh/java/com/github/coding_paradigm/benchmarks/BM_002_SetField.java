package com.github.coding_paradigm.benchmarks;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.lang.invoke.MethodHandles;
import java.lang.invoke.VarHandle;
import java.lang.reflect.Field;
import java.util.concurrent.TimeUnit;

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

        public static int staticField = 42;
        public int field = 42;

    }

    @Benchmark
    @OperationsPerInvocation(1000)
    public void staticField(Blackhole bh) {
        bh.consume(TestClass.staticField);
    }

    @Benchmark
    @OperationsPerInvocation(1000)
    public void staticFieldReflection(Blackhole bh) throws IllegalAccessException {
        bh.consume((int) TestClass.STATIC_FIELD.get(null));
    }

    @Benchmark
    @OperationsPerInvocation(1000)
    public void staticFieldVH(Blackhole bh) {
        bh.consume((int) TestClass.STATIC_FIELD_HANDLE.get());
    }

    @Benchmark
    @OperationsPerInvocation(1000)
    public void field(Blackhole bh) {
        bh.consume(TestClass.INSTANCE.field);
    }

    @Benchmark
    @OperationsPerInvocation(1000)
    public void fieldReflection(Blackhole bh) throws IllegalAccessException {
        bh.consume((int) TestClass.FIELD.get(TestClass.INSTANCE));
    }

    @Benchmark
    @OperationsPerInvocation(1000)
    public void fieldVH(Blackhole bh) {
        bh.consume((int) TestClass.FIELD_HANDLE.get(TestClass.INSTANCE));
    }

}
