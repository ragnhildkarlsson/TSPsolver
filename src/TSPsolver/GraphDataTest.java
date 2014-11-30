package TSPsolver;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class GraphDataTest {

    @Test
    public void testCalculateDistanceRounding() {
	Map<Integer, Coordinates> nodeMap = new HashMap<>();
	GraphData graphData = new GraphData(nodeMap);

	// lines along x-axis
	Coordinates coord0 = new Coordinates(0, 0);
	Coordinates coord1 = new Coordinates(0.1, 0);
	assertEquals("Integer square distance between points: 0 and 0.1 on a line should be 0", 0,
		graphData.calculateDistance(coord0, coord1));

	Coordinates coord2 = new Coordinates(0.9, 0);
	assertEquals("Integer square distance between points: 0 and 0.9 on a line should be 1", 1,
		graphData.calculateDistance(coord0, coord2));

	// lines along y-axis
	Coordinates coordy0 = new Coordinates(0, 0);
	Coordinates coordy1 = new Coordinates(0, 0.1);
	assertEquals("Integer square distance between points: 0 and 0.1 on a line should be 0", 0,
		graphData.calculateDistance(coordy0, coordy1));

	Coordinates coordy2 = new Coordinates(0, 0.9);
	assertEquals("Integer square distance between points: 0 and 0.9 on a line should be 1", 1,
		graphData.calculateDistance(coordy0, coordy2));

    }

    @Test
    public void testCalculateDistanceUnit() {
	Map<Integer, Coordinates> nodeMap = new HashMap<>();
	GraphData graphData = new GraphData(nodeMap);

	Coordinates coord0 = new Coordinates(0, 0);
	Coordinates coord1 = new Coordinates(1, 0);
	assertEquals("distance should be 1", 1, graphData.calculateDistance(coord0, coord1));

	coord1 = new Coordinates(0, 1);
	assertEquals("distance should be 1", 1, graphData.calculateDistance(coord0, coord1));

	coord1 = new Coordinates(0, -1);
	assertEquals("distance should be 1", 1, graphData.calculateDistance(coord0, coord1));

	coord1 = new Coordinates(-1, 0);
	assertEquals("distance should be 1", 1, graphData.calculateDistance(coord0, coord1));

    }

    @Test
    public void testCalculateDistance() {
	Map<Integer, Coordinates> nodeMap = new HashMap<>();
	GraphData graphData = new GraphData(nodeMap);

	double x1 = 3456.54;
	double x2 = 4564.97;
	double y1 = 6465.4;
	double y2 = 1222.32;
	Coordinates coord1 = new Coordinates(x1, y1);
	Coordinates coord2 = new Coordinates(x2, y2);

	long result = graphData.calculateDistance(coord1, coord2);
	assertEquals(true, result <= 28718505 && result >= 28718504);
    }

    @Test
    public void testDistanceMatrixGeneration() {
	Map<Integer, Coordinates> nodeMap = new HashMap<>();

	Coordinates coord0 = new Coordinates(0, 0);
	Coordinates coord1 = new Coordinates(1, 0);
	Coordinates coord2 = new Coordinates(0, 1);
	Coordinates coord3 = new Coordinates(-1, 0);
	Coordinates coord4 = new Coordinates(0, -1);

	nodeMap.put(0, coord0);
	nodeMap.put(1, coord1);
	nodeMap.put(2, coord2);
	nodeMap.put(3, coord3);
	nodeMap.put(4, coord4);

	GraphData graphData = new GraphData(nodeMap);

	// All nodes should have distance 1 to node 0 except the 0 node itself.
	for (int i = 1; i <= 4; i++) {
	    assertEquals(1, graphData.getDistance(0, i));
	}

	assertEquals("Integer square distance 4 between (1,0) and (-1,0)", 4, graphData.getDistance(1, 3));

    }
}
