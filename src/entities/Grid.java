package entities;

import utils.BuildMatrix;
import utils.UseGolRules;

import java.util.concurrent.TimeUnit;

public class Grid {
    private boolean running;
    private final int scale = 10;
    private int step;
    private final int stepLimit;
    private final int speed;
    private int[][] cells;
    private int width;
    private int height;

    public Grid(String cellsPosition, int speed, int limit, int width, int height) {
        boolean isRandomGrid = cellsPosition.equals("rnd");
        this.cells = isRandomGrid ?
                BuildMatrix.buildRandom(width, height) : BuildMatrix.build(cellsPosition, width, height);

        this.speed = speed;
        this.stepLimit = limit;
        this.width = width;
        this.height = height;
    }

    public void handle() {
        if (stepLimit == 0) {
            while (true) {
                loadGame();
                this.step++;
            }
        }

        for (this.step = 0; this.step < this.stepLimit; this.step++) {
            loadGame();
        }
    }

    private void loadGame() {
        showGrid();
        loadStep();

        sleep();

        System.out.println("\n" + "------------------------------" + "\n");
    }

    private void sleep() {
        try {
            TimeUnit.MILLISECONDS.sleep(this.speed);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadStep() {
        UseGolRules useGolRules = new UseGolRules(this.cells, this.width, this.height);
        this.cells = useGolRules.buildMatrix();
    }

    private void showGrid() {
        for (int line = 0; line < this.height; line++) {
            for (int column = 0; column < this.width; column++) {
                String cell = this.cells[line][column] == 1 ? (char)27 + "[36mX" : (char)27 + "[33m0";
                System.out.print(" " + cell + " ");
            }
            System.out.print("\n");
        }

        System.out.println("\nGENERATION: " + (this.step + 1));
        System.out.println("CELLS: " + liveCellCounter());
    }

    private int liveCellCounter() {
        int counter = 0;

        for (int[] line : this.cells) {
            for (int cell : line) {
                if (cell == 1) counter++;
            }
        }

        return counter;
    }
}
