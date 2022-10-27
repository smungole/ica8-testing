import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;

public class UrinalsTests {
    @Test
    void testIsValidTrue() {
        System.out.println("====== Sameer Mungole == TEST ONE EXECUTED =======");

        String[] cases = { "0000", "0001", "1000", "1001" };
        Urinals urinals = new Urinals();
        for (String test : cases) {
            Boolean actual = urinals.isValid(test);
            assertTrue(String.format("%s should return true", test), actual);
        }
    }
}
