package sqlite3;

import java.util.ArrayList;

/**
 *
 * @author Abhishek
 */
public class Sqlite3 {
    
     public static void main(String args[]) {
        Database db = new Database("name.db");
        String query = "CREATE TABLE IF NOT EXISTS main"
                + "(_id INTEGER PRIMARY KEY, NAME TEXT, "
                + "lat REAL, lng REAL, code TEXT,country_code INTEGER, population INTEGER, elevation INTEGER)";
        db.executeQuery1(query);

        String[] fields = {"ADM1"};
        FileHandling file = new FileHandling("allCountries.txt", fields);
        ArrayList queries = file.generateDbQuery();
        int length = queries.size();
        for (int i = 0; i < length; i++) {
            System.out.println(queries.get(i));
            db.executeQuery1(queries.get(i).toString());
        }
        db.closeDb();
    }
}
