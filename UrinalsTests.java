import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UrinalsTests {
    private Urinals urinals = null;

    @BeforeEach
    void setup() {
        urinals = new Urinals();
    }

    @AfterEach
    void clean() {
        urinals = null;
    }

    @Test
    void testIsValidTrue() {
        System.out.println("====== Sameer Mungole == TEST ONE EXECUTED =======");

        String[] cases = {
                "0000", "0001", "1000", "1001", "0100",
                "0101010", "1010101", "1001001", "1001001001"
        };
        for (String test : cases) {
            Boolean actual = urinals.isValid(test);
            assertTrue(String.format("%s should return true", test), actual);
        }
    }
}
