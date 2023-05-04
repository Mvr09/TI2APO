package model;

public class BinarySearchTree<T extends Comparable<T>> {
    private TreeNode<T> root;

    public BinarySearchTree() {
        root = null;
    }

    public void insert(T item) {
        root = insertRec(root, item);
    }

    private TreeNode<T> insertRec(TreeNode<T> root, T item) {
        if (root == null) {
            root = new TreeNode<>(item);
            return root;
        }

        if (item.compareTo(root.item) < 0) {
            root.left = insertRec(root.left, item);
        } else if (item.compareTo(root.item) > 0) {
            root.right = insertRec(root.right, item);
        }

        return root;
    }

    public void remove(T item) {
        root = removeRec(root, item);
    }

    private TreeNode<T> removeRec(TreeNode<T> root, T item) {
        if (root == null) {
            return root;
        }

        int cmp = item.compareTo(root.item);
        if (cmp < 0) {
            root.left = removeRec(root.left, item);
        } else if (cmp > 0) {
            root.right = removeRec(root.right, item);
        } else {
            if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            }

            root.item = minValue(root.right);
            root.right = removeRec(root.right, root.item);
        }

        return root;
    }

    private T minValue(TreeNode<T> root) {
        T minValue = root.item;
        while (root.left != null) {
            minValue = root.left.item;
            root = root.left;
        }
        return minValue;
    }

    public T search(T item) {
        return searchRec(root, item);
    }

    private T searchRec(TreeNode<T> root, T item) {
        if (root == null || root.item.compareTo(item) == 0) {
            return (root != null) ? root.item : null;
        }

        if (item.compareTo(root.item) < 0) {
            return searchRec(root.left, item);
        }

        return searchRec(root.right, item);
    }
}
