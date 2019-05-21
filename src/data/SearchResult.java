package data;

import akka.actor.ActorRef;

import java.io.Serializable;

public class SearchResult implements Serializable {
    private double price;
    private String title;
    private boolean foundBook = false;
    public ActorRef replyTo;

    public SearchResult() {

    }

    public SearchResult(double price, String title) {
        this.price = price;
        this.title = title;
    }

    public ActorRef getReplyTo() {
        return this.replyTo;
    }

    public void setReplyTo(ActorRef replyTo) {
        this.replyTo = replyTo;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isFoundBook() {
        return foundBook;
    }

    public void setFoundBook(boolean foundBook) {
        this.foundBook = foundBook;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
