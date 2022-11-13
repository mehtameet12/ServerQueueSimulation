public class ArrivalEvent extends Event {
    public ArrivalEvent(int server, double duration){
        super(server, duration);
    }
    public String getEventType(){
        return "arrival";
    }
}
