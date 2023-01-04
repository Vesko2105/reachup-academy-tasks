package tasks.puzzle.tile_puzzle;


import tasks.data_structures.*;
import tasks.data_structures.PriorityQueue;
import tasks.data_structures.Queue;
import tasks.data_structures.Stack;
import tasks.puzzle.SearchMethod;

import java.util.*;

public class TilePuzzle {

    private TilePuzzleNode startingState;
    int nodesChecked;
    int maxNodes;

    private static Comparator<TilePuzzleNode> uniformCostComparator = Comparator.comparingInt(TilePuzzleNode::distanceFromGoal);

    public TilePuzzle(TilePuzzleNode startingState) {
        this.startingState = startingState;
    }

    private DataStructure<TilePuzzleNode> getDataStructure(SearchMethod searchMethod) {
        return switch (searchMethod) {
            case DFS, IDDS:
                yield new Stack<>();
            case BrFS:
                yield new Queue<>();
            case BeFS:
                yield new PriorityQueue<>(uniformCostComparator, Order.ASCENDING);
        };
    }

    private TilePuzzleNode DepthLimitedSearch(
            int depth,
            TilePuzzleNode currentNode,
            DataStructure<TilePuzzleNode> states,
            VisitedNodes visitedNodes
    ) {
        while (!currentNode.isGoal() && !states.isEmpty()) {
            currentNode = states.remove();
            if (!visitedNodes.shouldBeChecked(currentNode)) {
                continue;
            }
            visitedNodes.put(currentNode);
            nodesChecked++;
            if (currentNode.stepAt < depth) {
                currentNode.getChildren().forEach(states::add);
                maxNodes = Math.max(states.size(), maxNodes);
            }
        }
        return currentNode;
    }

    public int findSolution(SearchMethod searchMethod) {
        DataStructure<TilePuzzleNode> states = getDataStructure(searchMethod);
        VisitedNodes visitedNodes = new VisitedNodes();
        states.add(startingState);
        TilePuzzleNode currentNode = startingState;
        nodesChecked = 0;
        maxNodes = 0;
        if (searchMethod == SearchMethod.IDDS) {
            int depth = 1;
            while (!currentNode.isGoal()) {
                currentNode = DepthLimitedSearch(depth, currentNode, states, visitedNodes);
                visitedNodes.clear();
                states.add(startingState);
                depth++;
            }
        } else {
            while (!currentNode.isGoal()) {
                currentNode = states.remove();
                if (!visitedNodes.shouldBeChecked(currentNode)) {
                    continue;
                }
                visitedNodes.put(currentNode);
                nodesChecked++;
                currentNode.getChildren().forEach(states::add);
                maxNodes = Math.max(states.size(), maxNodes);
            }
        }
        printSolution(currentNode, searchMethod);
        return currentNode.stepAt;
    }

    private void printSolution(TilePuzzleNode goalNode, SearchMethod searchMethod) {
        TilePuzzleNode currentNode = goalNode;
        Stack<TilePuzzleNode> stack = new Stack<>();
        int solutionSteps = 0;
        while (!currentNode.equals(startingState)) {
            stack.push(currentNode);
            solutionSteps++;
            currentNode = currentNode.getParent();
        }
        System.out.printf("Found a solution with %d steps!%n", solutionSteps);
        System.out.printf("Method : %s%n", searchMethod);
        System.out.printf("Steps : %d%n", nodesChecked);
        System.out.printf("Max nodes stored: %d%n", maxNodes);

        while (!stack.isEmpty()) {
            System.out.println(stack.pop().directionTakenFromParentNode);
        }
    }


    private void printSolutionWithSteps(TilePuzzleNode goalNode, SearchMethod searchMethod) {
        TilePuzzleNode currentNode = goalNode;
        Stack<TilePuzzleNode> stack = new Stack<>();
        int solutionSteps = 0;
        while (!currentNode.equals(startingState)) {
            stack.push(currentNode);
            solutionSteps++;
            currentNode = currentNode.getParent();
        }
        stack.push(currentNode);
        System.out.printf("Found a solution with %d steps!%n", solutionSteps);
        System.out.printf("Method : %s%n", searchMethod);
        System.out.printf("Steps : %d%n", nodesChecked);
        System.out.printf("Max nodes stored: %d%n", maxNodes);

        while (!stack.isEmpty()) {
            System.out.println(stack.pop().toString());
        }
    }
}
