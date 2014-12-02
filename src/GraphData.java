public class GraphData {

    private long[][] distanceMatrix;

    public GraphData(double[][] nodes) {

	int numberOfNodes = nodes.length;

	// Create and fill in the distances of the nodes
	this.distanceMatrix = new long[numberOfNodes][numberOfNodes];

	for (int i = 0; i < numberOfNodes; i++) {
	    for (int j = i; j < numberOfNodes; j++) { // j = i : no need to
						      // recalculate old values
		distanceMatrix[i][j] = calculateDistance(nodes[i], nodes[j]);
		distanceMatrix[j][i] = distanceMatrix[i][j]; // symmetry
	    }
	}

    }

    // /**
    // * Returns the squared distance of the two given coordinates in the plane.
    // *
    // * @param coordinates
    // * @param coordinates2
    // * @return
    // */
    // public long calculateDistance(Coordinates coordinates, Coordinates
    // coordinates2) {
    // // Calculate the Euclidean distance
    // double distanceReal = (coordinates.getX() - coordinates2.getX()) *
    // (coordinates.getX() - coordinates2.getX());
    // distanceReal += (coordinates.getY() - coordinates2.getY()) *
    // (coordinates.getY() - coordinates2.getY());
    // return (long) distanceReal;
    // }

    public long calculateDistance(double[] node1, double[] node2) {
	// Calculate the Euclidean distance
	double distanceReal = (node1[0] - node2[0]) * (node1[0] - node2[0]);
	distanceReal += (node1[1] - node2[1]) * (node1[1] - node2[1]);
	return (long) distanceReal;
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

    public int numberOfNodes() {
	return numberOfNodes();
    }

}
