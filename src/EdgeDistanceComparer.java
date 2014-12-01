

import java.util.Comparator;

public class EdgeDistanceComparer implements Comparator<Edge> {

    @Override
    public int compare(Edge edge1, Edge edge2) {

	return Long.compare(edge1.getDistance(), edge2.getDistance());
    }
}
