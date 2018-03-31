import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import java.util.Scanner;

/**
 * /**
 * Suraj Sharma
 * Id # 109606910
 * .
 * This class only contains the main method and calls the simulation with an option of either reqular or optimal.
 */
public class Analyzer {

    public static void main(String [] args) {



        // Simulator.simulate(1.0,5,2,10);
         //OptimalSimulator.simulate(1.0,5,2,10);

        Scanner s = new Scanner(System.in);

        System.out.println("Welcome to Simulator");

        System.out.println("Menu Option: ");
        System.out.println("A - for Regular Simulator.");
        System.out.println("B - for Optimal Simulator");
        System.out.println("BOTH - type both for both of them simultaneously");
        System.out.print("Your Choice: ");
        String input = s.next();

            System.out.print("Please enter the probability of arrival for Requests: ");
            double probability = s.nextDouble();
            System.out.print("Please enter the number of floors: ");
            int floors = s.nextInt();
            System.out.print("Please enter the number of elevators: ");
            int numberOfElevators = s.nextInt();
            System.out.print("Please enter the length of the simulation (in time units): ");
            int simulation = s.nextInt();

        System.out.println();


            try {

                if(input.equalsIgnoreCase("a")) {
                    System.out.println("Result with regular Simulation");
                    Simulator.simulate(probability, floors, numberOfElevators, simulation);
                }

                else if(input.equalsIgnoreCase("b")) {
                    System.out.println("Result with Optimal Simulation");
                    OptimalSimulator.simulate(probability, floors, numberOfElevators, simulation);
                }
                else if(input.equalsIgnoreCase("both")){
                    System.out.println("Result with regular Simulation");
                    Simulator.simulate(probability, floors, numberOfElevators, simulation);
                    System.out.println();
                    System.out.println("Result with Optimal Simulation");
                    OptimalSimulator.simulate(probability,floors,numberOfElevators,simulation);
                }
                else
                    System.out.println("Invalid Input");


            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                System.out.println(" Hence, No Simulation can occur");
            }


    }
}
