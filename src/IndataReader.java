public class IndataReader {

	public IndataReader() {
	}

	public double[][] read(Kattio kattio) {

		// Read in the number of nodes
		int numberOfNodes = kattio.getInt();
		double[][] nodes = new double[numberOfNodes][2];
		// for each node read the coordinates
		for (int i = 0; i < numberOfNodes; i++) {
			double x = kattio.getDouble();
			double y = kattio.getDouble();
			nodes[i][0] = x;
			nodes[i][1] = y;

		}

		return nodes;

	}
}
