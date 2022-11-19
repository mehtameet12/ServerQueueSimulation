import java.util.Random;                                                //add seed

public class GenerateTimes {
    private static Random rnd;                                          //random object

    private static boolean arrivalDebug = false;                        //debuggers when turned on helps validate program
    private static boolean s1Debug = false;
    private static boolean s2Debug = false;
    private static boolean s3Debug = false;

    public static double generateArrival(){                             //generate interarrival times
        rnd = new Random();
        double x = 0 + (1 - 0) * rnd.nextDouble(); // Generate random value between 0 and 1
        double value = 97.2 * Math.log(1/(1-x));

        if(value<=35){
            if(arrivalDebug)System.out.println("Generate interarrival time: "+15);
            return 15;
        }else if(value <=50){
            if(arrivalDebug)System.out.println("Generate interarrival time: "+45);
            return 45;
        }else if(value <=60){
            if(arrivalDebug)System.out.println("Generate interarrival time: "+75);
            return 75;
        }else if(value <=70){
            if(arrivalDebug)System.out.println("Generate interarrival time: "+105);
            return 105;
        }else if(value <=75){
            if(arrivalDebug)System.out.println("Generate interarrival time: "+135);
            return 135;
        }else if(value <=84){
            if(arrivalDebug)System.out.println("Generate interarrival time: "+165);
            return 165;
        }else if(value <=89){
            if(arrivalDebug)System.out.println("Generate interarrival time: "+195);
            return 195;
        }else if(value <=91){
            if(arrivalDebug)System.out.println("Generate interarrival time: "+225);
            return 225;
        }else if(value <=94){
            if(arrivalDebug)System.out.println("Generate interarrival time: "+255);
            return 255;
        }else if(value <=95){
            if(arrivalDebug)System.out.println("Generate interarrival time: "+285);
            return 285;
        }else if(value <=97){
            if(arrivalDebug)System.out.println("Generate interarrival time: "+375);
            return 375;
        }else if(value <=98){
            if(arrivalDebug)System.out.println("Generate interarrival time: "+405);
            return 405;
        }else if(value <=99){
            if(arrivalDebug)System.out.println("Generate interarrival time: "+435);
            return 435;
        }
        if(arrivalDebug)System.out.println("Generate interarrival time: "+495);
        return 495;    
    }

    public static double generateS1(){                                                  //generate service times for server 1
        rnd = new Random();
        double x = 0 + (1 - 0) * rnd.nextDouble(); // Generate random value between 0 and 1
        double value = 91.65 * Math.log(1/(1-x));

        if(value<=15){
            if(s1Debug)System.out.println("Generate server 1 time: "+15);
            return 15;
        }else if(value <=45){
            if(s1Debug)System.out.println("Generate server 1 time: "+45);
            return 45;
        }else if(value <=64){
            if(s1Debug)System.out.println("Generate server 1 time: "+75);
            return 75;
        }else if(value <=76){
            if(s1Debug)System.out.println("Generate server 1 time: "+105);
            return 105;
        }else if(value <=84){
            if(s1Debug)System.out.println("Generate server 1 time: "+135);
            return 135;
        }else if(value <=91){
            if(s1Debug)System.out.println("Generate server 1 time: "+165);
            return 165;
        }else if(value <=93){
            if(s1Debug)System.out.println("Generate server 1 time: "+195);
            return 195;
        }else if(value <=94){
            if(s1Debug)System.out.println("Generate server 1 time: "+225);
            return 225;
        }else if(value <=95){
            if(s1Debug)System.out.println("Generate server 1 time: "+255);
            return 255;
        }else if(value <=97){
            if(s1Debug)System.out.println("Generate server 1 time: "+315);
            return 315;
        }else if(value <=98){
            if(s1Debug)System.out.println("Generate server 1 time: "+465);
            return 465;
        }else if(value <=99){
            if(s1Debug)System.out.println("Generate server 1 time: "+495);
            return 495;
        }
        if(s1Debug)System.out.println("Generate server 1 time: "+525);
        return 525;
    }

    public static double generateS2(){                                                  //generate service times for server 2
        rnd = new Random(); 
        double x = 0 + (1 - 0) * rnd.nextDouble(); // Generate random value between 0 and 1
        double value = 57.3 * Math.log(1/(1-x));

        if(value<=15){
            if(s2Debug)System.out.println("Generate server 2 time: "+15);
            return 15;
        }else if(value <=62){
            if(s2Debug)System.out.println("Generate server 2 time: "+45);
            return 45;
        }else if(value <=88){
            if(s2Debug)System.out.println("Generate server 2 time: "+75);
            return 75;
        }else if(value <=97){
            if(s2Debug)System.out.println("Generate server 2 time: "+105);
            return 105;
        }
        if(s2Debug)System.out.println("Generate server 2 time: "+165);
        return 165;
        
    }

    public static double generateS3(){                                                  //generate service times for server 3
        rnd = new Random();
        double x = 0 + (1 - 0) * rnd.nextDouble(); // Generate random value between 0 and 1
        double value = 31.2 * Math.log(1/(1-x));

        if(value<=55){
            if(s3Debug)System.out.println("Generate server 3 time: "+15);
            return 15;
        }else if(value <=91){
            if(s3Debug)System.out.println("Generate server 3 time: "+45);
            return 45;
        }
        if(s3Debug)System.out.println("Generate server 3 time: "+75);
        return 75;
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
