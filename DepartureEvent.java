public class DepartureEvent extends Event {
    public DepartureEvent(int server, double duration){
        super(server, duration);
    }
    public String getEventType(){
        return "departure";
    }
}
