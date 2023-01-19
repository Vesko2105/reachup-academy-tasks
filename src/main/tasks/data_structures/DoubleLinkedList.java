package tasks.data_structures;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.StringJoiner;

//Оптимизация - може да се търси от началото или от края, в зависимост от това в коя част на списъка се търси

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
                nextNode = nextNode.getNext();
                return nodeToReturn.getElement();
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
                nextNode = nextNode.getNext();
                return nodeToReturn;
            } else {
                throw new NoSuchElementException();
            }
        }

        public Node<E> previous() {
            if (hasNext()) {
                Node<E> nodeToReturn = nextNode;
                nextNode = nextNode.getPrevious();
                return nodeToReturn;
            } else {
                throw new NoSuchElementException();
            }
        }
    } 
    

    private Node<E> front;

    private Node<E> back;

    private int size;

    public DoubleLinkedList() {
        front = null;
        back = null;
        size = 0;
    } 

    public static DoubleLinkedList<Integer> of(Integer... elements) {
        DoubleLinkedList<Integer> newList = new DoubleLinkedList<>();
        for (Integer element : elements) {
            newList.add(element);
        }
        return newList;
    }

    public DoubleLinkedList<E> subList(int index, int size) {
        if (index + size >= this.size) {
            throw new IndexOutOfBoundsException();
        }
        DoubleLinkedList<E> subList = new DoubleLinkedList<>();
        Iterator<Node<E>> iterator = nodeIterator(index);
        int currentIndex = index;
        while (iterator.hasNext() && currentIndex < index + size) {
            subList.add(iterator.next().getElement());
            currentIndex++;
        }
        return subList;
    }

    @Override
    public Iterator<E> iterator() {
        return new DoubleLinkedListIterator(front);
    } 

    public Iterator<E> iterator(int index) {
        return new DoubleLinkedListIterator(getNode(index));
    } 

    private NodeIterator nodeIterator() {
        return new NodeIterator(front);
    } 
    private NodeIterator nodeIterator(int index) {
        return new NodeIterator(getNode(index));
    }

    private Node<E> getNode(int index) {
        if (size < 1) {
            throw new NoSuchElementException();
        } else if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        } else if (index == 0) {
            return front;
        } else if (index == size - 1) {
            return back;
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
        } else if (searched != null && searched.equals(front)) {
            return 0;
        } else if (searched != null && searched.equals(back)){
            return size - 1;
        } else {
            NodeIterator iterator = nodeIterator();
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

    private int findLastNode(Node<E> searched) {
        if (size < 1) {
            throw new NoSuchElementException();
        } else if (searched != null && searched.equals(front)) {
            return 0;
        } else if (searched != null && searched.equals(back)){
            return size - 1;
        } else {
            NodeIterator iterator = nodeIterator();
            int index = 0;
            while (iterator.hasNext()) {
                if (iterator.previous().equals(searched)) {
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
            front = newNode;
            back = newNode;
        } else if (size == 1) {
            newNode.setNext(back);
            back.setPrevious(newNode);
            front = newNode;
        } else {
            newNode.setNext(front);
            front.setPrevious(newNode);
            front = newNode;
        }
        size++;
    }

    public int addLast(E element) {
        Node<E> newNode = new Node<>(element);
        if (size == 0) {
            front = newNode;
            back = newNode;
        } else if (size == 1) {
            newNode.setPrevious(front);
            front.setNext(newNode);
            back = newNode;
        } else {
            newNode.setPrevious(back);
            back.setNext(newNode);
            back = newNode;
        }
        return size++;
    }

    public void add(int index, E element) {
        if (index == 0) {
            addFirst(element);
        } else if (index == size) {
            addLast(element);
        } else {
            Node<E> currentNodeAtIndex = getNode(index);

            if (currentNodeAtIndex != null) {
                Node<E> newNode = new Node<>(element);
                newNode.setNext(currentNodeAtIndex);
                newNode.setPrevious(currentNodeAtIndex.getPrevious());
                currentNodeAtIndex.getPrevious().setNext(newNode);
                currentNodeAtIndex.setPrevious(newNode);
                size++;
            }
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
        Node<E> nodeToRemove = front;
        if (size == 1) {
            front = null;
            back = null;
            size = 0;
        } else if (size > 0) {
            front = front.getNext();
            size--;
        } else {
            throw new NoSuchElementException();
        }
        return nodeToRemove.getElement();
    }

    public E removeLast() {
        if (size < 2) {
            return removeFirst();
        } else {
            Node<E> nodeToRemove = back;
            back = back.getPrevious();
            back.setNext(null);
            size--;
            return nodeToRemove.getElement();
        }
    }

    public E remove(int index) {
        Node<E> nodeToRemove = getNode(index);
        if (index == 0) {
            return removeFirst();
        } else if (index == size - 1) {
            return removeLast();
        } else {
            nodeToRemove.getNext().setPrevious(nodeToRemove.getPrevious());
            nodeToRemove.getPrevious().setNext(nodeToRemove.getNext());
            size--;
            return nodeToRemove.getElement();
        }
    }

    public E remove() {
        return removeLast();
    }

    public int findFirst(E searched) {
        Node<E> searchedNode = new Node<>(searched);
        return findFirstNode(searchedNode);
    }

    public int findLast(E searched) {
        Node<E> searchedNode = new Node<>(searched);
        return findLastNode(searchedNode);
    }

    public E get(int index) {
        Node<E> searchedNode = getNode(index);
        return searchedNode != null ? searchedNode.getElement() : null;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void clear() {
        this.front = null;
        this.back = null;
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