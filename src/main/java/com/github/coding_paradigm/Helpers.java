package com.github.coding_paradigm;

import sun.misc.Unsafe;

public class Helpers {

    private static Unsafe unsafe;

    public static Unsafe getUnsafe() {
        if (unsafe == null) {
            try {
                unsafe = Unsafe.getUnsafe();
            } catch (SecurityException e) {
                try {
                    unsafe = (Unsafe) Class.forName("sun.misc.Unsafe").getDeclaredField("theUnsafe").get(null);
                } catch (Exception ex) {
                    throw new RuntimeException("Unable to access Unsafe", ex);
                }
            }
        }
        return unsafe;
    }

}
