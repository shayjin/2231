import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.sequence.Sequence;

/**
 * JUnit test fixture for {@code Sequence<String>}'s constructor and kernel
 * methods.
 *
 * @author Put your name here
 *
 */
public abstract class SequenceTest {

    /**
     * Invokes the appropriate {@code Sequence} constructor for the
     * implementation under test and returns the result.
     *
     * @return the new sequence
     * @ensures constructorTest = <>
     */
    protected abstract Sequence<String> constructorTest();

    /**
     * Invokes the appropriate {@code Sequence} constructor for the reference
     * implementation and returns the result.
     *
     * @return the new sequence
     * @ensures constructorRef = <>
     */
    protected abstract Sequence<String> constructorRef();

    /**
     *
     * Creates and returns a {@code Sequence<String>} of the implementation
     * under test type with the given entries.
     *
     * @param args
     *            the entries for the sequence
     * @return the constructed sequence
     * @ensures createFromArgsTest = [entries in args]
     */
    private Sequence<String> createFromArgsTest(String... args) {
        Sequence<String> sequence = this.constructorTest();
        for (String s : args) {
            sequence.add(sequence.length(), s);
        }
        return sequence;
    }

    /**
     *
     * Creates and returns a {@code Sequence<String>} of the reference
     * implementation type with the given entries.
     *
     * @param args
     *            the entries for the sequence
     * @return the constructed sequence
     * @ensures createFromArgsRef = [entries in args]
     */
    private Sequence<String> createFromArgsRef(String... args) {
        Sequence<String> sequence = this.constructorRef();
        for (String s : args) {
            sequence.add(sequence.length(), s);
        }
        return sequence;
    }

    @Test
    public final void testAddFront() {
        Sequence<String> s = this.createFromArgsTest("Arjun Thomas",
                "James Roski", "Bill Jebestan", "Siyoon Jung");
        Sequence<String> sExpected = this.createFromArgsRef("Sarah Park",
                "Arjun Thomas", "James Roski", "Bill Jebestan", "Siyoon Jung");
        s.add(0, "Sarah Park");
        assertEquals(sExpected, s);
    }

    @Test
    public final void testAddMiddle() {
        Sequence<String> s = this.createFromArgsTest("Arjun Thomas",
                "James Roski", "Bill Jebestan", "Siyoon Jung");
        Sequence<String> sExpected = this.createFromArgsRef("Arjun Thomas",
                "James Roski", "Sarah Park", "Bill Jebestan", "Siyoon Jung");
        s.add(s.length() / 2, "Sarah Park");
        assertEquals(sExpected, s);
    }

    @Test
    public final void testAddBack() {
        Sequence<String> s = this.createFromArgsTest("Arjun Thomas",
                "James Roski", "Bill Jebestan", "Siyoon Jung");
        Sequence<String> sExpected = this.createFromArgsRef("Arjun Thomas",
                "James Roski", "Bill Jebestan", "Siyoon Jung", "Sarah Park");
        s.add(s.length(), "Sarah Park");
        assertEquals(sExpected, s);
    }

    @Test
    public final void testAddFromEmpty() {
        Sequence<String> s = this.createFromArgsTest();
        Sequence<String> sExpected = this.createFromArgsRef("Arjun Thomas");
        s.add(0, "Arjun Thomas");
        assertEquals(sExpected, s);
    }

    @Test
    public final void testRemoveFront() {
        Sequence<String> s = this.createFromArgsTest("Arjun Thomas",
                "James Roski", "Bill Jebestan", "Siyoon Jung", "Sarah Park");
        Sequence<String> sExpected = this.createFromArgsRef("James Roski",
                "Bill Jebestan", "Siyoon Jung", "Sarah Park");
        s.remove(0);
        assertEquals(sExpected, s);
    }

    @Test
    public final void testRemoveMiddle() {
        Sequence<String> s = this.createFromArgsTest("Arjun Thomas",
                "James Roski", "Bill Jebestan", "Siyoon Jung", "Sarah Park");
        Sequence<String> sExpected = this.createFromArgsRef("Arjun Thomas",
                "James Roski", "Siyoon Jung", "Sarah Park");
        s.remove(s.length() / 2);
        assertEquals(sExpected, s);
    }

    @Test
    public final void testRemoveBack() {
        Sequence<String> s = this.createFromArgsTest("Arjun Thomas",
                "James Roski", "Bill Jebestan", "Siyoon Jung", "Sarah Park");
        Sequence<String> sExpected = this.createFromArgsRef("Arjun Thomas",
                "James Roski", "Bill Jebestan", "Siyoon Jung");
        s.remove(s.length() - 1);
        assertEquals(sExpected, s);
    }

    @Test
    public final void testRemoveLeavingEmpty() {
        Sequence<String> s = this.createFromArgsTest("Arjun Thomas");
        Sequence<String> sExpected = this.createFromArgsRef();
        s.remove(0);
        assertEquals(sExpected, s);
    }

    @Test
    public final void testLength0() {
        Sequence<String> s = this.createFromArgsTest();
        Sequence<String> sExpected = this.createFromArgsRef();
        assertEquals(0, s.length());
        assertEquals(sExpected, s);
    }

    @Test
    public final void testLength3() {
        Sequence<String> s = this.createFromArgsTest("Arjun Thomas",
                "James Roski", "Bill Jebestan");
        Sequence<String> sExpected = this.createFromArgsRef("Arjun Thomas",
                "James Roski", "Bill Jebestan");
        assertEquals(3, s.length());
        assertEquals(sExpected, s);
    }

    @Test
    public final void testLength10() {
        Sequence<String> s = this.createFromArgsTest("Arjun Thomas",
                "James Roski", "Bill Jebestan", "Siyoon Jung", "Sarah Park",
                "Shay Jin", "Luke Wingert", "Logan Sigler", "Andre Farinazo",
                "Natalia Farinazo");
        Sequence<String> sExpected = this.createFromArgsRef("Arjun Thomas",
                "James Roski", "Bill Jebestan", "Siyoon Jung", "Sarah Park",
                "Shay Jin", "Luke Wingert", "Logan Sigler", "Andre Farinazo",
                "Natalia Farinazo");
        assertEquals(10, s.length());
        assertEquals(sExpected, s);
    }
}
