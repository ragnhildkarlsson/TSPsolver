package TSPsolver;

import java.util.List;

public class TSPsolver {

    IndataReader ir;

    public TSPsolver(IndataReader ir) {
	this.ir = ir;
    }

    public List<Integer> solve(TourConstructStrategy tourConstructStrategy) {
	GraphData graphData = ir.read();
	Tour tour = tourConstructStrategy.constructTour(graphData);
	return tour.getTourList();
    }

}
