
public class Server {
    private int queue;
    private int processing;

    public Server(){
        queue = 0;
        processing = 0;
    }

    public boolean isBusy(){
        return processing>0;
    }

    public void addToQueue(int customer){
        queue++;
    }
    public int getQueueLength(){
        return queue;
    }
    public void service(int customer){
        processing = customer;
    }

    public void removeFromQueue(){
        queue--;
    }
}
