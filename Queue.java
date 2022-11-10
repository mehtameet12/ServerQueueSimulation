import java.util.ArrayList;

public class Queue {
    String name;
    int stage;
    
    ArrayList<Customer> customers;
    int first;

    Queue(String name,int stage){
        this.name = name;
        this.stage =stage;
        
        customers = new ArrayList<Customer>();
        first = 0;
    }
    String getName(){
        return name;
    }
    int getStage(){
        return stage;
    }
    boolean isEmpty(){
        return customers.isEmpty();
    }
    boolean addCustomer(Customer e){
        customers.add(e);
        return true;
    }
    Customer getCustomer(){
        first ++;
        return customers.get(first - 1);
    }
    int size(){
        return customers.size();
    }

    



}
