package com.github.coding_paradigm.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Test_001_DynamicClassFromStaticMethod {

    public static class Base {

        static Class<?> staticGetClass() {
            // Unable to use this.getClass() in a static context
            return new Object(){ }.getClass().getEnclosingClass();
        }
        
        void log(String message) {
            System.out.println(staticGetClass() + ": " + message);
        }
        
    }
    
    public static class Extended extends Base {
        
    }

    @Test
    public void test() {
        Assertions.assertSame(Base.class, Base.staticGetClass());
        new Base().log("Hello World");
        new Extended().log("Hello World");
    }

}
