public class Server {
    String name;
    int stage;
    boolean busy;
    Queue enterQueue;
    Customer serving;
    Queue nextQueue;

    Server(String name, int stage, Queue queue){
        this.name = name;
        this.stage = stage;
        this.enterQueue = queue;
        busy = false;
    }
    boolean isBusy(){
        return busy;
    }
    String getName(){
        return name;
    }
    boolean serve(){
        if(busy || enterQueue.isEmpty()){
            return false;
        }
        else{
            serving = enterQueue.getCustomer();
            busy = true;
            //something with the time

            //leaving server
            serving.incStage();
            nextQueue.addCustomer(serving);
            return true;
        }
    }
}
