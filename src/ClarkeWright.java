import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class ClarkeWright implements TourConstructStrategy {

    private Random rand;

    public ClarkeWright(Random rand) {
	this.rand = rand;
    }

    @Override
    public Tour constructTour(GraphData graphData) {
	int nNodes = graphData.numberOfNodes();
	Set<Integer> nodes = new HashSet<Integer>(nNodes);
	for (int i = 0; i < nNodes; i++) {
	    nodes.add(i);
	}
	if (nodes.size() < 4) {
	    ArrayList<Integer> tourList = new ArrayList<Integer>();
	    tourList.addAll(nodes);
	    return new Tour(tourList);
	}

	// remove hub from nodes
	int hub = rand.nextInt(nodes.size());
	nodes.remove(hub);
	List<Edge> savings = getShortcuts(graphData, hub);
	// Sort savings in decreasing order
	EdgeDistanceComparer edgeComparer = new EdgeDistanceComparer();
	Collections.sort(savings, edgeComparer);
	// construct tour
	Map<Integer, List<Integer>> partialTour = new HashMap<>();
	for (Integer node : nodes) {
	    List<Integer> neighbours = new ArrayList<Integer>();
	    partialTour.put(node, neighbours);
	}

	int edgeIndex = 0;
	while (nodes.size() > 2) {
	    Edge edge = savings.get(edgeIndex);
	    int nodeA = edge.getNodeA();
	    int nodeB = edge.getNodeB();
	    if (partialTour.get(nodeA).size() < 2 && partialTour.get(nodeB).size() < 2) {
		if (!createsCycle(partialTour, nodeA, nodeB)) {
		    partialTour.get(nodeA).add(nodeB);
		    partialTour.get(nodeB).add(nodeA);
		}
		if (partialTour.get(nodeA).size() == 2) {
		    nodes.remove(nodeA);
		}
		if (partialTour.get(nodeB).size() == 2) {
		    nodes.remove(nodeB);
		}
	    }

	    edgeIndex++;
	}

	// Add remaining edges to the partial tour;
	Integer[] remainingNodes = nodes.toArray(new Integer[2]);
	int remainingNode1 = remainingNodes[0];
	int remainingNode2 = remainingNodes[1];
	List<Integer> start = new ArrayList<Integer>();
	start.add(remainingNode1);
	partialTour.put(hub, start);
	partialTour.get(remainingNode1).add(hub);
	partialTour.get(remainingNode2).add(hub);
	List<Integer> tourList = getTour(partialTour, hub);
	Tour tour = new Tour(tourList);
	return tour;
    }

    private List<Integer> getTour(Map<Integer, List<Integer>> partialGraph, int hub) {
	ArrayList<Integer> tour = new ArrayList<Integer>();
	int nodeBeforeInTour = hub;
	int node = partialGraph.get(hub).get(0);
	tour.add(node);
	while (node != hub) {
	    int neighbor = getOtherInt(partialGraph.get(node), nodeBeforeInTour);
	    tour.add(neighbor);
	    nodeBeforeInTour = node;
	    node = neighbor;
	}
	return tour;
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

    /**
     * Given a graphData Returns a list of edges with all possible edges between
     * nodes that are not the hub.
     * 
     * @param graphData
     *            The GraphData that the result is based on
     * @param hub
     *            the hub node;
     */
    private List<Edge> getShortcuts(GraphData graphData, int hub) {
	int nNodes = graphData.numberOfNodes();
	ArrayList<Edge> shortcuts = new ArrayList<Edge>();
	for (int i = 0; i < nNodes; i++) {
	    for (int j = i + 1; j < nNodes; j++) {
		if (j != hub && i != hub) {
		    Edge shortcut = new Edge(i, j, graphData.getDistance(i, j));
		    shortcuts.add(shortcut);
		}

	    }
	}
	return shortcuts;
    }

}
