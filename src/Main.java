import entities.Grid;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner scan = new Scanner(System.in);
    public static void main(String[] args) {
        int width = setWidth();
        int height = setHeight();

        String cellsPosition = setCellsPosition();
        int speed = setSpeed();
        int limit = setLimit();

        Grid grid = new Grid(cellsPosition, speed, limit, width, height);
        grid.handle();
    }

    public static int setWidth() {
        System.out.print("Type the width of the grid\n\n");
        System.out.println(
                "[1] 10\n" +
                "[2] 20\n" +
                "[3] 40\n" +
                "[4] 80\n");
        System.out.print("Select here: ");
        String option = scan.nextLine();

        int width = 0;
        switch (option) {
            case "1" -> width = 10;
            case "2" -> width = 20;
            case "3" -> width = 40;
            case "4" -> width = 80;
            default -> {
                System.out.println("You are typed an invalid value, i'll select for you");
                System.out.println("1\n");
                width = 10;
            }
        }

        return width;
    }

    public static int setHeight() {
        System.out.print("Type the height of the grid\n\n");
        System.out.println(
                "[1] 10\n" +
                "[2] 20\n" +
                "[3] 40\n");
        System.out.print("Select here: ");
        String option = scan.nextLine();

        int height = 0;
        switch (option) {
            case "1" -> height = 10;
            case "2" -> height = 20;
            case "3" -> height = 40;
            default -> {
                System.out.println("You are typed an invalid value, i'll select for you");
                System.out.println("1\n");
                height = 10;
            }
        }

        return height;
    }


    public static String setCellsPosition() {
        System.out.println(
                "Type the cells position, like 01#001#111 where:\n\n" +
                "# jump line\n" +
                "1 cell is life\n" +
                "0 cell is dead\n"
        );
        System.out.print("Type here: ");
        String cellsPosition;

        while (true) {
            cellsPosition = scan.nextLine();
            if (isCorrectPositions(cellsPosition)) break;

            System.out.print("You can't set this value, type again: ");
        }

        return cellsPosition;
    }

    public static int setSpeed() {
        System.out.print("Type the speed (0/1000)ms: ");
        int speed = Integer.parseInt(scan.nextLine());

        while (true) {
            if (speed < 0 || speed > 1000) {
                System.out.print("You can't set this value, type again: ");
                speed = Integer.parseInt(scan.nextLine());
            } else {
                break;
            }
        }

        return speed;
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

    public static int setLimit() {
        System.out.print("Type the limit of generations: ");
        return Integer.parseInt(scan.nextLine());
    }
}