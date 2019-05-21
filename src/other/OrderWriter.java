package other;

import java.io.*;

public class OrderWriter {
    private static final OrderWriter instance = new OrderWriter();

    private OrderWriter() {
        super();
    }

    public synchronized void writeToFile(String filename, String text) throws IOException {

        Writer output = new BufferedWriter(new FileWriter(filename,true));
        output.append(text).append("\n");
        output.close();
    }

    public static OrderWriter getInstance() {
        return instance;
    }

}
