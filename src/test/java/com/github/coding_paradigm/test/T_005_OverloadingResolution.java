package com.github.coding_paradigm.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class T_005_OverloadingResolution {

  public static class TestClass {

    public static int method(Object... args) {
      return 0;
    }

    public static int method(String arg, Object... args) {
      return 1;
    }
  }

  @Test
  public void test() {
    Assertions.assertSame(1, TestClass.method("test"));
    Assertions.assertSame(0, TestClass.method((Object) "test"));
  }
}
