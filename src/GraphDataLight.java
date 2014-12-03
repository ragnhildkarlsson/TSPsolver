import java.util.Arrays;

public class GraphDataLight {

	private int nNodes;
	private int k;
	private int[][][] neighbourList;
	private EdgeDistanceComparator comparator;

	/**
	 * Construct a new GraphDataLight given the original GraphData, the max number of neighbors to store and the
	 * comparator to compare edgeDistances.
	 */
	public GraphDataLight(double[][] nodesCoordinates, int NeigboursToStore, EdgeDistanceComparator comparator) {
		this.nNodes = nodesCoordinates.length;
		this.comparator = comparator;
		if (nNodes <= NeigboursToStore) {
			NeigboursToStore = nNodes - 1;
		}
		this.k = NeigboursToStore;
		// If the number nodes are less than 1 there are no neighbors
		if (nNodes < 2) {
			this.neighbourList = null;
		} else {

			this.neighbourList = new int[nNodes][k][3];
			for (int currentNode = 0; currentNode < nNodes; currentNode++) {
				// long[][] edges = new long[numberOfEdges][3];

				int[][] shortNeighborArray = generateShortNeighbourList(currentNode, nodesCoordinates);
				this.neighbourList[currentNode] = shortNeighborArray;
			}
		}
	}

	private int[][] generateShortNeighbourList(int currentNode, double[][] nodesCoordinates) {
		int edgeIndex = 0;
		long[][] edges = new long[nNodes - 1][3];
		for (int neighbourNode = 0; neighbourNode < nNodes; neighbourNode++) {

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

	public int[][] shortAndSort(long[][] edges, int neigboursToStore) {
		if (edges.length < neigboursToStore) {
			throw new IllegalStateException("Trying to shorten a list to size " + neigboursToStore
					+ " but it is already of length " + edges.length);
		}
		// Sort in increasing order
		Arrays.sort(edges, comparator);
		// Cut away all excess neighbors
		int[][] lessEdges = new int[neigboursToStore][3];
		for (int i = 0; i < neigboursToStore; i++) {
			int sqrtDistance = (int) Math.sqrt(edges[i][2]);
			lessEdges[i][0] = (int) edges[i][0];
			lessEdges[i][1] = (int) edges[i][1];
			lessEdges[i][2] = sqrtDistance;
		}
		return lessEdges;
	}

	/**
	 * Returns the K closest neighbors to the given node, where K is the initially set max value of neighbors to be
	 * stored.
	 */
	public int[][] getClosestKNeighbors(short node) {
		return this.neighbourList[node];
	}

	public short numberOfNodes() {
		return (short) this.nNodes;
	}
}
