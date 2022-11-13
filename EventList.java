import java.util.LinkedList;

public class EventList {
    LinkedList<Event> el;

    public EventList(){
        el = new LinkedList<Event>();
    }
    public void add(Event e){
        el.add(e);
    }
    public void sort(){

    }
    public Event getEvent(){
        return el.removeFirst();
        
    }
}
