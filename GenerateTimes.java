import java.util.Random;                                                //add seed

public class GenerateTimes {
    private static Random rnd;                                          //random object

    private static boolean arrivalDebug = false;                        //debuggers when turned on helps validate program
    private static boolean s1Debug = false;
    private static boolean s2Debug = false;
    private static boolean s3Debug = false;

    public static int generateArrival(){                             //generate interarrival times
        rnd = new Random(0);
        double x = 0 + (1 - 0) * rnd.nextDouble(); // Generate random value between 0 and 1
        double value = 97.2 * Math.log(1/(1-x));
        return (int) value;
    }

    public static int generateS1(){                                                  //generate service times for server 1
        rnd = new Random(0);
        double x = 0 + (1 - 0) * rnd.nextDouble(); // Generate random value between 0 and 1
        double value = 91.65 * Math.log(1/(1-x));
        return (int) value;
    }

    public static int generateS2(){                                                  //generate service times for server 2
        rnd = new Random(0); 
        double x = 0 + (1 - 0) * rnd.nextDouble(); // Generate random value between 0 and 1
        double value = 57.3 * Math.log(1/(1-x));
        return (int) value;
    }

    public static int generateS3(){                                                  //generate service times for server 3
        rnd = new Random(0);
        double x = 0 + (1 - 0) * rnd.nextDouble(); // Generate random value between 0 and 1
        double value = 31.2 * Math.log(1/(1-x));
        return (int) value;
    }
    
    public static void main(String[] args){
        Random rnd = new Random();
        int x = rnd.nextInt(100-1+1)+1;
        while(x!=100){
            x = rnd.nextInt(100-1+1)+1;
            System.out.println(x);

        }        
    }
}
