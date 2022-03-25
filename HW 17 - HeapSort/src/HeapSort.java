import components.binarytree.BinaryTree;

/**
 *
 * @author Jay Shin
 *
 */
public final class HeapSort {

    /**
     * Checks if the given {@code BinaryTree<Integer>} satisfies the heap
     * ordering property according to the <= relation.
     *
     * @param t
     *            the binary tree
     * @return true if the given tree satisfies the heap ordering property;
     *         false otherwise
     * @ensures <pre>
     * satisfiesHeapOrdering = [t satisfies the heap ordering property]
     * </pre>
     */
    private static boolean satisfiesHeapOrdering(BinaryTree<Integer> t) {
        BinaryTree<Integer> temp = t.newInstance();
        BinaryTree<Integer> left = t.newInstance();
        BinaryTree<Integer> right = t.newInstance();
        boolean resultLeft = true;
        boolean resultRight = true;
        while (t.size() > 1) {
            int root = t.disassemble(left, right);
            if (root > left.root() || root > right.root()) {
                return false;
            }
            resultLeft = satisfiesHeapOrdering(left);
            resultRight = satisfiesHeapOrdering(right);
            if (!(resultLeft && resultRight)) {
                return false;
            }
            t.assemble(root, left, right);
        }
        return true;
    }
}
