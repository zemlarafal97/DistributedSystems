package data;

import java.io.Serializable;

public class StreamResult implements Serializable {
    private String line;

    public StreamResult(String line) {
        this.line = line;
    }

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }
}
