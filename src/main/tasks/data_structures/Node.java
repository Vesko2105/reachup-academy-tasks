package tasks.data_structures;

import java.util.Objects;

class Node<T> {
    private T element;
    private Node<T> next;
    private Node<T> previous;

    public Node(T element) {
        this.element = element;
        next = null;
        previous = null;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        } else if (!(other instanceof Node<?> otherNode)) {
            return false;
        } else {
            return this.element.equals(otherNode.element);
        }
    }

    public T getElement() {
        return element;
    }

    public void setElement(T element) {
        this.element = element;
    }

    public Node<T> getNext() {
        return next;
    }

    public void setNext(Node<T> next) {
        this.next = next;
    }

    public Node<T> getPrevious() {
        return previous;
    }

    public void setPrevious(Node<T> previous) {
        this.previous = previous;
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
