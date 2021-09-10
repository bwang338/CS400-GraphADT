// --== CS400 File Header Information ==--
// Name: Ryan Szymanski
// Email: rpszymanski@wisc.edu
// Team: BA
// Role: Front End Developer 1
// TA: Brianna Cochran
// Lecturer: Florian
// Notes to Grader: None
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class GraphDriver {
  public static void main(String[] args) {
    System.out.println("Welcome to the GPS Driver!");
    CS400Graph<String> graph = new CS400Graph<>();
    Scanner sc = new Scanner(System.in);
    GraphAddon graphAddon = new GraphAddon();
    FileReader fileReader = new FileReader();
    char option = 0;
    while (option != 'q') {
      option = userGuide(sc);

      switch (option) {
        case 'l':
          CS400Graph<String> loadedGPS = loadGPS(sc);

          if (loadedGPS == null) {
            System.out.println("Unable to load file.");
          } else {
            graph = loadedGPS;

          }
          break;
        case 'a':
          addStudent(sc, graph);
          break;
        case 'd':
          removeAddress(sc, graph);
          break;
        case 'r':
          searchAddress(sc, graph);
          break;
        case 'e':
          deleteRoad(sc, graph);
          break;
        case 'c':
          insertRoad(sc, graph);
          break;
        case 's':
          shortestGPSPath(sc, graph, graphAddon);
          break;
        case 'h':
          helpScreen();
          break;
        case 'q':
          System.out.println("Exiting application.");
          break;
        default:
          System.out.println("Invaild Input! Please try again or type [H] for help.");
          break;
      }

      System.out.println("\n****Press any key to continue****");
      sc.nextLine();
    }


  }

  /**
   * Method to display the options menu of the GPS
   * 
   * @param sc
   * @return the char input
   */
  private static char userGuide(Scanner sc) {
    System.out.println("Please select what you want to do based on the Characters Below"
        + "\n\t L- Load in a new GPS." + "\n\t A- Updates the GPS and adds a new location to it. "
        + "\n\t D- Updates the GPS and deletes a location."
        + "\n\t R- Search for an address in the GPS."
        + "\n\t E- Updates the GPS and gets rid of a certain road/edge."
        + "\n\t C- Updates the GPS and adds a new road/edge."
        + "\n\t S- Finds the shortest path between two locations." + "\n\t H- Help menu."
        + "\n\t Q- Quit the GPS.");

    System.out.print("\nInput: ");
    char inputChar = sc.nextLine().toLowerCase().charAt(0);
    System.out.print("\n");

    return inputChar;
  }

  /**
   * Method that helps load in data from a file to make a new graph
   * 
   * @param sc
   * @return the GPS being loaded in
   */
  private static CS400Graph<String> loadGPS(Scanner sc) {
    System.out.print("File name: ");
    String filename = sc.nextLine().trim();
    System.out.print("\n");

    CS400Graph<String> loadedGPS = null;
    try {
      loadedGPS = FileReader.importData(filename);
      System.out.println("Successfully added GPS data.");
    } catch (Exception e) {
      System.out.println("Error: \n" + e.getMessage());
    }

    return loadedGPS;
  }

  /**
   * Method that adds a location to the GPS
   * 
   * @param sc
   * @param graph
   */

  private static void addStudent(Scanner sc, CS400Graph<String> graph) {
    System.out.println("New Address to be added: ");
    String name = sc.nextLine().trim();
    if (graph.containsVertex(name)) {
      System.out.println("This address is already in the GPS!");
      return;
    }
    try {
      graph.insertVertex(name);
      System.out.println("Successfully added the new address!");
      return;
    } catch (NullPointerException e) {
      System.out.println("Cannot add a null address! Please enter a valid address!");
      return;
    }

  }

  /**
   * Removes an address from the GPS
   * 
   * @param sc
   * @param graph
   */
  private static void removeAddress(Scanner sc, CS400Graph<String> graph) {
    System.out.println("Address to be removed: ");
    String removedAddress = sc.nextLine().trim();
    if (!graph.containsVertex(removedAddress)) {
      System.out.println("The Address is not in the GPS! Please enter a valid address!");
      return;
    }
    try {
      graph.removeVertex(removedAddress);
      System.out.println("Successfully removed the address!");
      return;
    } catch (NullPointerException e) {
      System.out.println("Cannot remove a null reference!");
      return;
    }
  }

  /**
   * Method that Inserts a new road into the GPS connecting two addresses
   * 
   * @param sc
   * @param graph
   */
  private static void insertRoad(Scanner sc, CS400Graph<String> graph) {
    System.out.println("Source Address ");
    String startAddress = sc.nextLine().trim();
    System.out.println("Target Address ");
    String endAddress = sc.nextLine().trim();
    if (!graph.containsVertex(startAddress) || !graph.containsVertex(endAddress)) {
      System.out.println("One or more of these addresses are not valid! Please try again!");
      return;
    }
    System.out.println("Length of road: ");
    int distance;
    try {
      distance = Integer.parseInt(sc.nextLine().trim());
    } catch (InputMismatchException e) {
      System.out.println("Invalid input. Please try again and provide a valid integer");
      return;
    }
    if (distance < 0) {
      System.out.println("Error: Cannot enter a road with a negative distance!");
    }
    graph.insertEdge(startAddress, endAddress, distance);
    System.out.println("New Road Added!");
    return;
  }

  /**
   * Method to delete a road between two addresses
   * 
   * @param sc
   * @param graph
   */
  private static void deleteRoad(Scanner sc, CS400Graph<String> graph) {
    System.out.println("Source Address ");
    String startAddress = sc.nextLine().trim();
    System.out.println("Target Address ");
    String endAddress = sc.nextLine().trim();
    if (!graph.containsVertex(startAddress) || !graph.containsVertex(endAddress)) {
      System.out.println("One or more of these addresses are not valid! Please try again!");
      return;
    }
    if (graph.removeEdge(startAddress, endAddress)) {
      System.out.println("Road successfully removed.");
      return;
    } else {
      System.out.println("Error: There is no road connecting the two address.");
      return;
    }
  }

  /**
   * Finds and prints out the shortest path between two locations and also tells the user the total
   * distance as well.
   * 
   * @param sc
   * @param graph
   * @param graphAddon
   */
  private static void shortestGPSPath(Scanner sc, CS400Graph<String> graph, GraphAddon graphAddon) {
    if (graph.isEmpty()) {
      System.out.println("The GPS is empty! Please load in valid GPS data!");
    }
    System.out.println("Start Address ");
    String startAddress = sc.nextLine().trim();
    System.out.println("End Address ");
    String endAddress = sc.nextLine().trim();
    if (!graph.containsVertex(startAddress) || !graph.containsVertex(endAddress)) {
      System.out.println("One or more of these addresses are not valid! Please try again!");
      return;
    }
    try {
      String shortestPath = graphAddon.findShortestPath(graph, startAddress, endAddress);
      System.out.println(shortestPath);
      return;
    } catch (NoSuchElementException e) {
      System.out.println("There are no possible routes available!");
      return;
    }

  }

  /**
   * Method that searches for a specified address
   * 
   * @param sc
   * @param graph
   */
  private static void searchAddress(Scanner sc, CS400Graph<String> graph) {
    System.out.println("Address: ");
    String searchedAddress = sc.nextLine().trim();
    if (graph.containsVertex(searchedAddress)) {
      System.out.println("GPS does contain " + searchedAddress + "!");
    } else {
      System.out.println("The Address " + searchedAddress + " is not in the GPS.");
    }
  }

  /**
   * Method to print help screen for users.
   */
  private static void helpScreen() {
    System.out.println(

        "\n\t[L] Loads in a new GPS by reading a .txt file which contains all locations and roads in the map."
            + "\n"
            + "\n\t[A] Adds a brand new address to the GPS to account for a new building constructed. "
            + "\n\tThis function just takes a new String as the address and inserts it into the GPS as a new vertex. "
            + "\n"
            + "\n\t[D] Updates the GPS by deleting an already existing address from the GPS and also gets rid of all edges/roads"
            + "\n\tconnected to this address. Checks whether or not the address entered is in the GPS, and if so, correctly removes it. "
            + "\n"
            + "\n\t[R] Searches for an address in the GPS by the user typing in a String as the address being serached"
            + "\n\t and checks if it is in the GPS." + "\n"
            + "\n\t[E] Updates the GPS and gets rid of a certain road/edge by the user providing the source vertex and end vertex. "
            + "\n" + "\n\t[C] Updates the GPS and adds a new road/edge "
            + "\n\tby the user providing the source address, the end address, and the distance of the road."
            + "\n"
            + "\n\t[S] Finds the shortest path between two locations that the user specifies.");

  }
}