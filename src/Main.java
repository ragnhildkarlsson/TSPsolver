import java.util.List;
import java.util.Random;

public class Main {

    public static void main(String[] args) {

	Kattio io = new Kattio(System.in, System.out);
	IndataReader ir = new IndataReader();
	GraphData graphData = ir.read(io);
	TSPsolver solver = new TSPsolver();
	Random rand = new Random();
	EdgeDistanceComparator edgeDistanceComparator = new EdgeDistanceComparator();
	TourConstructStrategy constructStrategy = new ClarkeWright(rand, edgeDistanceComparator);
	List<Integer> res = solver.solve(graphData, constructStrategy);
	for (Integer node : res) {
	    io.println(node);
	}
	io.flush();
    }

}
