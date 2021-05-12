/**
 * Simple class representing a 7-digit phone number in the form "XXX-XXXX" for a
 * phone in the immediate OSU area.
 */
public class PhoneNumber {

    /**
     * The phone number representation.
     */
    private String rep;

    /**
     * Constructor. {@code pNum} must be in the form "XXX-XXXX" where each "X"
     * is a digit '0'-'9'.
     */
    public PhoneNumber(String pNum) {
        this.rep = pNum;
    }

    @Override
    public int hashCode() {
        int total = 0;
        for (int i = 0; i < this.rep.length(); i++) {
            if (this.rep.charAt(i) != '-') {
                total += Character.digit(this.rep.charAt(i), this.rep.length());
            }
        }
        return total;
    }
}