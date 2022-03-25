import components.sequence.Sequence;
import components.sequence.Sequence1L;

/**
 * Put a short phrase describing the program here.
 *
 * @author Jay Shin (shin.810)
 *
 */
public final class SmoothWithReturn {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private SmoothWithReturn() {
    }

    /**
     * Smooths a given {@code Sequence<Integer>}.
     *
     * @param s1
     *            the sequence to smooth
     *
     * @requires |s1| >= 1
     * @return the resulting sequence
     */
    public static Sequence<Integer> smoothRecursive(Sequence<Integer> s1) {
        assert s1 != null : "Violation of: s1 is not null";
        assert s1.length() >= 1 : "Violation of: |s1| >= 1";

        Sequence<Integer> s = new Sequence1L<Integer>();

        if (s1.length() >= 2) {
            int y = s1.remove(1);
            int x = s1.remove(0);
            int avg = (x + y) / 2;
            s1.add(0, y);
            s = smoothRecursive(s1);
            s.add(0, avg);
            s1.add(0, x);
        }
        return s;
    }

    /**
     * Smooths a given {@code Sequence<Integer>}.
     *
     * @param s1
     *            the sequence to smooth
     *
     * @requires |s1| >= 1
     * @return the resulting sequence
     */
    public static Sequence<Integer> smoothIterative(Sequence<Integer> s1) {
        assert s1 != null : "Violation of: s1 is not null";
        assert s1.length() >= 1 : "Violation of: |s1| >= 1";

        Sequence<Integer> s = new Sequence1L<Integer>();

        for (int i = 0; i < s1.length() - 1; i++) {
            int x = s1.entry(i);
            int y = s1.entry(i + 1);
            int avg = (x + y) / 2;
            s.add(i, avg);
        }
        return s;
    }
}