import java.util.Scanner;
public class ThreeServerQueueSimulation{    
    //Class Variables
    int sizeOfQueue;
    int clientInQueue1;
    int clientInQueue2;
    int clientInQueue3;
    
    // 0 indicates Server Available, 1 indicates Server Busy
    int serverOneBusy;
    int serverTwoBusy;
    int serverThreeBusy;

    double clock;
    double simulationTime;

    double[] arrivalTimeServer1;
    double[] arrivalTimeServer2;
    double[] arrivalTimeServer3;
    
    double[] departureTimeServer1;
    double[] departureTimeServer2;
    double[] departureTimeServer3;


    void initialize(){
        sizeOfQueue = 100;
        clientInQueue1 = 0;
        clientInQueue2 = 0;
        clientInQueue3 = 0;
        
        serverOneBusy = 0;
        serverTwoBusy = 0;
        serverThreeBusy = 0;

        clock = 0.0;
        simulationTime = 0.0;

        arrivalTimeServer1 = new double[sizeOfQueue];
        arrivalTimeServer2 = new double[sizeOfQueue];
        arrivalTimeServer3 = new double[sizeOfQueue];

        departureTimeServer1 = new double[sizeOfQueue];
        departureTimeServer2 = new double[sizeOfQueue];
        departureTimeServer3 = new double[sizeOfQueue];
    }
    public static void main(String args[]){
        
    }

    public void handleArrivalAtServerOne(){
        if (serverOneBusy == 0){
            //Sever 1 serves the customer 
        }else{
            setClientInQueue1();
        }
    }

    public void handleArrivalAtServerTwo(){
        if(serverTwoBusy == 0){
            //Server 2 serves the customer
        }else{
            setClientInQueue2();
        }
    }

    public void handleArrivalAtServerThree(){
        if(serverThreeBusy == 0){
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
    }
}
