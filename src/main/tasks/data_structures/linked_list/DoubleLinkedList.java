package tasks.data_structures.linked_list;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.StringJoiner;

//Оптимизация - може да се търси от началото или от края, в зависимост от това в коя част на списъка се търси
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

public class DoubleLinkedList<E> implements Iterable<E> {

    private class DoubleLinkedListIterator implements Iterator<E> {
        Node<E> nextNode;

        public DoubleLinkedListIterator(Node<E> node) {
            this.nextNode = node;
        }

        @Override
        public boolean hasNext() {
            return nextNode != null;
        }

        @Override
        public E next() {
            if (hasNext()) {
                Node<E> nodeToReturn = nextNode;
                nextNode = nextNode.next;
                return nodeToReturn.element;
            } else {
                throw new NoSuchElementException();
            }
        }
    }

    private class NodeIterator implements Iterator<Node<E>> {
        Node<E> nextNode;

        public NodeIterator(Node<E> node) {
            this.nextNode = node;
        }

        @Override
        public boolean hasNext() {
            return nextNode != null;
        }

        @Override
        public Node<E> next() {
            if (hasNext()) {
                Node<E> nodeToReturn = nextNode;
                nextNode = nextNode.next;
                return nodeToReturn;
            } else {
                throw new NoSuchElementException();
            }
        }
    }

    public Iterator<E> iterator(int index) {
        return new DoubleLinkedListIterator(getNode(index));
    }

    private Node<E> firstNodePointer;

    private Node<E> lastNodePointer;

    private int size;

    public DoubleLinkedList() {
        firstNodePointer = null;
        lastNodePointer = null;
        size = 0;
    }

    public static DoubleLinkedList<Integer> of(Integer... elements) {
        DoubleLinkedList<Integer> newList = new DoubleLinkedList<>();
        for (Integer element : elements) {
            newList.add(element);
        }
        return newList;
    }

    @Override
    public Iterator<E> iterator() {
        return new DoubleLinkedListIterator(firstNodePointer);
    }

    private Iterator<Node<E>> nodeIterator() {
        return new NodeIterator(firstNodePointer);
    }

    private Node<E> getNode(int index) {
        if (size < 1) {
            throw new NoSuchElementException();
        } else if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        } else if (index == 0) {
            return firstNodePointer;
        } else if (index == size - 1) {
            return lastNodePointer;
        } else {
            Iterator<Node<E>> iterator = nodeIterator();
            Node<E> nodeToReturn = null;
            for (int i = 0; i <= index; i++) {
                if (iterator.hasNext()) {
                    nodeToReturn = iterator.next();
                }
            }
            return nodeToReturn;
        }
    }

    private int findFirstNode(Node<E> searched) {
        if (size < 1) {
            throw new NoSuchElementException();
        } else if (searched != null && searched.equals(firstNodePointer)) {
            return 0;
        } else if (searched != null && searched.equals(lastNodePointer)){
            return size - 1;
        } else {
            Iterator<Node<E>> iterator = nodeIterator();
            int index = 0;
            while (iterator.hasNext()) {
                if (iterator.next().equals(searched)) {
                    return index;
                }
                index++;
            }
            return -1;
        }
    }

    public int add(E element) {
        return addLast(element);
    }

    public void addFirst(E element) {
        Node<E> newNode = new Node<>(element);
        if (size == 0) {
            firstNodePointer = newNode;
            lastNodePointer = newNode;
        } else if (size == 1) {
            newNode.next = lastNodePointer;
            lastNodePointer.previous = newNode;
            firstNodePointer = newNode;
        } else {
            newNode.next = firstNodePointer;
            firstNodePointer.previous = newNode;
            firstNodePointer = newNode;
        }
        size++;
    }

    public int addLast(E element) {
        Node<E> newNode = new Node<>(element);
        if (size == 0) {
            firstNodePointer = newNode;
            lastNodePointer = newNode;
        } else if (size == 1) {
            newNode.previous = firstNodePointer;
            firstNodePointer.next = newNode;
            lastNodePointer = newNode;
        } else {
            newNode.previous = lastNodePointer;
            lastNodePointer.next = newNode;
            lastNodePointer = newNode;
        }
        return size++;
    }

    public void add(int index, E element) {
        if (index == 0) {
            addFirst(element);
            size++;
        } else if (index == size) {
            addLast(element);
            size++;
        }

        Node<E> currentNodeAtIndex = getNode(index);

        if (currentNodeAtIndex != null) {
            Node<E> newNode = new Node<>(element);
            newNode.next = currentNodeAtIndex;
            newNode.previous = currentNodeAtIndex.previous;
            currentNodeAtIndex.previous.next = newNode;
            currentNodeAtIndex.previous = newNode;
            size++;
        }
    }

    public int remove(E element) {
        int searchedIndex = findFirst(element);
        try {
            remove(searchedIndex);
            return searchedIndex;
        } catch (IndexOutOfBoundsException e) {
            return searchedIndex;
        }
    }

    public E removeFirst() {
        if (size > 0) {
            Node<E> nodeToRemove = firstNodePointer;
            firstNodePointer = firstNodePointer.next;
            size--;
            return nodeToRemove.element;
        } else {
            throw new NoSuchElementException();
        }
    }

    public E removeLast() {
        if (size < 2) {
            return removeFirst();
        } else {
            Node<E> nodeToRemove = lastNodePointer;
            lastNodePointer = lastNodePointer.previous;
            lastNodePointer.next = null;
            size--;
            return nodeToRemove.element;
        }
    }

    public E remove(int index) {
        Node<E> nodeToRemove = getNode(index);
        if (index == 0) {
            return removeFirst();
        } else if (index == size - 1) {
            return removeLast();
        } else {
            nodeToRemove.next.previous = nodeToRemove.previous;
            nodeToRemove.previous.next = nodeToRemove.next;
            size--;
            return nodeToRemove.element;
        }
    }

    public E remove() {
        return removeLast();
    }

    public int findFirst(E searched) {
        Node<E> searchedNode = new Node<>(searched);
        return findFirstNode(searchedNode);
    }

    public E get(int index) {
        Node<E> searchedNode = getNode(index);
        return searchedNode != null ? searchedNode.element : null;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void clear() {
        this.firstNodePointer = null;
        this.lastNodePointer = null;
        size = 0;
    }

    @Override
    public String toString() {
        Iterator<E> iterator = iterator();
        StringJoiner stringJoiner = new StringJoiner(", ", "[", "]");
        while (iterator.hasNext()) {
            E element = iterator.next();
            stringJoiner.add(element == null ? null : element.toString());
        }
        return stringJoiner.toString();
    }
}
