import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.binarytree.BinaryTree;
import components.binarytree.BinaryTree1;

/**
 * JUnit test fixture for {@code BinarySearchTreeMethods}'s static methods
 * isInTree (and removeSmallest).
 */
public final class BinarySearchTreeMethodsTest {

    /**
     * Constructs and return a BST created by inserting the given {@code args}
     * into an empty tree in the order in which they are provided.
     *
     * @param args
     *            the {@code String}s to be inserted in the tree
     * @return the BST with the given {@code String}s
     * @requires [the Strings in args are all distinct]
     * @ensures createBSTFromArgs = [the BST with the given Strings]
     */
    private static BinaryTree<String> createBSTFromArgs(String... args) {
        BinaryTree<String> t = new BinaryTree1<String>();
        for (String s : args) {
            BinaryTreeUtility.insertInTree(t, s);
        }
        return t;
    }

    @Test
    public void testIsInTreeTrue() {
        /*
         * Set up variables
         */
        BinaryTree<String> t = createBSTFromArgs("b", "a", "c");
        BinaryTree<String> tExpected = createBSTFromArgs("b", "a", "c");
        /*
         * Call method under test
         */
        boolean inTree = BinarySearchTreeMethods.isInTree(t, "a");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(true, inTree);
        assertEquals(tExpected, t);
    }

    @Test
    public final void testIsInTreeFalse() {
        BinaryTree<String> t = createBSTFromArgs("b", "a", "c");
        BinaryTree<String> tExpected = createBSTFromArgs("b", "a", "c");
        boolean inTree = BinarySearchTreeMethods.isInTree(t, "d");
        assertEquals(false, inTree);
        assertEquals(tExpected, t);
    }

    @Test
    public final void testRemoveSmallest_Root_LeavingEmpty() {
        BinaryTree<String> t = createBSTFromArgs("b");
        BinaryTree<String> tExpected = createBSTFromArgs();
        String removed = BinarySearchTreeMethods.removeSmallest(t);
        assertEquals("b", removed);
        assertEquals(tExpected, t);
    }

    @Test
    public final void testRemoveSmallest_Root_NotLeavingEmpty() {
        BinaryTree<String> t = createBSTFromArgs("b", "c");
        BinaryTree<String> tExpected = createBSTFromArgs("c");
        String removed = BinarySearchTreeMethods.removeSmallest(t);
        assertEquals("b", removed);
        assertEquals(tExpected, t);
    }

    @Test
    public final void testRemoveSmallest_LeftHeight2() {
        BinaryTree<String> t = createBSTFromArgs("b", "a", "c");
        BinaryTree<String> tExpected = createBSTFromArgs("b", "c");
        String removed = BinarySearchTreeMethods.removeSmallest(t);
        assertEquals("a", removed);
        assertEquals(tExpected, t);
    }

    @Test
    public final void testRemoveSmallest_LeftHeight3() {
        BinaryTree<String> t = createBSTFromArgs("c", "b", "d", "a");
        BinaryTree<String> tExpected = createBSTFromArgs("c", "b", "d");
        String removed = BinarySearchTreeMethods.removeSmallest(t);
        assertEquals("a", removed);
        assertEquals(tExpected, t);
    }

}
