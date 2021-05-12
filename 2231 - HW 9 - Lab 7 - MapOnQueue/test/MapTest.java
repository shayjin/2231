import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import components.map.Map;
import components.map.Map.Pair;

/**
 * JUnit test fixture for {@code Map<String, String>}'s constructor and kernel
 * methods.
 *
 * @author Jay Shin
 *
 */
public abstract class MapTest {

    /**
     * Invokes the appropriate {@code Map} constructor for the implementation
     * under test and returns the result.
     *
     * @return the new map
     * @ensures constructorTest = {}
     */
    protected abstract Map<String, String> constructorTest();

    /**
     * Invokes the appropriate {@code Map} constructor for the reference
     * implementation and returns the result.
     *
     * @return the new map
     * @ensures constructorRef = {}
     */
    protected abstract Map<String, String> constructorRef();

    /**
     *
     * Creates and returns a {@code Map<String, String>} of the implementation
     * under test type with the given entries.
     *
     * @param args
     *            the (key, value) pairs for the map
     * @return the constructed map
     * @requires <pre>
     * [args.length is even]  and
     * [the 'key' entries in args are unique]
     * </pre>
     * @ensures createFromArgsTest = [pairs in args]
     */
    private Map<String, String> createFromArgsTest(String... args) {
        assert args.length % 2 == 0 : "Violation of: args.length is even";
        Map<String, String> map = this.constructorTest();
        for (int i = 0; i < args.length; i += 2) {
            assert !map.hasKey(args[i]) : ""
                    + "Violation of: the 'key' entries in args are unique";
            map.add(args[i], args[i + 1]);
        }
        return map;
    }

    /**
     *
     * Creates and returns a {@code Map<String, String>} of the reference
     * implementation type with the given entries.
     *
     * @param args
     *            the (key, value) pairs for the map
     * @return the constructed map
     * @requires <pre>
     * [args.length is even]  and
     * [the 'key' entries in args are unique]
     * </pre>
     * @ensures createFromArgsRef = [pairs in args]
     */
    private Map<String, String> createFromArgsRef(String... args) {
        assert args.length % 2 == 0 : "Violation of: args.length is even";
        Map<String, String> map = this.constructorRef();
        for (int i = 0; i < args.length; i += 2) {
            assert !map.hasKey(args[i]) : ""
                    + "Violation of: the 'key' entries in args are unique";
            map.add(args[i], args[i + 1]);
        }
        return map;
    }

    @Test
    public final void testConstructor() {
        Map<String, String> m = this.constructorTest();
        Map<String, String> mExpected = this.constructorRef();
        assertEquals(mExpected, m);
        assertEquals(0, m.size());
        assertEquals("{}", m.toString());
    }

    @Test
    public final void testAddFromEmpty() {
        Map<String, String> m = this.createFromArgsTest();
        Map<String, String> mExpected = this.createFromArgsRef("Shay", "Jin");
        m.add("Shay", "Jin");
        assertEquals(mExpected, m);
    }

    @Test
    public final void testAddFromNonEmpty() {
        Map<String, String> m = this.createFromArgsTest("Billy", "Boi", "Mogi",
                "Lee", "Ballboy", "Da Mogi Killer");
        Map<String, String> mExpected = this.createFromArgsRef("Billy", "Boi",
                "Mogi", "Lee", "Ballboy", "Da Mogi Killer", "Shay", "Jin");
        m.add("Shay", "Jin");
        assertEquals(mExpected, m);
    }

    @Test
    public final void testRemoveNotLeavingEmpty() {
        Map<String, String> m = this.createFromArgsTest("Billy", "Boi", "Mogi",
                "Lee", "Ballboy", "Da Mogi Killer", "Shay", "Jin");
        Map<String, String> mExpected = this.createFromArgsRef("Billy", "Boi",
                "Mogi", "Lee", "Ballboy", "Da Mogi Killer");
        m.remove("Shay");
        assertEquals(mExpected, m);
    }

    @Test
    public final void testRemoveLeavingEmpty() {
        Map<String, String> m = this.createFromArgsTest("Shay", "Jin");
        Map<String, String> mExpected = this.createFromArgsRef();
        m.remove("Shay");
        assertEquals(mExpected, m);
    }

    @Test
    public final void testRemoveAnyNotLeavingEmpty() {
        Map<String, String> m = this.createFromArgsTest("Billy", "Boi", "Mogi",
                "Lee", "Ballboy", "Da Mogi Killer", "Shay", "Jin");
        Map<String, String> mExpected = this.createFromArgsRef("Billy", "Boi",
                "Mogi", "Lee", "Ballboy", "Da Mogi Killer", "Shay", "Jin");
        Pair<String, String> pair = m.removeAny();
        assertEquals(m.size(), mExpected.size() - 1);
        assertTrue(mExpected.hasKey(pair.key()));
        assertTrue(mExpected.hasValue(pair.value()));
        mExpected.remove(pair.key());
        assertEquals(mExpected, m);
    }

    @Test
    public final void testRemoveAnyLeavingEmpty() {
        Map<String, String> m = this.createFromArgsTest("Shay", "Jin");
        Map<String, String> mExpected = this.createFromArgsRef("Shay", "Jin");
        Pair<String, String> pair = m.removeAny();
        assertEquals(m.size(), mExpected.size() - 1);
        assertTrue(mExpected.hasKey(pair.key()));
        assertTrue(mExpected.hasValue(pair.value()));
        mExpected.remove(pair.key());
        assertEquals(mExpected, m);
    }

    @Test
    public final void testValue() {
        Map<String, String> m = this.createFromArgsTest("Billy", "Boi", "Mogi",
                "Lee", "Ballboy", "Da Mogi Killer", "Shay", "Jin");
        Map<String, String> mExpected = this.createFromArgsRef("Billy", "Boi",
                "Mogi", "Lee", "Ballboy", "Da Mogi Killer", "Shay", "Jin");
        String value = m.value("Billy");
        assertEquals("Boi", value);
        assertEquals(mExpected, m);
    }

    @Test
    public final void testHasKeyTrue() {
        Map<String, String> m = this.createFromArgsTest("Billy", "Boi", "Mogi",
                "Lee", "Ballboy", "Da Mogi Killer", "Shay", "Jin");
        Map<String, String> mExpected = this.createFromArgsRef("Billy", "Boi",
                "Mogi", "Lee", "Ballboy", "Da Mogi Killer", "Shay", "Jin");
        assertEquals(true, m.hasKey("Billy"));
        assertEquals(mExpected, m);
    }

    @Test
    public final void testHasKeyFalse() {
        Map<String, String> m = this.createFromArgsTest("Billy", "Boi", "Mogi",
                "Lee", "Ballboy", "Da Mogi Killer", "Shay", "Jin");
        Map<String, String> mExpected = this.createFromArgsRef("Billy", "Boi",
                "Mogi", "Lee", "Ballboy", "Da Mogi Killer", "Shay", "Jin");
        assertEquals(false, m.hasKey("Shinji"));
        assertEquals(mExpected, m);
    }

    @Test
    public final void testSizeEmpty() {
        Map<String, String> m = this.createFromArgsTest();
        Map<String, String> mExpected = this.createFromArgsRef();
        assertEquals(0, m.size());
        assertEquals(mExpected, m);
    }

    @Test
    public final void testSizeNonEmpty() {
        Map<String, String> m = this.createFromArgsTest("Billy", "Boi", "Mogi",
                "Lee", "Ballboy", "Da Mogi Killer", "Shay", "Jin");
        Map<String, String> mExpected = this.createFromArgsRef("Billy", "Boi",
                "Mogi", "Lee", "Ballboy", "Da Mogi Killer", "Shay", "Jin");
        assertEquals(4, m.size());
        assertEquals(mExpected, m);
    }
}
