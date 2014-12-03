import java.util.ArrayList;
import java.util.List;

public class TSPsolver {

	public TSPsolver() {
	}

	public List<Integer> solve(GraphData graphData, TourConstructStrategy tourConstructStrategy) {
		Tour tour = tourConstructStrategy.constructTour(graphData);
		return tour.getTourList();
	}

	public List<Integer> solveLight(double[][] coordinates, TourConstructStrategy constructStrategy, TwoOpt opt) {

		// Construct an initial tour
		List<Integer> randomTour = new ArrayList<>();
		for (int i = 0; i < coordinates.length; i++) {
			randomTour.add(i);

		}
		Tour initialTour = new Tour(randomTour);

		// try to optimize it
		EdgeDistanceComparator edgeComparator = new EdgeDistanceComparator();
		Tour optimized = opt.optimizeTour(initialTour, new GraphDataLight(coordinates, 20, edgeComparator));
		return optimized.getTourList();
	}

}
