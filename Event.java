public class Event {
    eventName name;
    int time;
    Event(eventName en, int time){
        this.name = en;
        this.time = time;
    }
    eventName getName(){
        return name;
    }
}
