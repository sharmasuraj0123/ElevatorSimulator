/**
 * Suraj Sharma
 * Id # 109606910
 * Homework 3 - Queues
 *
 *
 * This class is used to actually carry out the simulation.
 * It is used to carry otu a better version of simulator in which the elevator can carry mutliple passengers.
 * Elevators now also have a direction, up or down.
 * As an elevator passes a floor, all waiting passengers are picked up that want to go in that direction.
 * An elevator moves until it no longer has any requests (sources or destinations) in the direction it was moving.
 * At this point, it is now idle.
 * Elevators can carry an unlimited number of passengers.
 * Idle elevators can move in any direction to pick up requests.
 *
 * The algorithm I used for this is a bit confusing but kind of effective.
 * This algorithm is still not the best way to handle the elevator system but is way more effective than the regular ones.
 * The algorithm is very slow if compare the time complexity since it is O(N^3).
 * Hence is not the effective for larger values.
 *
 * I declared a variable dest and direction for both elevator and request.
 * The direction for the elevator is initialized true as the elevator is on the first floor
 * it will definitely wanna go up in the beginning.
 * Each time the request is passed on the queue the method first looks at its direction.
 * and the entire list of direction is scanned on each floor for every elevator.
 * If the source floor of the request is same as the current floor of the elevator then the passenger gets on the elevator
 * and the wait time period is ended.
 *
 * The variable destination takes care if the destination of the passenger that got on the elevator is farther way in that direction or not.
 * If it is then the destination is changed and the elevator goes there.
 * A helper method compareDest is created to take care whterh the elevator is going up or down.
 * rest all remains the same.
 * In this algorithm only the first request is added as the request of the elevator, all rest of them are only used to calulate
 * the wait time and are not added as the request.
 * The direction of elevator is defined on every floor checking if it is going up or down.
 */
public class OptimalSimulator {

    public static void simulate(double probability, int floors, int numberOfElevators,
                                int simulation) throws IllegalArgumentException {

        if (probability < 0 || probability > 1.0)
            throw new IllegalArgumentException("The probability is out of range");
        if (floors <= 1)
            throw new IllegalArgumentException("The number of floors has to be greater than 1");
        if (numberOfElevators <= 0)
            throw new IllegalArgumentException("The number of elevators has to be greater than 0");
        if (simulation <= 0)
            throw new IllegalArgumentException("The simulation time has to be a positive integer");


        int time;
        RequestQueue requests = new RequestQueue();
        BooleanSource arrival = new BooleanSource(probability);
        Elevator[] elevators = new Elevator[numberOfElevators];

        for (int i = 0; i < numberOfElevators; i++)
            elevators[i] = new Elevator();

        Request temp;
        int totalWaitTime = 0;
        int totalRequests = 0;
        boolean debug = false;


        for (time = 1; time <= simulation; time++) {

            if (arrival.requestArrived()) {

                temp = new Request(floors);
                temp.setTimeEntered(time);
                requests.enqueue(temp);

                if(debug) {
                    System.out.println("added");
                    System.out.println("Source floor: " + temp.getSourceFloor() +
                            " Dest floor: " + temp.getDestinationFloor());
                }
            }

            for (int i = 0; i < numberOfElevators; i++) {

                if (elevators[i].getElevatorState() == Elevator.IDLE && !requests.isEmpty()) {
                    elevators[i].setRequest(requests.dequeue());
                    elevators[i].setElevatorState(Elevator.TO_SOURCE);
                    elevators[i].setDirection(elevators[i].getRequest().getDirection());
                    elevators[i].setDest(elevators[i].getRequest().getDestinationFloor());
                    if(debug)
                    System.out.println(1 + i + "source floor: " + elevators[i].getRequest().getSourceFloor());
                }
                if (elevators[i].getRequest() == null) {

                } else {

                    if (elevators[i].getElevatorState() == Elevator.TO_SOURCE) {
                        if (elevators[i].getCurrentFloor() < elevators[i].getRequest().getSourceFloor()) {
                            elevators[i].setCurrentFloor(elevators[i].getCurrentFloor() + 1);
                            elevators[i].setDirection(true);
                        } else if (elevators[i].getCurrentFloor() > elevators[i].getRequest().getSourceFloor()) {
                            elevators[i].setCurrentFloor(elevators[i].getCurrentFloor() - 1);
                            elevators[i].setDirection(false);
                        }
                    }

                    if (elevators[i].getElevatorState() == Elevator.TO_DESTINATION) {
                        if (elevators[i].getCurrentFloor() < elevators[i].getDest()) {
                            elevators[i].setCurrentFloor(elevators[i].getCurrentFloor() + 1);
                            elevators[i].setDirection(true);
                        } else if (elevators[i].getCurrentFloor() > elevators[i].getDest()) {
                            elevators[i].setCurrentFloor(elevators[i].getCurrentFloor() - 1);
                            elevators[i].setDirection(false);
                        }
                    }

                    for (int j = 0; j < requests.size(); j++) {
                        if ((requests.elementAt(j).getSourceFloor() == elevators[i].getCurrentFloor() &&
                                elevators[i].isDirection() == requests.elementAt(j).getDirection())) {
                            if (elevators[i].getRequest().getDirection() == requests.elementAt(j).getDirection()
                                    || elevators[i].getRequest().getSourceFloor()
                                    - requests.elementAt(j).getDestinationFloor() > 0) {
                                temp = requests.remove(j);
                                if (temp.getDestinationFloor() != temp.getSourceFloor())
                                    elevators[i].setDest(compareDest(elevators[i].getDest(),
                                            temp.getDestinationFloor(), elevators[i].isDirection()));

                                totalWaitTime += time - temp.getTimeEntered();
                                totalRequests++;
                            }
                        }
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

                    if (elevators[i].getCurrentFloor() == elevators[i].getDest()
                            && elevators[i].getElevatorState() == Elevator.TO_DESTINATION) {
                        elevators[i].setElevatorState(Elevator.IDLE);
                    }


                    if (debug) {
                        System.out.println(i + 1 + " current floor: " + elevators[i].getCurrentFloor()
                                + " current state " + elevators[i].getElevatorState() + " Dest floor: "
                                + elevators[i].getDest());
                        System.out.println(totalWaitTime + " " + totalRequests);
                        System.out.println("");

                    }
                }


        }
    }



        double avgWaitTime = (double)totalWaitTime/totalRequests;
        avgWaitTime=(double)Math.round(avgWaitTime * 100) / 100;
        System.out.println("Avg wait time = "
                + avgWaitTime + " seconds");

    }


    public static int compareDest(int dest1, int dest2, boolean direction) {
        if (direction) {
            if (dest1 > dest2)
                return dest1;
            else return dest2;
        } else {
            if (dest1 > dest2)
                return dest2;
            else return dest1;
        }
    }

}