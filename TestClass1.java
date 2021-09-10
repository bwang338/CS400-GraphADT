// --== CS400 File Header Information ==--
// Name: <Brian Wang>
// Email: <bwang338@wisc.edu>
// Team: <BA>
// TA: <Brianna Cochran>
// Lecturer: <Gary Dahl>
// Notes to Grader: <optional extra notes>

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestClass1 {
   
	//Tests Neel's and Elaina's Data Wrangler Methods.
	@Test
	void testDataHandler() {   
        CS400Graph<String> nGraph = new CS400Graph<>();
        nGraph = FileReader.importData("nAddresses.txt");
        assertTrue(!nGraph.isEmpty());
        assertTrue(nGraph.getVertexCount()==5);
        assertTrue(nGraph.getWeight("1115 Drury Lane", "1313 Johnson Street")==30);
        assertTrue(nGraph.getWeight("1313 Johnson Street", "1115 Drury Lane")==30);
        CS400Graph<String> eGraph = new CS400Graph<>();
        eGraph= DataHandler.importData("Addresses.txt");
        assertTrue(eGraph.isEmpty()==false);
        assertTrue(eGraph.getVertexCount()==6);
        assertTrue(eGraph.getWeight("550 Park St", "505 University Ave")==18);
        assertTrue(eGraph.getWeight("505 University Ave", "550 Park St")==18);
	}
	
	//Tests Ian's BackEnd
	@Test
	void testBackEnd() {
		CS400Graph<String> nGraph = new CS400Graph<>();
        nGraph = FileReader.importData("nAddresses.txt");
        assertTrue(GraphAddon.findShortestPath(nGraph, "1115 Drury Lane", "1212 Jackson Pkwy").contains("1115 Drury Lane to 1313 Johnson Street (30 miles)"));
        assertTrue(GraphAddon.findShortestPath(nGraph, "1115 Drury Lane", "1212 Jackson Pkwy").contains("Total distance: (90 miles)"));
	}

}
