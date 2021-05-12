import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.binarytree.BinaryTree;

/**
 * JUnit test fixture for {@code BinaryTree<String>}'s methods.
 *
 * @author Paolo Bucci, Jay Shin
 *
 */
public final class BinaryTreeMethodsTest {

    @Test
    public void testHeightEmpty() {
        /*
         * Set up variables
         */
        BinaryTree<String> t = BinaryTreeUtility.treeFromString("()");
        BinaryTree<String> tExpected = BinaryTreeUtility.treeFromString("()");
        /*
         * Call method under test
         */
        int h = BinaryTreeMethods.height(t);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(0, h);
        assertEquals(tExpected, t);
    }

    @Test
    public void testHeightOne() {
        /*
         * Set up variables
         */
        BinaryTree<String> t = BinaryTreeUtility.treeFromString("a(()())");
        BinaryTree<String> tExpected = BinaryTreeUtility
                .treeFromString("a(()())");
        /*
         * Call method under test
         */
        int h = BinaryTreeMethods.height(t);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(1, h);
        assertEquals(tExpected, t);
    }

    @Test
    public void testHeightTwo() {
        /*
         * Set up variables
         */
        BinaryTree<String> t = BinaryTreeUtility.treeFromString("a(b(()())())");
        BinaryTree<String> tExpected = BinaryTreeUtility
                .treeFromString("a(b(()())())");
        /*
         * Call method under test
         */
        int h = BinaryTreeMethods.height(t);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(2, h);
        assertEquals(tExpected, t);
    }

    @Test
    public void testHeightMany() {
        /*
         * Set up variables
         */
        BinaryTree<String> t = BinaryTreeUtility
                .treeFromString("a(b(()())c(d(()e(f(()())()))()))");
        BinaryTree<String> tExpected = BinaryTreeUtility
                .treeFromString("a(b(()())c(d(()e(f(()())()))()))");
        /*
         * Call method under test
         */
        int h = BinaryTreeMethods.height(t);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(5, h);
        assertEquals(tExpected, t);
    }

    @Test
    public void testHeightMore() {
        /*
         * Set up variables
         */
        BinaryTree<String> t = BinaryTreeUtility.treeFromString(
                "a(b(d(g(j(()())())())())c(e(h(()())i(()()))f(()())))");
        BinaryTree<String> tExpected = BinaryTreeUtility.treeFromString(
                "a(b(d(g(j(()())())())())c(e(h(()())i(()()))f(()())))");
        /*
         * Call method under test
         */
        int h = BinaryTreeMethods.height(t);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(5, h);
        assertEquals(tExpected, t);
    }

    @Test
    public void testIsInTreeNonRecursiveEmpty() {
        /*
         * Set up variables
         */
        BinaryTree<String> t = BinaryTreeUtility.treeFromString("()");
        BinaryTree<String> tExpected = BinaryTreeUtility.treeFromString("()");
        /*
         * Call method under test
         */
        boolean found = BinaryTreeMethods.isInTreeNonRecursive(t, "a");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(false, found);
        assertEquals(tExpected, t);
    }

    @Test
    public void testIsInTreeNonRecursiveOneTrue() {
        /*
         * Set up variables
         */
        BinaryTree<String> t = BinaryTreeUtility.treeFromString("a(()())");
        BinaryTree<String> tExpected = BinaryTreeUtility
                .treeFromString("a(()())");
        /*
         * Call method under test
         */
        boolean found = BinaryTreeMethods.isInTreeNonRecursive(t, "a");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(true, found);
        assertEquals(tExpected, t);
    }

    @Test
    public void testIsInTreeNonRecursiveOneFalse() {
        /*
         * Set up variables
         */
        BinaryTree<String> t = BinaryTreeUtility.treeFromString("a(()())");
        BinaryTree<String> tExpected = BinaryTreeUtility
                .treeFromString("a(()())");
        /*
         * Call method under test
         */
        boolean found = BinaryTreeMethods.isInTreeNonRecursive(t, "b");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(false, found);
        assertEquals(tExpected, t);
    }

    @Test
    public void testIsInTreeNonRecursiveManyLeftTrue() {
        /*
         * Set up variables
         */
        BinaryTree<String> t = BinaryTreeUtility
                .treeFromString("a(b(()())c(d(()e(f(()())()))()))");
        BinaryTree<String> tExpected = BinaryTreeUtility
                .treeFromString("a(b(()())c(d(()e(f(()())()))()))");
        /*
         * Call method under test
         */
        boolean found = BinaryTreeMethods.isInTreeNonRecursive(t, "b");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(true, found);
        assertEquals(tExpected, t);
    }

    @Test
    public void testIsInTreeNonRecursiveManyRightTrue() {
        /*
         * Set up variables
         */
        BinaryTree<String> t = BinaryTreeUtility
                .treeFromString("a(b(()())c(d(()e(f(()())()))()))");
        BinaryTree<String> tExpected = BinaryTreeUtility
                .treeFromString("a(b(()())c(d(()e(f(()())()))()))");
        /*
         * Call method under test
         */
        boolean found = BinaryTreeMethods.isInTreeNonRecursive(t, "c");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(true, found);
        assertEquals(tExpected, t);
    }

    @Test
    public void testIsInTreeNonRecursiveManyLeafTrue() {
        /*
         * Set up variables
         */
        BinaryTree<String> t = BinaryTreeUtility
                .treeFromString("a(b(()())c(d(()e(f(()())()))()))");
        BinaryTree<String> tExpected = BinaryTreeUtility
                .treeFromString("a(b(()())c(d(()e(f(()())()))()))");
        /*
         * Call method under test
         */
        boolean found = BinaryTreeMethods.isInTreeNonRecursive(t, "f");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(true, found);
        assertEquals(tExpected, t);
    }

    @Test
    public void testIsInTreeNonRecursiveManyRootTrue() {
        /*
         * Set up variables
         */
        BinaryTree<String> t = BinaryTreeUtility
                .treeFromString("a(b(()())c(d(()e(f(()())()))()))");
        BinaryTree<String> tExpected = BinaryTreeUtility
                .treeFromString("a(b(()())c(d(()e(f(()())()))()))");
        /*
         * Call method under test
         */
        boolean found = BinaryTreeMethods.isInTreeNonRecursive(t, "a");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(true, found);
        assertEquals(tExpected, t);
    }

    @Test
    public void testIsInTreeNonRecursiveManyFalse() {
        /*
         * Set up variables
         */
        BinaryTree<String> t = BinaryTreeUtility
                .treeFromString("a(b(()())c(d(()e(f(()())()))()))");
        BinaryTree<String> tExpected = BinaryTreeUtility
                .treeFromString("a(b(()())c(d(()e(f(()())()))()))");
        /*
         * Call method under test
         */
        boolean found = BinaryTreeMethods.isInTreeNonRecursive(t, "g");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(false, found);
        assertEquals(tExpected, t);
    }

    @Test
    public void testIsInTreeRecursiveEmpty() {
        /*
         * Set up variables
         */
        BinaryTree<String> t = BinaryTreeUtility.treeFromString("()");
        BinaryTree<String> tExpected = BinaryTreeUtility.treeFromString("()");
        /*
         * Call method under test
         */
        boolean found = BinaryTreeMethods.isInTreeRecursive(t, "a");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(false, found);
        assertEquals(tExpected, t);
    }

    @Test
    public void testIsInTreeRecursiveOneTrue() {
        /*
         * Set up variables
         */
        BinaryTree<String> t = BinaryTreeUtility.treeFromString("a(()())");
        BinaryTree<String> tExpected = BinaryTreeUtility
                .treeFromString("a(()())");
        /*
         * Call method under test
         */
        boolean found = BinaryTreeMethods.isInTreeRecursive(t, "a");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(true, found);
        assertEquals(tExpected, t);
    }

    @Test
    public void testIsInTreeRecursiveOneFalse() {
        /*
         * Set up variables
         */
        BinaryTree<String> t = BinaryTreeUtility.treeFromString("a(()())");
        BinaryTree<String> tExpected = BinaryTreeUtility
                .treeFromString("a(()())");
        /*
         * Call method under test
         */
        boolean found = BinaryTreeMethods.isInTreeRecursive(t, "b");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(false, found);
        assertEquals(tExpected, t);
    }

    @Test
    public void testIsInTreeRecursiveManyLeftTrue() {
        /*
         * Set up variables
         */
        BinaryTree<String> t = BinaryTreeUtility
                .treeFromString("a(b(()())c(d(()e(f(()())()))()))");
        BinaryTree<String> tExpected = BinaryTreeUtility
                .treeFromString("a(b(()())c(d(()e(f(()())()))()))");
        /*
         * Call method under test
         */
        boolean found = BinaryTreeMethods.isInTreeRecursive(t, "b");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(true, found);
        assertEquals(tExpected, t);
    }

    @Test
    public void testIsInTreeRecursiveManyRightTrue() {
        /*
         * Set up variables
         */
        BinaryTree<String> t = BinaryTreeUtility
                .treeFromString("a(b(()())c(d(()e(f(()())()))()))");
        BinaryTree<String> tExpected = BinaryTreeUtility
                .treeFromString("a(b(()())c(d(()e(f(()())()))()))");
        /*
         * Call method under test
         */
        boolean found = BinaryTreeMethods.isInTreeRecursive(t, "c");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(true, found);
        assertEquals(tExpected, t);
    }

    @Test
    public void testIsInTreeRecursiveManyLeafTrue() {
        /*
         * Set up variables
         */
        BinaryTree<String> t = BinaryTreeUtility
                .treeFromString("a(b(()())c(d(()e(f(()())()))()))");
        BinaryTree<String> tExpected = BinaryTreeUtility
                .treeFromString("a(b(()())c(d(()e(f(()())()))()))");
        /*
         * Call method under test
         */
        boolean found = BinaryTreeMethods.isInTreeRecursive(t, "f");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(true, found);
        assertEquals(tExpected, t);
    }

    @Test
    public void testIsInTreeRecursiveManyRootTrue() {
        /*
         * Set up variables
         */
        BinaryTree<String> t = BinaryTreeUtility
                .treeFromString("a(b(()())c(d(()e(f(()())()))()))");
        BinaryTree<String> tExpected = BinaryTreeUtility
                .treeFromString("a(b(()())c(d(()e(f(()())()))()))");
        /*
         * Call method under test
         */
        boolean found = BinaryTreeMethods.isInTreeRecursive(t, "a");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(true, found);
        assertEquals(tExpected, t);
    }

    @Test
    public void testIsInTreeRecursiveManyFalse() {
        /*
         * Set up variables
         */
        BinaryTree<String> t = BinaryTreeUtility
                .treeFromString("a(b(()())c(d(()e(f(()())()))()))");
        BinaryTree<String> tExpected = BinaryTreeUtility
                .treeFromString("a(b(()())c(d(()e(f(()())()))()))");
        /*
         * Call method under test
         */
        boolean found = BinaryTreeMethods.isInTreeRecursive(t, "g");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(false, found);
        assertEquals(tExpected, t);
    }

}
