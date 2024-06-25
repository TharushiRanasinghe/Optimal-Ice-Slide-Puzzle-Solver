package W1956425_20221508;

import java.util.List;

/**
 *  @author 20221508 & w1956425
 * @author R.H.A.Tharushi Nethma Ranasinghe
 */

//Represents an element in the priority queue used in the Dijkstra's algorithm.
//contains information about the distance, row, column, and the visited list.
public class QueueElement implements Comparable<QueueElement>{

    int mapDistance; // Distance of this element in the map
    int mapRow; // Row index of this element in the map
    int mapColumn; // Column index of this element in the map
    List<Integer[]> visitedList; // List of visited positions in the map

    // Constructor to initialize QueueElement
    public QueueElement(int mapDistance, int mapRow, int mapColumn, List<Integer[]> visitedList)  {
        this.mapDistance = mapDistance;
        this.mapRow = mapRow;
        this.mapColumn = mapColumn;
        this.visitedList = visitedList;
    }

    // Implementation of compareTo method to compare QueueElements based on distance
    public int compareTo(QueueElement queue) {
        return Integer.compare(mapDistance, queue.mapDistance);
    }
}
