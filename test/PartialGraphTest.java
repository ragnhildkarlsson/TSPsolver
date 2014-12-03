import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class PartialGraphTest {

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
     * Should both a call with node1=E and node2 = D to createsCycle(...) return
     * false and a call with node1=D and node2 = E to createsCycle(...) return
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

	PartialGraph partialGraph = new PartialGraph(7);
	partialGraph.addEdge(nodeA, nodeB);
	partialGraph.addEdge(nodeB, nodeC);
	partialGraph.addEdge(nodeC, nodeD);
	boolean res = partialGraph.hasCycle(nodeD, nodeE);
	assertEquals(false, res);
	res = partialGraph.hasCycle(nodeE, nodeD);
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
     * Should both a call with node1=E and node2 = D to createsCycle(...) return
     * true and a call with node1=D and node2 = E to createsCycle(...) return
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

	PartialGraph partialGraph = new PartialGraph(7);
	partialGraph.addEdge(nodeA, nodeB);
	partialGraph.addEdge(nodeA, nodeE);
	partialGraph.addEdge(nodeB, nodeC);
	partialGraph.addEdge(nodeC, nodeD);

	boolean res = partialGraph.hasCycle(nodeD, nodeE);
	assertEquals(true, res);
	res = partialGraph.hasCycle(nodeE, nodeD);
	assertEquals(true, res);

    }

}
