import java.util.ArrayList;

public class Server {
    String name;
    int stage;
    boolean busy;
    Customer serving;
    Clock clock;


    Server(String name, int stage, Queue queue,Clock clock){
        this.name = name;
        this.stage = stage;
        busy = false;
        this.clock = clock;
    }
    boolean isBusy(){
        return busy;
    }
    String getName(){
        return name;
    }

    int serve(Customer serving){
        if(busy){
            return -1;
        }
        else{
            int arrival = clock.getTime();
            serving.addArrival(arrival);
            busy = true;
            int serviceTime = 1;
            
            //something with the time

            //leaving server
            int departure = clock.getTime() + serviceTime;
            serving.addDeparture(departure);
            return departure;
        }
    }
}
