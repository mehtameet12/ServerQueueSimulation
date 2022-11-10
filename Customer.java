import java.util.ArrayList;

public class Customer {
    int stage;
    ArrayList<Integer> arrivals;
    ArrayList<Integer> departures;
    Customer(){
        stage = 0;
        arrivals = new ArrayList<Integer>();
        departures = new ArrayList<Integer>();
    }
    boolean incStage(){
        stage++;
        return true;
    }
    boolean addArrival(int a){
        arrivals.add(a);
        return true;
    }
    boolean addDeparture(int d){
        departures.add(d);
        return true;
    }
    ArrayList<Integer> getArrivals(){
        return arrivals;
    }
    ArrayList<Integer> getDepartures(){
        return departures;
    }
    
}
