import java.util.Iterator;
import java.util.LinkedList;

public class EventList implements Iterable<Event> { // makes Event List iterable, so can view all Events in Event List
    LinkedList<Event> el; // create structure to store all events

    public Iterator<Event> iterator() {
        return el.iterator();
    }

    public EventList() {
        el = new LinkedList<Event>();
    }

    public void addEvent(Event e) { // add Event to Event List
        place(e);
    }

    public Event eHead() { // view the head of the Event List
        return el.getFirst();
    }

    public Event getEvent() { // removes the head of the Event List
        return el.removeFirst();
    }

    private void place(Event e) {                                               //positions events in increasing clock order, where departures occur first, then arrivals and then end of simulation events
        for (int i = 0; i < el.size(); i++) {
            if (e.getDuration() <= el.get(i).getDuration()) {                   //if the current event's duration is less than the next
                if (e.getEventType() == EventType.DEPARTURE) {                  //and it is a departure event, add the event to this position
                    el.add(i, e);                                               //ensures that departure events have priority over other events of the same clock value
                    return;
                }                                                               //this if statement ensures that the arrival event is placed after a departure event of the same clock value
                else if (e.getEventType() == EventType.ARRIVAL) {               //check if event is an arrival event
                    while (el.get(i).getDuration() == e.getDuration()           //while the next event has the same clock number as the current event
                            && el.get(i).getEventType() == EventType.DEPARTURE) {//and the next event is a departure event
                            i++;                                                 //increment counter
                            if(i==el.size()){                                   //if we reach the end of the list, add event to the end
                                el.add(e);
                                return;
                            }
                    }                                                       
                    el.add(i, e);                                               //once a non-departure event is found or the next event has a larger clock value             
                    return;                                                     //insert arrival event before them
                }
                else{                                                           //this if statement ensures that the End of Simulation event is placed after arrivals and departures of the same clock value
                    while (el.get(i).getDuration() == e.getDuration()           //while the clock values are the same and
                            && (el.get(i).getEventType() == EventType.DEPARTURE ||el.get(i).getEventType()==EventType.ARRIVAL)) { //the next event is either a departure or arrival event
                        i++;                                                    //increment the counter
                        if(i==el.size()){                                       //if we reach the end of the list, add event to the end
                            el.add(e);
                            return;
                        }
                    }
                    el.add(i, e);                                               //insert the event
                    return;
                }
            }
        }                                                                       //if we cannot find an event with a larger clock value, then this must be the event that occurs last
        el.add(e);                                                              //add it to the end of the Event List       
    }

    public static void main(String[] args) {
        EventList el = new EventList();                                         //testing the placement of events for Event List
        el.addEvent(new Event(2, 10, EventType.DEPARTURE));
        el.addEvent(new Event(2, 10, EventType.DEPARTURE));
        el.addEvent(new Event(2, 10, EventType.DEPARTURE));
        el.addEvent(new Event(2, 10, EventType.END));
        el.addEvent(new Event(2, 10, EventType.DEPARTURE));
        el.addEvent(new Event(2, 10, EventType.ARRIVAL));
        el.addEvent(new Event(2, 10, EventType.END));
        el.addEvent(new Event(2, 10, EventType.ARRIVAL));
        el.addEvent(new Event(2, 10, EventType.ARRIVAL));
        el.addEvent(new Event(2, 10, EventType.DEPARTURE));
        

        for (Event e : el) {
            System.out.println("Next event is " + e.getEventType() + " at " + e.getDuration());
        }
        
        System.out.println(el.el.size());
    }
}
