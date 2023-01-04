package tasks.puzzle.tile_puzzle;

import java.util.HashMap;

public class VisitedNodes extends HashMap<TilePuzzleNode, Integer> {
    public boolean shouldBeChecked(TilePuzzleNode node) {
        return !super.containsKey(node) || node.stepAt <= super.get(node);
    }

    public Integer put(TilePuzzleNode node) {
        return super.put(node, node.stepAt);
    }
}
