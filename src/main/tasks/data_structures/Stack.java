package tasks.data_structures;

public class Stack<E> implements DataStructure<E>{
    DoubleLinkedList<E> list;

    public Stack() {
        this.list = new DoubleLinkedList<>();
    }

    public E pop() {
        return list.removeLast();
    }

    public void push(E element) {
        list.addLast(element);
    }

    public E peek() {
        return list.get(list.size() - 1);
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }
    public int size() {
        return list.size();
    }

    @Override
    public boolean add(E element) {
        push(element);
        return true;
    }

    @Override
    public E remove() {
        return pop();
    }
}
