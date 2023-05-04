package model;

public class TreeNode<T> {
    T item;
    TreeNode<T> left;
    TreeNode<T> right;

    public TreeNode(T item) {
        this.item = item;
        left = null;
        right = null;
    }
}
