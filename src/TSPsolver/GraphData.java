package TSPsolver;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GraphData {

    private long[][] distanceMatrix;
    private Map<Integer, Coordinates> nodeCoordinates;

    public GraphData(Map<Integer, Coordinates> nodeMap) {
	// Store the mapping between nodes and coordinates
	this.nodeCoordinates = nodeMap;

	int numberOfNodes = nodeMap.size();

	// Create and fill in the distances of the nodes
	this.distanceMatrix = new long[numberOfNodes][numberOfNodes];

	for (int i = 0; i < numberOfNodes; i++) {
	    for (int j = i; j < numberOfNodes; j++) { // j = i : no need to recalculate old values
		distanceMatrix[i][j] = calculateDistance(nodeMap.get(i), nodeMap.get(j));
		distanceMatrix[j][i] = distanceMatrix[i][j]; // symmetry
	    }
	}
    }

    /**
     * Returns the squared distance of the two given coordinates in the plane.
     * 
     * @param coordinates
     * @param coordinates2
     * @return
     */
    public long calculateDistance(Coordinates coordinates, Coordinates coordinates2) {
	// Calculate the Euclidean distance

	double distanceReal = Math.pow(coordinates.getX() - coordinates2.getX(), 2);
	distanceReal += Math.pow(coordinates.getY() - coordinates2.getY(), 2);
	// distanceReal = Math.sqrt(distanceReal); // squared distance, no need to take sqrt
	distanceReal = Math.round(distanceReal);

	return (long) distanceReal;
    }

    /**
     * @return an unsorted list of all the nodes in this Graph.
     */
    public List<Integer> getNodes() {
	List<Integer> listOFNodes = new ArrayList<>(this.nodeCoordinates.keySet());
	return listOFNodes;
    }

    /**
     * Returns the squared distance between node A and node B
     * 
     * @param nodeA
     * @param nodeB
     * @return
     */
    public long getDistance(int nodeA, int nodeB) {
	return this.distanceMatrix[nodeA][nodeB];
    }

}
