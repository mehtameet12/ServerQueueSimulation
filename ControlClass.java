import java.util.Scanner;
public class ControlClass{    
    //Class Variables
    int sizeOfQueue;
    int clientInQueue1;
    int clientInQueue2;
    int clientInQueue3;
    
    // 0 indicates Server Available, 1 indicates Server Busy
    int serverOneStatus;
    int serverTwoStatus;
    int serverThreeStatus;

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
        
        serverOneStatus = 0;
        serverTwoStatus = 0;
        serverThreeStatus = 0;

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

    }

    public void handleArrivalAtServerTwo(){

    }

    public void handleArrivalAtServerThree(){

    }

    public void handleDepartureAtServerOne(){

    }

    public void handleDepartureAtServerTwo(){

    }

    public void handleDepartureAtServerThree(){

    }
    
}
