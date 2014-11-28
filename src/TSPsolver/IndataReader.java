package TSPsolver;

import java.util.HashMap;
import java.util.Map;

public class IndataReader {

    public GraphData read(Kattio kattio) {

	Map<Integer, Coordinates> nodeMap = new HashMap<>();

	// Read in the number of nodes
	int numberOfNodes = kattio.getInt();

	// for each node read the coordinates
	for (int i = 0; i < numberOfNodes; i++) {
	    double x = kattio.getDouble();
	    double y = kattio.getDouble();
	    nodeMap.put(i, new Coordinates(x, y));

	}

	return new GraphData(nodeMap);

    }
}
