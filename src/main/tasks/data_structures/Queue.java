package tasks.data_structures;

public class Queue<E> implements DataStructure<E>{
    protected DoubleLinkedList<E> list;

    public Queue() {
        this.list = new DoubleLinkedList<>();
    }

    public void push(E element) {
        list.addFirst(element);
    }

    public E pop() {
        return list.removeLast();
    }

    public E top() {
        return list.get(list.size() - 1);
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public int find(E element) {
        return list.findLast(element);
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
