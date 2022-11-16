
public class Server {
    private int queue;
    private int processing;
    private int maxQueue;

    public Server(){
        queue = 0;
        processing = 0;
        maxQueue = 0;
    }
    private void determineMaxQueue(int queueLength){
        if(maxQueue<queueLength){
            maxQueue = queueLength;
        }
    }
    public boolean isBusy(){
        return processing>0;
    }

    public void addToQueue(){
        queue++;
        determineMaxQueue(getQueueLength());
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
    public int getMaxQueue(){
        return maxQueue;
    }
    public void removeFromQueue(){
        queue--;
    }
}
