import org.junit.jupiter.api.Test;
import vars.TestVar;
import static org.junit.jupiter.api.Assertions.*;
import static reflection.ConstructorReflection.*;
import static reflection.FieldReflection.*;

public class ConstructorTest {

    @Test
    public void noConstructor() {
        TestVar testVar = createInstanceWithoutConstructor(TestVar.class);
        assertEquals(0, testVar.publicFinalField);
    }

    @Test
    public void getPrivateConstructorInt() {
        TestVar testVar = useConstructor(getConstructor(TestVar.class, int.class), 1);
        assertEquals(1, testVar.publicFinalField);
    }

    @Test
    public void getPrivateConstructorInteger() {
        TestVar testVar = useConstructor(getConstructor(TestVar.class, Integer.class), Integer.valueOf(1));
        assertEquals(1, testVar.publicFinalField);
    }

    @Test
    public void getPrivateConstructor() {
        TestVar testVar = useConstructor(getConstructor(TestVar.class));
        assertEquals(1, testVar.publicFinalField);
    }
}
