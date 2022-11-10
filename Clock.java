public class Clock {
    //singleton class
    int time;
    Clock t;
    Clock(){
        if(t == null){
            time = 0;
            t = this;
        }
    }
    /**
     * returns current server time
     */
    int getTime(){
        return time;
    }
    /**
     * 
     * @param minutes the number if minutes to increase the time by
     * @return
     */
    int incTime(int minutes){
        time += minutes;
        return time;
    }

    /**
     * returns this clock itself
     */
    Clock getClock(){
        return t;
    }
    

}
