package reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Boxing {
    public static Class[] classes = {Byte.class, Short.class, Integer.class, Long.class, Float.class, Double.class, Character.class, Boolean.class};
    public static Class[] primitives = {byte.class, short.class, int.class, long.class, float.class, double.class, char.class, boolean.class};

    public static Object box(Object o) {
        return isWrapper(o.getClass()) ? o : null;
    }

    public static byte unbox(Byte o) {
        return o.byteValue();
    }

    public static short unbox(Short o) {
        return o.shortValue();
    }

    public static int unbox(Integer o) {
        return o.intValue();
    }

    public static long unbox(Long o) {
        return o.longValue();
    }

    public static float unbox(Float o) {
        return o.floatValue();
    }

    public static double unbox(Double o) {
        return o.doubleValue();
    }

    public static char unbox(Character o) {
        return o.charValue();
    }

    public static boolean unbox(Boolean o) {
        return o.booleanValue();
    }

    public static boolean isWrapper(Class clazz) {
        return Arrays.stream(classes).toList().contains(clazz);
    }

    public static Class getWrapperType(Object o) {

        for(Class c : classes) {
            if(o.getClass() == c) {
                return c;
            }
        }
        return null;
    }

    public static Class getPrimitiveType(Object o) {

        for(int i = 0;i < 8; i++) {
            if(o.getClass() == classes[i]) {
                return primitives[i];
            }
        }
        return null;
    }

    public static Class wrapperToPrimitive(Object o) {

        for(int i = 0;i < 8; i++) {
            if(o.getClass() == classes[i]) {
                return primitives[i];
            }
        }
        return null;
    }

    public static Class PrimitiveToWrapper(Object o) {

        if(isWrapper(o.getClass())) {
            return o.getClass();
        }
        return null;
    }

    public static Constructor getWrappersConstructors(Object o) {
        try {
            if(isWrapper(o.getClass())) {
                return o.getClass().getConstructor(wrapperToPrimitive(o));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
