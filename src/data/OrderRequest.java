package data;

import java.io.Serializable;

public class OrderRequest extends Request implements Serializable {
    private String title;
    public OrderRequest(String title) {
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
