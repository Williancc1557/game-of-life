package utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class BuildMatrix {
    /**
     *   The cellsPosition can be like a #0111#11 where each # is a line down
     */
    public static int[][] build(String cellsPosition, int width, int height) {
        int matrix[][] = new int[height][width];
        char[] positions = cellsPosition.toCharArray();

        int line = 0;
        int positionIndex = 0;
        while (line < height) {
            for (int column = 0; column < width; column++) {
                char positionLetter = positions[positionIndex];
                switch (positionLetter) {
                    case '#':
                        line++;
                        column = -1;
                        break;
                    case '1':
                        matrix[line][column] = 1;
                        break;
                }

                if (positionIndex == positions.length - 1) break;
                else positionIndex++;
            }

            if (positionIndex == positions.length - 1) break;
        }

        return matrix;
    }

    public static int[][] buildRandom(int width, int height) {
        int matrix[][] = new int[height][width];

        for (int line = 0; line < height; line++) {
            for (int column = 0; column < width; column++) {
                Random r = new Random();
                int value = r.nextInt(2);
                matrix[line][column] = value;
            }
        }

        return matrix;
    }

}
