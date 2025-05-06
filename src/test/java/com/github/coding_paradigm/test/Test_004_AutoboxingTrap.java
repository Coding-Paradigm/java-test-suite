package com.github.coding_paradigm.test;

import org.junit.jupiter.api.Test;

public class Test_004_SneakyThrow {

    public static class TestClass {

        public static <E extends Throwable> void sneakyThrow(Throwable e) throws E {
            throw (E) e;
        }

        private static void throwSneakyException() {
            sneakyThrow(new Exception("SneakyThrow"));
        }

    }

    @Test
    public void test() {

    }

}
