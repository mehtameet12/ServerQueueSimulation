public class Event {                                                //Event Class
    int server;                                                     //Stores Server number of this Event
    double duration;                                                //stores at what clock time this event should occur
    EventType type;                                                 //Stores what kind of Event this is (ARRIVAL, DEPARTURE, END)

    public Event(double duration, EventType type){                  //constructor for END OF SIMULATION Event          
        this.duration = duration;
        this.type = type;
        this.server = -1;
    }
    public Event(int server, double duration, EventType type){      //constructor for DEPARTURE and ARRIVAL Events
        this.server = server;
        this.duration = duration;
        this.type = type;
    }

    public double getDuration(){                                    //return clock time Event should occur at
        return duration;
    }

    public int getServer(){                                         //returns the server number
        return server;
    }

    public String getClassName(){                                   //returns the Event Type as a string (helpful for debugging)
        if(type==EventType.END){
            return "End of Simulation";
        }else if(type==EventType.ARRIVAL){
            return "Arrival";
        }else if(type==EventType.DEPARTURE){
            return "Departure";
        }else{
            return "non-existent";
        }
    }

    public EventType getEventType(){                                //return the Event Type (ARRIVAL, DEPARTURE, END)
        return type;
    }
}
