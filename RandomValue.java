import java.util.NavigableMap;
import java.util.Random;
import java.util.TreeMap;
public class RandomValue {
    int randomServer1;
    int randomServer2;
    int randomServer3;
    int randomInterArrival;
    Random randInt = new Random();
    // public static void main(String args[]){
    //     RandomValue r = new RandomValue();
    //     System.out.println(r.getRandomValueForServer3());
    // }
    public int getRandomValueForServer1(){
        randomServer1 = randInt.nextInt(100);
        randomServer1 = randomServer1+1;
        NavigableMap<Integer, Integer> map = new TreeMap<Integer, Integer>();
        map.put(1,15);
        map.put(16,45);
        map.put(46,75);
        map.put(65,105);
        map.put(77,135);
        map.put(85,165);
        map.put(92,195);
        map.put(94,225);
        map.put(95,255);
        map.put(96,315);
        map.put(98,465);
        map.put(99,495);
        map.put(100,525);
        return map.floorEntry(randomServer1).getValue();
    }

    public int getRandomValueForServer2(){
        randomServer2 = randInt.nextInt(100);
        randomServer2 = randomServer2+1;
        NavigableMap<Integer, Integer> map = new TreeMap<Integer, Integer>();
        map.put(1,15);
        map.put(16,45);
        map.put(63,75);
        map.put(89,105);
        map.put(98,165);
        return map.floorEntry(randomServer2).getValue();
    }

    public int getRandomValueForServer3(){
        randomServer3 = randInt.nextInt(100);
        randomServer3 = randomServer3+1;
        NavigableMap<Integer, Integer> map = new TreeMap<Integer, Integer>();
        map.put(1,15);
        map.put(56,45);
        map.put(92,75);
        return map.floorEntry(randomServer3).getValue();
    }

    public int getRandomInterArrival(){
        randomInterArrival = randInt.nextInt(100);
        randomInterArrival = randomInterArrival+1;
        NavigableMap<Integer, Integer> map = new TreeMap<Integer, Integer>();
        map.put(1,15);
        map.put(36,45);
        map.put(51,75);
        map.put(61,105);
        map.put(71,135);
        map.put(76,165);
        map.put(85,195);
        map.put(90,225);
        map.put(92,255);
        map.put(95,285);
        map.put(96,375);
        map.put(98,405);
        map.put(99,435);
        map.put(100,495);
        return map.floorEntry(randomInterArrival).getValue();
    }

}
