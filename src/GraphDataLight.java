import java.util.Arrays;

public class GraphDataLight {

	private int numberOfNodes;
	private int k;
	private long[][][] neighbourList;
	private EdgeDistanceComparator comparator;

	/**
	 * Construct a new GraphDataLight given the original GraphData, the max number of neighbors to store and the
	 * comparator to compare edgeDistances.
	 */
	public GraphDataLight(double[][] nodesCoordinates, int NeigboursToStore, EdgeDistanceComparator comparator) {
		this.numberOfNodes = nodesCoordinates.length;
		this.comparator = comparator;
		if (numberOfNodes <= NeigboursToStore) {
			NeigboursToStore = numberOfNodes - 1;
		}
		this.k = NeigboursToStore;
		// If the number nodes are less than 1 there are no neighbors
		if (numberOfNodes < 2) {
			this.neighbourList = null;
		} else {

			int numberOfEdges = numberOfNodes - 1;

			this.neighbourList = new long[numberOfNodes][k][3];
			for (int currentNode = 0; currentNode < numberOfNodes; currentNode++) {
				// long[][] edges = new long[numberOfEdges][3];
				int edgeIndex = 0;

				long[][] shortNeighborArray = generateShortNeighbourList(currentNode, nodesCoordinates);
				this.neighbourList[currentNode] = shortNeighborArray;
			}
		}
	}

	private long[][] generateShortNeighbourList(int currentNode, double[][] nodesCoordinates) {
		int edgeIndex = 0;
		long[][] edges = new long[numberOfNodes - 1][3];
		for (int neighbourNode = 0; neighbourNode < numberOfNodes; neighbourNode++) {

			if (currentNode != neighbourNode) { // don't add the edge
												// between the same node
				// long[] edge = { currentNode, neighbourNode,
				// originalData.getDistance(currentNode, neighbourNode) };
				// edges[edgeIndex] = edge;

				edges[edgeIndex][0] = currentNode;
				edges[edgeIndex][1] = neighbourNode;
				edges[edgeIndex][2] = calculateDistance(nodesCoordinates[currentNode], nodesCoordinates[neighbourNode]);
				edgeIndex++;
			}
		}
		return shortAndSort(edges, k);
	}

	public long calculateDistance(double[] node1, double[] node2) {
		// Calculate the Euclidean distance
		double distanceReal = (node1[0] - node2[0]) * (node1[0] - node2[0]);
		distanceReal += (node1[1] - node2[1]) * (node1[1] - node2[1]);
		return (long) distanceReal;
	}

	public long[][] shortAndSort(long[][] edges, int neigboursToStore) {
		if (edges.length < neigboursToStore) {
			throw new IllegalStateException("Trying to shorten a list to size " + neigboursToStore
					+ " but it is already of length " + edges.length);
		}
		// Sort in increasing order
		Arrays.sort(edges, comparator);
		// Cut away all excess neighbors
		long[][] lessEdges = new long[neigboursToStore][3];
		for (int i = 0; i < neigboursToStore; i++) {
			lessEdges[i] = edges[i];
		}
		return lessEdges;
	}

	/**
	 * Returns the K closest neighbors to the given node, where K is the initially set max value of neighbors to be
	 * stored.
	 */
	public long[][] getClosestKNeighbors(short node) {
		return this.neighbourList[node];
	}
}
