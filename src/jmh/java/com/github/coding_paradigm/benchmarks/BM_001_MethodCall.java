package com.github.coding_paradigm.benchmarks;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

@State(Scope.Thread)
@BenchmarkMode(Mode.AverageTime)
@Fork(1)
@Warmup(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
public class BM_001_MethodCall {

    public static class TestClass {

        static final TestClass INSTANCE = new TestClass();

        static final Method STATIC_METHOD, METHOD;
        static final MethodHandle STATIC_METHOD_HANDLE, METHOD_HANDLE;

        static {
            try {
                STATIC_METHOD = TestClass.class.getMethod("staticMethod");
                STATIC_METHOD_HANDLE = MethodHandles.lookup().unreflect(STATIC_METHOD);
                METHOD = TestClass.class.getMethod("method");
                METHOD_HANDLE = MethodHandles.lookup().unreflect(METHOD);
            } catch (ReflectiveOperationException e) {
                throw new RuntimeException(e);
            }
        }

        public static int staticMethod() {
            return 42;
        }

        public int method() {
            return 42;
        }

    }

    @Benchmark
    @OperationsPerInvocation(1000)
    public void staticMethod(Blackhole bh) {
        bh.consume(TestClass.staticMethod());
    }

    @Benchmark
    @OperationsPerInvocation(1000)
    public void staticMethodReflection(Blackhole bh) throws InvocationTargetException, IllegalAccessException {
        bh.consume(TestClass.STATIC_METHOD.invoke(null));
    }

    @Benchmark
    @OperationsPerInvocation(1000)
    public void staticMethodMH(Blackhole bh) throws Throwable {
        bh.consume(TestClass.STATIC_METHOD_HANDLE.invoke());
    }

    @Benchmark
    @OperationsPerInvocation(1000)
    public void staticMethodMHExact(Blackhole bh) throws Throwable {
        bh.consume((int) TestClass.STATIC_METHOD_HANDLE.invokeExact());
    }

    @Benchmark
    @OperationsPerInvocation(1000)
    public void method(Blackhole bh) {
        bh.consume(TestClass.INSTANCE.method());
    }

    @Benchmark
    @OperationsPerInvocation(1000)
    public void methodReflection(Blackhole bh) throws InvocationTargetException, IllegalAccessException {
        bh.consume(TestClass.METHOD.invoke(TestClass.INSTANCE));
    }

    @Benchmark
    @OperationsPerInvocation(1000)
    public void methodMH(Blackhole bh) throws Throwable {
        bh.consume(TestClass.METHOD_HANDLE.invoke(TestClass.INSTANCE));
    }

    @Benchmark
    @OperationsPerInvocation(1000)
    public void methodMHExact(Blackhole bh) throws Throwable {
        bh.consume((int) TestClass.METHOD_HANDLE.invokeExact(TestClass.INSTANCE));
    }

}
