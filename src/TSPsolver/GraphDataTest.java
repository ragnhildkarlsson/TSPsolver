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
	assertEquals("Integer distance between points: 0 and 0.1 on a line should be 0", 0,
		graphData.calculateDistance(coord0, coord1));

	Coordinates coord2 = new Coordinates(0.6, 0);
	assertEquals("Integer distance between points: 0 and 0.6 on a line should be 1", 1,
		graphData.calculateDistance(coord0, coord2));

	// lines along y-axis
	Coordinates coordy0 = new Coordinates(0, 0);
	Coordinates coordy1 = new Coordinates(0, 0.1);
	assertEquals("Integer distance between points: 0 and 0.1 on a line should be 0", 0,
		graphData.calculateDistance(coordy0, coordy1));

	Coordinates coordy2 = new Coordinates(0, 0.6);
	assertEquals("Integer distance between points: 0 and 0.6 on a line should be 1", 1,
		graphData.calculateDistance(coordy0, coordy2));

    }

    @Test
    public void testCalculateDistanceSimple() {
	Map<Integer, Coordinates> nodeMap = new HashMap<>();
	GraphData graphData = new GraphData(nodeMap);

	Coordinates coord0 = new Coordinates(0, 0);
	Coordinates coord1 = new Coordinates(1, 0);
	assertEquals("distance should be 1", 1, graphData.calculateDistance(coord0, coord1));

	coord1 = new Coordinates(0, 1);
	assertEquals("distance should be 1", 1, graphData.calculateDistance(coord0, coord1));

    }
}
