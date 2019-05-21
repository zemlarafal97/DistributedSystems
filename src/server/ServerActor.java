package server;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import data.OrderRequest;
import data.SearchRequest;
import data.StreamRequest;

public class ServerActor extends AbstractActor {

    private final LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(SearchRequest.class, searchRequest -> {
                    log.info("Received search request for title " + searchRequest.getTitle());
                    ActorRef actor = getContext().actorOf(Props.create(SearchActor.class));
                    actor.tell(searchRequest,self());
                })
                .match(OrderRequest.class, orderRequest -> {
                    log.info("Received order request for title " + orderRequest.getTitle());
                    ActorRef actor = getContext().actorOf(Props.create(OrderActor.class));
                    actor.tell(orderRequest,self());
                })
                .match(StreamRequest.class, streamRequest -> {
                    log.info("Received stream request for title " + streamRequest.getTitle());
                    ActorRef actor = getContext().actorOf(Props.create(StreamActor.class));
                    actor.tell(streamRequest,self());
                })
                .matchAny(o -> log.info("SERVER: received unknown message"))
                .build();
    }
}
