package TSPsolver;

public class Edge {
    private int nodeA;
    private int nodeB;
    private int saving;

    public Edge(int nodeA, int nodeB, int saving) {
	this.nodeA = nodeA;
	this.nodeB = nodeB;
	this.saving = saving;
    }

    public int getNodeA() {
	return nodeA;
    }

    public int getNodeB() {
	return nodeB;
    }

    public int getSaving() {
	return saving;
    }
}
