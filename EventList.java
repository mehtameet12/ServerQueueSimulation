import java.util.Iterator;
import java.util.LinkedList;

public class EventList implements Iterable<Event> {
    LinkedList<Event> el;

    public Iterator<Event> iterator() {
        return el.iterator();
    }

    public EventList() {
        el = new LinkedList<Event>();
    }

    public void addEvent(Event e) {
        el.add(e);
        sort();
    }

    public void sort() {
        for (int i = 1; i < el.size(); i++) {
            for (int j = 0; j < i; j++) {
                if (el.get(j).getDuration() > el.get(i).getDuration()) {
                    Event tmp = el.get(j);
                    el.set(j, el.get(i));
                    el.set(i, tmp);
                }
                else if (el.get(j).getDuration() == el.get(i).getDuration() &&
                        el.get(j).getEventType().equals("arrival") &&
                        el.get(i).getEventType().equals("departure")) {
                    Event tmp = el.get(j);
                    el.set(j, el.get(i));
                    el.set(i, tmp);
                }

            }
        }
    }

    public Event getEvent() {
        return el.removeFirst();
    }
}
