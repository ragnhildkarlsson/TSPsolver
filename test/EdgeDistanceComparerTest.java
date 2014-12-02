import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Collections;

import org.junit.Test;

public class EdgeDistanceComparerTest {

    @Test
    public void sortInIncreasingOrderTest() {
	Edge edgeShort = new Edge(1, 0, 1);
	Edge edgeLong = new Edge(1, 0, 10);
	ArrayList<Edge> list = new ArrayList<Edge>();
	list.add(edgeShort);
	list.add(edgeLong);
	EdgeDistanceComparer comparator = new EdgeDistanceComparer();

	Collections.sort(list, comparator);
	assertEquals(edgeShort, list.get(0));

    }

}
