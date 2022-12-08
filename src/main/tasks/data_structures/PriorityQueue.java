package tasks.data_structures;

import java.util.Comparator;
import java.util.Iterator;

public class PriorityQueue<E> extends Queue<E> {
    private Comparator<E> comparator;

    public PriorityQueue(Comparator<E> comparator) {
        super();
        this.comparator = comparator;
    }

    private int findIndex(E element) {
        Iterator<E> iterator = super.list.iterator();
        int index = 0;
        while (iterator.hasNext() && comparator.compare(element, iterator.next()) >= 0) {
            index++;
        }
        return index;
    }
    @Override
    public void push(E element) {
        super.list.add(findIndex(element), element);
    }
}
