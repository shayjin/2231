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
    public void testConstructor() {
        Map<String, String> m = this.constructorTest();
        Map<String, String> mExpected = this.constructorRef();
        assertEquals(mExpected, m);
    }

    @Test
    public void testAddEmpty() {
        Map<String, String> m = this.createFromArgsTest();
        Map<String, String> mExpected = this.createFromArgsRef("Shay Jin",
                "OSU");
        m.add("Shay Jin", "OSU");
    }

    @Test
    public void testAddNonEmpty() {
        Map<String, String> m = this.createFromArgsTest("Shay Jin", "OSU",
                "Bill Jebestan", "DCU", "Mogi Lee", "YU");
        Map<String, String> mExpected = this.createFromArgsRef("Shay Jin",
                "OSU", "Bill Jebestan", "DCU", "Mogi Lee", "YU", "Cangmin Coe",
                "TSU");
        m.add("Cangmin Coe", "TSU");
        assertEquals(mExpected, m);
    }

    @Test
    public void testRemoveLeavingEmpty() {
        Map<String, String> m = this.createFromArgsTest("Shay Jin", "OSU");
        Map<String, String> mExpected = this.createFromArgsRef();
        Pair<String, String> p = m.remove("Shay Jin");
        assertEquals("Shay Jin", p.key());
        assertEquals("OSU", p.value());
        assertEquals(mExpected, m);
    }

    @Test
    public void testRemoveNotLeavingEmpty() {
        Map<String, String> m = this.createFromArgsTest("Shay Jin", "OSU",
                "Bill Jebestan", "DCU", "Mogi Lee", "YU", "Cangmin Coe", "TSU");
        Map<String, String> mExpected = this.createFromArgsRef("Shay Jin",
                "OSU", "Bill Jebestan", "DCU", "Mogi Lee", "YU");
        Pair<String, String> p = m.remove("Cangmin Coe");
        assertEquals("Cangmin Coe", p.key());
        assertEquals("TSU", p.value());
        assertEquals(mExpected, m);
    }

    @Test
    public void testRemoveAnyLeavingEmpty() {
        Map<String, String> m = this.createFromArgsTest("Shay Jin", "OSU");
        Map<String, String> mExpected = this.createFromArgsRef("Shay Jin",
                "OSU");
        Pair<String, String> p = m.removeAny();
        assertTrue(mExpected.hasKey(p.key()));
        assertTrue(mExpected.hasValue(p.value()));
        assertEquals(mExpected.size() - 1, m.size());
        Pair<String, String> pExpected = mExpected.remove(p.key());
        assertEquals(pExpected, p);
        assertEquals(mExpected, m);
    }

    @Test
    public void testRemoveAnyNotLeavingEmpty() {
        Map<String, String> m = this.createFromArgsTest("Shay Jin", "OSU",
                "Bill Jebestan", "DCU", "Mogi Lee", "YU", "Cangmin Coe", "TSU");
        Map<String, String> mExpected = this.createFromArgsRef("Shay Jin",
                "OSU", "Bill Jebestan", "DCU", "Mogi Lee", "YU", "Cangmin Coe",
                "TSU");
        Pair<String, String> p = m.removeAny();
        assertTrue(mExpected.hasKey(p.key()));
        assertTrue(mExpected.hasValue(p.value()));
        assertEquals(mExpected.size() - 1, m.size());
        Pair<String, String> pExpected = mExpected.remove(p.key());
        assertEquals(pExpected, p);
        assertEquals(mExpected, m);
    }

    @Test
    public void testValue() {
        Map<String, String> m = this.createFromArgsTest("Shay Jin", "OSU",
                "Bill Jebestan", "DCU", "Mogi Lee", "YU", "Cangmin Coe", "TSU");
        Map<String, String> mExpected = this.createFromArgsRef("Shay Jin",
                "OSU", "Bill Jebestan", "DCU", "Mogi Lee", "YU", "Cangmin Coe",
                "TSU");
        assertEquals("DCU", m.value("Bill Jebestan"));
        assertEquals(mExpected, m);
    }

    @Test
    public void testHasKeyTrue() {
        Map<String, String> m = this.createFromArgsTest("Shay Jin", "OSU",
                "Bill Jebestan", "DCU", "Mogi Lee", "YU", "Cangmin Coe", "TSU");
        Map<String, String> mExpected = this.createFromArgsRef("Shay Jin",
                "OSU", "Bill Jebestan", "DCU", "Mogi Lee", "YU", "Cangmin Coe",
                "TSU");
        assertEquals(true, m.hasKey("Cangmin Coe"));
        assertEquals(mExpected, m);
    }

    @Test
    public void testHasKeyFalse() {
        Map<String, String> m = this.createFromArgsTest("Shay Jin", "OSU",
                "Bill Jebestan", "DCU", "Mogi Lee", "YU", "Cangmin Coe", "TSU");
        Map<String, String> mExpected = this.createFromArgsRef("Shay Jin",
                "OSU", "Bill Jebestan", "DCU", "Mogi Lee", "YU", "Cangmin Coe",
                "TSU");
        assertEquals(false, m.hasKey("Lee Sumin"));
        assertEquals(mExpected, m);
    }

    @Test
    public void testSize0() {
        Map<String, String> m = this.createFromArgsTest();
        Map<String, String> mExpected = this.createFromArgsRef();
        assertEquals(0, m.size());
        assertEquals(mExpected, m);
    }

    @Test
    public void testSize4() {
        Map<String, String> m = this.createFromArgsTest("Shay Jin", "OSU",
                "Bill Jebestan", "DCU", "Mogi Lee", "YU", "Cangmin Coe", "TSU");
        Map<String, String> mExpected = this.createFromArgsRef("Shay Jin",
                "OSU", "Bill Jebestan", "DCU", "Mogi Lee", "YU", "Cangmin Coe",
                "TSU");
        assertEquals(4, m.size());
        assertEquals(mExpected, m);
    }
}