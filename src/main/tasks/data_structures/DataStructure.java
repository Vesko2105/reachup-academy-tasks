package tasks.data_structures;

public interface DataStructure<E> {
    boolean add(E element);
    E remove();
    int size();
    boolean isEmpty();

}
