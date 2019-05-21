package server;

import akka.actor.AbstractActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import data.OrderRequest;
import data.OrderResult;
import other.OrderWriter;

import java.io.IOException;

public class OrderActor extends AbstractActor {

    private final LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(OrderRequest.class, this::handleOrder)
                .matchAny(o -> System.out.println(o.toString()))
                .build();
    }

    private void handleOrder(OrderRequest request) {

        OrderResult result = new OrderResult();
        result.setConfirmation(true);
        result.setTitle(request.getTitle());

        try {
            OrderWriter.getInstance().writeToFile("orders.txt",request.getTitle());
        } catch (IOException e) {
            System.out.println("Write order to file failed!");
            result.setConfirmation(false);
        }

        request.replyTo.tell(result,self());
        getContext().stop(getSelf());

    }

}
