
public class Simulation{
    double clock;
    Server s1;
    Server s2;
    Server s3;
    int customer;
    EventList el;

    public Simulation(){
        clock = 0;
        s1 = new Server();
        s2 = new Server();
        s3 = new Server();
        customer = 1;
        el = new EventList();
    }
    public void run(double duration){
        initialArrival();
        while(clock<duration){
            Event curE = el.getEvent();
            clock=curE.getDuration();
            if(curE.getEventType().equals("arrival")){
                eventArrive(curE);
            }else if(curE.getEventType().equals("departure")){
                eventDepart(curE);
            }else{
                System.out.println(curE.getEventType());
            }
            //printStats();
            //debugEL();

        }
    }
    public void initialArrival(){
        el.addEvent(new ArrivalEvent(1,clock+genArrivalTime()));
    }
    public Server findServer(Event e){
        if(e.getServer()==1){
            return s1;
        }else if(e.getServer()==2){
            return s2;
        }else{
            return s3;
        }
    }
    public void eventArrive(Event e){
        Server curServer = findServer(e);
        if(curServer.isBusy()){
            curServer.addToQueue(customer);
        }else{
            curServer.service();
            el.addEvent(new DepartureEvent(e.getServer(), clock+ genServiceTime()));
        }
        if(e.getServer()==1)
            el.addEvent(new ArrivalEvent(e.getServer(), clock+genArrivalTime()));

    }
    public void eventDepart(Event e){
        Server curServer = findServer(e);
        if(e.getServer()!=3)
            el.addEvent(new ArrivalEvent(e.getServer()+1, clock));
        if(curServer.getQueueLength()>0){
            curServer.removeFromQueue();
            el.addEvent(new DepartureEvent(e.getServer(), clock+genServiceTime()));
        }else{
            curServer.setIdle();
        }
        
    }
    public void debugEL(){
        for(Event e: el){
            System.out.println("Server "+e.getServer()+" takes "+e.getDuration()+" and is a "+e.getEventType());
        }
        System.out.println("/////");
    }
    public void printStats(){
        String res = (s1.isBusy())?"active":"inactive";
        System.out.println("S1 currently is currently: "+res+". S1 has "+s1.getQueueLength()+" people in queue.");
        res = (s2.isBusy())?"active":"inactive";
        System.out.println("S2 currently is currently: "+res+". S2 has "+s2.getQueueLength()+" people in queue.");
        res = (s3.isBusy())?"active":"inactive";
        System.out.println("S3 currently is currently: "+res+". S3 has "+s3.getQueueLength()+" people in queue.");
        System.out.println();
    }
    public double genArrivalTime(){             //create ranges later
        return 1;
    }
    public double genServiceTime(){             //create ranges later
        return 3;
    }

    public static void main(String[] args){
        Simulation s = new Simulation();
        s.run(15);
    }

}