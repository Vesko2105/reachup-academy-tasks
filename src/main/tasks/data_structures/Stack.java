package tasks.data_structures;

public class Stack<E> {
    DoubleLinkedList<E> list;

    public Stack() {
        this.list = new DoubleLinkedList<>();
    }

    E pop() {
        return list.removeLast();
    }

    void push(E element) {
        list.addLast(element);
    }

    E peek() {
        return list.get(list.size() - 1);
    }

    boolean isEmpty() {
        return list.isEmpty();
    }
    int size() {
        return list.size();
    }

}
