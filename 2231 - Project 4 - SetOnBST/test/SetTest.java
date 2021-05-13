import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import components.set.Set;

/**
 * JUnit test fixture for {@code Set<String>}'s constructor and kernel methods.
 *
 * @author Jay Shin
 *
 */
public abstract class SetTest {

    /**
     * Invokes the appropriate {@code Set} constructor for the implementation
     * under test and returns the result.
     *
     * @return the new set
     * @ensures constructorTest = {}
     */
    protected abstract Set<String> constructorTest();

    /**
     * Invokes the appropriate {@code Set} constructor for the reference
     * implementation and returns the result.
     *
     * @return the new set
     * @ensures constructorRef = {}
     */
    protected abstract Set<String> constructorRef();

    /**
     * Creates and returns a {@code Set<String>} of the implementation under
     * test type with the given entries.
     *
     * @param args
     *            the entries for the set
     * @return the constructed set
     * @requires [every entry in args is unique]
     * @ensures createFromArgsTest = [entries in args]
     */
    private Set<String> createFromArgsTest(String... args) {
        Set<String> set = this.constructorTest();
        for (String s : args) {
            assert !set.contains(
                    s) : "Violation of: every entry in args is unique";
            set.add(s);
        }
        return set;
    }

    /**
     * Creates and returns a {@code Set<String>} of the reference implementation
     * type with the given entries.
     *
     * @param args
     *            the entries for the set
     * @return the constructed set
     * @requires [every entry in args is unique]
     * @ensures createFromArgsRef = [entries in args]
     */
    private Set<String> createFromArgsRef(String... args) {
        Set<String> set = this.constructorRef();
        for (String s : args) {
            assert !set.contains(
                    s) : "Violation of: every entry in args is unique";
            set.add(s);
        }
        return set;
    }

    @Test
    public final void testConstructor() {
        Set<String> s = this.constructorTest();
        Set<String> sExpected = this.constructorRef();
        assertEquals(sExpected, s);
    }

    @Test
    public final void testAddFromEmpty() {
        Set<String> s = this.createFromArgsTest();
        Set<String> sExpected = this.createFromArgsRef("Sarah Park");
        s.add("Sarah Park");
        assertEquals(sExpected, s);
    }

    @Test
    public final void testAddFromNonEmpty() {
        Set<String> s = this.createFromArgsTest("Siyoon Jug", "Moonju Kang",
                "Sul Kim", "Cangmin Coe");
        Set<String> sExpected = this.createFromArgsRef("Siyoon Jug",
                "Moonju Kang", "Sul Kim", "Cangmin Coe", "Sarah Park");
        s.add("Sarah Park");
        assertEquals(sExpected, s);
    }

    @Test
    public final void testRemoveLeavingEmpty() {
        Set<String> s = this.createFromArgsTest("Sarah Park");
        Set<String> sExpected = this.createFromArgsRef();
        s.remove("Sarah Park");
        assertEquals(sExpected, s);
    }

    @Test
    public final void testRemoveNotLeavingEmpty() {
        Set<String> s = this.createFromArgsTest("Siyoon Jug", "Moonju Kang",
                "Sul Kim", "Cangmin Coe", "Sarah Park");
        Set<String> sExpected = this.createFromArgsRef("Siyoon Jug",
                "Moonju Kang", "Sul Kim", "Cangmin Coe");
        s.remove("Sarah Park");
        assertEquals(sExpected, s);
    }

    @Test
    public final void testRemoveAnyLeavingEmpty() {
        Set<String> s = this.createFromArgsTest("Sarah Park");
        Set<String> sExpected = this.createFromArgsRef();
        String removed = s.removeAny();
        assertEquals("Sarah Park", removed);
        assertEquals(sExpected, s);
    }

    @Test
    public final void testRemoveAnyNotLeavingEmpty() {
        Set<String> s = this.createFromArgsTest("Siyoon Jug", "Moonju Kang",
                "Sul Kim", "Cangmin Coe", "Sarah Park");
        Set<String> sExpected = this.createFromArgsRef("Siyoon Jug",
                "Moonju Kang", "Sul Kim", "Cangmin Coe", "Sarah Park");
        String remove = s.removeAny();
        assertEquals(sExpected.size() - 1, s.size());
        assertTrue(sExpected.contains(remove));
        sExpected.remove(remove);
        assertEquals(sExpected, s);
    }

    @Test
    public final void testContainsTrue() {
        Set<String> s = this.createFromArgsTest("Siyoon Jug", "Moonju Kang",
                "Sul Kim", "Cangmin Coe", "Sarah Park");
        Set<String> sExpected = this.createFromArgsRef("Siyoon Jug",
                "Moonju Kang", "Sul Kim", "Cangmin Coe", "Sarah Park");
        assertTrue(s.contains("Sul Kim"));
        assertEquals(sExpected, s);
    }

    @Test
    public final void testContainsFalse() {
        Set<String> s = this.createFromArgsTest("Siyoon Jug", "Moonju Kang",
                "Sul Kim", "Cangmin Coe", "Sarah Park");
        Set<String> sExpected = this.createFromArgsRef("Siyoon Jug",
                "Moonju Kang", "Sul Kim", "Cangmin Coe", "Sarah Park");
        assertTrue(!s.contains("Rick Hendre"));
        assertEquals(sExpected, s);
    }

    @Test
    public final void testSize0() {
        Set<String> s = this.createFromArgsTest();
        Set<String> sExpected = this.createFromArgsRef();
        assertEquals(0, s.size());
        assertEquals(sExpected, s);
    }

    @Test
    public final void testSize5() {
        Set<String> s = this.createFromArgsTest("Siyoon Jug", "Moonju Kang",
                "Sul Kim", "Cangmin Coe", "Sarah Park");
        Set<String> sExpected = this.createFromArgsRef("Siyoon Jug",
                "Moonju Kang", "Sul Kim", "Cangmin Coe", "Sarah Park");
        assertEquals(5, s.size());
        assertEquals(sExpected, s);
    }
}
