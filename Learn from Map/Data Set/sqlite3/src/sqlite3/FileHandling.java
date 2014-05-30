package sqlite3;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Abhishek
 */
public class FileHandling {

    final private int ID = 0;
    final private int NAME = 1;
    final private int LEVEL0 = 10;
    final private int LEVEL1 = 11;
    final private int POPULATION = 14;
    final private int ELEVATION = 16;
    final private int COUNTRYCODE = 8;
    final private int CODE = 7;
    final private int LAT = 4;
    final private int LNG = 5;
    private FileReader fr;
    private BufferedReader data;
    private String[] fields;

    public FileHandling(String fileName, String[] fields) {
        try {
            this.fields = fields;
            this.fr = new FileReader(fileName);
            this.data = new BufferedReader(this.fr);
        } catch (FileNotFoundException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }

    }

    /*    public ArrayList dataToLineArray() {
     String tempLine;
     ArrayList<String> line = new ArrayList<>();
     try {
     while ((tempLine = data.readLine()) != null && tempLine.length() != 0) {
     line.add(tempLine);
     }
     } catch (IOException e) {
     System.err.println(e.getClass().getName() + ": " + e.getMessage());
     System.exit(0);
     }
     return line;
     }*/
    public ArrayList generateDbQuery() {
        ArrayList<String> queries = new ArrayList<>();
        String query;
        String tempLine;
        String[] field;
        //ArrayList line = dataToLineArray();
        try {
            while ((tempLine = data.readLine()) != null && tempLine.length() != 0) {
                //System.out.println(tempLine);
                field = tempLine.split("\t");
                if (matchField(field[CODE])) {
                    
                    String name = field[NAME].replaceAll("'", " ");
                    System.out.println(field[NAME]);
                    query = "INSERT INTO main VALUES ("
                            + field[ID] + ", '" + name + "', " + field[LAT]
                            + ", " + field[LNG] + ", '" + field[CODE] + "', '"
                            + field[COUNTRYCODE] + "', " + field[POPULATION] + ", "
                            + field[ELEVATION] + ");";
                    queries.add(query);
                }
            }
        } catch (IOException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return queries;
    }

    public boolean matchField(String field) {
        for (String field1 : fields) {
            if (field1.equals(field)) {
                return true;
            }
        }
        return false;
    }

}
