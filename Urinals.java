import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * In men's public toilets with urinals, there is this unwritten rule that you
 * leave at least one urinal free between you and the next person peeing.
 * 
 * @author Sameer Mungole - smungole
 */
public class Urinals {
    private static final char ZERO = '0';
    private static final char ONE = '1';
    private static final Pattern REGEX = Pattern.compile("^0*(1?0+)*1?$");

    public static void main(String[] args) throws Exception {
        Urinals urinals = new Urinals();

        String inputFileName = String.format("input%s%s", File.separator, "urinal.dat");
        List<String> contents = urinals.read(inputFileName);

        System.out.printf("\n\tInput file: %s read successfully\n\t", inputFileName);
        System.out.println(contents);

        List<Integer> results = new ArrayList<>();
        System.out.println("\n\tFinding count of empty urinals...");
        for (String restroom : contents) {
            Integer count = urinals.countEmptyUrinals(restroom);
            System.out.printf("\t\t%s -> %d\n", restroom, count);
            results.add(count);
        }

        Path outputFilePath = urinals.getOutputFilePath();
        urinals.write(results, outputFilePath);
        System.out.printf("\n\tWritten results to output file: %s\n\n", outputFilePath.toString());
    }

    /**
     * Check to see if the pattern is valid
     * 
     * @param pattern
     * @return true, if the pattern doesn't have any consecutive 1s
     */
    public Boolean isValid(String pattern) {
        return pattern != null && !pattern.isEmpty() && REGEX.matcher(pattern).find();
    }

    /**
     * A function that returns the maximum of free urinals as an integer according
     * to the unwritten rule.
     * 
     * @param restroom
     * @return total number of empty urinals from currently state of the
     *         restroom, given that no consecutive urinals should be occupied
     */
    public Integer countEmptyUrinals(String restroom) {
        if (!isValid(restroom)) {
            return -1;
        }

        char[] urinals = restroom.toCharArray();
        switch (urinals.length) { // check for base cases
            case 1:
                return urinals[0] == ZERO ? 1 : 0;
            case 2:
                return restroom.equals("00") ? 1 : 0;
        }

        Integer count = 0;
        int total = urinals.length;
        for (int i = 0; i < total; i++) {
            if (urinals[i] == ONE) {
                // if this position is occupied, skip
                continue;
            }
            if (i == 0 && urinals[i + 1] == ONE) {
                // if the second position is occupied, skip
                continue;
            }
            if (i == total - 1 && urinals[i - 1] == ONE) {
                // if the second last is occupied, skip
                continue;
            }
            if (i > 0 && i < total - 1 && (urinals[i - 1] == ONE || urinals[i + 1] == ONE)) {
                // if any of the adjacent positions are occupied, skip
                continue;
            }

            urinals[i] = ONE;
            count++;
        }
        return count;
    }

    /**
     * Read contents from input file for empty urinal counting
     * 
     * @param fileName
     * @return list of input lines read from given file
     * @throws FileNotFoundException
     */
    public List<String> read(String fileName) throws FileNotFoundException {
        Scanner sc = new Scanner(new File(fileName));
        sc.useDelimiter("\n");

        List<String> lines = new ArrayList<>();
        while (sc.hasNext()) {
            String line = sc.next();
            if (line.equals("-1")) {
                break;
            }
            lines.add(line);
        }

        sc.close();
        return lines;
    }

    /**
     * Writes result of empty urinals counting to output file
     *
     * @param contents
     * @param path
     */
    public void write(List<Integer> contents, Path path) throws IOException {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < contents.size(); i++) {
            sb.append(contents.get(i));
            sb.append('\n');
        }

        String data = sb.toString().trim();
        Files.writeString(path, data);
    }

    /**
     * Output to rule.txt. If the file rule.txt already exists, increment a counter
     * and rename the file using the following rule pattern: rule1.txt, rule2.txt, etc.
     *
     * @return Returns the Path object for output file
     */
    public Path getOutputFilePath() {
        String fileName = "rule";
        Path path = Path.of(String.format("output%s%s.txt", File.separator, fileName));
        int count = 0;
        while (Files.exists(path)) {
            count++;
            path = Path.of(String.format("output%s%s%d.txt", File.separator, fileName, count));
        }
        return path;
    }
}