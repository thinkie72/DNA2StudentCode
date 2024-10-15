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
        HashMap<Character, Integer> lettersToVal = new HashMap<>();
        lettersToVal.put('A', 1);
        lettersToVal.put('C', 2);
        lettersToVal.put('T', 3);
        lettersToVal.put('G', 4);
        int STRlength = STR.length();
        int sequenceLength = sequence.length();
        int STRvalue = 0;
        int value = 0;
        for (int i = 0; i < STRlength; i++) {
            STRvalue += (int) (lettersToVal.get(STR.charAt(i)) * Math.pow(10, STRlength - i - 1));
            value += (int) (lettersToVal.get(sequence.charAt(i)) * Math.pow(10, STRlength - i - 1));
        }
        System.out.println(STR);
        System.out.println(STRvalue);
        System.out.println(sequence.substring(0, STRlength));
        System.out.println(value);
        int length = sequenceLength - STRlength;
        int index = 1;
        int count = 0;
        ArrayList<Integer> attempts = new ArrayList<>();
        while (index < length) {
            if (value == STRvalue) {
                count++;
                value = 0;
                for (int i = 0; i < STRlength; i++) {
                    value += (int) (lettersToVal.get(sequence.charAt(index + i - 1)) * Math.pow(10, STRlength - i - 1));
                }
                System.out.println(sequence.substring(index, STRlength));
                System.out.println(value);
            } else {
                if (count > 0) attempts.add(count);
                count = 0;
                value *= 10;
                value -= (int) (lettersToVal.get(sequence.charAt(index)) * Math.pow(10, STRlength));
                value += lettersToVal.get(sequence.charAt(index + STRlength));
                index++;
            }
        }

        int maxLength = 0;

        for (int attempt : attempts)
            if (attempt > maxLength) maxLength = attempt;

        return maxLength;
    }
}
