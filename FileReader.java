// --== CS400 File Header Information ==--
// Name: Neel Murthy
// Email: nmurthy@wisc.edu
// Team: BA
// TA: Bri
// Lecturer: Dahl
// Notes to Grader: None
import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

public class FileReader {

    /** 
     * Takes as input the name of a file to read data from, the file is assumed
     * to be a txt file containing strings representing addresses and distances from each address
     * @param filename the name of the file to be read
     * @return A graph translated from the text file contents
     */
    protected static CS400Graph<String> importData(String filename) {
        CS400Graph<String> map = new CS400Graph<String>();
        File inFile = new File(filename);
        Scanner sc;
        try {
            sc = new Scanner(inFile);
        } catch (Exception E) {
            return null;
        }
        while(sc.hasNextLine()) {
            String addressStart = sc.nextLine();    //start address
            String addressEnd=sc.nextLine();		//end address
            map.insertVertex(addressStart);
            map.insertVertex(addressEnd);
            String distance=sc.nextLine();				//distance between addresses
            String isTwoWay=sc.nextLine();     //checks whether it is a two way road
            if(!isTwoWay.trim().equals("True"))
            {
            	map.insertEdge(addressStart, addressEnd, Integer.parseInt(distance.trim()));
            }
            else {
            	map.insertEdge(addressStart, addressEnd, Integer.parseInt(distance.trim()));
            	map.insertEdge(addressEnd, addressStart, Integer.parseInt(distance.trim()));
            }
        }
        sc.close();
        return map;
    }
}