import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
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
            Assertions.assertTrue(actual, String.format("%s should return true", test));
        }
    }

    @Test
    void testIsValidFalse() {
        System.out.println("====== Sameer Mungole == TEST TWO EXECUTED =======");

        String[] cases = {
                "011", "110", "10011001", "158043", "APLHAbets", "a1phanum5ric",
                " \t\r\n", "!@#$%^&*-_=+;:\"',<.>/?\\", "",
        };
        for (String test : cases) {
            Boolean actual = urinals.isValid(test);
            Assertions.assertFalse(actual, String.format("%s should return false", test));
        }
    }

    @Test
    void testCountEmptyUrinals() {
        System.out.println("====== Sameer Mungole == TEST FOUR EXECUTED =======");

        EmptyUrinalTestCase[] cases = {
                new EmptyUrinalTestCase("10001", 1),
                new EmptyUrinalTestCase("1001", 0),
                new EmptyUrinalTestCase("00000", 3),
                new EmptyUrinalTestCase("0000", 2),
                new EmptyUrinalTestCase("0", 1),
                new EmptyUrinalTestCase("1", 0),
                new EmptyUrinalTestCase("00", 1),
                new EmptyUrinalTestCase("01", 0),
        };
        for (EmptyUrinalTestCase test : cases) {
            Integer actual = urinals.countEmptyUrinals(test.input);
            assertEquals(test.expected, actual, test.message(actual));
        }
    }

    @Test
    void testCountEmptyUrinalsInvalid() {
        System.out.println("====== Sameer Mungole == TEST THREE EXECUTED =======");

        EmptyUrinalTestCase[] cases = {
                new EmptyUrinalTestCase("011", -1),
                new EmptyUrinalTestCase("110", -1),
                new EmptyUrinalTestCase("10011001", -1),
                new EmptyUrinalTestCase("158043", -1),
                new EmptyUrinalTestCase("APLHAbets", -1),
                new EmptyUrinalTestCase("a1phanum5ric", -1),
                new EmptyUrinalTestCase(" \t\r\n", -1),
                new EmptyUrinalTestCase("!@#$%^&*-_=+;:\"',<.>/?\\", -1),
                new EmptyUrinalTestCase("", -1),
        };
        for (EmptyUrinalTestCase test : cases) {
            Integer actual = urinals.countEmptyUrinals(test.input);
            assertEquals(test.expected, actual, test.message(actual));
        }
    }

    @Test
    void testReadFileNotFound() {
        System.out.println("====== Sameer Mungole == TEST FIVE EXECUTED =======");

        String fileName = "not-urinal.dat";
        assertThrows(FileNotFoundException.class, () -> urinals.read(fileName));
    }

    @Test
    void testRead() {
        System.out.println("====== Sameer Mungole == TEST SIX EXECUTED =======");

        String fileName = "input/urinal.dat";
        try {
            List<String> contents = urinals.read(fileName);
            Assertions.assertNotNull(contents, "should not return null");
        } catch (FileNotFoundException e) {
            fail("should not return FileNotFoundException");
        }
    }

    @Test
    void testReadEmptyFile() {
        System.out.println("====== Sameer Mungole == TEST TEN EXECUTED =======");

        String fileName = "input/urinal_empty.dat";
        try {
            List<String> contents = urinals.read(fileName);
            Assertions.assertTrue(contents.isEmpty());
        } catch (FileNotFoundException e) {
            fail("should not return FileNotFoundException");
        }
    }

    @Test
    void testWriteThrows() {
        System.out.println("====== Sameer Mungole == TEST NINE EXECUTED =======");

        Path path = Path.of("not-output/rule.txt");
        assertThrows(IOException.class, () -> urinals.write(new ArrayList<>(), path));
    }

    @Test
    void testGetOutputPath() {
        System.out.println("====== Sameer Mungole == TEST EIGHT EXECUTED =======");

        String path = urinals.getOutputFilePath().toString();
        Assertions.assertTrue(path.startsWith("output/rule"), String.format("incorrect file path: %s", path));
    }

    @Test
    void testWrite() {
        System.out.println("====== Sameer Mungole == TEST SEVEN EXECUTED =======");

        try {
            List<Integer> result = List.of(1, 0, 3, 2, 1);
            Path path = urinals.getOutputFilePath();
            urinals.write(result, path);

            List<String> expected = List.of("1", "0", "3", "2", "1");
            List<String> contents = urinals.read(path.toString());
            assertIterableEquals(expected, contents);
        } catch (FileNotFoundException e) {
            fail("should not return FileNotFoundException");
        } catch (IOException e) {
            fail("should not return IOException");
        }
    }

    private static class EmptyUrinalTestCase {
        String input;
        Integer expected;

        EmptyUrinalTestCase(String input, Integer expected) {
            this.input = input;
            this.expected = expected;
        }

        String message(Integer actual) {
            return String.format("input: %s, expected: %d, got: %d",
                    input, expected, actual);
        }
    }
}