import java.util.Arrays;

public class GraphDataLight {

	private int numberOfNodes;
	private long[][][] neighbourList;
	private EdgeDistanceComparator comparator;

	public GraphDataLight(GraphData originalData, int NeigboursToStore, EdgeDistanceComparator comparator) {

		this.comparator = comparator;
		this.numberOfNodes = originalData.numberOfNodes();

		this.neighbourList = new long[numberOfNodes][numberOfNodes][3];
		for (int currentNode = 0; currentNode < numberOfNodes; currentNode++) {

			long[][] edges = new long[numberOfNodes][3];
			for (int neighbourNode = 0; neighbourNode < numberOfNodes; neighbourNode++) {

				long[] edge = { currentNode, neighbourNode, originalData.getDistance(currentNode, neighbourNode) };

				edges[neighbourNode] = edge;
			}

			long[][] lessEdges = shortAndSort(edges, NeigboursToStore);

		}

	}

	public long[][] shortAndSort(long[][] edges, int NeigboursToStore) {
		// Sort in increasing order
		Arrays.sort(edges, comparator);
		// Cut away all excess neighbors
		long[][] lessEdges = new long[NeigboursToStore][3];
		for (int i = 0; i < NeigboursToStore; i++) {
			lessEdges[i] = edges[i];
		}
		return lessEdges;
	}
}
