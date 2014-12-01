import java.util.List;

public class TSPsolver {

    public TSPsolver() {
    }

    public List<Integer> solve(GraphData graphData, TourConstructStrategy tourConstructStrategy) {
	Tour tour = tourConstructStrategy.constructTour(graphData);
	return tour.getTourList();
    }

}
