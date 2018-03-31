/**
 * Suraj Sharma
 * Id # 109606910
 * Homework 3 - Queues
 *
 * This class is used to actually carry out the simulation.
 * This class contains a single static method called simulate that accepts the following four parameters,
 * carries out the simulation, and prints the results:
 */
public class Simulator {

    public static void simulate(double probability , int floors ,int numberOfElevators ,
                                int simulation) throws IllegalArgumentException{
        // checks whether all the parameters are in the range or not.

        if(probability <0 || probability>1.0)
            throw new IllegalArgumentException("The probability is out of range");
        if(floors <=1)
            throw new IllegalArgumentException("The number of floors has to be greater than 1");
        if(numberOfElevators <=0)
            throw new IllegalArgumentException("The number of elevators has to be greater than 0");
        if(simulation<=0)
            throw new IllegalArgumentException("The simulation time has to be a positive integer");

        int time;
        RequestQueue requests = new RequestQueue();
        BooleanSource arrival = new BooleanSource(probability);
        Elevator [] elevators = new Elevator[numberOfElevators];

        for(int i =0;i<numberOfElevators;i++)
            elevators[i]=new Elevator();



        Request r; // a temperary variable used to store and save values.
        int totalWaitTime =0;
        int totalRequests =0;
        boolean debug = false;


        for (time =1; time<= simulation;time++){

            if(arrival.requestArrived()) {

                r = new Request(floors);
                r.setTimeEntered(time);
                requests.enqueue(r);

                if(debug) {
                    System.out.println("added");
                    System.out.println("Source floor: " + r.getSourceFloor() +
                            " Dest floor: " + r.getDestinationFloor());
                }
            }

            for(int i =0 ; i<numberOfElevators;i++) {
                if (elevators[i].getElevatorState() == Elevator.IDLE && !requests.isEmpty()) {
                    elevators[i].setRequest(requests.dequeue());
                    elevators[i].setElevatorState(Elevator.TO_SOURCE);
                    if(debug)
                    System.out.println( 1+i+ "source floor: " + elevators[i].getRequest().getSourceFloor());
                }

                if(elevators[i].getRequest()==null){

                }
                else {

                    if (elevators[i].getElevatorState() == Elevator.TO_DESTINATION) {
                        if (elevators[i].getCurrentFloor() <elevators[i].getRequest().getDestinationFloor())
                            elevators[i].setCurrentFloor(elevators[i].getCurrentFloor() + 1);
                        else if(elevators[i].getCurrentFloor() > elevators[i].getRequest().getDestinationFloor())
                            elevators[i].setCurrentFloor(elevators[i].getCurrentFloor() - 1);
                    }

                    if (elevators[i].getCurrentFloor() == elevators[i].getRequest().getSourceFloor()
                            && elevators[i].getElevatorState() == Elevator.TO_SOURCE) {
                        elevators[i].setElevatorState(Elevator.TO_DESTINATION);
                        totalWaitTime += time - elevators[i].getRequest().getTimeEntered();
                        totalRequests++;
                        if(debug)
                        System.out.println(i + 1 + "destination floor: " +
                                elevators[i].getRequest().getDestinationFloor());
                    }

                    if (elevators[i].getCurrentFloor() == elevators[i].getRequest().getDestinationFloor()
                            && elevators[i].getElevatorState() == Elevator.TO_DESTINATION) {
                        elevators[i].setElevatorState(Elevator.IDLE);
                    }
                    if (elevators[i].getElevatorState() == Elevator.TO_SOURCE) {
                        if (elevators[i].getCurrentFloor() < elevators[i].getRequest().getSourceFloor())
                            elevators[i].setCurrentFloor(elevators[i].getCurrentFloor() + 1);
                        else if(elevators[i].getCurrentFloor() >elevators[i].getRequest().getSourceFloor())
                            elevators[i].setCurrentFloor(elevators[i].getCurrentFloor() - 1);

                        if (elevators[i].getCurrentFloor() == elevators[i].getRequest().getSourceFloor()) {
                            elevators[i].setElevatorState(Elevator.TO_DESTINATION);
                            totalWaitTime += 1+ time - elevators[i].getRequest().getTimeEntered();
                            totalRequests++;
                            if(debug)
                            System.out.println(i + 1 + "destination floor: " +
                                    elevators[i].getRequest().getDestinationFloor());
                        }

                    }
                }
                if (debug) {
                    System.out.println(i+1 + " current floor: " + elevators[i].getCurrentFloor()
                                + " current state " + elevators[i].getElevatorState());
                    System.out.println(totalWaitTime + " " + totalRequests);
                    System.out.println("");
                    }


            }




        }
        double avgWaitTime = (double)totalWaitTime/totalRequests;
        avgWaitTime=(double)Math.round(avgWaitTime * 100) / 100;
        System.out.println("Avg wait time = "
                + avgWaitTime + " seconds");

    }
}
