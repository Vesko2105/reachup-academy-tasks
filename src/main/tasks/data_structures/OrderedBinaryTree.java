package tasks.data_structures;

import java.util.Collection;
import java.util.Iterator;

public class OrderedBinaryTree<E extends Comparable<E>> implements Collection<E>, Iterable<E> {

    int size;
    Node<E> root;

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }

    @Override
    public boolean add(E e) {
        root = addRecursively(e, root);
        return true;
    }

    private Node<E> addRecursively(E e, Node<E> currentNode) {
        if (currentNode == null) {
            size++;
            return new Node<>(e);
        } else if (currentNode.element.compareTo(e) > 0) {
            currentNode.previous = addRecursively(e, currentNode.previous);
        } else if (currentNode.element.compareTo(e) < 0) {
            currentNode.next = addRecursively(e, currentNode.next);
        }
        return currentNode;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public String toString() {
        return toStringDFS(root, Order.ASCENDING).trim();
    }

    public String toString(Order order) {
        return switch (order) {
            case ASCENDING -> toStringDFS(root, Order.ASCENDING).trim();
            case DESCENDING -> toStringDFS(root, Order.DESCENDING).trim();
            case ROW_BY_ROW -> toStringBFS(root);
        };
    }

    private String toStringDFS(Node<E> startingNode, Order order) {
        StringBuilder stringBuilder = new StringBuilder();
        if (startingNode != null) {
            if (order == Order.ASCENDING) {
                stringBuilder.append(toStringDFS(startingNode.previous, order));
                stringBuilder.append(startingNode + " ");
                stringBuilder.append(toStringDFS(startingNode.next, order));
            } else {
                stringBuilder.append(toStringDFS(startingNode.next, order));
                stringBuilder.append(startingNode + " ");
                stringBuilder.append(toStringDFS(startingNode.previous, order));
            }
        }
        return stringBuilder.toString();
    }

    private String toStringBFS(Node<E> startingNode) {
        Queue<Node<E>> queue = new Queue<>();
        StringBuilder stringBuilder = new StringBuilder();
        queue.push(startingNode);
        while ((!queue.isEmpty())) {
            Node<E> currentNode = queue.pop();
            stringBuilder.append(currentNode + " ");
            if (currentNode.previous != null) {
                queue.push(currentNode.previous);
            }
            if (currentNode.next != null) {
                queue.push(currentNode.next);
            }
        }
        return stringBuilder.toString().trim();
    }
}