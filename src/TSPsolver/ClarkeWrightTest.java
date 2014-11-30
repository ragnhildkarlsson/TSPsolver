package TSPsolver;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.junit.Test;
import org.mockito.Mockito;

public class ClarkeWrightTest {

    /**
     * Test that given following coordinate system and nodes
     *
     * <pre>
     * 0 1 2 3 4 5
     * 0 * * * B * A
     * 1 * * * * * *
     * 2 * * * * H *
     * 3 C * * * * *
     * </pre>
     * 
     * with the following distances between nodes dist(A-H) =3 dist(A-B) =2
     * dist(A-C) =6 dist(B-C)= 4 dist(B-H) =3 dist(C-H) =4
     *
     * where H is the hub, should constructTour return a tour with all nodes
     * where each node occurs only once.
     * 
     */
    @Test
    void testCreateTour() {
	Random mockRand = Mockito.mock(Random.class);
	int nodeA = 0;
	int nodeB = 1;
	int nodeC = 2;
	int nodeH = 3;
    }

    /**
     * Test that given the following neighborLists
     * 
     * <pre>
     * A - B
     * B - A , C
     * C - D, B
     * D - C
     * </pre>
     * 
     * Should both a call with node1=E and node2 = D to ceatesCycle(...) return
     * false and a call with node1=D and node2 = E to ceatesCycle(...) return
     * false
     */
    @Test
    public void testcreatesCycleReturnFalseWhenNoCycleExists() {
	// Initiate neighbor list
	int nodeA = 0;
	int nodeB = 1;
	int nodeC = 2;
	int nodeD = 3;
	int nodeE = 4;
	ArrayList<Integer> nodeANeighbors = new ArrayList<>();
	nodeANeighbors.add(nodeB);
	ArrayList<Integer> nodeBNeighbors = new ArrayList<>();
	nodeBNeighbors.add(nodeA);
	nodeBNeighbors.add(nodeC);
	ArrayList<Integer> nodeCNeighbors = new ArrayList<>();
	nodeCNeighbors.add(nodeB);
	nodeCNeighbors.add(nodeD);
	ArrayList<Integer> nodeDNeighbors = new ArrayList<>();
	nodeDNeighbors.add(nodeC);
	ArrayList<Integer> nodeENeighbors = new ArrayList<>();
	Map<Integer, List<Integer>> partialGraph = new HashMap<Integer, List<Integer>>();
	partialGraph.put(nodeA, nodeANeighbors);
	partialGraph.put(nodeB, nodeBNeighbors);
	partialGraph.put(nodeC, nodeCNeighbors);
	partialGraph.put(nodeD, nodeDNeighbors);
	partialGraph.put(nodeE, nodeENeighbors);

	ClarkeWright cw = new ClarkeWright(new Random());
	boolean res = cw.createsCycle(partialGraph, nodeD, nodeE);
	assertEquals(false, res);
	res = cw.createsCycle(partialGraph, nodeE, nodeD);
	assertEquals(false, res);

    }

    /**
     * Test that given the following neighborLists
     * 
     * <pre>
     * A - B , E
     * B - A , C
     * C - D, B
     * D - C
     * E - A
     * </pre>
     * 
     * Should both a call with node1=E and node2 = D to ceatesCycle(...) return
     * true and a call with node1=D and node2 = E to ceatesCycle(...) return
     * true
     */
    @Test
    public void testcreatesCycleReturnTrueWhenCycleExists() {
	// Initiate neighbor list
	int nodeA = 0;
	int nodeB = 1;
	int nodeC = 2;
	int nodeD = 3;
	int nodeE = 4;

	ArrayList<Integer> nodeANeighbors = new ArrayList<>();
	nodeANeighbors.add(nodeB);
	nodeANeighbors.add(nodeE);
	ArrayList<Integer> nodeBNeighbors = new ArrayList<>();
	nodeBNeighbors.add(nodeA);
	nodeBNeighbors.add(nodeC);
	ArrayList<Integer> nodeCNeighbors = new ArrayList<>();
	nodeCNeighbors.add(nodeB);
	nodeCNeighbors.add(nodeD);
	ArrayList<Integer> nodeDNeighbors = new ArrayList<>();
	nodeDNeighbors.add(nodeC);
	ArrayList<Integer> nodeENeighbors = new ArrayList<>();
	nodeENeighbors.add(nodeA);
	Map<Integer, List<Integer>> partialGraph = new HashMap<Integer, List<Integer>>();
	partialGraph.put(nodeA, nodeANeighbors);
	partialGraph.put(nodeB, nodeBNeighbors);
	partialGraph.put(nodeC, nodeCNeighbors);
	partialGraph.put(nodeD, nodeDNeighbors);
	partialGraph.put(nodeE, nodeENeighbors);

	ClarkeWright cw = new ClarkeWright(new Random());
	boolean res = cw.createsCycle(partialGraph, nodeD, nodeE);
	assertEquals(true, res);
	res = cw.createsCycle(partialGraph, nodeE, nodeD);
	assertEquals(true, res);

    }
}
