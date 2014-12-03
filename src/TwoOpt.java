import java.util.Queue;
import java.util.Set;

public class TwoOpt {

	private Set shelf;
	private Queue fifoQueue;
	private int[] currentTour;

	public Tour optimizeTour(Tour tour, GraphDataLight graphData) {
		// We don't try to optimize tours below size 4

		this.currentTour = getArray(tour);

		if (tour.getTourList().size() < 4) {
			return tour;
		}

		return null;
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
