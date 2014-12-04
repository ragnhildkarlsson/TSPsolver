import java.util.List;
import java.util.Random;

public class Main {

	public static void main(String[] args) {

		IndataReader ir = new IndataReader();

		TSPsolver solver = new TSPsolver();
		Random rand = new Random();
		// EdgeDistanceComparator edgeDistanceComparator = new EdgeDistanceComparator();
		NearestNeighbour constructStrategy = new NearestNeighbour(rand);
		double[][] coordinates = ir.read();
		TwoOpt opt = new TwoOpt();
		List<Integer> res = solver.solveLight(coordinates, constructStrategy, opt);
		for (Integer node : res) {
			System.out.println(node);
		}

	}

}
