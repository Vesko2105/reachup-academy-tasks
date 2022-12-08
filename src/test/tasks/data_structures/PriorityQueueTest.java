package tasks.data_structures;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
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
        assertSame(expected.get(expected.size() - 1), actualInteger);
        expected.sort((i1, i2) -> i1.compareTo(i2) * -1);
        for (Integer expectedInteger : expected) {
            actualInteger = queue.pop();
            assertSame(expectedInteger, actualInteger);
        }
        assertTrue(queue.isEmpty());
    }
}
