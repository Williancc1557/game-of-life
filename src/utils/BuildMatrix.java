package utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BuildMatrix {
    /***
     *   The cellsPosition can be like a #0111#11 where each # is a line down
     */
    public int[][] build(String cellsPosition, int scale) {
        int matrix[][] = new int[scale][scale];
        char[] positions = cellsPosition.toCharArray();

        if (!isCorrectPositions(cellsPosition)) return matrix;

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

    public boolean isCorrectPositions(String cellsPosition) {
        char[] positions = cellsPosition.toCharArray();
        List<Character> validPositions = new ArrayList<>();
        validPositions.add('#');
        validPositions.add('1');
        validPositions.add('2');

        for (char position : positions) {
            if (!validPositions.contains(position)) return false;
        }

        return true;
    }
}
