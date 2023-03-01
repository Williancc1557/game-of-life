import entities.Grid;

import java.util.Scanner;

public class Main {
    private static final Scanner scan = new Scanner(System.in);
    public static void main(String[] args) {
        System.out.println(
                "Type the cells position, like #010110 where:\n\n" +
                "# jump line\n" +
                "1 cell is life\n" +
                "0 cell is dead\n"
        );
        System.out.print("Type here: ");
        String cellsPosition = scan.nextLine();

        Grid grid = new Grid(cellsPosition);
    }
}