import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class TwoOpt {

	private Set<Integer> shelf;
	private Queue<Integer> fifoQueue;
	private int[] currentTour;
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
		while (!fifoQueue.isEmpty()) {
			int currentNode = fifoQueue.poll();
			// Get the next node in the order defined by currentTour.
			int currentNeighbour = getOrderedNeighbor(currentNode);
			// Check if this distance is stored in GraphDataLight (if not its
			// bigger than all stored)
			long distance = getDistanceIfExist(currentNode, currentNeighbour);
			// go through the neighbor list for the current node to find a node
			// which has shorter distance
			long[][] neighbors = this.graphDataLight.getClosestKNeighbors((short) currentNode);
			boolean updatedTour = false;
			for (int i = 0; i < neighbors.length; i++) {
				if (neighbors[i][2] < distance) {
					// found a shorter distance! this edge would be a better
					// choice
					int betterNeighbour = (int) neighbors[i][1];
					int betterNeighboursNeighbour = getOrderedNeighbor(betterNeighbour);
					// Check if the other edge needed also is a better choice
					long otherEdgeCurrentDistance = getDistanceIfExist(betterNeighbour, betterNeighboursNeighbour);
					long otherEdgepossiblyDistance = getDistanceIfExist(currentNeighbour, betterNeighboursNeighbour);
					if (otherEdgepossiblyDistance < otherEdgeCurrentDistance) {
						// a perfect match, both new edges are shorter than the originals, lets exchange!
						int[] newTour = createNewTour(currentNode, currentNeighbour, betterNeighbour,
								betterNeighboursNeighbour);
						fifoQueue.add(currentNode); // put the node last in queue
						removeFromShelf(currentNeighbour, betterNeighbour, betterNeighboursNeighbour);
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
			finalTour.add(this.currentTour[i]);
		}
		Tour result = new Tour(finalTour);
		return result;
	}

	/**
	 * Removes the three nodes from the shelf if present and puts them back into the queue.
	 */
	private void removeFromShelf(int currentNeighbour, int betterNeighbour, int betterNeighboursNeighbour) {
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

	private int[] createNewTour(int currentNode, int currentNeighbour, int betterNeighbour,
			int betterNeighboursNeighbour) {
		int[] newTour = new int[numberOfNodes];
		int node = betterNeighboursNeighbour;
		int index = 0;
		// Get the first part of the tour
		while (node != currentNode) {
			newTour[index] = node;
			node = getOrderedNeighbor(node);
		}
		// Get the second part of the tour
		node = currentNeighbour;
		while (node != betterNeighbour) {
			newTour[index] = node;
			node = getOrderedNeighbor(node);
		}
		if ((index + 1) != numberOfNodes) {
			throw new IllegalStateException("New optimized tour is lacking nodes");
		}
		return newTour;
	}

	private long getDistanceIfExist(int mainNode, int possibleNeighbor) {
		long[][] edges = this.graphDataLight.getClosestKNeighbors((short) mainNode);
		for (int i = 0; i < edges.length; i++) {
			if (edges[i][1] == possibleNeighbor) {
				return edges[i][2];
			}
		}
		return Long.MAX_VALUE;
	}

	private int getOrderedNeighbor(int currentNode) {
		int indexOfCurrentNode = Arrays.binarySearch(currentTour, currentNode);
		int nextIndex = (indexOfCurrentNode + 1) % currentTour.length;
		return currentTour[nextIndex];
	}

	private int[] getArray(Tour tour) {
		int[] intTour = new int[tour.getTourList().size()];
		int index = 0;
		for (Integer integer : tour.getTourList()) {
			intTour[index] = (int) integer;
			index++;

		}
		return intTour;
	}

}
