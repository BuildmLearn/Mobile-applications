package sqlite3;

import java.util.ArrayList;

/**
 *
 * @author Abhishek
 */
public class Sqlite3 {
    
     public static void main(String args[]) {
        Database db = new Database("data.db");
        String query = "CREATE TABLE IF NOT EXISTS main"
                + "(_id INTEGER PRIMARY KEY, NAME TEXT, "
                + "lat REAL, lng REAL, code TEXT,country_code TEXT, capital TEXT, country TEXT, state TEXT, continent TEXT , population INTEGER, elevation INTEGER)";
        db.executeQuery1(query);
        db.executeQuery1("CREATE TABLE 'android_metadata' ('locale' TEXT DEFAULT 'en_US')");
        db.executeQuery1("INSERT INTO 'android_metadata' VALUES ('en_US')");

        String[] fields = {"PCLI","PEN", "PLN", "PPLC", "ADM1", "VLC", "OCN", "SEA", "MTS", "PLTU",
                             "FLLS", "GLCR", "DSRT", "PEN", "PLN"}; // PCLI - Countries,PPLC - Capital, ADM1 - States, VLC - Volcano, OCN - Ocean, SEA - sea
        //MTS - Mountains,PLTU - Plateau, FLLS- Waterfalls, GLCR - Glacier, DSRT - Dessert
        // PEN -  Peninsula, PLN - Plains
        FileHandling file = new FileHandling("allCountries.txt", fields);
        ArrayList queries = file.generateDbQuery();
        int length = queries.size();
        for (int i = 0; i < length; i++) {
            //System.out.println(queries.get(i));
            db.executeQuery1(queries.get(i).toString());
        }
        db.closeDb();
    }
}
