package TSPsolver;

import java.util.HashMap;
import java.util.Map;

public class IndataReader {

    private Kattio kattio;

    public IndataReader(Kattio kattio) {
	this.kattio = kattio;
    }

    public GraphData read() {

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
