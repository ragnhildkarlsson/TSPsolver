

public class Edge {
    private int nodeA;
    private int nodeB;
    private long distance;

    public Edge(int nodeA, int nodeB, long distance) {
	this.nodeA = nodeA;
	this.nodeB = nodeB;
	this.distance = distance;
    }

    public int getNodeA() {
	return nodeA;
    }

    public int getNodeB() {
	return nodeB;
    }

    public long getDistance() {
	return distance;
    }
}
