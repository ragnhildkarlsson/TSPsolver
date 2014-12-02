import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class GraphDataTest {

    @Test
    public void testCalculateDistanceRounding() {
	double[][] nodes = new double[0][0];
	GraphData graphData = new GraphData(nodes);

	// lines along x-axis
	double[] node0 = { 0, 0 };
	double[] node1 = { 0.1, 0 };
	assertEquals("Integer square distance between points: 0 and 0.1 on a line should be 0", 0,
		graphData.calculateDistance(node0, node1));

	double[] node2 = { 0.9, 0 };
	Coordinates coord2 = new Coordinates(0.9, 0);
	assertEquals("Integer square distance between points: 0 and 0.9 on a line should be 0", 0,
		graphData.calculateDistance(node0, node2));

	// lines along y-axis
	double[] nodey0 = { 0, 0 };
	double[] nodey1 = { 0, 0.1 };
	assertEquals("Integer square distance between points: 0 and 0.1 on a line should be 0", 0,
		graphData.calculateDistance(nodey0, nodey1));

	double[] nodey2 = { 0, 0.9 };
	assertEquals("Integer square distance between points: 0 and 0.9 on a line should be 0", 0,
		graphData.calculateDistance(nodey0, nodey2));

    }

    @Test
    public void testCalculateDistanceUnit() {
	double[][] nodes = new double[0][0];
	GraphData graphData = new GraphData(nodes);

	double[] node0 = { 0, 0 };
	double[] node1 = { 1, 0 };
	assertEquals("distance should be 1", 1, graphData.calculateDistance(node0, node1));

	node1[0] = 0;
	node1[1] = 1;
	assertEquals("distance should be 1", 1, graphData.calculateDistance(node0, node1));

	node1[0] = 0;
	node1[1] = -1;
	assertEquals("distance should be 1", 1, graphData.calculateDistance(node0, node1));
	node1[0] = -1;
	node1[1] = -0;
	assertEquals("distance should be 1", 1, graphData.calculateDistance(node0, node1));

    }

    @Test
    public void testCalculateDistance() {

	double[][] nodes = new double[0][0];
	GraphData graphData = new GraphData(nodes);

	double x1 = 3456.54;
	double x2 = 4564.97;
	double y1 = 6465.4;
	double y2 = 1222.32;
	double[] node1 = { x1, y1 };
	double[] node2 = { x2, y2 };

	long result = graphData.calculateDistance(node1, node2);
	assertEquals(true, result <= 28718505 && result >= 28718504);
    }

    @Test
    public void testDistanceMatrixGeneration() {

	double[][] nodes = new double[5][2];
	nodes[0][0] = 0;
	nodes[0][1] = 0;

	nodes[1][0] = 1;
	nodes[1][1] = 0;

	nodes[2][0] = 0;
	nodes[2][1] = 1;

	nodes[3][0] = -1;
	nodes[3][1] = 0;

	nodes[4][0] = 0;
	nodes[4][1] = -1;

	GraphData graphData = new GraphData(nodes);

	// All nodes should have distance 1 to node 0 except the 0 node itself.
	for (int i = 1; i <= 4; i++) {
	    assertEquals(1, graphData.getDistance(0, i));
	}

	assertEquals("Integer square distance 4 between (1,0) and (-1,0)", 4, graphData.getDistance(1, 3));

    }
}
