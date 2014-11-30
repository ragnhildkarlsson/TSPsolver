package TSPsolver;

import java.util.List;
import java.util.stream.Collectors;

public class Tour {

    List<Integer> tour;

    public Tour(List<Integer> tour) {
	this.tour = tour;
    }

    public List<Integer> getTourList() {
	tour.stream().map(node -> node).collect(Collectors.toList());
	return tour;
    }
}
