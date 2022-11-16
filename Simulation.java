import java.util.Scanner;
import java.lang.Exception;
public class Simulation{
    double clock;                                           //variable to keep track of time
    
    Server s1;                                              //server object variables
    Server s2;                                              
    Server s3;
    int customer;                                           //total number of people who came into store
    int served;                                             //total number of people who finished being served
    EventList el;                                           //EventList Object
    double totalArrivalTime;                                //variable to store total interarrival times
    double totalServiceTimeS1;                              //variable to store total service times for server 1
    double totalServiceTimeS2;                              //variable to store total service times for server 2
    double totalServiceTimeS3;                              //variable to store total service times for server 3
    double duration;                                        //variable to store how long the code should be run

    double s1Wait;                                          //variable to store total wait time for server 1
    double s2Wait;                                          //variable to store total wait time for server 2
    double s3Wait;                                          //variable to store total wait time for server 3

    double operatingTimeS1;                                 //stores total processing time for server 1
    double operatingTimeS2;                                 //stores total processing time for server 2
    double operatingTimeS3;                                 //stores total processing time for server 3

    boolean arrivalDebug;                                   //if set to true, displays interarrival times and at what clock time
    boolean s1Debug;                                        //if set to true, displays server 1 service times and at what clock time
    boolean s2Debug;                                        //if set to true, displays server 2 service times and at what clock time
    boolean s3Debug;                                        //if set to true, displays server 3 service times and at what clock time
    boolean showSteps;                                      //if set to true, displays each individual step of the Simulation
    
    public Simulation(String dur){
        try{                                                //convert user entered duration to double
            duration = Double.parseDouble(dur);
        }catch(Exception e){
            System.out.println("Please enter a valid number");
        }
        clock = 0;                                          //initializes Simulation variables
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

        s1Wait = 0;
        s2Wait = 0;
        s3Wait = 0;

        operatingTimeS1 = 0;
        operatingTimeS2 = 0;
        operatingTimeS3 = 0;

        arrivalDebug = false;
        s1Debug = false;
        s2Debug = false;
        s3Debug = false;
        showSteps = true;
    }
    public void run(){
        initialArrival();                                       //create the first arrival event and add to Event List
        el.addEvent(new Event(duration, EventType.END));        //add the end of simulation event to the Event List
        if(showSteps)printSteps(el.eHead().getEventType());     //if showSteps toggled on, display what happens in the first step

        while(!(el.eHead().getEventType()==EventType.END)){     //if we haven't reached the end of the simulation event, loop:
            Event curE = el.getEvent();                         //remove the next event from the Event List
            clock=curE.getDuration();                           //take the clock value of this event (@ what time this event occurs)
            if(curE.getEventType()==EventType.ARRIVAL){         //if the event taken is an ARRIVAL EVENT
                eventArrive(curE);                              //execute the Arrival event operations
                
                if(showSteps)printSteps(el.eHead().getEventType()); //show what occurs in this step if showSteps is toggled on
                
            }else if(curE.getEventType()==EventType.DEPARTURE){ //if the event is a DEPARTURE EVENT
                eventDepart(curE);                              //execute the operations for a Departure event
                if(showSteps)printSteps(el.eHead().getEventType()); //show what occurs in this step if showSteps is toggled on
                  
            }calculateWait();
            
        }
        System.out.println("The end of the simulation has been reached at time "+el.getEvent().getDuration()+".\n");
        
    }
    
    public void initialArrival(){
        double time = genArrivalTime();                                      //generate a random time
        el.addEvent(new Event(1,clock+time, EventType.ARRIVAL));     //create new arrival event and add to event list
        incrementRespectiveTime(0,time);                             //add the total arrival time
    }

    private Server findServer(Event e){                                     //method returns the corresponding server that should process the current EVENT
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
    private Server nextServer(int server){                                  //method returns the next Server given the value of the current server
        if(server==1){                                                      //this method is useful when the departure of a customer from current server has to go to the next server
            return s2;
        }else if(server==2){
            return s3;
        }else{
            return null;
        }
    }

    private void eventArrive(Event e){                                      //process to take when the next Event from Event List is an ARRIVAL EVENT
        Server curServer = findServer(e);                                   //find the server that has to deal with this Event
        if(curServer.isBusy()){                                             //if this server is busy, increment its queue
            curServer.addToQueue();
            
        }else{
            curServer.service();                                            //if there is no one being serviced, set the server to busy
            double time = genServiceTime(e.getServer());                    //generate a random server time
            el.addEvent(new Event(e.getServer(), clock+ time,EventType.DEPARTURE)); //create a new DEPARTURE EVENT and add it to the Event List
            incrementRespectiveTime(e.getServer(),time);                    //add the service time for this server
            calculateBusyTime(e.getServer(),time);
            
        }
        if(e.getServer()==1){                                               //if this is server 1, we also want to create the next interarrival time
            double time = genArrivalTime();
            el.addEvent(new Event(e.getServer(), clock+time, EventType.ARRIVAL));
            incrementRespectiveTime(0,time);                        //add this interarrival time to the total
            
        }
    }
    private void eventDepart(Event e){                                      //process to take if this is a DEPARTURE EVENT
        Server curServer = findServer(e);                                   
        Server nextServer = nextServer(e.getServer());                      //departure from one server means arrival to the next, so find the next server
        if(e.getServer()<3){                                                   
            if(nextServer.isBusy()){                                        //if the next server is busy, increment the waiting line for it
                nextServer.addToQueue();
            }else{
                el.addEvent(new Event(e.getServer()+1, clock, EventType.ARRIVAL)); //otherwise start servicing customer by creating an ARRIVAL EVENT to the next server
                
            }
        }else{
            served++;                                                       //if the current server is 3, then departure from third server means this customer finished his business here
                                                                            //increment number of people served
        }                                                                   
        if(curServer.getQueueLength()>0){                                   //departure from one server means that current server is no longer busy
            curServer.removeFromQueue();                                    //if there are people waiting in line for this server, decrement the waiting line
            double time = genServiceTime(e.getServer());                    
            el.addEvent(new Event(e.getServer(), clock+time, EventType.DEPARTURE)); //start servicing person by creating a DEPARTURE EVENT
            incrementRespectiveTime(e.getServer(),time);                    //add Server time
            calculateBusyTime(e.getServer(),time);
        }else{
            curServer.setIdle();                                            //if there's no one waiting in the line, set the server to idle state
        }
        
    }
    private void calculateWait(){                                           //calculates total wait time for each server
        s1Wait+=s1.getQueueLength()*(el.eHead().getDuration()-clock);
        s2Wait+=s2.getQueueLength()*(el.eHead().getDuration()-clock);
        s3Wait+=s3.getQueueLength()*(el.eHead().getDuration()-clock);
    }
    private void calculateBusyTime(int server, double time){                //calculates total busy time for each server
        if(server==1){
            if(time+clock<=duration)
                operatingTimeS1+=time;
            else                                                            //if there's an event that goes over simulation duration
                operatingTimeS1+=time -(time+clock-duration);               //take difference of generated service time and excess value      
        }
        if(server==2){
            if(time+clock<=duration)
                operatingTimeS2+=time;
            else
                operatingTimeS2+=time -(time+clock-duration);
        }
        if(server==3){
            if(time+clock<=duration)
                operatingTimeS3+=time;
            else
                operatingTimeS3+=time -(time+clock-duration);
        }
       
    }
    private void incrementRespectiveTime(int server, double time){          //method calculates the total interarrival and service times for the servers
        if(server==0){
            if(time+clock<=duration){                                       //since Event Lists can contain events that occur after END OF SIMULATION, make sure
                                                                            //not to include times that occur after END OF SIMULATION
                totalArrivalTime+=time;
                customer++;
            }
            if(arrivalDebug)
                System.out.println("Interarrival time generated: "+time+" @ clock = "+(time+clock));
        }else if(server==1){
            if(time+clock<=duration)
                totalServiceTimeS1+=time;
            if(s1Debug)
                System.out.println("Server 1 time generated: "+time+" @ clock = "+(time+clock));
        }else if(server==2){
            if(time+clock<=duration)
                totalServiceTimeS2+=time;
            if(s2Debug)
                System.out.println("Server 2 time generated: "+time+" @ clock = "+(time+clock));
        }else if(server==3){
            if(time+clock<=duration)
                totalServiceTimeS3+=time;
            if(s3Debug)
                System.out.println("Server 3 time generated: "+time+" @ clock = "+(time+clock));
        }
    }
    // private void printRemainingEvents(){                                          //displays rest of events in Event List
    //     for(Event e:el){
    //         System.out.println("Server "+e.getServer()+" has event type "+e.getClassName()+" @ clock = "+e.getDuration()+" remaining.");
    //     }
    // }
    private void printSteps(EventType et){                                        //prints the process of each customer moving throughout the system 
        System.out.println("Clock is "+clock);
        String res = (s1.isBusy())?"active":"inactive";
        System.out.println("S1 is currently: "+res+". S1 has "+s1.getQueueLength()+" people in queue.");
        res = (s2.isBusy())?"active":"inactive";
        System.out.println("S2 is currently: "+res+". S2 has "+s2.getQueueLength()+" people in queue.");
        res = (s3.isBusy())?"active":"inactive";
        System.out.println("S3 is currently: "+res+". S3 has "+s3.getQueueLength()+" people in queue.");
        if(et==EventType.END){
            System.out.println("The next event occurs at "+el.eHead().getDuration()+" and is a(n) "+el.eHead().getClassName()+" type.");
        }else{  
            System.out.println("Server "+el.eHead().getServer()+"'s next event occurs at "+el.eHead().getDuration()+" and is a(n) "+el.eHead().getClassName()+" type.");
        }
        System.out.println();
    }

    private double genArrivalTime(){                                    //generate random interarrival time     
        return GenerateTimes.generateArrival();
    }
    private double genServiceTime(int server){                          //generate random service time
        if(server==1){
            return GenerateTimes.generateS1();
        }else if(server==2){
            return GenerateTimes.generateS2();
        }else{
            return GenerateTimes.generateS3();
        }
    }
    private void printData(){                                           //prints important stats such as total time and customers served
        System.out.println("Stats: ");  
        System.out.println("Total number of customers: "+customer);
        System.out.println("Total number of customers served: "+served);
        System.out.println("Total interarrival time: "+ totalArrivalTime);
        System.out.println("Total service time for s1: "+ totalServiceTimeS1);
        System.out.println("Total service time for s2: "+ totalServiceTimeS2);
        System.out.println("Total service time for s3: "+ totalServiceTimeS3);
        System.out.println("---------------------------------");
        System.out.println("Max length of server 1's queue is: "+s1.getMaxQueue());
        System.out.println("Max length of server 2's queue is: "+s2.getMaxQueue());
        System.out.println("Max length of server 3's queue is: "+s3.getMaxQueue());
        System.out.println("---------------------------------");
        System.out.println("Total wait time for server 1's queue is: "+s1Wait);
        System.out.println("Total wait time for server 2's queue is: "+s2Wait);
        System.out.println("Total wait time for server 3's queue is: "+s3Wait);
        System.out.println("---------------------------------");
        System.out.println("Total busy time of server 1 is: "+operatingTimeS1);
        System.out.println("Total busy time of server 2 is: "+operatingTimeS2);
        System.out.println("Total busy time of server 3 is: "+operatingTimeS3);
    }
    public static void main(String[] args) throws Exception{
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter how long the simulation should run:");
        Simulation s = new Simulation(sc.next());
        s.run();
        s.printData();
        sc.close();
    }

}