import java.util.List;

public class TourEvaluator {

    int[][] distanceMatrix;

    public TourEvaluator(double[][] nodes) {
	this.distanceMatrix = new int[nodes.length][nodes.length];
	readDistances(nodes);
    }

    public int getDistOfTour(Tour tour) {
	List<Integer> tourList = tour.getTourList();
	int totalDistance = 0;
	for (int i = 0; i < distanceMatrix.length - 1; i++) {
	    int node = tourList.get(i);
	    int neighbor = tourList.get(i + 1);
	    int dist = distanceMatrix[node][neighbor];
	    totalDistance += dist;
	}
	totalDistance += distanceMatrix[tourList.get(0)][tourList.get(tourList.size() - 1)];
	return totalDistance;
    }

    private void readDistances(double[][] nodes) {
	for (int i = 0; i < nodes.length; i++) {
	    for (int j = i; j < nodes.length; j++) {
		int dist = calculateDistance(nodes[i], nodes[j]);
		distanceMatrix[i][j] = dist;
		distanceMatrix[j][i] = dist;
	    }
	}

    }

    private int calculateDistance(double[] node1, double[] node2) {
	// Calculate the Euclidean distance
	double distanceReal = (node1[0] - node2[0]) * (node1[0] - node2[0]);
	distanceReal += (node1[1] - node2[1]) * (node1[1] - node2[1]);
	int res = (int) Math.sqrt(distanceReal);
	return res;
    }

}
