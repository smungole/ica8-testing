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
     */
    public String[] read(String fileName) {
        System.out.println("Not yet implemented");
        return new String[] {};
    }
}