import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.mockito.Mockito;

public class TwoOptTest {

	/**
	 * Check that crossing paths are optimized
	 * 
	 * <pre>
	 * 1-2       1-2
	 *  X   -->  | |
	 * 0-3       0-3
	 * where all distances are 1 except for diagonal lines which have distance 2.
	 * 
	 * <pre>
	 */
	@Test
	public void optimizationTest() {
		// prepare a mock tour
		List<Integer> initialTour = new ArrayList<>();
		initialTour.add(0);
		initialTour.add(2);
		initialTour.add(1);
		initialTour.add(3);
		int numberOfNodes = initialTour.size();

		Tour mockTour = Mockito.mock(Tour.class);
		Mockito.when(mockTour.getTourList()).thenReturn(initialTour);
		// prepare a mock GraphDataLight

		GraphDataLight mockData = Mockito.mock(GraphDataLight.class);
		long[][] edgesFrom0 = { { 0, 1, 1 }, { 0, 3, 1 }, { 0, 2, 2 } }; // include all edges to see that works
		long[][] edgesFrom1 = { { 1, 0, 1 }, { 1, 2, 1 } }; // skip {1,3,2} has it should work anyway
		long[][] edgesFrom2 = { { 2, 3, 1 }, { 2, 1, 1 }, { 2, 0, 2 } };
		long[][] edgesFrom3 = { { 3, 0, 1 }, { 3, 2, 1 } };

		Mockito.when(mockData.getClosestKNeighbors((short) 0)).thenReturn(edgesFrom0);
		Mockito.when(mockData.getClosestKNeighbors((short) 1)).thenReturn(edgesFrom1);
		Mockito.when(mockData.getClosestKNeighbors((short) 2)).thenReturn(edgesFrom2);
		Mockito.when(mockData.getClosestKNeighbors((short) 3)).thenReturn(edgesFrom3);

		TwoOpt twoOpt = new TwoOpt();
		Tour resultTour = twoOpt.optimizeTour(mockTour, mockData);
		List<Integer> result = resultTour.getTourList();

		assertEquals("New tour should be same size", numberOfNodes, result.size());
		// Check that the wanted tour has been created, it should either be 0->1->2->3 or 3->2->1->0
		int indexOfNode0 = result.indexOf(0);
		int indexOfNode1 = result.indexOf(1);
		int indexOfNode2 = result.indexOf(2);
		int indexOfNode3 = result.indexOf(3);

		int nextNodeAheadOf0 = getNodeAhead(result, indexOfNode0);
		assertEquals(true, (nextNodeAheadOf0 == 1 || nextNodeAheadOf0 == 3));

		if (nextNodeAheadOf0 == 1) {
			assertEquals(2, getNodeAhead(result, indexOfNode1));
			assertEquals(3, getNodeAhead(result, indexOfNode2));
			assertEquals(0, getNodeAhead(result, indexOfNode3));
		} else {
			assertEquals(2, getNodeAhead(result, indexOfNode3));
			assertEquals(1, getNodeAhead(result, indexOfNode2));
			assertEquals(0, getNodeAhead(result, indexOfNode1));

		}

	}

	private int getNodeAhead(List<Integer> list, int indexOfNode) {
		int index = (indexOfNode + 1) % list.size();
		return list.get(index);
	}

}
