import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TestDataGenerator {
    private int numberOfNodes;
    private double maxValue = 10e6;

    // private double maxValue = 1000;

    public TestDataGenerator(int numberOfNodes) {
	this.numberOfNodes = numberOfNodes;
    }

    public String getTestCase() {
	Random random = new Random();
	List<Coordinates> nodeCoordinates = new ArrayList<>();

	for (int i = 0; i < numberOfNodes; i++) {
	    Coordinates coord = new Coordinates(random.nextDouble() * maxValue, random.nextDouble() * maxValue);
	    nodeCoordinates.add(coord);

	}

	StringBuilder sb = new StringBuilder();
	sb.append(numberOfNodes + "\n");
	for (Coordinates coord : nodeCoordinates) {
	    sb.append(coord.getX());
	    sb.append(" ");
	    sb.append(coord.getY());
	    sb.append("\n");
	}

	return sb.toString();

    }
}
