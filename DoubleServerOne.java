import java.util.Scanner;
import java.lang.Exception;
public class DoubleServerOne{
    
    double clock;                                           //variable to keep track of time
    double duration;                                        //variable to store how long the code should be run

    Server s1a, s1b, s2, s3;                                //server object variables
    int customer;                                           //total number of people who came into store
    int served;                                             //total number of people who finished being served
    EventList el;                                           //EventList Object
    
    double totalArrivalTime;                                //variable to store total interarrival times
    double totalServiceTimeS1a;                             //variable to store total service times for server 1a
    double totalServiceTimeS1b;                             //variable to store total service times for server 1b
    double totalServiceTimeS2;                              //variable to store total service times for server 2
    double totalServiceTimeS3;                              //variable to store total service times for server 3
    
    double s1aWait, s1bWait, s2Wait, s3Wait;                //variable to store total wait time for server 1, 2 and 3

    boolean arrivalDebug;                                   //if set to true, displays interarrival times and at what clock time
    boolean s1aDebug;                                        //if set to true, displays server 1 service times and at what clock time
    boolean s1bDebug;                                        //if set to true, displays server 1 service times and at what clock time
    boolean s2Debug;                                        //if set to true, displays server 2 service times and at what clock time
    boolean s3Debug;                                        //if set to true, displays server 3 service times and at what clock time
    boolean showSteps;                                      //if set to true, displays each individual step of the Simulation
    int swap = 0;

    public DoubleServerOne(String dur){
        
        try{                                                //convert user entered duration to double
            duration = Double.parseDouble(dur);
        }catch(Exception e){
            System.out.println("Please enter a valid number");
        }
        clock = 0;                                          //initializes Simulation variables
        s1a = new Server();
        s1b = new Server();
        s2 = new Server();
        s3 = new Server();
        el = new EventList();

        customer = 0;                                       //number of customers and customers served
        served = 0;
        totalArrivalTime = 0;                               //total interarrival times and service times for the servers
        totalServiceTimeS1a =0; 
        totalServiceTimeS1b =0;                              
        totalServiceTimeS2 =0;                              
        totalServiceTimeS3 =0;                              

        s1aWait = 0;                                         //total wait times for the servers
        s1bWait = 0;
        s2Wait = 0;
        s3Wait = 0;

        arrivalDebug = false;                               //debugging option
        s1aDebug = false;
        s1bDebug = false;
        s2Debug = false;
        s3Debug = false;
        showSteps = true;
    }

    public void run() {
        el.addEvent(new Event(duration, EventType.END));        //add the end of simulation event to the Event List
        initialArrival();                                       //create the first arrival event and add to Event List
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
                  
            }
            calculateWait();
            calculateService();
            calculateInterTime();
        }
        System.out.println("The end of the simulation has been reached at time "+el.getEvent().getDuration()+".\n");
        
    }

    public void initialArrival(){
        el.addEvent(new Event(1,clock,genArrivalTime(), EventType.ARRIVAL));     //create new arrival event and add to event list
        calculateInterTime();
    }

    private Server findServer(Event e){                                     //method returns the corresponding server that should process the current EVENT
        if(e.getServer()==1){                                               
            if(swap==0) {
                swap = 1;
                return s1a;
            }
            swap = 0;
            return s1b;
        }else if(e.getServer()==2){
            return s2;
        }else if(e.getServer()==3){
            return s3;
        }else{
            System.out.println("Well, that wasn't supposed to happen");
            return null;
        }
    }

    private Server nextServer(int server) {                                  //method returns the next Server given the value of the current server
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
        if(curServer==s1a || curServer==s1b) {
            customer++;
        }
        if(curServer.isBusy()){                                             //if this server is busy, increment its queue
            curServer.addToQueue();
        }else{
            curServer.service();                                            //if there is no one being serviced, set the server to busy
            el.addEvent(new Event(e.getServer(), clock+ genServiceTime(e.getServer()),EventType.DEPARTURE)); //create a new DEPARTURE EVENT and add it to the Event List
            
        }
        if(e.getServer()==1){                                               //if this is server 1, we also want to create the next interarrival time
            el.addEvent(new Event(e.getServer(), clock,genArrivalTime(), EventType.ARRIVAL));
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
            el.addEvent(new Event(e.getServer(), clock+genServiceTime(e.getServer()), EventType.DEPARTURE)); //start servicing person by creating a DEPARTURE EVENT
        }else{
            curServer.setIdle();                                            //if there's no one waiting in the line, set the server to idle state
        }
        
    }

    private void calculateWait(){                                           //calculates total wait time for each server
        s1aWait += s1a.getQueueLength()*(el.eHead().getDuration()-clock);
        s1bWait += s1b.getQueueLength()*(el.eHead().getDuration()-clock);
        s2Wait += s2.getQueueLength()*(el.eHead().getDuration()-clock);
        s3Wait += s3.getQueueLength()*(el.eHead().getDuration()-clock);
    }

    private void calculateService(){
        totalServiceTimeS1a += s1a.getProcessing()*(el.eHead().getDuration()-clock);
        totalServiceTimeS1b += s1b.getProcessing()*(el.eHead().getDuration()-clock);
        totalServiceTimeS2 += s2.getProcessing()*(el.eHead().getDuration()-clock);
        totalServiceTimeS3 += s3.getProcessing()*(el.eHead().getDuration()-clock);
        
        if(s1aDebug)System.out.println(s1a.getProcessing()*(el.eHead().getDuration()-clock) + " @ clock " + el.eHead().getDuration());
        if(s1bDebug)System.out.println(s1b.getProcessing()*(el.eHead().getDuration()-clock) + " @ clock " + el.eHead().getDuration());
        if(s2Debug)System.out.println(s2.getProcessing()*(el.eHead().getDuration()-clock) + " @ clock " + el.eHead().getDuration());
        if(s3Debug)System.out.println(s3.getProcessing()*(el.eHead().getDuration()-clock) + " @ clock " + el.eHead().getDuration());
    }

    private void calculateInterTime(){
        if(el.eHead().getEventType()==EventType.ARRIVAL&&el.eHead().getServer()==1)
            totalArrivalTime+=el.eHead().getEventTime();
        if(arrivalDebug)
            System.out.println(el.eHead().getEventTime()+ " @ clock " + el.eHead().getDuration());
    }

    private void printSteps(EventType et){                                        //prints the process of each customer moving throughout the system 
        System.out.println("Clock is "+clock);
        String res = (s1a.isBusy()) ? "active" : "inactive";
        String res2 = (s1b.isBusy()) ? "active" : "inactive";
        System.out.println("S1a is currently: " + res + ". S1a has " + s1a.getQueueLength() + " people in queue.");
        System.out.println("S1b is currently: " + res2 + ". S1b has " + s1b.getQueueLength() + " people in queue.");
        res = (s2.isBusy()) ? "active" : "inactive";
        System.out.println("S2 is currently: "+ res + ". S2 has " + s2.getQueueLength() + " people in queue.");
        res = (s3.isBusy()) ? "active" : "inactive";
        System.out.println("S3 is currently: " + res+". S3 has " + s3.getQueueLength() + " people in queue.");
        // if(et==EventType.END){
        //     System.out.println("The next event occurs at "+el.eHead().getDuration()+" and is a(n) "+el.eHead().getClassName()+" type.");
        // }else{  
        //     System.out.println("Server "+el.eHead().getServer()+"'s next event occurs at "+el.eHead().getDuration()+" and is a(n) "+el.eHead().getClassName()+" type.");
        // }
        System.out.println("The remaining Events in the Event List: ");
        for(Event e:el){
            if(e.getEventType()==EventType.END)
                System.out.println("This is an " + e.getClassName() + " Event that occurs at " + e.getDuration());
            else System.out.println("This is a(n) " + e.getClassName() + " Event belonging to Server " + e.getServer() + " that occurs at " + e.getDuration());
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
        System.out.println("Total number of customers: " + customer);
        System.out.println("Total number of customers served: " + served);
        System.out.println("Average interarrival time: " + totalArrivalTime/customer);
        System.out.println("Average service time for s1a: " + totalServiceTimeS1a/customer);
        System.out.println("Average service time for s1b: " + totalServiceTimeS1b/customer);
        System.out.println("Average service time for s2: " + totalServiceTimeS2/customer);
        System.out.println("Average service time for s3: " + totalServiceTimeS3/customer);
        System.out.println("---------------------------------");
        System.out.println("Max length of server 1's queue is: " + (s1a.getMaxQueue() > s1b.getMaxQueue() ? s1a.getMaxQueue() : s1b.getMaxQueue()) );
        System.out.println("Max length of server 2's queue is: " + s2.getMaxQueue());
        System.out.println("Max length of server 3's queue is: " + s3.getMaxQueue());
        System.out.println("---------------------------------");
        System.out.println("Average wait time for server 1a's queue is: " + s1aWait/customer);
        System.out.println("Average wait time for server 1b's queue is: " + s1bWait/customer);
        System.out.println("Average wait time for server 2's queue is: " + s2Wait/customer);
        System.out.println("Average wait time for server 3's queue is: " + s3Wait/customer);
    }

    public static void main(String[] args) throws Exception{
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter how long the simulation should run: ");
        DoubleServerOne s = new DoubleServerOne(sc.next());
        s.run();
        s.printData();
        sc.close();
    }


}