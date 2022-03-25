import components.binarytree.BinaryTree;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Utility class with implementation of {@code BinaryTree} static, generic
 * methods height and isInTree.
 *
 * @author Jay Shin
 *
 */
public final class BinaryTreeMethods {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private BinaryTreeMethods() {
    }

    /**
     * Returns the size of the given {@code BinaryTree<T>} recursively.
     *
     * @param <T>
     *            the type of the {@code BinaryTree} node labels  
     * @param t
     *            the {@code BinaryTree} whose size to return  
     * @return the size of the given {@code BinaryTree}  
     * @ensures size = |t|  
     */
    public static <T> int sizeRecursive(BinaryTree<T> t) {
        int size = 0;
        T root;
        BinaryTree<T> left;
        BinaryTree<T> right;
        if (t.height() > 0) {
            left = t.newInstance();
            right = t.newInstance();
            root = t.disassemble(left, right);
            size += sizeRecursive(left);
            size += sizeRecursive(right);
        } else {
            return 1;
        }
        t.assemble(root, left, right);
        return size;
    }

    /**
     * Returns the size of the given {@code BinaryTree<T>} non-recursively.
     *
     * @param <T>
     *            the type of the {@code BinaryTree} node labels  
     * @param t
     *            the {@code BinaryTree} whose size to return  
     * @return the size of the given {@code BinaryTree}  
     * @ensures size = |t|  
     */
    public static <T> int sizeNonRecursive(BinaryTree<T> t) {
        int size = 0;
        for (T node : t) {
            size++;
        }
        return size;
    }

    /**
     * Returns the height of the given {@code BinaryTree<T>}.
     *
     * @param <T>
     *            the type of the {@code BinaryTree} node labels
     * @param t
     *            the {@code BinaryTree} whose height to return
     * @return the height of the given {@code BinaryTree}
     * @ensures height = ht(t)
     */
    public static <T> int height(BinaryTree<T> t) {
        assert t != null : "Violation of: t is not null";
        int height = 0;
        int leftHeight = 0;
        int rightHeight = 0;
        BinaryTree<T> left = t.newInstance();
        BinaryTree<T> right = t.newInstance();

        if (t.size() > 0) {
            T root = t.disassemble(left, right);
            height++;
            leftHeight += height(left);
            rightHeight += height(right);
            if (leftHeight > rightHeight) {
                height += leftHeight;
            } else {
                height += rightHeight;
            }
            t.assemble(root, left, right);

        } else {
            return height;
        }
        return height;
    }

    /**
     * Returns true if the given {@code T} is in the given {@code BinaryTree<T>}
     * or false otherwise.
     *
     * @param <T>
     *            the type of the {@code BinaryTree} node labels
     * @param t
     *            the {@code BinaryTree} to search
     * @param x
     *            the {@code T} to search for
     * @return true if the given {@code T} is in the given {@code BinaryTree},
     *         false otherwise
     * @ensures isInTree = [true if x is in t, false otherwise]
     */
    public static <T> boolean isInTreeNonRecursive(BinaryTree<T> t, T x) {
        assert t != null : "Violation of: t is not null";
        assert x != null : "Violation of: x is not null";
        for (T node : t) {
            if (node.equals(x)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns true if the given {@code T} is in the given {@code BinaryTree<T>}
     * or false otherwise.
     *
     * @param <T>
     *            the type of the {@code BinaryTree} node labels
     * @param t
     *            the {@code BinaryTree} to search
     * @param x
     *            the {@code T} to search for
     * @return true if the given {@code T} is in the given {@code BinaryTree},
     *         false otherwise
     * @ensures isInTree = [true if x is in t, false otherwise]
     */
    public static <T> boolean isInTreeRecursive(BinaryTree<T> t, T x) {
        assert t != null : "Violation of: t is not null";
        assert x != null : "Violation of: x is not null";
        BinaryTree<T> left = t.newInstance();
        BinaryTree<T> right = t.newInstance();
        boolean result = false;
        boolean leftResult = false;
        boolean rightResult = false;
        if (t.size() > 0) {
            T root = t.disassemble(left, right);
            if (root.equals(x)) {
                result = true;
            }
            leftResult = isInTreeRecursive(left, x);
            rightResult = isInTreeRecursive(right, x);
            if (leftResult || rightResult) {
                result = true;
            }
            t.assemble(root, left, right);
        }
        return result;
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();

        out.print("Input a tree (or just press Enter to terminate): ");
        String str = in.nextLine();
        while (str.length() > 0) {
            BinaryTree<String> t = BinaryTreeUtility.treeFromString(str);
            out.println("Tree = " + BinaryTreeUtility.treeToString(t));
            out.println("Height = " + height(t));
            out.print("  Input a label to search "
                    + "(or just press Enter to input a new tree): ");
            String label = in.nextLine();
            while (label.length() > 0) {
                if (isInTreeNonRecursive(t, label)) {
                    out.println("    \"" + label + "\" is in the tree");
                } else {
                    out.println("    \"" + label + "\" is not in the tree");
                }
                out.print("  Input a label to search "
                        + "(or just press Enter to input a new tree): ");
                label = in.nextLine();
            }
            out.println();
            out.print("Input a tree (or just press Enter to terminate): ");
            str = in.nextLine();
        }

        in.close();
        out.close();
    }

}
