import java.util.Iterator;

import components.sequence.Sequence;
import components.tree.Tree;

/**
 * Put a short phrase describing the program here.
 *
 * @author Jay Shin
 *
 */
public final class TreeAndRecursion {

    /**
     * Returns the size of the given {@code Tree<T>}.
     *
     * @param <T>
     *            the type of the {@code Tree} node labels
     * @param t
     *            the {@code Tree} whose size to return
     * @return the size of the given {@code Tree}
     * @ensures size = |t|
     */
    public static <T> int sizeRecursive(Tree<T> t) {
        int size = 1;
        Sequence<Tree<T>> children = t.newSequenceOfTree();
        T root = t.disassemble(children);
        if (t.height() > 0) {
            for (Tree<T> tree : children) {
                size += sizeRecursive(tree);
            }
        }
        t.assemble(root, children);
        return size;
    }

    /**
     * Returns the size of the given {@code Tree<T>}.
     *
     * @param <T>
     *            the type of the {@code Tree} node labels
     * @param t
     *            the {@code Tree} whose size to return
     * @return the size of the given {@code Tree}
     * @ensures size = |t|
     */
    public static <T> int sizeNonrecursive(Tree<T> t) {
        int size = 0;
        Iterator<T> iter = t.iterator();
        while (iter.hasNext()) {
            size++;
        }
        return size;
    }

    /**
     * Returns the height of the given {@code Tree<T>}.
     *
     * @param <T>
     *            the type of the {@code Tree} node labels
     * @param t
     *            the {@code Tree} whose height to return
     * @return the height of the given {@code Tree}
     * @ensures height = ht(t)
     */
    public static <T> int height(Tree<T> t) {
        int height = 0;
        int maxHeight = 0;
        Sequence<Tree<T>> children = t.newSequenceOfTree();
        if (t.size() > 0) {
            T root = t.disassemble(children);
            height++;
            for (Tree<T> tree : children) {
                height = 1;
                height += height(tree);
                if (height > maxHeight) {
                    maxHeight = height;
                }
            }
            t.assemble(root, children);
        }
        return height;
    }

    /**
     * Returns the largest integer in the given {@code Tree<Integer>}.
     *
     * @param t
     *            the {@code Tree<Integer>} whose largest integer to return
     * @return the largest integer in the given {@code Tree<Integer>}
     * @requires |t| > 0
     * @ensures <pre>
     * max is in labels(t)  and
     * for all i: integer where (i is in labels(t)) (i <= max)
     * </pre>
     */
    public static int max(Tree<Integer> t) {
        int max = t.root();
        Sequence<Tree<Integer>> children = t.newSequenceOfTree();
        if (t.size() > 0) {
            int root = t.disassemble(children);
            for (Tree<Integer> tree : children) {
                max = Math.max(max(tree), max);
            }
            t.assemble(root, children);
        }
        return max;
    }
}