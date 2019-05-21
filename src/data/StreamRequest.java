package data;

public class StreamRequest extends Request {
    private String title;

    public StreamRequest(String title) {
        super(null);
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
