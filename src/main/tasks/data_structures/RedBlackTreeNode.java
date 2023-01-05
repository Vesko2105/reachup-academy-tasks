package tasks.data_structures;

import java.util.Objects;

public class RedBlackTreeNode<E> extends Node<E> {
    boolean isRed;

    public RedBlackTreeNode(E element) {
        super(element);
        isRed = true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        RedBlackTreeNode<?> that = (RedBlackTreeNode<?>) o;
        return isRed == that.isRed;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), isRed);
    }
}
