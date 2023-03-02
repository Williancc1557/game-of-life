package utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UseGolRules {
    public int[][] matrix;

    public UseGolRules(int[][] matrix) {
        this.matrix = matrix;
    }

    public int[][] buildMatrix() {
        List<List<Integer>> cellsToDelete = deleteCells();
        for (List<Integer> cellLocation : cellsToDelete) {
            for (int i : cellLocation) {
                System.out.println(i);
            }
        }
        //reviveCells();

       return this.matrix;
    }

    private void reviveCells() {
        for (int line = 0; line < this.matrix.length; line++) {
            for (int column = 0; column < this.matrix.length; column++) {
                boolean revive = isCellToRevive(line, column);
                if (revive) {
                    this.matrix[line][column] = 1;
                };
            }
        }
    }

    private List<List<Integer>> deleteCells() {
        List<List<Integer>> cellsToDelete = new ArrayList<>();

        for (int line = 0; line < this.matrix.length; line++) {
            for (int column = 0; column < this.matrix.length; column++) {
                boolean isLivingCell = this.matrix[line][column] == 1;
                if (isLivingCell) {
                    boolean delete = isCellToDelete(line, column);
                    if (delete) {
                        List<Integer> lineAndColumn = new ArrayList<>();
                        lineAndColumn.add(line);
                        lineAndColumn.add(column);

                        cellsToDelete.add(lineAndColumn);
                    }
                }
            }
        }

        return cellsToDelete;
    }

    private boolean isCellToRevive(int line, int column) {
        int neighbors = neighborCounter(line, column);
        return  neighbors == 3;
    }

    private boolean isCellToDelete(int line, int column) {
        int neighbors = neighborCounter(line, column);
        return  neighbors <= 1 || neighbors > 3;
    }

    private int neighborCounter(int line, int column) {
        int counter = 0;

        if (!isInTheBottom(line)) {
            counter += downCellsChecker(line, column);
        }

        if (!isInTheTop(line)) {
            counter += upperCellsChecker(line, column);
        }

        if (!isInTheLeft(column)) {
            counter += leftCellsChecker(line, column);

            if (!isInTheTop(line)) {
                counter += diagonalUpperLeftCellsChecker(line, column);
            }

            if (!isInTheBottom(line)) {
                counter += diagonalDownLeftCellsChecker(line, column);
            }
        }

        if (!isInTheRight(column)) {
            counter += rightCellsChecker(line, column);

            if (!isInTheTop(line)) {
                counter += diagonalUpperRightCellsChecker(line, column);
            }

            if (!isInTheBottom(line)) {
                counter += diagonalDownRightCellsChecker(line, column);
            }
        }


        return counter;
    }

    private boolean isInTheTop(int line) {
        return line == 0;
    }

    private boolean isInTheBottom(int line) {
        return line == this.matrix.length - 1;
    }

    private boolean isInTheLeft(int column) {
        return column == 0;
    }

    private boolean isInTheRight(int column) {
        return column == this.matrix.length - 1;
    }

    private int downCellsChecker(int line, int column) {
        int counter = 0;

        int downCell = this.matrix[line + 1][column];
        if (downCell == 1) counter++;

        return counter;
    }

    private int upperCellsChecker(int line, int column) {
        int counter = 0;

        int upperCell = this.matrix[line - 1][column];
        if (upperCell == 1) counter++;

        return counter;
    }

    private int leftCellsChecker(int line, int column) {
        int counter = 0;

        int leftCell = this.matrix[line][column - 1];
        if (leftCell == 1) counter++;

        return counter;
    }

    private int rightCellsChecker(int line, int column) {
        int counter = 0;

        int rightCell = this.matrix[line][column + 1];
        if (rightCell == 1) counter++;

        return counter;
    }

    private int diagonalUpperRightCellsChecker(int line, int column) {
        int counter = 0;

        int upperCellRight = this.matrix[line - 1][column + 1];
        if (upperCellRight == 1) counter++;

        return counter;
    }

    private int diagonalUpperLeftCellsChecker(int line, int column) {
        int counter = 0;

        int upperCellLeft = this.matrix[line - 1][column - 1];
        if (upperCellLeft == 1) counter++;

        return counter;
    }

    private int diagonalDownRightCellsChecker(int line, int column) {
        int counter = 0;

        int downRightCell = this.matrix[line + 1][column + 1];
        if (downRightCell == 1) counter++;

        return counter;
    }

    private int diagonalDownLeftCellsChecker(int line, int column) {
        int counter = 0;

        int downLeftCell = this.matrix[line + 1][column - 1];
        if (downLeftCell == 1) counter++;

        return counter;
    }
}
