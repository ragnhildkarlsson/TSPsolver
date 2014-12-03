import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class GraphDataLightTest {

	@Test
	public void shortAndSortTest() {
		int numberOfNeighbours = 3;
		long[][] arrayOfArrays = { { 0, 0, 4 }, { 0, 0, 16 }, { 0, 0, 9 }, { 0, 0, 1 } };
		EdgeDistanceComparator comparator = new EdgeDistanceComparator();

		double[][] coordinates = new double[4][2];
		GraphDataLight graphDataLight = new GraphDataLight(coordinates, numberOfNeighbours, comparator);

		int[][] result = graphDataLight.shortAndSort(arrayOfArrays, numberOfNeighbours);

		assertEquals("Should get numberOfNeighbour sized result array", numberOfNeighbours, result.length);
		for (int i = 0; i < result.length; i++) {
			assertEquals(i + 1, result[i][2]);
		}

	}

	/**
	 * Test that a neighbor list for the following graph can be constructed:
	 * 
	 * 0-1--2
	 * 
	 * Where "-" represents the distance 1;
	 * 
	 */

	@Test
	public void testNeighbourListConstruction() {
		int numberOfNeighbours = 2;
		EdgeDistanceComparator comparator = new EdgeDistanceComparator();

		double[][] coordinates = { { 0, 0 }, { 1, 0 }, { 3, 0 } };

		GraphDataLight graphDataLight = new GraphDataLight(coordinates, numberOfNeighbours, comparator);
		int[][] edgesToNode0 = graphDataLight.getClosestKNeighbors((short) 0);
		// first edge to node0 should be {0,1,1}
		assertEquals(0, edgesToNode0[0][0]);
		assertEquals(1, edgesToNode0[0][1]);
		assertEquals(1, edgesToNode0[0][1]);
		// Second edge to node0 should be {0,2,9}
		assertEquals(0, edgesToNode0[1][0]);
		assertEquals(2, edgesToNode0[1][1]);
		assertEquals(3, edgesToNode0[1][2]);
		// There should be no more edges than these two
		assertEquals(2, edgesToNode0.length);

		int[][] edgesToNode1 = graphDataLight.getClosestKNeighbors((short) 1);
		// first edge to node1 should be {1,0,1}
		assertEquals(1, edgesToNode1[0][0]);
		assertEquals(0, edgesToNode1[0][1]);
		assertEquals(1, edgesToNode1[0][2]);
		// Second edge to node1 should be {1,2,4}
		assertEquals(1, edgesToNode1[1][0]);
		assertEquals(2, edgesToNode1[1][1]);
		assertEquals(2, edgesToNode1[1][2]);
		// There should be no more edges than these two
		assertEquals(2, edgesToNode1.length);

		int[][] edgesToNode2 = graphDataLight.getClosestKNeighbors((short) 2);
		// first edge to node2 should be {2,1,4}
		assertEquals(2, edgesToNode2[0][0]);
		assertEquals(1, edgesToNode2[0][1]);
		assertEquals(2, edgesToNode2[0][2]);
		// Second edge to node2 should be {2,0,9}
		assertEquals(2, edgesToNode2[1][0]);
		assertEquals(0, edgesToNode2[1][1]);
		assertEquals(3, edgesToNode2[1][2]);
		// There should be no more edges than these two
		assertEquals(2, edgesToNode2.length);
	}
}
