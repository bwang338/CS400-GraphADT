// --== CS400 File Header Information ==--
// Name: Ian Koh
// Email: iskoh@wisc.edu
// Team: BA
// TA: Brianna Cochran
// Lecturer: Florian Heimerl
// Notes to Grader: <optional extra notes>

import java.util.List;

public class GraphAddon {
	
	/**
	 * 
	 * @param graph Map graph being used
	 * @param start Starting address
	 * @param end Destination address
	 * @return String representation of the shortest path from the start to end addresses, 
	 * 		separated by lines. Distances for each leg and the total trip are also included. 
	 */
	public static String findShortestPath(CS400Graph<String> graph, String start, String end) {
		
		List<String> path = graph.shortestPath(start, end);
		
		String mapPath = path.get(0);
		String prev = path.get(0);
		path.remove(0);
		for (String curr : path) {
			mapPath += " to " + curr + " (" + graph.getWeight(prev, curr) + " miles) \n";
			prev = curr;
		}
		
		mapPath += "Total distance: (" + graph.getPathCost(start, end) + " miles)";
		
		return mapPath;
	}
	
	public static void main(String[] args) {
		CS400Graph<String> nGraph = new CS400Graph<>();
        nGraph = FileReader.importData("nAddresses.txt");
        System.out.println(findShortestPath(nGraph, "1115 Drury Lane", "1212 Jackson Pkwy"));
	}
}