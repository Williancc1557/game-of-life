package utils;

import java.util.Arrays;

public class BuildMatrix {
    /***
     *   The cellsPosition can be like a #0123#23 where each # is a line down
     */
    public int[][] build(String cellsPosition, int scale) {
        int matrix[][] = new int[scale][scale];
        char[] positions = cellsPosition.toCharArray();

        int line = 0;
        int positionIndex = 0;
        while (line < scale) {
            for (int column = 0; column < scale; column++) {
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
}
