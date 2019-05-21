package data;

import java.io.Serializable;

public class SearchRequest extends Request implements Serializable {
    private String title;

    public SearchRequest(String title) {
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
