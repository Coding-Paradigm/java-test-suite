package com.github.coding_paradigm.test;

import java.io.IOException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class T_001_CafeBabe {

  @Test
  public void test() throws IOException {
    Class clazz = this.getClass();
    String className = clazz.getName();
    String classAsPath = className.replace('.', '/') + ".class";
    try (var stream = clazz.getClassLoader().getResourceAsStream(classAsPath)) {
      var builder = new StringBuilder();
      for (byte b : stream.readNBytes(4)) {
        builder.append(String.format("%02X", b));
      }
      Assertions.assertEquals("CAFEBABE", builder.toString());
    }
  }
}
