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

    private static final long p = 9223372036854775783L;
    private static long radixSubtract = 0;

    /**
     * TODO: Complete this function, STRCount(), to return longest consecutive run of STR in sequence.
     */
    public static int STRCount(String sequence, String STR) {
        int STRlength = STR.length();
        radixSubtract = (long) (Math.pow(STRlength, STRlength - 1) % p);
        int seqLength = sequence.length();
        long STRhash = hash(STR, STRlength);
        long seqHash = hash(sequence.substring(0, STRlength), STRlength);
        int i = 1;
        int counter = 0;
        int test = 0;
        while (i != seqLength) {
            if (seqHash == STRhash) {
                test = STRcounter(sequence, i, seqHash, STRhash, STRlength);
                if (test > counter) counter = test;
                i += test * STRlength;
            }
            else {
                i++;
                seqHash = updateHash(sequence, i, seqHash, STRhash, STRlength);
                if (seqHash == 0) return counter;
            }
        }

        return counter;
    }

    private static long hash(String t, int STRlength) {
        long hash = 0;
        int m = t.length();
        for (int i = 0; i < m; i++) {
            hash = (hash * STRlength + t.charAt(i)) % p;
        }
        return hash;
    }

    private static long updateHash(String t, int i, long seqHash, long STRhash, int STRlength) {
        seqHash = (seqHash - (t.charAt(i - 1) * radixSubtract % p + p) % p) % p;
        seqHash = (seqHash * STRlength + t.charAt(i + STRlength - 1)) % p;
        return seqHash;
    }

    private static int STRcounter(String t, int i, long seqHash, long STRhash, int STRlength) {
        int counter = 0;

        while (i + STRlength <= t.length() && seqHash == STRhash) {
            counter++;
            i += STRlength;
            if (i + STRlength <= t.length()) {
                seqHash = updateHash(t, i, seqHash, STRhash, STRlength);
            }
        }

        return counter;
    }
}
