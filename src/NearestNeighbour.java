import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class NearestNeighbour implements TourConstructStrategy {

	private Random random;

	public NearestNeighbour(Random random) {
		this.random = random;
	}

	public Tour constructLightTour(GraphDataLight data) {
		short numberOfNodes = data.numberOfNodes();
		List<Short> nodes = new ArrayList<>(numberOfNodes);
		for (short i = 0; i < numberOfNodes; i++) {
			nodes.add(i);
		}
		// Pick a random first node
		short firstNode = (short) random.nextInt(nodes.size());
		nodes.remove(new Short(firstNode));

		List<Integer> partialTour = new ArrayList<>();
		partialTour.add((int) firstNode);
		short currentNode = firstNode;

		while (!nodes.isEmpty()) {
			short bestNode = -1;
			int[][] neighborList = data.getClosestKNeighbors(currentNode);
			for (int i = 0; i < neighborList.length; i++) {
				if (nodes.contains(neighborList[i][1])) {
					bestNode = (short) neighborList[i][1];
				}
			}
			if (bestNode == -1) { // we could not find a close neighbor, take random.
				bestNode = nodes.get(random.nextInt(nodes.size()));
			}

			// Add the best node to tour and remove it from nodes
			if (bestNode == -1) {
				throw new IllegalStateException("trying to construct illegal tour");
			}
			nodes.remove(new Short(bestNode));
			partialTour.add((int) bestNode);
			currentNode = bestNode;
		}

		return new Tour(partialTour);

	}

	@Override
	public Tour constructTour(GraphData graphData) {

		int nNodes = graphData.numberOfNodes();
		Set<Integer> nodes = new HashSet<Integer>(nNodes);
		for (int i = 0; i < nNodes; i++) {
			nodes.add(i);
		}
		// Pick a random first node
		int firstNode = random.nextInt(nodes.size());
		nodes.remove(firstNode);

		List<Integer> partialTour = new ArrayList<>();
		partialTour.add(firstNode);
		int currentNode = firstNode;

		while (!nodes.isEmpty()) {
			long bestDistance = Long.MAX_VALUE;
			int bestNode = -1;
			for (Integer node : nodes) {
				if (!(currentNode == node)) { // for all nodes except it self
					// check distance
					long distance = graphData.getDistance(currentNode, node);
					if (distance < bestDistance) {
						bestDistance = distance;
						bestNode = node;
					}

				}
			}
			// Add the best node to tour and remove it from nodes
			if (bestNode == -1) {
				throw new IllegalStateException("trying to construct illegal tour");
			}
			nodes.remove(bestNode);
			partialTour.add(bestNode);
			currentNode = bestNode;
		}
		return new Tour(partialTour);
	}

}
