package tasks;

import java.util.Objects;

public class Pair<T> {
    T value1;
    T value2;

    public Pair(T value1, T value2) {
        this.value1 = value1;
        this.value2 = value2;
    }

    public T getValue1() {
        return value1;
    }

    public void setValue1(T value1) {
        this.value1 = value1;
    }

    public T getValue2() {
        return value2;
    }

    public void setValue2(T value2) {
        this.value2 = value2;
    }

    @Override
    public String toString() {
        return String.format("(%s, %s)", value1.toString(), value2.toString());
    }

    @Override
    public int hashCode() {
        return Objects.hash(value1, value2);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Pair<?> pair = (Pair<?>) o;
        return Objects.equals(value1, pair.value1) && Objects.equals(value2, pair.value2);
    }
}