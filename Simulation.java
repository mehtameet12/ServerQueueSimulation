import java.util.Scanner;
import java.lang.Exception;
public class Simulation{
    double clock;
    Server s1;
    Server s2;
    Server s3;
    int customer;
    int served;
    EventList el;
    double totalArrivalTime;
    double totalServiceTimeS1;
    double totalServiceTimeS2;
    double totalServiceTimeS3;
    double duration;

    boolean arrivalDebug;
    boolean s1Debug;
    boolean s2Debug;
    boolean s3Debug;
    boolean showSteps;
    public Simulation(String dur){
        try{
            duration = Double.parseDouble(dur);
        }catch(Exception e){
            System.out.println("Please enter a valid number");
        }
        clock = 0;
        s1 = new Server();
        s2 = new Server();
        s3 = new Server();
        el = new EventList();
        customer = 0;
        served = 0;
        totalArrivalTime = 0;
        totalServiceTimeS1 =0;
        totalServiceTimeS2 =0;
        totalServiceTimeS3 =0;

        arrivalDebug = false;
        s1Debug = false;
        s2Debug = false;
        s3Debug = true;
        showSteps = false;
    }
    public void run(){
        String end = "";
        initialArrival();
        if(showSteps)printSteps(end);
        el.addEvent(new Event(duration, EventType.END));
        while(!(el.eHead().getEventType()==EventType.END)){
            Event curE = el.getEvent();
            clock=curE.getDuration();
            if(curE.getEventType()==EventType.ARRIVAL){
                eventArrive(curE);
                if(el.eHead().getEventType()==EventType.END){
                    end = "end";
                }
                if(showSteps)printSteps(end);
                end = "";
            }else if(curE.getEventType()==EventType.DEPARTURE){
                eventDepart(curE);
                if(el.eHead().getEventType()==EventType.END){
                    end = "end";
                }
                if(showSteps)printSteps(end);
                end = "";
                    
            }
        }
        System.out.println("The end of the simulation has been reached at time "+el.getEvent().getDuration()+".\n");
        
    }
    
    public void initialArrival(){
        double time = genArrivalTime();
        el.addEvent(new Event(1,clock+time, EventType.ARRIVAL));
        incrementArrivalTime(time);
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
    private void eventArrive(Event e){
        Server curServer = findServer(e);
        if(curServer.isBusy()){
            curServer.addToQueue();
        }else{
            curServer.service();
            double time = genServiceTime(e.getServer());
            el.addEvent(new Event(e.getServer(), clock+ time,EventType.DEPARTURE));
            if(e.getServer()==1)incrementS1Time(time);
            else if(e.getServer()==2)incrementS2Time(time);
            else if(e.getServer()==3)incrementS3Time(time);
        }
        if(e.getServer()==1){
            double time = genArrivalTime();
            el.addEvent(new Event(e.getServer(), clock+time, EventType.ARRIVAL));
            incrementArrivalTime(time);
        }
    }
    private void eventDepart(Event e){
        Server curServer = findServer(e);
        Server nextServer = nextServer(e.getServer());
        if(e.getServer()<3){
            if(nextServer.isBusy()){
                nextServer.addToQueue();
            }else{
                el.addEvent(new Event(e.getServer()+1, clock, EventType.ARRIVAL));
            }
        }else{
            served++;
        }
        if(curServer.getQueueLength()>0){
            curServer.removeFromQueue();
            double time = genServiceTime(e.getServer());
            el.addEvent(new Event(e.getServer(), clock+time, EventType.DEPARTURE));
            if(e.getServer()==1)incrementS1Time(time);
            else if(e.getServer()==2)incrementS2Time(time);
            else if(e.getServer()==3)incrementS3Time(time);
        }else{
            curServer.setIdle();
        }
        
    }
    private void incrementArrivalTime(double time){
        if(time+clock<=duration){
            totalArrivalTime+=time;
            customer++;
        }
        if(arrivalDebug)
            System.out.println("Interarrival time generated: "+time+" @ clock = "+(time+clock));
    }
    private void incrementS1Time(double time){
        if(time+clock<= duration){
            totalServiceTimeS1+=time;
        }
        if(s1Debug)
            System.out.println("Server 1 time generated: "+time+" @ clock = "+(time+clock));
    }
    private void incrementS2Time(double time){
        if(time+clock<= duration){
            totalServiceTimeS2+=time;
        }
        if(s2Debug)
            System.out.println("Server 2 time generated: "+time+" @ clock = "+(time+clock));
    }
    private void incrementS3Time(double time){
        if(time+clock<= duration){
            totalServiceTimeS3+=time;
        }
        if(s3Debug)
            System.out.println("Server 3 time generated: "+time+" @ clock = "+(time+clock));
    }
    private void printSteps(String e){
        System.out.println("Clock is "+clock);
        String res = (s1.isBusy())?"active":"inactive";
        System.out.println("S1 is currently: "+res+". S1 has "+s1.getQueueLength()+" people in queue.");
        res = (s2.isBusy())?"active":"inactive";
        System.out.println("S2 is currently: "+res+". S2 has "+s2.getQueueLength()+" people in queue.");
        res = (s3.isBusy())?"active":"inactive";
        System.out.println("S3 is currently: "+res+". S3 has "+s3.getQueueLength()+" people in queue.");
        if(e.equals("end")){
            System.out.println("The next event occurs at "+el.eHead().getDuration()+" and is a(n) "+el.eHead().getClassName()+" type.");
        }else{  
            System.out.println("Server "+el.eHead().getServer()+"'s next event occurs at "+el.eHead().getDuration()+" and is a(n) "+el.eHead().getClassName()+" type.");
        }
        System.out.println();
    }

    private double genArrivalTime(){                       //create ranges later
        return GenerateTimes.generateArrival();
    }
    private double genServiceTime(int server){             //create ranges later
        if(server==1){
            return GenerateTimes.generateS1();
        }else if(server==2){
            return GenerateTimes.generateS2();
        }else{
            return GenerateTimes.generateS3();
        }
    }
    private void printData(){
        System.out.println("Stats: ");
        System.out.println("Total customers in system: "+customer);
        System.out.println("Total customers served: "+served);
        System.out.println("Total interarrival time: "+ totalArrivalTime);
        System.out.println("Total service time for s1: "+ totalServiceTimeS1);
        System.out.println("Total service time for s2: "+ totalServiceTimeS2);
        System.out.println("Total service time for s3: "+ totalServiceTimeS3);
    }
    public static void main(String[] args) throws Exception{
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter how long the simulation should run:");
        Simulation s = new Simulation(sc.next());
        s.run();
        s.printData();
    }

}