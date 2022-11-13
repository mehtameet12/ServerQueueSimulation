
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
            clock+=curE.getDuration();
            if(curE.getEventType().equals("arrival")){
                eventArrive(curE);
            }else if(curE.getEventType().equals("arrival")){
                eventDepart(curE);
            }else{
                System.out.println("error");
            }
        }
    }
    public void initialArrival(){
        el.add(new ArrivalEvent(1,clock+genArrivalTime()));
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
            curServer.service(customer);
            el.add(new DepartureEvent(e.getServer(), clock+ genServiceTime()));
        }
        if(e.getServer()==1)
            el.add(new ArrivalEvent(e.getServer(), clock+genArrivalTime()));
    }
    public void eventDepart(Event e){
        Server curServer = findServer(e);
        if(e.getServer()!=3)
            el.add(new ArrivalEvent(e.getServer()+1, clock));
        if(curServer.getQueueLength()>0){
            curServer.removeFromQueue();
            el.add(new DepartureEvent(e.getServer(), clock+genServiceTime()));
        }
    }
    public double genArrivalTime(){             //create ranges later
        return 4;
    }
    public double genServiceTime(){             //create ranges later
        return 3;
    }
    public void main(String[] args){
        Simulation s = new Simulation();
        s.run(200);
    }

}