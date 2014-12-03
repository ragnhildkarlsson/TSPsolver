import java.util.Arrays;

public class GraphDataLight {

	private int numberOfNodes;
	private long[][][] neighbourList;
	private EdgeDistanceComparator comparator;

	/**
	 * Construct a new GraphDataLight given the original GraphData, the max
	 * number of neighbors to store and the comparator to compare edgeDistances.
	 */
	public GraphDataLight(GraphData originalData, int NeigboursToStore, EdgeDistanceComparator comparator) {
		this.numberOfNodes = originalData.numberOfNodes();
		this.comparator = comparator;
		// If the number nodes are less than 1 there are no neighbors
		if (numberOfNodes < 2) {
			this.neighbourList = null;
		} else {

			int numberOfEdges = numberOfNodes - 1;

			this.neighbourList = new long[numberOfNodes][numberOfEdges][3];
			for (int currentNode = 0; currentNode < numberOfNodes; currentNode++) {
				long[][] edges = new long[numberOfEdges][3];
				int edgeIndex = 0;

				for (int neighbourNode = 0; neighbourNode < numberOfNodes; neighbourNode++) {

					if (currentNode != neighbourNode) { // don't add the edge
														// between the same node
						long[] edge = { currentNode, neighbourNode,
								originalData.getDistance(currentNode, neighbourNode) };
						edges[edgeIndex] = edge;
						edgeIndex++;
					}
				}

				long[][] lessEdges = shortAndSort(edges, NeigboursToStore);
				this.neighbourList[currentNode] = lessEdges;
			}
		}
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
	 * Returns the K closest neighbors to the given node, where K is the
	 * initially set max value of neighbors to be stored.
	 */
	public long[][] getClosestKNeighbors(short node) {
		return this.neighbourList[node];
	}
}
