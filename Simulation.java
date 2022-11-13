import java.util.Scanner;
import java.lang.Exception;
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
        String end = "";
        initialArrival();
        printStats(end);
        el.addEvent(new EndEvent(duration));
        while(!el.eHead().getEventType().equals("end")){
            Event curE = el.getEvent();
            clock=curE.getDuration();
            if(curE.getEventType().equals("arrival")){
                eventArrive(curE);
                if(el.eHead().getEventType().equals("end")){
                    end = "end";
                }
                printStats(end);
                end = "";
            }else if(curE.getEventType().equals("departure")){
                eventDepart(curE);
                if(el.eHead().getEventType().equals("end")){
                    end = "end";
                }
                printStats(end);
                end = "";
                    
            }
            
            //debugEL();
        }
        System.out.println("The end of the simulation has been reached at time "+el.getEvent().getDuration()+".");
    }
    public void initialArrival(){
        el.addEvent(new ArrivalEvent(1,clock+genArrivalTime()));
    }
    private Server findServer(Event e){
        if(e.getServer()==1){
            return s1;
        }else if(e.getServer()==2){
            return s2;
        }else if(e.getServer()==3){
            return s3;
        }else{
            System.out.println("Well, that wasn't supposed to happen");
            return null;
        }
    }
    private Server nextServer(int server){
        if(server==1){
            return s2;
        }else if(server==2){
            return s3;
        }else{
            return null;
        }
    }
    public void eventArrive(Event e){
        Server curServer = findServer(e);
        if(curServer.isBusy()){
            curServer.addToQueue(customer);
        }else{
            curServer.service();
            el.addEvent(new DepartureEvent(e.getServer(), clock+ genServiceTime(e.getServer())));
        }
        if(e.getServer()==1)
            el.addEvent(new ArrivalEvent(e.getServer(), clock+genArrivalTime()));

    }
    public void eventDepart(Event e){
        Server curServer = findServer(e);
        Server nextServer = nextServer(e.getServer());
        if(e.getServer()<3){
            if(nextServer.isBusy()){
                nextServer.addToQueue(customer);
            }else{
                el.addEvent(new ArrivalEvent(e.getServer()+1, clock));
            }
        }
        if(curServer.getQueueLength()>0){
            curServer.removeFromQueue();
            el.addEvent(new DepartureEvent(e.getServer(), clock+genServiceTime(e.getServer())));
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
    public void printStats(String e){
        System.out.println("Clock is "+clock);
        String res = (s1.isBusy())?"active":"inactive";
        System.out.println("S1 is currently: "+res+". S1 has "+s1.getQueueLength()+" people in queue.");
        res = (s2.isBusy())?"active":"inactive";
        System.out.println("S2 is currently: "+res+". S2 has "+s2.getQueueLength()+" people in queue.");
        res = (s3.isBusy())?"active":"inactive";
        System.out.println("S3 is currently: "+res+". S3 has "+s3.getQueueLength()+" people in queue.");
        if(e.equals("end")){
            System.out.println("The next event occurs at "+el.eHead().getDuration()+" and is of "+el.eHead().getClass());
        }else{
            System.out.println("Server "+el.eHead().getServer()+"'s next event occurs at "+el.eHead().getDuration()+" and is of "+el.eHead().getClass());
        }
        System.out.println();
    }
    public double genArrivalTime(){             //create ranges later
        return GenerateTimes.generateArrival();
    }
    public double genServiceTime(int server){             //create ranges later
        if(server==1){
            return GenerateTimes.generateS1();
        }else if(server==2){
            return GenerateTimes.generateS2();
        }else{
            return GenerateTimes.generateS3();
        }
    }
    private static double getDuration(String str)throws Exception{
        double duration=0;
        try{
            duration = Double.parseDouble(str);
        }catch(Exception e){
            System.out.println("Please enter a numeric value for the duration measured in seconds");
        }
        return duration;
    }
    public static void main(String[] args) throws Exception{
        Scanner sc = new Scanner(System.in);
        Simulation s = new Simulation();
        System.out.println("Enter how long the simulation should run:");
        s.run(getDuration(sc.next()));
    }

}