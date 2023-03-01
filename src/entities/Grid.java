package entities;

import utils.BuildMatrix;

public class Grid {
    private boolean running;
    private final int scale = 10;
    private int step = 1;
    private int stepLimit = 500;
    private final int speed = 1000;
    private int[][] cells;

    public Grid(String cellsPosition) {
        this.cells = new BuildMatrix().build(cellsPosition, scale);
    }

    private void showGrid() {
        for (int line = 0; line < this.scale; line++) {
            for (int column = 0; column < this.scale; column++) {
                String cell = this.cells[line][column] == 1 ? "X" : "0";
                System.out.print(" " + cell + " ");
            }
            System.out.print("\n");
        }
    }
}
