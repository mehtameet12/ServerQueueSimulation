import java.util.Random;

public class GenerateTimes {
    private static Random rnd;
    public static double generateArrival(){
        rnd = new Random();
        int value = rnd.nextInt(100-1+1)+1;
        if(value<=35){
            return 15;
        }else if(value <=50){
            return 45;
        }else if(value <=60){
            return 75;
        }else if(value <=70){
            return 105;
        }else if(value <=75){
            return 135;
        }else if(value <=84){
            return 165;
        }else if(value <=89){
            return 195;
        }else if(value <=91){
            return 225;
        }else if(value <=94){
            return 255;
        }else if(value <=95){
            return 285;
        }else if(value <=97){
            return 375;
        }else if(value <=98){
            return 405;
        }else if(value <=99){
            return 435;
        }
        return 495;
        
    }
    public static double generateS1(){
        rnd = new Random();
        int value = rnd.nextInt(100-1+1)+1;
        if(value<=15){
            return 15;
        }else if(value <=45){
            return 45;
        }else if(value <=64){
            return 75;
        }else if(value <=76){
            return 105;
        }else if(value <=84){
            return 135;
        }else if(value <=91){
            return 165;
        }else if(value <=93){
            return 195;
        }else if(value <=94){
            return 225;
        }else if(value <=95){
            return 255;
        }else if(value <=97){
            return 315;
        }else if(value <=98){
            return 465;
        }else if(value <=99){
            return 495;
        }
        return 525;
    }
    public static double generateS2(){
        rnd = new Random();
        int value = rnd.nextInt(100-1+1)+1;
        if(value<=15){
            return 15;
        }else if(value <=62){
            return 45;
        }else if(value <=88){
            return 75;
        }else if(value <=97){
            return 105;
        }
        return 165;
        
    }
    public static double generateS3(){
        rnd = new Random();
        int value = rnd.nextInt(100-1+1)+1;
        if(value<=55){
            return 15;
        }else if(value <=91){
            return 45;
        }
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
