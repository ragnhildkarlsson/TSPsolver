import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.Test;
import org.mockito.Mockito;

public class NearestNeighbourTest {

    @Test
    public void testCreateTour() {
	Random mockRand = Mockito.mock(Random.class);
	int nodeA = 0;
	int nodeB = 1;
	int nodeC = 2;
	int nodeH = 3;
	List<Integer> nodes = new ArrayList<>();
	nodes.add(nodeA);
	nodes.add(nodeB);
	nodes.add(nodeC);
	nodes.add(nodeH);

	Mockito.when(mockRand.nextInt(4)).thenReturn(3);
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

	TourConstructStrategy nearestNeighbour = new NearestNeighbour(mockRand);
	Tour tour = nearestNeighbour.constructTour(mockGraphData);
	List<Integer> res = tour.getTourList();
	assertEquals(4, res.size());
	assertEquals(true, res.contains(nodeA));
	assertEquals(true, res.contains(nodeB));
	assertEquals(true, res.contains(nodeC));
	assertEquals(true, res.contains(nodeH));
    }

}
