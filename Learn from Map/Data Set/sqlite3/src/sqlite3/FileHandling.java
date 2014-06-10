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
    final private int POPULATION = 14;
    final private int ELEVATION = 16;
    final private int COUNTRYCODE = 8;
    final private int STATE = 10;
    final private int CODE = 7;
    final private int LAT = 4;
    final private int LNG = 5;
    private FileReader fr;
    private BufferedReader data;
    private String[] fields;
    private int count;

    public FileHandling(String fileName, String[] fields) {
        count = 0;
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
                    String state = getAdminLevel(field[COUNTRYCODE] + "." + field[STATE], true);
                    String name = field[NAME].replaceAll("'", "''");
                    String[] dataArray = getCountryDetails(field[COUNTRYCODE], true);
                    if(dataArray == null)
                    {
                        dataArray = new String[3];
                    }
                    //System.out.println(dataArray[1]);
                    //System.out.println(field[NAME]);
                    query = "INSERT INTO main VALUES ("
                            + field[ID] + ", '" + name + "', " + field[LAT]
                            + ", " + field[LNG] + ", '" + field[CODE] + "', '"
                            + field[COUNTRYCODE] + "', '" + dataArray[2] + "', '" + dataArray[1] +"', '" + state + "', '" + dataArray[0] + "', " + field[POPULATION] + ", "
                            + field[ELEVATION] + ");";
                    queries.add(query);
                }
            }
        } catch (IOException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Quesries Formed");
        return queries;
    }

    public String getAdminLevel(String code, boolean isState) {
        String codeFile = "code.txt";
        String codeLine;
        //System.out.println(code);
        try {
            FileReader fr1 = new FileReader(codeFile);
            BufferedReader codes = new BufferedReader(fr1);

            while ((codeLine = codes.readLine()) != null && codeLine.length() != 0) {
                String[] codeArray = codeLine.split("\t");
                if (codeArray[0].equals(code)) {
                    //System.out.println("Found, Count: " + count);
                    count++;
                    String state  = codeArray[1].replaceAll("'", "''");;
                    return state;
                }

            }
            fr1.close();
            codes.close();
        } catch (IOException e) {

        }
        return "";
    }

    public String[] getCountryDetails(String code, boolean isState) {
        String codeFile = "countryInfo.txt";
        String codeLine;
        //System.out.println(code);
        try {
            FileReader fr1 = new FileReader(codeFile);
            BufferedReader codes = new BufferedReader(fr1);

            while ((codeLine = codes.readLine()) != null && codeLine.length() != 0) {

                String[] codeArray = codeLine.split("\t");
                if (codeArray[0].equals(code)) {
                    String capital = codeArray[5];
                    String country = codeArray[4];
                    //int area = Integer.parseInt(codeArray[6], 1);
                    String contCode = codeArray[8];
                    String continent;
                    if (contCode.equals("AF")) {
                        continent = "Africa";
                    } else if (contCode.equals("AS")) {
                        continent = "Asia";
                    } else if (contCode.equals("EU")) {
                        continent = "Europe";
                    } else if (contCode.equals("NA")) {
                        continent = "North America";
                    } else if (contCode.equals("OC")) {
                        continent = "Ocenia";
                    } else if (contCode.equals("SA")) {
                        continent = "South America";
                    } else {
                        continent = "Anartica";
                    }
                    String[] dataArray = new String[3];
                    dataArray[0] = continent.replaceAll("'", "''");
                    dataArray[1] = country.replaceAll("'", "''");
                    dataArray[2] = capital.replaceAll("'", "''");
                    fr1.close();
                    codes.close();
                    return dataArray;
                }
                
            }
            fr1.close();
            codes.close();
        } catch (IOException e) {

        }
        System.out.println("No data found for: " + code);
        return null;
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
