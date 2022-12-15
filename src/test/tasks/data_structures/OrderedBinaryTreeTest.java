package tasks.data_structures;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class OrderedBinaryTreeTest {
    @Test
    void smokeTest() {
        OrderedBinaryTree<Integer> tree = new OrderedBinaryTree<>();
        tree.add(5);
        tree.add(2);
        tree.add(1);
        tree.add(12);
        tree.add(7);
        tree.add(6);
        tree.add(1);
        tree.add(9);
        Assertions.assertEquals(7, tree.size());
        Assertions.assertEquals( "1 2 5 6 7 9 12", tree.toString(Order.ASCENDING));
        Assertions.assertEquals( "12 9 7 6 5 2 1", tree.toString(Order.DESCENDING));
        Assertions.assertEquals("5 2 12 1 7 6 9", tree.toString(Order.ROW_BY_ROW));
    }
}
