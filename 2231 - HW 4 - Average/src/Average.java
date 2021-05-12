/**
 * Put a short phrase describing the program here.
 *
 * @author Jay Shin
 *
 */
public final class Average {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private Average() {
    }

    /**
     * Returns the integer average of two given {@code int}s.
     *
     * @param j
     *            the first of two integers to average
     * @param k
     *            the second of two integers to average
     * @return the integer average of j and k
     * @ensures average = (j+k)/2
     */
    public static int average(int j, int k) {
        return (int) (((double) j + (double) k) / 2);
    }
}
