import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import components.naturalnumber.NaturalNumber;

/**
 * JUnit test fixture for {@code NaturalNumber}'s constructors and kernel
 * methods.
 *
 * @author Jay Shin
 *
 */
public abstract class NaturalNumberTest {

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * implementation under test and returns the result.
     *
     * @return the new number
     * @ensures constructorTest = 0
     */
    protected abstract NaturalNumber constructorTest();

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * implementation under test and returns the result.
     *
     * @param i
     *            {@code int} to initialize from
     * @return the new number
     * @requires i >= 0
     * @ensures constructorTest = i
     */
    protected abstract NaturalNumber constructorTest(int i);

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * implementation under test and returns the result.
     *
     * @param s
     *            {@code String} to initialize from
     * @return the new number
     * @requires there exists n: NATURAL (s = TO_STRING(n))
     * @ensures s = TO_STRING(constructorTest)
     */
    protected abstract NaturalNumber constructorTest(String s);

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * implementation under test and returns the result.
     *
     * @param n
     *            {@code NaturalNumber} to initialize from
     * @return the new number
     * @ensures constructorTest = n
     */
    protected abstract NaturalNumber constructorTest(NaturalNumber n);

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * reference implementation and returns the result.
     *
     * @return the new number
     * @ensures constructorRef = 0
     */
    protected abstract NaturalNumber constructorRef();

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * reference implementation and returns the result.
     *
     * @param i
     *            {@code int} to initialize from
     * @return the new number
     * @requires i >= 0
     * @ensures constructorRef = i
     */
    protected abstract NaturalNumber constructorRef(int i);

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * reference implementation and returns the result.
     *
     * @param s
     *            {@code String} to initialize from
     * @return the new number
     * @requires there exists n: NATURAL (s = TO_STRING(n))
     * @ensures s = TO_STRING(constructorRef)
     */
    protected abstract NaturalNumber constructorRef(String s);

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * reference implementation and returns the result.
     *
     * @param n
     *            {@code NaturalNumber} to initialize from
     * @return the new number
     * @ensures constructorRef = n
     */
    protected abstract NaturalNumber constructorRef(NaturalNumber n);

    @Test
    public final void testConstructor() {
        NaturalNumber n1 = this.constructorTest();
        NaturalNumber n1Expected = this.constructorRef();
        NaturalNumber n2 = this.constructorTest(3);
        NaturalNumber n2Expected = this.constructorRef(3);
        NaturalNumber n3 = this.constructorTest("3");
        NaturalNumber n3Expected = this.constructorRef(3);
        NaturalNumber n4 = this.constructorTest(this.constructorTest(3));
        NaturalNumber n4Expected = this.constructorRef(3);
        assertEquals(n1Expected, n1);
        assertEquals(n2Expected, n2);
        assertEquals(n3Expected, n3);
        assertEquals(n4Expected, n4);
    }

    @Test
    public final void testMultiplyBy10Add0() {
        NaturalNumber n1 = this.constructorTest(3);
        NaturalNumber n1Expected = this.constructorRef(30);
        NaturalNumber n2 = this.constructorTest("3");
        NaturalNumber n2Expected = this.constructorRef(30);
        NaturalNumber n3 = this.constructorTest(this.constructorTest(3));
        NaturalNumber n3Expected = this.constructorRef(30);
        n1.multiplyBy10(0);
        n2.multiplyBy10(0);
        n3.multiplyBy10(0);
        assertEquals(n1Expected, n1);
        assertEquals(n2Expected, n2);
        assertEquals(n3Expected, n3);
    }

    @Test
    public final void testMultiplyBy10Add3() {
        NaturalNumber n1 = this.constructorTest(3);
        NaturalNumber n1Expected = this.constructorRef(33);
        NaturalNumber n2 = this.constructorTest("3");
        NaturalNumber n2Expected = this.constructorRef(33);
        NaturalNumber n3 = this.constructorTest(this.constructorTest(3));
        NaturalNumber n3Expected = this.constructorRef(33);
        n1.multiplyBy10(3);
        n2.multiplyBy10(3);
        n3.multiplyBy10(3);
        assertEquals(n1Expected, n1);
        assertEquals(n2Expected, n2);
        assertEquals(n3Expected, n3);
    }

    @Test
    public final void testDivdeBy10OneDigit() {
        NaturalNumber n1 = this.constructorTest(3);
        NaturalNumber n1Expected = this.constructorRef(0);
        NaturalNumber n2 = this.constructorTest("3");
        NaturalNumber n2Expected = this.constructorRef(0);
        NaturalNumber n3 = this.constructorTest(this.constructorTest(3));
        NaturalNumber n3Expected = this.constructorRef(0);
        int r1 = n1.divideBy10();
        int r2 = n2.divideBy10();
        int r3 = n3.divideBy10();
        assertEquals(3, r1);
        assertEquals(3, r2);
        assertEquals(3, r3);
        assertEquals(n1Expected, n1);
        assertEquals(n2Expected, n2);
        assertEquals(n3Expected, n3);
    }

    @Test
    public final void testDivdeBy10TwoDigits() {
        NaturalNumber n1 = this.constructorTest(33);
        NaturalNumber n1Expected = this.constructorRef(3);
        NaturalNumber n2 = this.constructorTest("33");
        NaturalNumber n2Expected = this.constructorRef(3);
        NaturalNumber n3 = this.constructorTest(this.constructorTest(33));
        NaturalNumber n3Expected = this.constructorRef(3);
        int r1 = n1.divideBy10();
        int r2 = n2.divideBy10();
        int r3 = n3.divideBy10();
        assertEquals(3, r1);
        assertEquals(3, r2);
        assertEquals(3, r3);
        assertEquals(n1Expected, n1);
        assertEquals(n2Expected, n2);
        assertEquals(n3Expected, n3);
    }

    @Test
    public final void testIsZeroTrue() {
        NaturalNumber n1 = this.constructorTest();
        NaturalNumber n1Expected = this.constructorRef(0);
        NaturalNumber n2 = this.constructorTest(0);
        NaturalNumber n2Expected = this.constructorRef(0);
        NaturalNumber n3 = this.constructorTest("0");
        NaturalNumber n3Expected = this.constructorRef(0);
        NaturalNumber n4 = this.constructorTest(this.constructorTest(0));
        NaturalNumber n4Expected = this.constructorRef(0);
        assertTrue(n1.isZero());
        assertTrue(n2.isZero());
        assertTrue(n3.isZero());
        assertTrue(n4.isZero());
        assertEquals(n1Expected, n1);
        assertEquals(n2Expected, n2);
        assertEquals(n3Expected, n3);
        assertEquals(n4Expected, n4);
    }

    @Test
    public final void testIsZeroFalse() {
        NaturalNumber n1 = this.constructorTest(2019);
        NaturalNumber n1Expected = this.constructorRef(2019);
        NaturalNumber n2 = this.constructorTest("2020");
        NaturalNumber n2Expected = this.constructorRef(2020);
        NaturalNumber n3 = this.constructorTest(this.constructorTest("2021"));
        NaturalNumber n3Expected = this.constructorRef(2021);
        assertTrue(!n1.isZero());
        assertTrue(!n2.isZero());
        assertTrue(!n3.isZero());
        assertEquals(n1Expected, n1);
        assertEquals(n2Expected, n2);
        assertEquals(n3Expected, n3);
    }

}
