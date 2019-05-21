package client;

import akka.actor.AbstractActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import data.OrderResult;
import data.Request;
import data.SearchResult;
import data.StreamResult;

public class ClientActor extends AbstractActor {

    private final LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);

    final String serverPath = "akka.tcp://server_system@127.0.0.1:3553/user/server";

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(Request.class, request -> {
                    request.replyTo = getSelf();
                    getContext().actorSelection(serverPath).tell(request,getSelf());
                })
                .match(SearchResult.class, searchResult -> {
                    if(searchResult.isFoundBook()) {
                        System.out.println("[SEARCH] book: " + searchResult.getTitle() + " costs: " + searchResult.getPrice());
                    } else {
                        System.out.println("[SEARCH] book: " + searchResult.getTitle() + " NOT FOUND!");
                    }
                })
                .match(OrderResult.class, orderResult -> {
                    System.out.println("[ORDER] book: " + orderResult.getTitle() + " confirm: " + orderResult.isConfirmation());
                })
                .match(StreamResult.class, result -> {
                    System.out.println(result.getLine());
                })
                .matchAny(o -> log.info("received unknown message"))
                .build();
    }
}
