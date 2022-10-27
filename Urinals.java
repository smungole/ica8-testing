import java.util.regex.Pattern;

/**
 * In men's public toilets with urinals, there is this unwritten rule that you
 * leave at least one urinal free between you and the next person peeing.
 * 
 * @author Sameer Mungole - smungole
 */
public class Urinals {
    private static final Pattern REGEX = Pattern.compile("^0*(1?0+)*1?$");

    /**
     * Check to see if the pattern is valid
     * 
     * @param pattern
     * @return true, if the pattern doesn't have any consecutive 1s
     */
    public Boolean isValid(String pattern) {
        return REGEX.matcher(pattern).find();
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
        System.out.println("Not yet implemented");
        return 0;
    }
}