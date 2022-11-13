public class EndEvent extends Event{
    public EndEvent(double duration){
        super(duration);
    }
    public String getEventType(){
        return "end";
    }
}
