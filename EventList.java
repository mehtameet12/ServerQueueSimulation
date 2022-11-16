import java.util.Iterator;
import java.util.LinkedList;

public class EventList implements Iterable<Event> {         //makes Event List iterable, so can view all Events in Event List
    LinkedList<Event> el;                                   //create structure to store all events      

    public Iterator<Event> iterator() {
        return el.iterator();
    }

    public EventList() {                                    
        el = new LinkedList<Event>();
    }

    public void addEvent(Event e) {                         //add Event to Event List
        el.add(e);
        sort();
    }
    public Event eHead(){                                   //view the head of the Event List
        return el.getFirst();
    }
    public void sort() {                                    //sorts the Events in an Event List
        for (int i = 1; i < el.size(); i++) {
            for (int j = 0; j < i; j++) {
                if (el.get(j).getDuration() > el.get(i).getDuration()) {    //ensures all Events that occur at an earlier time are run first
                    Event tmp = el.get(j);
                    el.set(j, el.get(i));
                    el.set(i, tmp);
                }
                else if (el.get(j).getDuration() == el.get(i).getDuration() &&  //ensures that if there are Arrival Events and Departure Events occur at the same time
                        el.get(j).getEventType()==EventType.ARRIVAL &&          //Departure Events are processed first to remove potential errors
                        el.get(i).getEventType()==EventType.DEPARTURE) {
                    Event tmp = el.get(j);
                    el.set(j, el.get(i));
                    el.set(i, tmp);
                }
                if(el.get(j).getDuration() == el.get(i).getDuration() &&        //ensures that if Arrival Events, Departure Events and End of Simulation Events occur at the same time
                        ((el.get(j).getEventType()==EventType.END&&             //Departure and Arrival Events are run before End of Simulation
                        el.get(i).getEventType()==EventType.DEPARTURE)||
                        el.get(j).getEventType()==EventType.END&&
                        el.get(i).getEventType()==EventType.ARRIVAL)){
                    Event tmp = el.get(j);
                    el.set(j, el.get(i));
                    el.set(i, tmp);
                }

            }
        }
    }

    public Event getEvent() {                                                   //removes the head of the Event List
        return el.removeFirst();
    }
}
