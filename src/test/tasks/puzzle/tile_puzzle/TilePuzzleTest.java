package tasks.puzzle.tile_puzzle;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import tasks.puzzle.SearchMethod;
import tasks.puzzle.tile_puzzle.TilePuzzle;
import tasks.puzzle.tile_puzzle.TilePuzzleNode;

class TilePuzzleTest {
    @Test
    void smokeTest1() {
        TilePuzzle tilePuzzle = new TilePuzzle(
                new TilePuzzleNode(
                        3,
                        new char[][]{
                                {'X', '1', '2'},
                                {'3', '4', '5'},
                                {'6', '7', '8'}
                        },
                        null,
                        0,
                        new char[][]{
                                {'1', '2', '5'},
                                {'3', '4', '8'},
                                {'6', '7', 'X'}
                        },
                        null
                )
        );
        int expectedSteps = 4;
        Assertions.assertEquals(expectedSteps, tilePuzzle.findSolution(SearchMethod.BrFS));
    }

    @Test
    void smokeTest2() {
        TilePuzzle tilePuzzle = new TilePuzzle(
                new TilePuzzleNode(
                        3,
                        new char[][]{
                                {'X', '1', '2'},
                                {'3', '4', '5'},
                                {'6', '7', '8'}
                        },
                        null,
                        0,
                        new char[][]{
                                {'X', '3', '2'},
                                {'6', '1', '5'},
                                {'7', '4', '8'}
                        },
                        null
                )
        );
        int expectedSteps = 6;
        Assertions.assertEquals(expectedSteps, tilePuzzle.findSolution(SearchMethod.BrFS));
    }

    @Test
    void smokeTest3() {
        TilePuzzle tilePuzzle = new TilePuzzle(
                new TilePuzzleNode(
                        4,
                        new char[][]{
                                {'A', 'B', 'C', 'D'},
                                {'E', 'F', 'G', 'H'},
                                {'I', 'J', 'K', 'L'},
                                {'M', 'N', 'O', 'X'}
                        },
                        null,
                        0,
                        new char[][]{
                                {'A', 'B', 'C', 'D'},
                                {'E', 'F', 'G', 'H'},
                                {'I', 'J', 'K', 'L'},
                                {'M', 'N', 'X', 'O'}
                        },
                        null
                )
        );
        int expectedSteps = 1;
        Assertions.assertEquals(expectedSteps, tilePuzzle.findSolution(SearchMethod.BrFS));
    }

    @Test
    void smokeTest4() {
        TilePuzzle tilePuzzle = new TilePuzzle(
                new TilePuzzleNode(
                        4,
                        new char[][]{
                                {'A', 'B', 'C', 'D'},
                                {'E', 'F', 'G', 'H'},
                                {'I', 'J', 'K', 'L'},
                                {'M', 'N', 'O', 'X'}
                        },
                        null,
                        0,
                        new char[][]{
                                {'A', 'B', 'C', 'X'},
                                {'F', 'J', 'H', 'D'},
                                {'E', 'K', 'G', 'L'},
                                {'I', 'M', 'N', 'O'}
                        },
                        null
                )
        );
        int expectedSteps = 11;
        Assertions.assertEquals(expectedSteps, tilePuzzle.findSolution(SearchMethod.BeFS));
    }
}