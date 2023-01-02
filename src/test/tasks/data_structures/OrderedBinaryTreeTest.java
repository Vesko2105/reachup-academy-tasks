package tasks.data_structures;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

// TODO: 28.12.2022 г. Write some f-ing tests
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
        Assertions.assertEquals( "[1, 2, 5, 6, 7, 9, 12]", tree.toString(TraverseStrategy.DFSLPR));
        Assertions.assertEquals( "[12, 9, 7, 6, 5, 2, 1]", tree.toString(TraverseStrategy.DFSRPL));
        Assertions.assertEquals("[5, 2, 12, 1, 7, 6, 9]", tree.toString(TraverseStrategy.BFS));
        tree.remove(7);
        Assertions.assertEquals( "[1, 2, 5, 6, 9, 12]", tree.toString(TraverseStrategy.DFSLPR));
        Assertions.assertEquals( "[12, 9, 6, 5, 2, 1]", tree.toString(TraverseStrategy.DFSRPL));
        Assertions.assertEquals("[5, 2, 12, 1, 9, 6]", tree.toString(TraverseStrategy.BFS));
        tree.add(7);
        Assertions.assertEquals( "[1, 2, 5, 6, 7, 9, 12]", tree.toString(TraverseStrategy.DFSLPR));
        Assertions.assertEquals( "[12, 9, 7, 6, 5, 2, 1]", tree.toString(TraverseStrategy.DFSRPL));
        Assertions.assertEquals("[5, 2, 12, 1, 9, 6, 7]", tree.toString(TraverseStrategy.BFS));
        tree.remove(5);
        tree.add(8);
        Assertions.assertEquals( "[1, 2, 6, 7, 8, 9, 12]", tree.toString(TraverseStrategy.DFSLPR));
        Assertions.assertEquals( "[12, 9, 8, 7, 6, 2, 1]", tree.toString(TraverseStrategy.DFSRPL));
        Assertions.assertEquals("[6, 2, 12, 1, 9, 7, 8]", tree.toString(TraverseStrategy.BFS));
    }
}
