package server;

import akka.actor.AbstractActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import data.SearchRequest;
import data.SearchResult;
import other.DBFinder;
import other.NoBookInDbException;

import java.io.IOException;

public class SearchDB2Actor extends AbstractActor {

    private final LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(SearchRequest.class, searchRequest -> {
                    handleSearch(searchRequest);
                    System.out.println("SEARCH DB ACTOR 2");
                })
                .matchAny(o -> System.out.println(o.toString()))
                .build();
    }

    private void handleSearch(SearchRequest request) {

        SearchResult result = new SearchResult();
        result.setFoundBook(true);
        try {
            result.setPrice(DBFinder.findBookPriceInDB("baza2.txt",request.getTitle()));
        } catch (IOException | NoBookInDbException e ) {
            result.setFoundBook(false);
        }

        result.setTitle(request.getTitle());
        result.setReplyTo(request.replyTo);
        getSender().tell(result, self());
    }
}
