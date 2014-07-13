package sqlite3;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Abhishek
 */
public class Sqlite3 {

    final private static int ID = 0;
    final private static int NAME = 1;
    final private static int POPULATION = 14;
    final private static int ELEVATION = 16;
    final private static int COUNTRYCODE = 8;
    final private static int STATE = 10;
    final private static int CODE = 7;
    final private static int LAT = 4;
    final private static int LNG = 5;

    public static void main(String args[]) {
        Database db = new Database("data_test12.db");
        String query = "CREATE TABLE IF NOT EXISTS country (_id INTEGER PRIMARY KEY, name TEXT, capital TEXT)";
        db.executeQuery1(query);
        for (String sub : genCountryQueries()) {
            db.executeQuery1(sub);
        }
        System.out.println("Country done");
        query = "CREATE TABLE IF NOT EXISTS state (_id INTEGER PRIMARY KEY, name TEXT)";
        db.executeQuery1(query);
        for (String sub : genStateQueries()) {
            db.executeQuery1(sub);
        }
        System.out.println("State done");
        String[] fields = {"PCLI", "PEN", "PLN", "PPLC", "ADM1", "VLC", "OCN", "SEA", "MTS", "PLTU",
            "FLLS", "GLCR", "DSRT"};
        for (String field : fields) {
            query = "CREATE TABLE IF NOT EXISTS " + field.toLowerCase()
                    + " (_id INTEGER PRIMARY KEY, NAME TEXT, "
                    + "lat REAL, lng REAL, continent INTEGER, "
                    + "country INTEGER, state INTEGER, population INTEGER, elevation INTEGER)";
            db.executeQuery1(query);
            System.out.println("Table created: " + field);
            for (String sub : getCat(field)) {
                db.executeQuery1(sub);
            }
            System.out.println("Insertion done: " + field);
        }
        db.closeDb();
    }

    public static ArrayList<String> getCat(String category) {

        ArrayList<String> queries = new ArrayList<>();
        try {
            FileReader fr = new FileReader("allCountries.txt");
            BufferedReader data = new BufferedReader(fr);
            String tempLine;
            String field[];

            while ((tempLine = data.readLine()) != null && tempLine.length() != 0) {

                tempLine = tempLine.replaceAll("'", "''");
                field = tempLine.split("\t");
                if (field[CODE].equals(category)) {
                    String id = field[ID];
                    String name = field[NAME];
                    String lat = field[LAT];
                    String lng = field[LNG];
                    String countryCode = field[COUNTRYCODE];
                    String stateCode = field[STATE];
                    String population = field[POPULATION];
                    String elevation = field[ELEVATION];
                    String countryId = getCountryId(countryCode);
                    String contId = getContId(countryCode);
                    String stateId = getStateId(countryCode + "." + stateCode);
                    String query = "INSERT INTO " + category.toLowerCase() + " VALUES ("
                            + id + ", '" + name + "', " + lat
                            + ", " + lng + ", " + contId + ", " + countryId + ", " + stateId + ", " + population + ", " + elevation + ")";
                    queries.add(query);
                }

            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Sqlite3.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Sqlite3.class.getName()).log(Level.SEVERE, null, ex);
        }
        return queries;
    }

    public static String getContId(String code) {
        try {
            FileReader fr = new FileReader("countryInfo.txt");
            BufferedReader data = new BufferedReader(fr);
            String tempLine;
            String field[];

            while ((tempLine = data.readLine()) != null && tempLine.length() != 0) {
                //System.out.println(tempLine);
                field = tempLine.split("\t");
                if (field[0].equals(code)) {
                    String contCode = field[8];
                    if (contCode.equals("AF")) {
                        return "1";
                    } else if (contCode.equals("AS")) {
                        return "2";
                    } else if (contCode.equals("EU")) {
                        return "3";
                    } else if (contCode.equals("NA")) {
                        return "4";
                    } else if (contCode.equals("OC")) {
                        return "5";
                    } else if (contCode.equals("SA")) {
                        return "6";
                    } else {
                        return "7";
                    }
                }
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Sqlite3.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Sqlite3.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "-1";
    }

    public static String getCountryId(String code) {
        try {
            FileReader fr = new FileReader("countryInfo.txt");
            BufferedReader data = new BufferedReader(fr);
            String tempLine;
            String field[];

            while ((tempLine = data.readLine()) != null && tempLine.length() != 0) {
                //System.out.println(tempLine);
                field = tempLine.split("\t");
                if (field[0].equals(code)) {
                    return field[2];
                }
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Sqlite3.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Sqlite3.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "-1";
    }

    public static String getStateId(String code) {
        try {
            FileReader fr = new FileReader("state.txt");
            BufferedReader data = new BufferedReader(fr);
            String tempLine;
            String field[];

            while ((tempLine = data.readLine()) != null && tempLine.length() != 0) {
                //System.out.println(tempLine);
                field = tempLine.split("\t");
                if (field[0].equals(code)) {
                    return field[3];
                }
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Sqlite3.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Sqlite3.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "-1";
    }

    public static ArrayList<String> genStateQueries() {
        ArrayList<String> queries = new ArrayList<>();
        try {
            FileReader fr = new FileReader("state.txt");
            BufferedReader data = new BufferedReader(fr);
            String tempLine;
            String field[];

            while ((tempLine = data.readLine()) != null && tempLine.length() != 0) {
                //System.out.println(tempLine);
                field = tempLine.split("\t");
                String code = field[3];
                String name = field[1];
                name = name.replaceAll("'", "''");  
                String query = "INSERT INTO state VALUES (" + code + ", '" + name + "');";
                queries.add(query);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Sqlite3.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Sqlite3.class.getName()).log(Level.SEVERE, null, ex);
        }
        return queries;
    }

    public static ArrayList<String> genCountryQueries() {
        ArrayList<String> queries = new ArrayList<>();
        try {
            FileReader fr = new FileReader("countryInfo.txt");
            BufferedReader data = new BufferedReader(fr);
            String tempLine;
            String field[];

            while ((tempLine = data.readLine()) != null && tempLine.length() != 0) {
                //System.out.println(tempLine);
                field = tempLine.split("\t");
                String code = field[2];
                String name = field[4];
                String capital = field[5].replaceAll("'", "''");

                name = name.replace("'", "''");
                String query = "INSERT INTO country VALUES (" + code + ", '" + name + "', '" + capital + "');";
                queries.add(query);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Sqlite3.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Sqlite3.class.getName()).log(Level.SEVERE, null, ex);
        }
        return queries;
    }
}
