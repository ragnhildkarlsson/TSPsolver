import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class IndataReader {

	public IndataReader() {
	}

	public double[][] read() {
		try {
			BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
			String s = bufferRead.readLine();
			int numberOfNodes = Integer.parseInt(s);

			double[][] nodes = new double[numberOfNodes][2];
			String doubles;
			// for each node read the coordinates
			for (int i = 0; i < numberOfNodes; i++) {
				doubles = bufferRead.readLine();
				String[] doublesSplit = doubles.split(" ");
				double x = Double.parseDouble(doublesSplit[0]);
				double y = Double.parseDouble(doublesSplit[1]);
				nodes[i][0] = x;
				nodes[i][1] = y;

			}
			return nodes;

		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;

	}
}
