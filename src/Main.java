import java.util.List;
import java.util.Random;

public class Main {

	public static void main(String[] args) {

		Kattio io = new Kattio(System.in, System.out);
		IndataReader ir = new IndataReader();

		TSPsolver solver = new TSPsolver();
		Random rand = new Random();
		EdgeDistanceComparator edgeDistanceComparator = new EdgeDistanceComparator();
		TourConstructStrategy constructStrategy = new NearestNeighbour(rand);
		double[][] coordinates = ir.read(io);
		TwoOpt opt = new TwoOpt();
		List<Integer> res = solver.solveLight(coordinates, constructStrategy, opt);
		for (Integer node : res) {
			io.println(node);
		}
		io.flush();
	}

}
