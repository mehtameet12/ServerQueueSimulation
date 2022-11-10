import java.util.ArrayList;
import java.util.Scanner;

public class ThreeServerQueueSimulation{    
    //Class Variables
   int sizeOfQueue;
    /*int clientInQueue1;
    int clientInQueue2;
    int clientInQueue3;
    
    // 0 indicates Server Available, 1 indicates Server Busy
   /* boolean serverOneBusy;
    boolean serverTwoBusy;
    boolean serverThreeBusy;

    double clock;
    double simulationTime;*/

    double[] arrivalTimeServer1;
    double[] arrivalTimeServer2;
    double[] arrivalTimeServer3;
    
    double[] departureTimeServer1;
    double[] departureTimeServer2;
    double[] departureTimeServer3;

    Server server1;
    Server server2;
    Server server3;

    Queue queue1;
    Queue queue2;
    Queue queue3;

    static Clock clock;

    void initialize(){
        sizeOfQueue = 100;
      /*  clientInQueue1 = 0;
        clientInQueue2 = 0;
        clientInQueue3 = 0;*/

        clock = new Clock();
        
        queue1 = new Queue("Queue1", 1);
        queue2 = new Queue("Queue2", 2);
        queue3 = new Queue("Queue3", 3);

        server1 = new Server("Server1",1, queue1, clock);
        server2 = new Server("Server2",2, queue2, clock);
        server3 = new Server("Server3",3, queue3, clock);




      /*  serverOneBusy = false;
        serverTwoBusy = false;
        serverThreeBusy = false;

        clock = 0.0;
        simulationTime = 0.0;*/

        arrivalTimeServer1 = new double[sizeOfQueue];
        arrivalTimeServer2 = new double[sizeOfQueue];
        arrivalTimeServer3 = new double[sizeOfQueue];

        departureTimeServer1 = new double[sizeOfQueue];
        departureTimeServer2 = new double[sizeOfQueue];
        departureTimeServer3 = new double[sizeOfQueue];
    }
    public static void main(String args[]){
        ArrayList <Event> eventList = new ArrayList<Event>();
        ArrayList<Customer> cs = new ArrayList<Customer>();
        for(int i = 0; i < 20; i++){
            cs.add(new Customer());
        }
        eventList.add(new Event(eventName.ArrivalS1, clock.getTime()));

       
        
    }

/*    public void handleArrivalAtServerOne(){
        if (!server1.isBusy()){
            //Sever 1 serves the customer 
        }else{
            setClientInQueue1();
        }
    }

    public void handleArrivalAtServerTwo(){
        if(!server2.isBusy()){
            //Server 2 serves the customer
        }else{
            setClientInQueue2();
        }
    }

    public void handleArrivalAtServerThree(){
        if(!server3.isBusy()){
            //Server 3 serves the customer
        }else{
            setClientInQueue3();
        }
    }

    public void handleDepartureAtServerOne(){
        //depending on ther service time, to be fed from the data
        //Clock to be updated
    }

    public void handleDepartureAtServerTwo(){
        //depending on ther service time, to be fed from the data
        //Clock to be updated
    }

    public void handleDepartureAtServerThree(){
        //depending on ther service time, to be fed from the data
        //Clock to be updated
    }

    public void setClientInQueue1 (){
        clientInQueue1++;
    }

    public void setClientInQueue2 (){
        clientInQueue2++;
    }

    public void setClientInQueue3 (){
        clientInQueue3++;
    }

    public int getClientInQueue1 (){
        return clientInQueue1;
    }

    public int getClientInQueue2 (){
        return clientInQueue2;
    }

    public int getClientInQueue3 (){
        return clientInQueue3;
    }

    public void setClock(double timeIncreament){
        clock = clock + timeIncreament;
    }

    public double getClock(){
        return clock;
    }*/
}
