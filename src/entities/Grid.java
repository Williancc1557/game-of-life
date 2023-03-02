package entities;

import utils.BuildMatrix;
import utils.UseGolRules;

import java.util.concurrent.TimeUnit;

public class Grid {
    private boolean running;
    private final int scale = 6;
    private int step;
    private int stepLimit = 500;
    private final int speed = 5000;
    private int[][] cells;

    public Grid(String cellsPosition) {
        this.cells = BuildMatrix.build(cellsPosition, scale);
    }

    public void handle() {
        try {
            showGrid();
            TimeUnit.MILLISECONDS.sleep(this.speed);
            for (this.step = 0; this.step < stepLimit; this.step++) {
                    loadStep();
                    showGrid();
                TimeUnit.MILLISECONDS.sleep(this.speed);
                System.out.println("\n" + "------------------------------" + "\n");
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadStep() {
        UseGolRules useGolRules = new UseGolRules(this.cells);
        this.cells = useGolRules.buildMatrix();
    }

    private void showGrid() {
        for (int line = 0; line < this.scale; line++) {
            for (int column = 0; column < this.scale; column++) {
                String cell = this.cells[line][column] == 1 ? (char)27 + "[36mX" : (char)27 + "[33m0";
                System.out.print(" " + cell + " ");
            }
            System.out.print("\n");
        }

        System.out.println("\nSTEP: " + (this.step + 1));
    }
}
