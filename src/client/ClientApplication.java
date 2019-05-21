package client;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import data.OrderRequest;
import data.SearchRequest;
import data.StreamRequest;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class ClientApplication {


    public static void main(String []args) throws IOException {
        final File configFile = new File("client_app.conf");
        final Config config = ConfigFactory.parseFile(configFile);

        final ActorSystem system = ActorSystem.create("client_system",config);

        ActorRef clientActor = system.actorOf(Props.create(ClientActor.class),"client");

        printHelp();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while(true) {
            String line = br.readLine();
            if(line.equals("q")) {
                break;
            } else if(line.startsWith("search")) {
                String title = line.substring(7);
                SearchRequest request = new SearchRequest(title);
                clientActor.tell(request,null);
            } else if(line.startsWith("order")) {
                String title = line.substring(6);
                OrderRequest request = new OrderRequest(title);
                clientActor.tell(request,null);
            } else if(line.startsWith("stream")) {
                String title = line.substring(7);
                StreamRequest request = new StreamRequest(title);
                clientActor.tell(request,null);
            } else {
                System.out.println("Incorrect command!");
            }
        }

        system.terminate();
    }

    private static void printHelp() {
        System.out.println("======= H E L P =======");
        System.out.println("search [book_title]");
        System.out.println("order [boon_title]");
        System.out.println("stream [book_title]");
        System.out.println("=======================");
    }

}
