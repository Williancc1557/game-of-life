package entities;

import utils.BuildMatrix;

import java.util.concurrent.TimeUnit;

public class Grid {
    private boolean running;
    private final int scale = 10;
    private int step;
    private int stepLimit = 500;
    private final int speed = 1000;
    private int[][] cells;

    public Grid(String cellsPosition) {
        this.cells = new BuildMatrix().build(cellsPosition, scale);
    }

    public void handle() {
        for (this.step = 0; this.step < stepLimit; this.step++) {
            try {
                loadStep();
                showGrid();
                TimeUnit.MILLISECONDS.sleep(this.speed);
                System.out.println("\n" + "------------------------------" + "\n");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void loadStep() {
        //this.cells = cellsWithRulesApplied();
    }

    public int[][] cellsWithRulesApplied() {
        int[][] newCells = new int[this.scale][this.scale];

        return newCells;
    }

    private void showGrid() {
        for (int line = 0; line < this.scale; line++) {
            for (int column = 0; column < this.scale; column++) {
                String cell = this.cells[line][column] == 1 ? "X" : "0";
                System.out.print(" " + cell + " ");
            }
            System.out.print("\n");
        }

        System.out.println("\nSTEP: " + (this.step + 1));
    }
}
