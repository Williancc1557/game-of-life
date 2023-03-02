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

        if (!cellsPosition.equals("rnd")) {
            if (!isCorrectPositions(cellsPosition) ) {
                System.out.println("Invalid cells positions provided. The cells position can be like 1#111#11.");
                return;
            }

            if (isLargePositions(cellsPosition, width, height)) {
                System.out.println("Invalid cells length");
                return;
            }
        }

        int speed = Integer.parseInt(getParamValue(args, "s"));
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
}