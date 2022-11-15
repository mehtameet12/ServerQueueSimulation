
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

    public void addToQueue(){
        queue++;
    }
    public int getQueueLength(){
        return queue;
    }
    public void service(){
        processing = 1;
    }
    public void setIdle(){
        processing = 0;
    }

    public void removeFromQueue(){
        queue--;
    }
}
