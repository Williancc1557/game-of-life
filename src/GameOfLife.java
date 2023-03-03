import entities.Grid;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class GameOfLife {
    private static final Scanner scan = new Scanner(System.in);
    public static void main(String[] args) {
        int width = Integer.parseInt(getParamValue(args, "w"));
        int height = Integer.parseInt(getParamValue(args, "h"));
        String cellsPosition = getParamValue(args, "p");

        if (!positionsValidation(cellsPosition, width, height)) {
            return;
        }

        if (!isScaleCorrect(width, height)) {
            return;
        }

        int speed = Integer.parseInt(getParamValue(args, "s"));
        if (!isSpeedCorrect(speed)) {
            System.out.println("Speed just between 250 and 1000.");
            return;
        }

        int limit = Integer.parseInt(getParamValue(args, "g"));

        Grid grid = new Grid(cellsPosition, speed, limit, width, height);
        grid.handle();
    }

    public static String getParamValue(String[] args, String field) {
        for (String param : args) {
            String[] keyAndValue = param.split("=");
            if (keyAndValue[0].equals(field)) {
                return keyAndValue[1];
            }
        }

        throw new RuntimeException("Invalid param provided");
    }

    public static boolean positionsValidation(String cellsPosition, int width, int height) {
        if (!cellsPosition.equals("rnd")) {
            if (!isCorrectPositions(cellsPosition) ) {
                System.out.println("Invalid cells positions provided. The cells position can be like 1#111#11.");
                return false;
            }

            if (isLargePositions(cellsPosition, width, height)) {
                System.out.println("Invalid cells length.");
                return false;
            }
        }

        return true;
    }

    public static boolean isCorrectPositions(String cellsPosition) {
        char[] positions = cellsPosition.toCharArray();
        List<Character> validPositions = new ArrayList<>();
        validPositions.add('#');
        validPositions.add('1');
        validPositions.add('0');

        for (char position : positions) {
            if (!validPositions.contains(position)) return false;
        }

        return true;
    }

    public static boolean isLargePositions(String cellsPosition, int width, int height) {
        String lines[] = cellsPosition.split("#");

        if (lines.length > height) return true;

        for (String line : lines) {
            int lineWidth = line.toCharArray().length;
            if (lineWidth > width) return true;
        }

        return false;
    }

    public static boolean isScaleCorrect(int width, int height) {
        if (!isWidthCorrect(width) || !isHeightCorrect(height)) {
            System.out.println("Invalid width or height.\n" +
                    "correct options:\n" +
                    "height: 10,20,40\n" +
                    "width: 10,20,40,80");
            return false;
        }

        return true;
    }

    public static boolean isWidthCorrect(int width) {
        List<Integer> valuesAllowed = new ArrayList<>();

        int[] valuesToAdd = {10,20,40,80};
        for (int value : valuesToAdd) {
            valuesAllowed.add(value);
        }

        return valuesAllowed.contains(width);
    }

    public static boolean isHeightCorrect(int height) {
        List<Integer> valuesAllowed = new ArrayList<>();

        int[] valuesToAdd = {10,20,40};
        for (int value : valuesToAdd) {
            valuesAllowed.add(value);
        }

        return valuesAllowed.contains(height);
    }

    public static boolean isSpeedCorrect(int speed) {
        return speed >= 250 && speed <= 1000;
    }
}