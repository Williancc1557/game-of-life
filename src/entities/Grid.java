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
}
