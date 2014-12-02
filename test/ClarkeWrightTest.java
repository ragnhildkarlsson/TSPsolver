import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.mockito.Mockito;

public class ClarkeWrightTest {

    // /**
    // * Test that given following coordinate system and nodes
    // *
    // * <pre>
    // * 0 1 2 3 4 5
    // * 0 * * * B * A
    // * 1 * * * * * *
    // * 2 * * * * H *
    // * 3 C * * * * *
    // *
    // * with the following distances between nodes
    // * dist(A-H) =3
    // * dist(A-B) =2
    // * dist(A-C) =6
    // * dist(B-C)= 4
    // * dist(B-H) =3
    // * dist(C-H) =4
    // * </pre>
    // *
    // * where H is the hub, should constructTour return a tour with all nodes
    // * where each node occurs only once.
    // *
    // */
    // @Test
    // public void testCreateTour() {
    // Random mockRand = Mockito.mock(Random.class);
    // int nodeA = 0;
    // int nodeB = 1;
    // int nodeC = 2;
    // int nodeH = 3;
    // List<Integer> nodes = new ArrayList<>();
    // nodes.add(nodeA);
    // nodes.add(nodeB);
    // nodes.add(nodeC);
    // nodes.add(nodeH);
    //
    // Mockito.when(mockRand.nextInt(4)).thenReturn(3);
    // GraphData mockGraphData = Mockito.mock(GraphData.class);
    // Mockito.when(mockGraphData.numberOfNodes()).thenReturn(4);
    // // Mock distances
    // Mockito.when(mockGraphData.getDistance(nodeA, nodeH)).thenReturn((long)
    // 3);
    // Mockito.when(mockGraphData.getDistance(nodeA, nodeB)).thenReturn((long)
    // 2);
    // Mockito.when(mockGraphData.getDistance(nodeA, nodeC)).thenReturn((long)
    // 6);
    //
    // Mockito.when(mockGraphData.getDistance(nodeB, nodeA)).thenReturn((long)
    // 2);
    // Mockito.when(mockGraphData.getDistance(nodeB, nodeC)).thenReturn((long)
    // 4);
    // Mockito.when(mockGraphData.getDistance(nodeB, nodeH)).thenReturn((long)
    // 3);
    //
    // Mockito.when(mockGraphData.getDistance(nodeC, nodeA)).thenReturn((long)
    // 6);
    // Mockito.when(mockGraphData.getDistance(nodeC, nodeB)).thenReturn((long)
    // 4);
    // Mockito.when(mockGraphData.getDistance(nodeC, nodeH)).thenReturn((long)
    // 4);
    //
    // Mockito.when(mockGraphData.getDistance(nodeH, nodeA)).thenReturn((long)
    // 3);
    // Mockito.when(mockGraphData.getDistance(nodeH, nodeB)).thenReturn((long)
    // 3);
    // Mockito.when(mockGraphData.getDistance(nodeH, nodeC)).thenReturn((long)
    // 4);
    //
    // ClarkeWright cw = new ClarkeWright(mockRand);
    // Tour tour = cw.constructTour(mockGraphData);
    // List<Integer> res = tour.getTourList();
    // assertEquals(4, res.size());
    // assertEquals(true, res.contains(nodeA));
    // assertEquals(true, res.contains(nodeB));
    // assertEquals(true, res.contains(nodeC));
    // assertEquals(true, res.contains(nodeH));
    // }
    //
    // /**
    // * Test that given the following neighborLists
    // *
    // * <pre>
    // * A - B
    // * B - A , C
    // * C - D, B
    // * D - C
    // * </pre>
    // *
    // * Should both a call with node1=E and node2 = D to createsCycle(...)
    // return
    // * false and a call with node1=D and node2 = E to createsCycle(...) return
    // * false
    // */
    // @Test
    // public void testcreatesCycleReturnFalseWhenNoCycleExists() {
    // // Initiate neighbor list
    // int nodeA = 0;
    // int nodeB = 1;
    // int nodeC = 2;
    // int nodeD = 3;
    // int nodeE = 4;
    // ArrayList<Integer> nodeANeighbors = new ArrayList<>();
    // nodeANeighbors.add(nodeB);
    // ArrayList<Integer> nodeBNeighbors = new ArrayList<>();
    // nodeBNeighbors.add(nodeA);
    // nodeBNeighbors.add(nodeC);
    // ArrayList<Integer> nodeCNeighbors = new ArrayList<>();
    // nodeCNeighbors.add(nodeB);
    // nodeCNeighbors.add(nodeD);
    // ArrayList<Integer> nodeDNeighbors = new ArrayList<>();
    // nodeDNeighbors.add(nodeC);
    // ArrayList<Integer> nodeENeighbors = new ArrayList<>();
    // Map<Integer, List<Integer>> partialGraph = new HashMap<Integer,
    // List<Integer>>();
    // partialGraph.put(nodeA, nodeANeighbors);
    // partialGraph.put(nodeB, nodeBNeighbors);
    // partialGraph.put(nodeC, nodeCNeighbors);
    // partialGraph.put(nodeD, nodeDNeighbors);
    // partialGraph.put(nodeE, nodeENeighbors);
    //
    // ClarkeWright cw = new ClarkeWright(null);
    // boolean res = cw.createsCycle(partialGraph, nodeD, nodeE);
    // assertEquals(false, res);
    // res = cw.createsCycle(partialGraph, nodeE, nodeD);
    // assertEquals(false, res);
    //
    // }
    //
    // /**
    // * Test that given the following neighborLists
    // *
    // * <pre>
    // * A - B , E
    // * B - A , C
    // * C - D, B
    // * D - C
    // * E - A
    // * </pre>
    // *
    // * Should both a call with node1=E and node2 = D to createsCycle(...)
    // return
    // * true and a call with node1=D and node2 = E to createsCycle(...) return
    // * true
    // */
    // @Test
    // public void testcreatesCycleReturnTrueWhenCycleExists() {
    // // Initiate neighbor list
    // int nodeA = 0;
    // int nodeB = 1;
    // int nodeC = 2;
    // int nodeD = 3;
    // int nodeE = 4;
    //
    // ArrayList<Integer> nodeANeighbors = new ArrayList<>();
    // nodeANeighbors.add(nodeB);
    // nodeANeighbors.add(nodeE);
    // ArrayList<Integer> nodeBNeighbors = new ArrayList<>();
    // nodeBNeighbors.add(nodeA);
    // nodeBNeighbors.add(nodeC);
    // ArrayList<Integer> nodeCNeighbors = new ArrayList<>();
    // nodeCNeighbors.add(nodeB);
    // nodeCNeighbors.add(nodeD);
    // ArrayList<Integer> nodeDNeighbors = new ArrayList<>();
    // nodeDNeighbors.add(nodeC);
    // ArrayList<Integer> nodeENeighbors = new ArrayList<>();
    // nodeENeighbors.add(nodeA);
    // Map<Integer, List<Integer>> partialGraph = new HashMap<Integer,
    // List<Integer>>();
    // partialGraph.put(nodeA, nodeANeighbors);
    // partialGraph.put(nodeB, nodeBNeighbors);
    // partialGraph.put(nodeC, nodeCNeighbors);
    // partialGraph.put(nodeD, nodeDNeighbors);
    // partialGraph.put(nodeE, nodeENeighbors);
    //
    // ClarkeWright cw = new ClarkeWright(null);
    // boolean res = cw.createsCycle(partialGraph, nodeD, nodeE);
    // assertEquals(true, res);
    // res = cw.createsCycle(partialGraph, nodeE, nodeD);
    // assertEquals(true, res);
    //
    // }

    /**
     * Test that given following coordinate system and nodes
     *
     * <pre>
     * 0 1 2 3 4 5
     * 0 * * * B * A
     * 1 * * * * * *
     * 2 * * * * H *
     * 3 C * * * * *
     * 
     * with the following distances between nodes
     * dist(A-H) =3
     * dist(A-B) =2
     * dist(A-C) =6
     * dist(B-C)= 4
     * dist(B-H) =3
     * dist(C-H) =4
     * </pre>
     * 
     * where H is the hub, should getShortcuts return a matrix with teh
     * following edges
     * 
     * <pre>
     * [A,B,2] or [B,A,2]
     * and
     * [A,C,6] or [C,A,6]
     * and 
     * [B,C,4] or [C,B,4]
     * </pre>
     */
    @Test
    public void testGetShortCutsReturnValidEdges() {
	int nodeA = 0;
	int nodeB = 1;
	int nodeC = 2;
	int nodeH = 3;
	List<Integer> nodes = new ArrayList<>();
	nodes.add(nodeA);
	nodes.add(nodeB);
	nodes.add(nodeC);
	nodes.add(nodeH);

	GraphData mockGraphData = Mockito.mock(GraphData.class);
	Mockito.when(mockGraphData.numberOfNodes()).thenReturn(4);
	// Mock distances
	Mockito.when(mockGraphData.getDistance(nodeA, nodeH)).thenReturn((long) 3);
	Mockito.when(mockGraphData.getDistance(nodeA, nodeB)).thenReturn((long) 2);
	Mockito.when(mockGraphData.getDistance(nodeA, nodeC)).thenReturn((long) 6);

	Mockito.when(mockGraphData.getDistance(nodeB, nodeA)).thenReturn((long) 2);
	Mockito.when(mockGraphData.getDistance(nodeB, nodeC)).thenReturn((long) 4);
	Mockito.when(mockGraphData.getDistance(nodeB, nodeH)).thenReturn((long) 3);

	Mockito.when(mockGraphData.getDistance(nodeC, nodeA)).thenReturn((long) 6);
	Mockito.when(mockGraphData.getDistance(nodeC, nodeB)).thenReturn((long) 4);
	Mockito.when(mockGraphData.getDistance(nodeC, nodeH)).thenReturn((long) 4);

	Mockito.when(mockGraphData.getDistance(nodeH, nodeA)).thenReturn((long) 3);
	Mockito.when(mockGraphData.getDistance(nodeH, nodeB)).thenReturn((long) 3);
	Mockito.when(mockGraphData.getDistance(nodeH, nodeC)).thenReturn((long) 4);
	ClarkeWright cw = new ClarkeWright(null);
	long[][] res = cw.getShortcuts(mockGraphData, 3);
	assertEquals(3, res.length);
	// expected edges
	long[] e1 = { 0, 1, ((3 + 3) - 2) };
	long[] e2 = { 0, 2, ((3 + 4) - 6) };
	long[] e3 = { 1, 2, ((3 + 4) - 4) };
	assertEquals(3, res.length);
	assertEquals(true, containsEdge(res, e1));
	assertEquals(true, containsEdge(res, e2));
	assertEquals(true, containsEdge(res, e3));
    }

    private boolean containsEdge(long[][] edgeList, long[] edge) {
	for (int i = 0; i < edgeList.length; i++) {
	    if (edgeList[i][0] == edge[0] && edgeList[i][1] == edge[1] && edgeList[i][2] == edge[2]) {
		// exactly the same
		return true;
	    }
	    if (edgeList[i][0] == edge[1] && edgeList[i][1] == edge[0] && edgeList[i][2] == edge[2]) {
		// same nodes and saving but switched order of nodes
		return true;
	    }
	}
	return false;
    }
}
