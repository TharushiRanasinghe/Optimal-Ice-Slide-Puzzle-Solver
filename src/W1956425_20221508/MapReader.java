package W1956425_20221508;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

/**
 * @author 20221508 & w1956425
 * @author R.H.A.Tharushi Nethma Ranasinghe
 */

public class MapReader {
    public static void main(String[] args) {
        try {
            // Record starting time for performance measurement
            long startingTime = System.currentTimeMillis();

            // Read the puzzle map from the file
            List<List<String>> map = PuzzleExplorer.readPuzzleFile(new File("src/W1956425_20221508/maze10_2.txt")); // store each row of the maze as an element in a 2D array

            // Check if the puzzle file is empty
            if (map.isEmpty()) {
                System.out.println("Puzzle file is empty.");
                return; // Exit the program
            }

            // Print map information
            System.out.println("***MAP INFORMATION***");
            System.out.println("Number of Rows: " + map.size());
            System.out.println("Number of Columns: " + (map.isEmpty() ? 0 : map.get(0).size())); // Assuming all rows have the same length

            // Count the number of rocks in the puzzle
            int rockCount = 0;
            for (List<String> row : map) {
                for (String cell : row) {
                    if (cell.equals("0")) {
                        rockCount++;
                    }
                }
            }
            System.out.println("Number of Rocks: " + rockCount);

            // Print starting and ending points
            Integer[] startPoint = findStartPoint(map);
            Integer[] endPoint = findEndPoint(map);
            System.out.println("Starting Point: (" + startPoint[1] + ", " + startPoint[0] + ")");
            System.out.println("Ending Point: (" + endPoint[1] + ", " + endPoint[0] + ")");

            System.out.println(); // Add an empty line

            // Solve the puzzle and record the solution path
            List<Integer[]> path = PuzzleExplorer.solvePuzzle(map);

            // Record ending time for performance measurement
            long endingTime = System.currentTimeMillis();
            double elapsedTime = endingTime - startingTime;

            // Output the solution path
            OutputPath.outputPath(path);

            System.out.println();
            // Print elapsed time
            System.out.println("Elapsed time = " + elapsedTime + " milliseconds");
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("An error occurred: " + e.getMessage());
        }
    }
    // Method to find the starting point in the map
    private static Integer[] findStartPoint(List<List<String>> map) {
        for (int i = 0; i < map.size(); i++) {
            for (int j = 0; j < map.get(i).size(); j++) {
                if (map.get(i).get(j).equals("S")) {
                    return new Integer[]{i+1, j+1};
                }
            }
        }
        return null; // Return null if starting point not found
    }

    // Method to find the ending point in the map
    private static Integer[] findEndPoint(List<List<String>> map) {
        for (int i = 0; i < map.size(); i++) {
            for (int j = 0; j < map.get(i).size(); j++) {
                if (map.get(i).get(j).equals("F")) {
                    return new Integer[]{i+1, j+1};
                }
            }
        }
        return null; // Return null if ending point not found
    }
}
