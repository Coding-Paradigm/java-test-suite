package com.github.coding_paradigm.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class T_002_StringComparison {

    @Test
    public void test() {
        String str1 = "Hello"; // Interned String
        String str2 = "Hello"; // Also interned String
        String str3 = new String("Hello"); // Not an interned String
        String str4 = str3.intern(); // Once again an interned String

        Assertions.assertSame(str1, str2);
        Assertions.assertNotSame(str1, str3);
        Assertions.assertSame(str1, str4);

        Assertions.assertEquals(str1, str2);
        Assertions.assertEquals(str1, str3);
        Assertions.assertEquals(str1, str4);
    }

}
