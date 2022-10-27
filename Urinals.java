import java.util.regex.Pattern;

/**
 * Urinals
 * 
 * @author Sameer Mungole - smungole
 */
public class Urinals {
    private static final Pattern REGEX = Pattern.compile("^1?0+1?$");

    /**
     * Check to see if the string is valid
     * 
     * @param rule
     * @return
     */
    public Boolean isValid(String rule) {
        return REGEX.matcher(rule).find();
    }
}