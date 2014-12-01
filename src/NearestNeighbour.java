import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;


public class NearestNeighbour implements TourConstructStrategy {
	
	private Random random;
	
	public NearestNeighbour(Random random) {
		this.random = random;
	}

	@Override
	public Tour constructTour(GraphData graphData) {
		
		Set<Integer> nodes = new HashSet<Integer>(graphData.getNodes());
		// Pick a random first node
		int firstNode = random.nextInt(nodes.size());
		nodes.remove(firstNode);
		
		List<Integer> partialTour = new ArrayList<>();
		partialTour.add(firstNode);
		int currentNode = firstNode;
		
		while(!nodes.isEmpty()){
			long bestDistance = Long.MAX_VALUE;
			int bestNode = -1;
			for(Integer node: nodes){
				if(!(currentNode == node)){ // for all nodes except it self check distance
					long distance = graphData.getDistance(currentNode, node);
					if( distance < bestDistance){
						bestDistance = distance;
						bestNode = node;
					}
					
				}
			}
			//Add the best node to tour and remove it from nodes
			if(bestNode == -1){
				throw new IllegalStateException("trying to construct illegal tour");
			}
			nodes.remove(bestNode);
			partialTour.add(bestNode);
			currentNode = bestNode;
		}
		return new Tour(partialTour);
	}

}
