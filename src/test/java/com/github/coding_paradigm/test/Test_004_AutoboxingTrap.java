package com.github.coding_paradigm.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Test_004_AutoboxingTrap {

    @Test
    public void test() {
        Integer a = 100;
        Integer b = 100;
        Assertions.assertSame(a, b);

        Integer x = 1000;
        Integer y = 1000;
        Assertions.assertNotSame(x, y);
    }

}
