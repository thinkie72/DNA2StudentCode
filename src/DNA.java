import java.util.ArrayList;
import java.util.HashMap;

/**
 * DNA
 * <p>
 * A puzzle created by Zach Blick
 * for Adventures in Algorithms
 * at Menlo School in Atherton, CA
 *</p>
 * <p>
 * Completed by: Tyler Hinkie
 *</p>
 */

public class DNA {

    /**
     * TODO: Complete this function, STRCount(), to return longest consecutive run of STR in sequence.
     */
    public static int STRCount(String sequence, String STR) {
        HashMap<String, Integer> map = new HashMap<>();
        int len1 = STR.length();
        String check;
        int len2 = sequence.length();
        int i = 0;
        while (i < len2 - len1) {
            check = sequence.substring(i , len1);
            if (map.containsKey(check)) {
                map.put(check, map.get(check) + 1);
                i += len1;
            } else {
                map.put(check, 1);
                i++;
            }
        }
        return map.get(STR);
    }
}
