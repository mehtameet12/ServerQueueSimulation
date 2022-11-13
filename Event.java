public class Event {
    int server;
    double duration;

    public Event(int server){
        this.server = server;
        this.duration = -1;
    }

    public Event(int server, double duration){
        this.server = server;
        this.duration = duration;
    }

    public double getDuration(){
        return duration;
    }

    public int getServer(){
        return server;
    }

    public String getEventType(){
        return "none";
    }
}
