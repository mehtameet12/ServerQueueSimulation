
public class Server {                                           //this is the server class
    private int queue;                                          //variable to keep track of how many people in its queue
    private int processing;                                 //set to true if server is busy with someone
    private int maxQueue;                                       //value to store max queue length
    private int maxProcessor;

    public Server(){
        queue = 0;
        processing = 0;
        maxQueue = 0;
        maxProcessor = 1;
    }
    public Server(int maxProcessor){
        queue = 0;
        processing = 0;
        maxQueue = 0;
        this.maxProcessor = maxProcessor;
    }

    private void determineMaxQueue(int queueLength){            //find the max queue length
        if(maxQueue<queueLength){
            maxQueue = queueLength;
        }
    }

    public boolean isBusy(){                                    //method to determine if server is busy
        return processing==maxProcessor;
    }

    public int getProcessing(){
        return processing;
    }
    
    public void addToQueue(){                                   //increase the waiting line by 1
        queue++;    
        determineMaxQueue(getQueueLength());
    }

    public int getQueueLength(){                                //get the current waiting line length
        return queue;
    }

    public void service(){                                      //set server to busy
        processing++;
    }

    public void setIdle(){                                      //set the server to idle
        processing--; 
    }

    public int getMaxQueue(){                                   //get max queue length that ever existed
        return maxQueue;
    }
    
    public void removeFromQueue(){                              //decrease the waiting line length by 1
        queue--;
    }
}
