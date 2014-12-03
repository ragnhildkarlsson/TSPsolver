import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class TwoOpt {

	// private final int ITER = Integer.MAX_VALUE;
	private Set<Short> shelf;
	private Queue<Short> fifoQueue;
	private short[] currentTour;
	private int numberOfNodes;
	private GraphDataLight graphDataLight;

	public Tour optimizeTour(Tour tour, GraphDataLight graphData) {

		// We don't try to optimize tours below size 4
		this.currentTour = getArray(tour);
		this.numberOfNodes = currentTour.length;

		if (numberOfNodes < 4) {
			return tour;
		}

		this.shelf = new HashSet<>();
		this.fifoQueue = new LinkedList<>();
		this.graphDataLight = graphData;

		// Add all nodes to the queue
		for (int i = 0; i < numberOfNodes; i++) {
			fifoQueue.add(currentTour[i]);
		}

		// Try to optimize as long as there is a node in the queue
		int iter = 0;
		while (!fifoQueue.isEmpty()) {
			iter++;
			short currentNode = fifoQueue.poll();
			// Get the next node in the order defined by currentTour.
			short currentAheadNeighbour = getNeighborAhead(currentNode);
			short currentBeforeNeighbour = getNeighborBefore(currentNode);
			// Check if this distance is stored in GraphDataLight (if not its
			// bigger than all stored)
			int distance = getDistanceIfExist(currentNode, currentAheadNeighbour);
			// go through the neighbor list for the current node to find a node
			// which has shorter distance
			int[][] neighbors = this.graphDataLight.getClosestKNeighbors((short) currentNode);
			boolean updatedTour = false;
			for (int i = 0; i < neighbors.length; i++) {
				if (neighbors[i][2] < distance && i != currentBeforeNeighbour) {
					// found a shorter distance! this edge would be a better choice
					short betterNeighbour = (short) neighbors[i][1];
					short betterNeighboursNeighbour = getNeighborAhead(betterNeighbour);
					// Check if the other edge needed also is a better choice
					long otherEdgeCurrentDistance = getDistanceIfExist(betterNeighbour, betterNeighboursNeighbour);
					long otherEdgepossiblyDistance = getDistanceIfExist(currentAheadNeighbour,
							betterNeighboursNeighbour);
					if (otherEdgepossiblyDistance < otherEdgeCurrentDistance) {
						// a perfect match, both new edges are shorter than the originals, lets exchange!
						short[] newTour = createNewTour(currentNode, currentAheadNeighbour, betterNeighbour,
								betterNeighboursNeighbour);
						fifoQueue.add((short) currentNode); // put the node last in queue
						removeFromShelf(currentAheadNeighbour, betterNeighbour, betterNeighboursNeighbour);
						this.currentTour = newTour;
						updatedTour = true;
						break;
					}
				}

			}
			// if we failed to updated the tour put this node on the shelf
			if (!updatedTour)
				shelf.add(currentNode);
			// try with the next node in the queue
		}
		// Return the resulting tour
		List<Integer> finalTour = new ArrayList<>(numberOfNodes);
		for (int i = 0; i < numberOfNodes; i++) {
			finalTour.add((int) this.currentTour[i]);
		}
		Tour result = new Tour(finalTour);
		return result;
	}

	/**
	 * Removes the three nodes from the shelf if present and puts them back into the queue.
	 */
	private void removeFromShelf(short currentNeighbour, short betterNeighbour, short betterNeighboursNeighbour) {
		if (this.shelf.remove(currentNeighbour)) {
			this.fifoQueue.add(currentNeighbour);
		}

		if (this.shelf.remove(betterNeighbour)) {
			this.fifoQueue.add(betterNeighbour);
		}

		if (this.shelf.remove(betterNeighboursNeighbour)) {
			this.fifoQueue.add(betterNeighboursNeighbour);
		}

	}

	private short[] createNewTour(short currentNode, short currentNeighbour, short betterNeighbour,
			short betterNeighboursNeighbour) {
		short[] newTour = new short[numberOfNodes];
		short node = betterNeighboursNeighbour;
		short index = 0;
		// Get the first part of the tour
		while (node != currentNode) {
			newTour[index] = node;
			node = getNeighborAhead(node);
			index++;
		}
		newTour[index] = node; // finish part 1
		index++;
		// Get the second part of the tour
		node = betterNeighbour;
		while (node != currentNeighbour) {
			newTour[index] = node;
			node = getNeighborBefore(node);
			index++;
		}
		newTour[index] = node; // finish part 2

		if ((index + 1) != numberOfNodes) {
			throw new IllegalStateException("New optimized tour is lacking nodes");
		}
		return newTour;
	}

	private int getDistanceIfExist(int mainNode, int possibleNeighbor) {
		int[][] edges = this.graphDataLight.getClosestKNeighbors((short) mainNode);
		for (int i = 0; i < edges.length; i++) {
			if (edges[i][1] == possibleNeighbor) {
				return edges[i][2];
			}
		}
		return Integer.MAX_VALUE;
	}

	private short getNeighborAhead(short currentNode) {
		short indexOfCurrentNode = getIndexOf(currentNode);
		short nextIndex = (short) ((indexOfCurrentNode + 1) % currentTour.length);
		return currentTour[nextIndex];
	}

	private short getNeighborBefore(short currentNode) {
		short indexOfCurrentNode = getIndexOf(currentNode);
		short nextIndex = (short) (indexOfCurrentNode - 1);
		if (nextIndex < 0) {
			nextIndex = (short) (currentTour.length - 1);
		}

		return currentTour[nextIndex];
	}

	private short getIndexOf(short nodeToFind) {
		for (short i = 0; i < numberOfNodes; i++) {
			if (currentTour[i] == nodeToFind) {
				return i;
			}
		}
		return Short.MAX_VALUE;
	}

	private short[] getArray(Tour tour) {
		short[] arrayTour = new short[tour.getTourList().size()];
		short index = 0;
		for (Integer integer : tour.getTourList()) {
			arrayTour[index] = (short) (int) integer;
			index++;

		}
		return arrayTour;
	}

}
