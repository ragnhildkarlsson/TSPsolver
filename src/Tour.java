

import java.util.ArrayList;
import java.util.List;

public class Tour {

    List<Integer> tour;

    public Tour(List<Integer> tour) {
	this.tour = tour;
    }

    public List<Integer> getTourList() {
	List<Integer> copy = new ArrayList<Integer>();
	copy.addAll(tour);
	return copy;
    }
}
