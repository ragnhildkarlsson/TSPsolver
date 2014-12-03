import java.util.List;

public class TSPsolver {

	public TSPsolver() {
	}

	public List<Integer> solve(GraphData graphData, TourConstructStrategy tourConstructStrategy) {
		Tour tour = tourConstructStrategy.constructTour(graphData);
		return tour.getTourList();
	}

	public List<Integer> solveLight(double[][] coordinates, NearestNeighbour nn, TwoOpt opt) {
		EdgeDistanceComparator edgeComparator = new EdgeDistanceComparator();
		GraphDataLight data = new GraphDataLight(coordinates, 20, edgeComparator);
		// Construct an initial tour

		// List<Integer> randomTour = new ArrayList<>();
		// for (int i = 0; i < coordinates.length; i++) {
		// randomTour.add(i);
		// }
		Tour tour = nn.constructLightTour(data);

		// try to optimize it

		tour = opt.optimizeTour(tour, data);
		return tour.getTourList();
	}

}
