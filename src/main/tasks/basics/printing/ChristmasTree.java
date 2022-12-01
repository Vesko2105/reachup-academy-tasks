package tasks.basics.printing;

import java.util.Scanner;

public class ChristmasTree {

    static void drawTree(int height) {
        for (int spaces = 0; spaces < height + 1; spaces++) {
            System.out.print(" ");
        }
        System.out.println("*");
        for (int row = 0; row < height - 1; row++) {
            for (int spaces = 0; spaces < height - row; spaces++) {
                System.out.print(" ");
            }
            System.out.print("*");
            for (int spaces = 0; spaces < 2 * row + 1; spaces++) {
                System.out.print(" ");
            }
            System.out.print("*");
            System.out.println();
        }
    }

    static void drawTreeRecursively(int height, int currentRow) {
        if (currentRow == 0) {
            drawFirstTreeRow(height);
        } else {
            drawTreeRecursively(height, currentRow - 1);
            drawRowRecursively(height, currentRow, 0);
        }
    }

    static void drawRowRecursively(int treeHeight, int rowIndex, int currentIndex) {
        if (currentIndex < treeHeight - rowIndex) {
            System.out.print(" ");
            drawRowRecursively(treeHeight, rowIndex, currentIndex + 1);
        } else if (currentIndex == treeHeight - rowIndex) {
            System.out.print("*");
            drawRowRecursively(treeHeight, rowIndex, currentIndex + 1);
        } else if (currentIndex < (rowIndex * 2) + (treeHeight - rowIndex)) {
            System.out.print(" ");
            drawRowRecursively(treeHeight, rowIndex, currentIndex + 1);
        } else {
            System.out.println("*");
        }
    }

    static void drawFirstTreeRow(int treeHeight) {
        if (treeHeight == 0) {
            System.out.println("*");
        } else {
            System.out.print(" ");
            drawFirstTreeRow(treeHeight - 1);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter tree height: ");
        int treeHeight = scanner.nextInt();
        drawTreeRecursively(treeHeight, treeHeight);
    }
}
