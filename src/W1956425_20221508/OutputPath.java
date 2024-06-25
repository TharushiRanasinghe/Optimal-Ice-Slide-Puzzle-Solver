package W1956425_20221508;

import java.util.List;

/**
 * @author 20221508 & w1956425
 * @author R.H.A.Tharushi Nethma Ranasinghe
 */
//Outputs the path obtained from solving the puzzle.
//Prints the sequence of movements from start to finish.
public class OutputPath {

    // Method to output the path obtained from solving the puzzle
    public static void outputPath(List<Integer[]> path) {
        // Print starting point
        if (path.size() > 0) {
            System.out.printf("1. Start at (%d, %d) \n", path.get(0)[1] + 1, path.get(0)[0] + 1);
            // Iterate over the path and print movements
            for (int i = 1; i < path.size(); i++) {
                int currentRow = path.get(i)[0];
                int currentColumn = path.get(i)[1];
                int previousRow = path.get(i - 1)[0];
                int previousColumn = path.get(i - 1)[1];
                int pathRow = currentRow - previousRow;
                int pathColumn = currentColumn - previousColumn;

                // Print movement direction
                if (pathRow < 0) {
                    System.out.printf((i + 1) + ". Move up to (%d, %d) \n", path.get(i)[1] + 1, path.get(i)[0] + 1);
                } else if (pathRow > 0) {
                    System.out.printf((i + 1) + ". Move Down to (%d, %d) \n", path.get(i)[1] + 1, path.get(i)[0] + 1);
                } else if (pathColumn < 0) {
                    System.out.printf((i + 1) + ". Move Left to (%d, %d) \n", path.get(i)[1] + 1, path.get(i)[0] + 1);
                } else {
                    System.out.printf((i + 1) + ". Move Right to (%d, %d) \n", path.get(i)[1] + 1, path.get(i)[0] + 1);
                }
            }
            // Print "Done!" after reaching the end
            System.out.println((path.size() + 1) + ". Done!");
        }
    }

}
