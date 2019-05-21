package server;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import data.SearchRequest;
import data.SearchResult;

public class SearchActor extends AbstractActor {

    private final LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);

    private int resQuantity=1;

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(SearchRequest.class, searchRequest -> {
                    ActorRef searchDB1Actor = getContext().actorOf(Props.create(SearchDB1Actor.class));
                    ActorRef searchDB2Actor = getContext().actorOf(Props.create(SearchDB2Actor.class));
                    searchDB1Actor.tell(searchRequest,getSelf());
                    searchDB2Actor.tell(searchRequest,getSelf());
                })
                .match(SearchResult.class, searchResult -> {
                    if(resQuantity == 1) {
                        if(searchResult.isFoundBook()) {
                            searchResult.replyTo.tell(searchResult,null);
                            getContext().stop(self());
                        }
                    } else if(resQuantity == 2) {
                            searchResult.replyTo.tell(searchResult,null);
                            getContext().stop(self());
                    }
                    resQuantity++;
                })
                .matchAny(o -> System.out.println(o.toString()))
                .build();
    }

}
