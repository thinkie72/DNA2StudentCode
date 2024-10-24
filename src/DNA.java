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

    // Define a large prime number p greater than 10^20 for Monte Carlo algo success
    private static final long P = 9223372036854775783L;


    // Function to return the longest consecutive run of STR in sequence using hashing.
    public static int STRCount(String sequence, String STR) {
        int maxCount = 0;
        int R = STR.length();
        int seqLength = sequence.length();

        // Calculate the hash of the STR modulo P using Horner's method
        long strHash = computeHash(STR, R);

        // Check every position in the sequence
        for (int i = 0; i <= seqLength - R; i++) {
            int currentCount = 0;
            int j = i;

            // Check for consecutive occurrences of STR using hash comparison
            while (j <= seqLength - R) {
                String substring = sequence.substring(j, j + R);
                long substringHash = computeHash(substring, R);

                // Compare hashes first
                if (substringHash == strHash && substring.equals(STR)) {
                    currentCount++;
                    // Move to the next potential STR occurrence
                    j += R;
                } else {
                    // Exit the inner loop if not a match
                    break;
                }
            }
            maxCount = Math.max(maxCount, currentCount);
        }

        return maxCount;
    }

    // Computes a hash for a given string using Horner's method with base R.
    private static long computeHash(String t, int R) {
        long hash = 0;
        for (int i = 0; i < R; i++) {
            hash = (hash * R + t.charAt(i)) % P;
        }
        return hash;
    }
}
