package tasks.data_structures;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class PriorityQueueTest {
    PriorityQueue<Integer> queue;
    List<Integer> expected;

    @BeforeEach
    void setup() {
        expected = new ArrayList<>();
        Random random = new Random();
        int length = random.nextInt(10, 101);
        int limit = 1001;
        for (int i = 0; i < length; i++) {
            expected.add(random.nextInt(0, limit));
        }
    }

    @Test
    @DisplayName("Smoke test")
    void smokeTest() {
        queue = new PriorityQueue<>(Integer::compareTo);
        for (Integer number : expected) {
            queue.push(number);
        }
        assertEquals(expected.size(), queue.size());
        expected.sort(Integer::compareTo);
        Integer actualInteger = queue.top();
        assertSame(expected.get(0), actualInteger);
        for (Integer expectedInteger : expected) {
            actualInteger = queue.pop();
            assertSame(expectedInteger, actualInteger);
        }
        assertTrue(queue.isEmpty());
    }

    @Test
    @DisplayName("Smoke test 2")
    void smokeTest2() {
        queue = new PriorityQueue<>(Integer::compareTo, Order.DESCENDING);
        for (Integer number : expected) {
            queue.push(number);
        }
        assertEquals(expected.size(), queue.size());
        expected.sort(Comparator.reverseOrder());
        Integer actualInteger = queue.top();
        assertSame(expected.get(0), actualInteger);
        for (Integer expectedInteger : expected) {
            actualInteger = queue.pop();
            assertSame(expectedInteger, actualInteger);
        }
        assertTrue(queue.isEmpty());
    }
}
