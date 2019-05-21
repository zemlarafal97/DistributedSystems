package data;

import java.io.Serializable;

public class OrderResult implements Serializable {
    private String title;
    private boolean confirmation = false;

    public boolean isConfirmation() {
        return confirmation;
    }

    public void setConfirmation(boolean confirmation) {
        this.confirmation = confirmation;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
