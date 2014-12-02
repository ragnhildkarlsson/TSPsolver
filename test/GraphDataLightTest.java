import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.mockito.Mockito;

public class GraphDataLightTest {

	@Test
	public void shortAndSortTest() {
		int numberOfNeighbours = 3;
		long[][] arrayOfArrays = { { 0, 0, 2 }, { 0, 0, 4 }, { 0, 0, 3 }, { 0, 0, 1 } };
		EdgeDistanceComparator comparator = new EdgeDistanceComparator();

		GraphData mockData = Mockito.mock(GraphData.class);
		Mockito.when(mockData.numberOfNodes()).thenReturn(0);
		GraphDataLight graphDataLight = new GraphDataLight(mockData, numberOfNeighbours, comparator);

		long[][] result = graphDataLight.shortAndSort(arrayOfArrays, numberOfNeighbours);

		assertEquals("Should get numberOfNeighbour sized result array", numberOfNeighbours, result.length);
		for (int i = 0; i < result.length; i++) {
			assertEquals(i + 1, result[i][2]);
		}

	}
}
