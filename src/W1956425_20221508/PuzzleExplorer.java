package W1956425_20221508;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.PriorityQueue;

/**
 * @author 20221508 & w1956425
 * @author R.H.A.Tharushi Nethma Ranasinghe
 */

//This class contains methods to read a puzzle map from a file and solve the puzzle using Dijkstra's algorithm.
// It also defines movement directions and contains a helper class for priority queue elements.
public class PuzzleExplorer {

    // Define movement directions: up, down, left, right
    private static final int[][] movementDirections = new int[][] {{1, 0},{-1, 0}, {0, 1}, {0, -1}}; // the four cardinal directions

    // Method to read the puzzle file and create the map
    public static List<List<String>> readPuzzleFile (File file) throws FileNotFoundException {
        List<List<String>> map = new ArrayList<>();
        Scanner inputScanner = new Scanner(file);
        while (inputScanner.hasNext()) {
            String[] line = inputScanner.nextLine().split("");
            map.add(Arrays.asList(line));
        }
        return map;
    }

    // Method to solve the puzzle using Dijkstra's algorithm
    public static List<Integer[]> solvePuzzle(List<List<String>> map) {
        // Initialize variables
        int rows = map.size();
        int columns = map.get(0).size();
        int startingRow = 0;
        int startingColumn = 0;
        int endingColumn = 0;
        int endingRow = 0;

        // Find starting and ending points
        for(int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if(map.get(i).get(j).equals("S")) {
                    startingRow = i;
                    startingColumn = j;
                }
                if (map.get(i).get(j).equals("F")) {
                    endingRow = i;
                    endingColumn = j;
                }
            }
        }

        // Initialize map distance array
        int[][] mazeDistance = new int[rows][columns]; // store the puzzle size

        for (int i = 0; i < mazeDistance.length; i++) {
            for(int j = 0; j < columns; j++) {
                mazeDistance[i][j] = Integer.MAX_VALUE; // putting a tentative value as the weight for all unvisited nodes
            }
        }
        // Initialize priority queue
        PriorityQueue<QueueElement> elementPriorityQueue = new PriorityQueue<>(); // stores Queue objs
        Integer[] arr = new Integer[] {startingRow, startingColumn}; // store position of 'S'
        List<Integer[]> arrayList = new ArrayList<>();
        arrayList.add(arr);
        elementPriorityQueue.add(new QueueElement(0, startingRow, startingColumn,  arrayList));

        // Dijkstra's algorithm Implementation
        while (!elementPriorityQueue.isEmpty()) {
            QueueElement element = elementPriorityQueue.remove(); // remove the Queue obj with the minimum weight
            List<Integer[]> path = element.visitedList;
            if (path.get(path.size() - 1)[0] == endingRow && path.get(path.size() - 1)[1] == endingColumn) return path;

            for(int[] direction : movementDirections) {
                int count = 0;
                int currentRow = element.mapRow;
                int currentColumn = element.mapColumn;
                int rowDirection = direction[0];
                int columnDirection = direction[1];

                // checking the edge conditions
                while (((0 <= currentRow + rowDirection) && (currentRow + rowDirection < rows)) && ((0 <=  currentColumn
                        + columnDirection) && (currentColumn + columnDirection < columns)) && !map.get(currentRow +
                        rowDirection).get(currentColumn + columnDirection).equals("0")) {
                    count += 1;
                    currentRow += rowDirection;
                    currentColumn += columnDirection;
                    if (map.get(currentRow).get(currentColumn).equals("F")) {
                        path.add(new Integer[] {currentRow, currentColumn});
                        return path ;
                    }
                }

                // checking whether the weight is the tentative value assigned or is greater
                 if (mazeDistance[currentRow][currentColumn] == Integer.MAX_VALUE || element.mapDistance + count < mazeDistance[currentRow][currentColumn]) {
                    mazeDistance[currentRow][currentColumn] = element.mapDistance + count;
                    List<Integer[]> integers = new ArrayList<>(path);
                    integers.add(new Integer[] {currentRow, currentColumn});
                    elementPriorityQueue.add(new QueueElement(element.mapDistance + count, currentRow, currentColumn, integers));
                }

            }
        }
        return new ArrayList<>(); // Return empty list if no solution found
    }

}
