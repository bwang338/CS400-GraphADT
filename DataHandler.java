// --== CS400 File Header Information ==--
// Name: Elaina Timmerman
// Email: eltimmerman@wisc.edu
// Team: BA
// TA: Brianna Cochran
// Lecturer: Gary Dahl
// Notes to Grader: <optional extra notes>

import java.io.File;
import java.util.Scanner;

public class DataHandler {

  /**
   * takes a file name from user input. The file should have a format of: String starting address,
   * String ending Address, int distance between the two addresses, and a boolean for it it is a two
   * way street. Puts the starting address as a vertex, ending address as an edge with the distance
   * between.
   * 
   * @param fileName
   * @return a map with all the address in file as vertexes
   */
  protected static CS400Graph importData(String fileName) {
    CS400Graph map = new CS400Graph();
    File file = new File(fileName);
    Scanner scnr;
    try {
      scnr = new Scanner(file);
    } catch (Exception e) {
      System.out.println("Cannot read the file");
      return null;
    }
    while (scnr.hasNextLine()) {
      String line = scnr.nextLine();
      String[] address = line.split(",");
      if (address.length == 4) {
        String startingAddress = address[0].trim();
        String endingAddress = address[1].trim();
        int distance = Integer.parseInt(address[2].trim());
        Boolean isTwoWay = Boolean.parseBoolean(address[3].trim());
        map.insertVertex(startingAddress);
        map.insertVertex(endingAddress);
        if (isTwoWay) {
          map.insertEdge(startingAddress, endingAddress, distance);
          map.insertEdge(endingAddress, startingAddress, distance);
        } else {
          map.insertEdge(startingAddress, endingAddress, distance);
        }
      }
    }
    scnr.close();
    return map;
  }
  
  public static void main(String[] args) {
	  CS400Graph<String> nGraph = new CS400Graph<>();
      nGraph = importData("Addresses.txt");
      System.out.println(nGraph.getWeight("550 Park St", "505 University Ave"));
	}
}