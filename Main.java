import java.util.Scanner;

public class Main {
    
    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter how long the simulation should run: ");
        
        String input = sc.next();

        ParallelServer1Simulation parallelServer = new ParallelServer1Simulation(input);
        RearrangedServerSimulation rearrangedServer = new RearrangedServerSimulation(input);
        UpgradedServer1Simulation enhancedServer = new UpgradedServer1Simulation(input);

        // Parallel Server Simulation
        parallelServer.run();
        parallelServer.printData();
        System.out.println("-----------------------------------------------------------------------");
    
        // Rearranged Server Simulation 
        rearrangedServer.run();
        rearrangedServer.printData();
        System.out.println("-----------------------------------------------------------------------");

        // Upgraded Server 1
        enhancedServer.run();
        enhancedServer.printData();

        sc.close();
    }


}
