package com.github.coding_paradigm.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Test_003_DataOverflow {

    @Test
    public void test() {
        int number = Integer.MAX_VALUE;
        Assertions.assertEquals(2147483647, number);// 2147483647
        Assertions.assertEquals(-2147483648, number + 1); // -2147483648
        Assertions.assertEquals(-2, number + number);
    }

}
