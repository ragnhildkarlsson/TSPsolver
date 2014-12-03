import java.util.List;
import java.util.Random;

public class Main {

    public static void main(String[] args) {

	long programStartTime = System.currentTimeMillis();
	Kattio io = new Kattio(System.in, System.out);
	IndataReader ir = new IndataReader();
	GraphData graphData = ir.read(io);
	TSPsolver solver = new TSPsolver();
	Random rand = new Random();
	EdgeDistanceComparator edgeDistanceComparator = new EdgeDistanceComparator();
	long timeOut = 100;
	TourConstructStrategy constructStrategy;
	if (graphData.numberOfNodes() < 750) {
	    constructStrategy = new ClarkeWright(rand, edgeDistanceComparator);
	} else {
	    constructStrategy = new NearestNeighbour(rand);
	}
	List<Integer> res = solver.solve(graphData, constructStrategy);
	for (Integer node : res) {
	    io.println(node);
	}
	io.flush();
    }
}
