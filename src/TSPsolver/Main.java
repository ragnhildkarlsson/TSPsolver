package TSPsolver;

import java.util.List;
import java.util.Random;

public class Main {

    public static void main(String[] args) {

	Kattio io = new Kattio(System.in, System.out);
	IndataReader ir = new IndataReader(io);
	TSPsolver solver = new TSPsolver(ir);
	Random rand = new Random();
	TourConstructStrategy constructStrategy = new ClarkeWright(rand);
	List<Integer> res = solver.solve(constructStrategy);
	for (Integer node : res) {
	    io.println(node);
	}
	io.flush();
    }

}
