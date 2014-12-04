import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;

import org.junit.Test;

public class EvaluatorTest {

    @Test
    public void evalQuality() throws IOException {
	// Save best solutions for each test case
	ArrayList<Integer> bestSolutions = new ArrayList<Integer>();
	bestSolutions.add(18660188);
	bestSolutions.add(224094);
	bestSolutions.add(239297);
	bestSolutions.add(336556);

	for (int i = 0; i < 4; i++) {
	    // Read In test case
	    System.out.println("Working Directory = " + System.getProperty("user.dir"));
	    String filename = "test" + i + ".txt";
	    String rawdata = readTestDataFromFile(filename);
	    InputStream indataStream = new ByteArrayInputStream(rawdata.getBytes(StandardCharsets.UTF_8));
	    TestDataReader reader = new TestDataReader();
	    Kattio io = new Kattio(indataStream);
	    double[][] nodes = reader.read(io);
	    // Prepare useful variables for test
	    EdgeDistanceComparator edgeComparator = new EdgeDistanceComparator();
	    long startTimeTotal = 0;
	    long elapsedTimeTotal;
	    long sumRuntime = 0;
	    double avverageRunTime;
	    long sumResult = 0;
	    double avverageResult;
	    int nTests = 10;
	    ArrayList<Long> runtimes = new ArrayList<Long>();
	    ArrayList<Integer> results = new ArrayList<>();
	    TourEvaluator tourEvaluator = new TourEvaluator(nodes);

	    // Name of Construct method
	    String constructMethod = " NN ";

	    // Name of local search method
	    String localSearchMethod = " none ";

	    // Start Test
	    for (int j = 0; j < nTests; j++) {
		// start time;
		startTimeTotal = System.currentTimeMillis();

		// Do tour calculation
		GraphData graphData = new GraphData(nodes);
		TourConstructStrategy constructStrategy = new NearestNeighbour();
		Tour tour = constructStrategy.constructTour(graphData);

		// stop time
		elapsedTimeTotal = System.currentTimeMillis() - startTimeTotal;
		// save results;
		runtimes.add(elapsedTimeTotal);
		int res = tourEvaluator.getDistOfTour(tour);
		results.add(res);
	    }

	    // Eval Result:
	    System.out.println("Evaluation of construct method " + constructMethod
		    + "combined with local search method " + localSearchMethod);
	    System.out.println("On test case " + filename);
	    System.out.println("That have the optimal solution " + bestSolutions.get(i));
	    for (int j = 1; j < nTests; j++) {
		// Skip the first result not representative
		sumResult += results.get(j);
		sumRuntime += runtimes.get(j);
	    }
	    avverageResult = (double) sumResult / (double) (nTests - 1);
	    avverageRunTime = (double) sumRuntime / (double) (nTests - 1);

	    System.out.println("The average runtime was " + avverageRunTime);
	    System.out.println("The average result was " + avverageResult);
	    Collections.sort(results);
	    Collections.sort(runtimes);
	    int median = nTests / 2;
	    System.out.println("The median runtime was " + runtimes.get(median));
	    System.out.println("The median result was " + results.get(median));

	    double procentSoulution = (double) (results.get(median) - bestSolutions.get(i))
		    / (double) bestSolutions.get(i);
	    System.out.println("(Medial solution-BestSolution)/BestSolution = " + procentSoulution);

	    System.out.println(" ");
	}

    }

    private String readTestDataFromFile(String filename) throws IOException {
	BufferedReader br = new BufferedReader(new FileReader(filename));
	String result;
	try {
	    StringBuilder sb = new StringBuilder();
	    String line = br.readLine();

	    while (line != null) {
		sb.append(line);
		sb.append(System.lineSeparator());
		line = br.readLine();
	    }
	    result = sb.toString();
	} finally {
	    br.close();
	}
	return result;
    }

}