import java.util.List;
import java.util.Map;

public class PartialGraph {

    int nNodes;

    public PartialGraph(int nNodes) {
	this.nNodes = nNodes;

    }

    /**
     * In a list with two unique integers. Where one is the first integer,
     * return the other
     */
    private int getOtherInt(List<Integer> integers, int firstInteger) {
	int indexOfFirst = integers.indexOf(firstInteger);
	if (indexOfFirst < 0) {
	    throw new IllegalArgumentException("In getOtherInt must the list 'integers' contain firstInteger");
	}
	int indexOfOther = (indexOfFirst + 1) % 2;
	return integers.get(indexOfOther);
    }

    /**
     * Given a neighborList over a graph that is a tree and two nodes. returns
     * true if the adding of a edge between node1 and node2 would create a cycle
     * in the graph, otherwise false.
     * 
     * @param partialGraph
     * @param node1
     * @param node2
     * @return true if the adding of a edge between node1 and node2 would create
     *         a cycle in the graph otherwise false.
     */
    public boolean createsCycle(Map<Integer, List<Integer>> partialGraph, int node1, int node2) {

	// Handle the case no neighbors
	if (partialGraph.get(node1).size() == 0 && partialGraph.get(node2).size() == 0) {
	    return false;
	}
	// Check for neighbors in both directions
	// and chose to continue in any direction where neighbors exist
	int node = -1;
	if (partialGraph.get(node1).size() > 0) {
	    node = node1;
	}
	if (partialGraph.get(node2).size() > 0) {
	    node = node2;
	}
	int neighbor = partialGraph.get(node).get(0);
	// check if we find a loop
	int iter = 0;
	while (true && iter < 2000) {
	    if (neighbor == node1 || neighbor == node2) {
		// we have detected a loop
		return true;
	    }
	    // continue search

	    List<Integer> neighbors = partialGraph.get(neighbor);
	    if (neighbors.size() == 1) {
		// found an end
		return false;
	    }
	    int oldNode = node;
	    node = neighbor;
	    neighbor = getOtherInt(neighbors, oldNode);
	    iter++;
	}

	throw new IllegalStateException(
		"This TSPSolver handle indata of size <=1000 nodes, 2000 iterations should not be needed");
    }
}
