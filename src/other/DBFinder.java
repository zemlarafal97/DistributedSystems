package other;

import other.NoBookInDbException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class DBFinder {

    public static double findBookPriceInDB(String dbFileName, String title) throws IOException, NoBookInDbException {

        BufferedReader reader = new BufferedReader(new FileReader(dbFileName));
        String line = reader.readLine();

        while(line != null) {
            String result[] = line.split("-");
            String dbBookTitle = result[0];
            double dbBookPrice = Double.parseDouble(result[1]);

            if(dbBookTitle.equals(title)) return dbBookPrice;

            line = reader.readLine();
        }
        reader.close();

        throw new NoBookInDbException("There is no book in [" + dbFileName + "] database");
    }



}
