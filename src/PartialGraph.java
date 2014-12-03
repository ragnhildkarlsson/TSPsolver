import java.util.ArrayList;

public class PartialGraph {

    private long[][] neighborList;
    private int nNodes;
    private short[] nNeighbors;
    private boolean[] completedNodes;
    private int nCompletedNodes;

    public PartialGraph(int nNodes) {
	this.nNodes = nNodes;
	nNeighbors = new short[nNodes];
	neighborList = new long[nNodes][2];
	completedNodes = new boolean[nNodes];
	for (int i = 0; i < nNodes; i++) {
	    nNeighbors[i] = 0; // initialize nNeighbors to 0 for all nodes
	    completedNodes[i] = false; // initalize that no node is complete
	}
    }

    public void addEdge(int node1, int node2) {
	neighborList[node1][nNeighbors[node1]] = node2;
	nNeighbors[node1]++;

	neighborList[node2][nNeighbors[node2]] = node1;
	nNeighbors[node2]++;
	if (nNeighbors[node1] > 1) {
	    completedNodes[node1] = true;
	    nCompletedNodes++;
	}
	if (nNeighbors[node2] > 1) {
	    completedNodes[node2] = true;
	    nCompletedNodes++;
	}
    }

    public boolean edgeCanBeAdded(int node1, int node2) {
	// Check if any of the nodes is completed
	if (completedNodes[node1] || completedNodes[node2]) {
	    return false;
	}
	return !hasCycle(node1, node2);

    }

    public boolean hasCycle(int node1, int node2) {

	// Handle the case no neighbors
	if (nNeighbors[node1] == 0 && nNeighbors[node2] == 0) {
	    return false;
	}
	// Check for neighbors in both directions
	// and chose to continue in any direction where neighbors exist
	int node = -1;
	if (nNeighbors[node1] > 0) {
	    node = node1;
	}
	if (nNeighbors[node2] > 0) {
	    node = node2;
	}
	int neighbor = (int) neighborList[node][0];
	// check if we find a loop
	int iter = 0;
	while (true && iter < 2000) {
	    if (neighbor == node1 || neighbor == node2) {
		// we have detected a loop
		return true;
	    }
	    // continue search
	    long[] neighbors = neighborList[neighbor];
	    if (nNeighbors[neighbor] == 1) {
		// found an end
		return false;
	    }
	    int oldNode = node;
	    node = neighbor;
	    neighbor = getOtherNeighbor(neighbors, oldNode);
	    iter++;
	}
	throw new IllegalStateException(
		"This TSPSolver handle indata of size <=1000 nodes, 2000 iterations should not be needed");

    }

    public Tour getTour(int startNode) {
	ArrayList<Integer> cycle = new ArrayList<Integer>();
	int nodeBeforeInTour = startNode;
	int node = (int) neighborList[startNode][0];
	cycle.add(node);
	while (node != startNode) {
	    int neighbor = getOtherNeighbor(neighborList[node], nodeBeforeInTour);
	    cycle.add(neighbor);
	    nodeBeforeInTour = node;
	    node = neighbor;
	}
	Tour tour = new Tour(cycle);
	return tour;
    }

    public boolean isComplete() {
	if (nNodes == nCompletedNodes) {
	    return true;
	}
	return false;
    }

    public short getnNeighbors(int node) {
	return nNeighbors[node];
    }

    /**
     * In a list with two unique integers. Where one is the first integer,
     * return the other
     */
    private int getOtherNeighbor(long[] neighbors, long firstNeighbor) {
	if (neighbors[0] == firstNeighbor) {
	    return (int) neighbors[1];
	}
	if (neighbors[1] == firstNeighbor) {
	    return (int) neighbors[0];
	}
	throw new IllegalArgumentException("In getOtherNeignbor must the given array contain firstNeighbor");
    }
}
