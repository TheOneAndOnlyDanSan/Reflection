import org.junit.jupiter.api.Test;
import reflection.Vars;
import vars.TestVar;
import vars.TestVar2;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import static org.junit.jupiter.api.Assertions.*;
import static reflection.FieldReflection.getField;
import static reflection.FieldReflection.getFieldValue;
import static reflection.MethodReflection.*;

public class MethodTest {

    @Test
    public void getPrivateMethodWithout() {
        TestVar testVar = new TestVar("");
        useMethod(getMethod(TestVar.class, "privateMethod", false), testVar);
        assertTrue(testVar.wasMethodUsed);
    }

    @Test
    public void getPrivateMethodWith() {
        TestVar testVar = new TestVar("");
        useMethod(getMethod(TestVar.class, "privateMethod", false, int.class), testVar, 1);
        assertTrue(testVar.wasMethodUsed);
    }

    @Test
    public void getPrivateStaticMethodWithout() {
        useMethod(getMethod(TestVar.class, "privateStaticMethod", false), null);
        assertTrue(TestVar.wasStaticMethodUsed);
    }

    @Test
    public void getPrivateStaticMethodWith() {
        useMethod(getMethod(TestVar.class, "privateStaticMethod", false, int.class), null, 1);
        assertTrue(TestVar.wasStaticMethodUsedWith);
    }

    @Test
    public void getPrivateMethodWithoutSuperclass() {
        TestVar2 fakeClass2 = new TestVar2();
        useMethod(getMethod(TestVar.class, "privateMethod", false), fakeClass2);
        assertTrue(fakeClass2.wasMethodUsed);
    }

    @Test
    public void getPrivateMethodWithSuperclass() {
        TestVar2 fakeClass2 = new TestVar2();
        useMethod(getMethod(TestVar.class, "privateMethod", false, int.class), fakeClass2, 1);
        assertTrue(fakeClass2.wasMethodUsed);
    }

    @Test
    public void getPrivateStaticMethodWithoutSuperclass() {
        useMethod(getMethod(TestVar2.class, "privateStaticMethodSuperclass", true), null);
        assertTrue(TestVar.wasStaticMethodUsedSuperclass);
    }

    @Test
    public void getPrivateStaticMethodWithSuperclass() {
        useMethod(getMethod(TestVar2.class, "privateStaticMethodSuperclass", true, int.class), null, 1);
        assertTrue(TestVar.wasStaticMethodUsedWithSuperclass);
    }

    @Test
    public void getUnusable() {
        if(Vars.javaVersion < 9) {
            try {
                assertEquals(getFieldValue(Class.forName("jdk.internal.org.objectweb.asm.Type").getField("BYTE_TYPE"), null), useMethod(Class.forName("jdk.internal.org.objectweb.asm.Type").getDeclaredMethod("getType", String.class), null, "B"));
            } catch (ClassNotFoundException | NoSuchMethodException | NoSuchFieldException e) {
                e.printStackTrace();
            }

        } else {
            Object o = getFieldValue(getField(Vars.unsafeClass, "theInternalUnsafe", false), null);
            Field f = getField(TestVar.class, "publicFinalField", false);
            Method m = getMethod(o.getClass(), "objectFieldOffset0", false, Field.class);
            assertDoesNotThrow(() -> useMethod(m, o, f));
            assertNotNull(useMethod(m, o, f));
        }
    }
}
