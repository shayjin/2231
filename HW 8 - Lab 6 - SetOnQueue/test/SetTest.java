import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.set.Set;

/**
 * JUnit test fixture for {@code Set<String>}'s constructor and kernel methods.
 *
 * @author Put your name here
 *
 */
public abstract class SetTest {

    /**
     * Invokes the appropriate {@code Set} constructor and returns the result.
     *
     * @return the new set
     * @ensures constructorTest = {}
     */
    protected abstract Set<String> constructorTest();

    /**
     * Invokes the appropriate {@code Set} constructor and returns the result.
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

    // TODO - add test cases for constructor, add, remove, removeAny, contains, and size
    @Test
    public final void testAdd() {
        Set<String> s = this.createFromArgsTest("Benjamin Hamms", "Layo Ramos");
        Set<String> sExpected = this.createFromArgsRef("Benjamin Hamms",
                "Layo Ramos", "Cody Brain Moore");
        s.add("Cody Brain Moore");
        assertEquals(sExpected, s);
    }

    @Test
    public final void testAddFromEmpty() {
        Set<String> s = this.createFromArgsTest();
        Set<String> sExpected = this.createFromArgsRef("Cody Brain Moore");
        s.add("Cody Brain Moore");
        assertEquals(sExpected, s);
    }

    @Test
    public final void testRemove() {
        Set<String> s = this.createFromArgsTest("Benjamin Hamms", "Layo Ramos",
                "Cody Brain Moore");
        Set<String> sExpected = this.createFromArgsRef("Benjamin Hamms",
                "Layo Ramos");
        String str = s.remove("Cody Brain Moore");
        assertEquals(sExpected, s);
        assertEquals("Cody Brain Moore", str);
    }

    @Test
    public final void testRemoveLeavingEmpty() {
        Set<String> s = this.createFromArgsTest("Cody Brain Moore");
        Set<String> sExpected = this.createFromArgsRef();
        String str = s.remove("Cody Brain Moore");
        assertEquals(sExpected, s);
        assertEquals("Cody Brain Moore", str);
    }

    @Test
    public final void testRemoveAny() {
        Set<String> s = this.createFromArgsTest("Benjamin Hamms", "Layo Ramos",
                "Cody Brain Moore");
        Set<String> sExpected = this.createFromArgsRef("Benjamin Hamms",
                "Layo Ramos", "Cody Brain Moore");
        String str = s.removeAny();
        assertEquals(sExpected.size() - 1, s.size());
        sExpected.remove(str);
        assertEquals(sExpected, s);
    }

    @Test
    public final void testRemoveAnyLeavingEmpty() {
        Set<String> s = this.createFromArgsTest("Cody Brain Moore");
        Set<String> sExpected = this.createFromArgsRef("Cody Brain Moore");
        String str = s.removeAny();
        assertEquals(sExpected.size() - 1, s.size());
        sExpected.remove(str);
        assertEquals(sExpected, s);
    }

    @Test
    public final void testContainsTrue() {
        Set<String> s = this.createFromArgsTest("Benjamin Hamms", "Layo Ramos",
                "Cody Brain Moore");
        Set<String> sExpected = this.createFromArgsRef("Benjamin Hamms",
                "Layo Ramos", "Cody Brain Moore");
        boolean b = s.contains("Cody Brain Moore");
        assertEquals(true, b);
        assertEquals(sExpected, s);
    }

    @Test
    public final void testContainsFalse() {
        Set<String> s = this.createFromArgsTest("Benjamin Hamms", "Layo Ramos",
                "Cody Brain Moore");
        Set<String> sExpected = this.createFromArgsRef("Benjamin Hamms",
                "Layo Ramos", "Cody Brain Moore");
        boolean b = s.contains("Hunter Moore");
        assertEquals(false, b);
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
    public final void testSize3() {
        Set<String> s = this.createFromArgsTest("Benjamin Hamms", "Layo Ramos",
                "Cody Brain Moore");
        Set<String> sExpected = this.createFromArgsRef("Benjamin Hamms",
                "Layo Ramos", "Cody Brain Moore");
        assertEquals(3, s.size());
        assertEquals(sExpected, s);
    }

    @Test
    public final void testSize10() {
        Set<String> s = this.createFromArgsTest("Benjamin Hamms", "Layo Ramos",
                "Cody Brain Moore", "Ethan Morris", "Joseangel Fernandez",
                "Owen McDermott", "Evan Dangler", "Aidan Segna", "Tony Liu",
                "Fate Bologone");
        Set<String> sExpected = this.createFromArgsRef("Benjamin Hamms",
                "Layo Ramos", "Cody Brain Moore", "Ethan Morris",
                "Joseangel Fernandez", "Owen McDermott", "Evan Dangler",
                "Aidan Segna", "Tony Liu", "Fate Bologone");
        assertEquals(10, s.size());
        assertEquals(sExpected, s);
    }

}
