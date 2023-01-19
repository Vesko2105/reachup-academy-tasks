package tasks.data_structures;

import java.util.Objects;

class TreeNode<T extends Comparable<T>> implements Comparable<TreeNode<T>> {
    public T element;
    public TreeNode<T> left;
    public TreeNode<T> right;
    public TreeNode<T> parent;

    public TreeNode(T element, TreeNode<T> parent) {
        this.element = element;
        this.parent = parent;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        } else if (!(other instanceof TreeNode<?> otherNode)) {
            return false;
        } else if (this.element != null) {
            return this.element.equals(otherNode.element);
        } else {
            return otherNode.element == null;
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(left, right, parent, element);
    }

    @Override
    public String toString() {
        return element == null ? "null" : element.toString();
    }


    @Override
    public int compareTo(TreeNode<T> otherNode) {
        return this.element.compareTo(otherNode.element);
    }

    public boolean isLeaf() {
        return left == null && right == null;
    }
}
