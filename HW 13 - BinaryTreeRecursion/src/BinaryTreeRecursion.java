import components.binarytree.BinaryTree;
import components.binarytree.BinaryTree1;

/**
 * Put a short phrase describing the program here.
 *
 * @author Put your name here
 *
 */
public final class BinaryTreeRecursion {

    /**
     * Returns the {@code String} prefix representation of the given
     * {@code BinaryTree<T>}.
     *
     * @param <T>
     *            the type of the {@code BinaryTree} node labels
     * @param t
     *            the {@code BinaryTree} to convert to a {@code String}
     * @return the prefix representation of {@code t}
     * @ensures treeToString = [the String prefix representation of t]
     */
    public static <T> String treeToString(BinaryTree<T> t) {
        String tree = "";
        T root = t.root();
        tree += root.toString() + "(";
        BinaryTree<T> left = t.newInstance();
        BinaryTree<T> right = t.newInstance();
        t.disassemble(left, right);

        if (left != null) {
            treeToString(left);
        } else {
            tree += "()";
        }

        if (right != null) {
            treeToString(right);
        } else {
            tree += "()";
        }

        tree += ")";
        return tree;
    }

    /**
     * Returns a copy of the the given {@code BinaryTree}.
     *
     * @param t
     *            the {@code BinaryTree} to copy
     * @return a copy of the given {@code BinaryTree}
     * @ensures copy = t
     */
    public static BinaryTree<Integer> copy(BinaryTree<Integer> t) {
        BinaryTree<Integer> copy = new BinaryTree1<Integer>();
        BinaryTree<Integer> left = t.newInstance();
        BinaryTree<Integer> right = t.newInstance();
        if (t.height() > 0) {
            int root = t.disassemble(left, right);
            copy.assemble(root, copy(left), copy(right));
            t.assemble(root, left, right);
        }
        return copy;
    }
}
