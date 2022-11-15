public class Event {
    int server;
    double duration;
    EventType type;

    public Event(double duration, EventType type){
        this.duration = duration;
        this.type = type;
    }
    public Event(int server, double duration, EventType type){
        this.server = server;
        this.duration = duration;
        this.type = type;
    }

    public double getDuration(){
        return duration;
    }

    public int getServer(){
        return server;
    }

    public String getClassName(){
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

    public EventType getEventType(){
        return type;
    }
}
