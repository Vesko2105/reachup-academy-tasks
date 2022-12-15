package tasks.data_structures;


import java.util.Objects;

class Node<T> {
    public final T element;
    public Node<T> next;
    public Node<T> previous;

    public Node(T element) {
        this.element = element;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        } else if (!(other instanceof Node<?> otherNode)) {
            return false;
        } else if (this.element != null) {
            return this.element.equals(otherNode.element);
        } else {
            return otherNode.element == null;
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(next, previous, element);
    }

    @Override
    public String toString() {
        return element == null ? "null" : element.toString();
    }
}
