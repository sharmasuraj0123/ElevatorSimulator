/**
 * Suraj Sharma
 * Id # 109606910
 * Homework 3 - Queues
 *
 *This class handles the request sent in by the person for the elevator.
 * It has all integer instance variables to take care of the destination floor
 * and the floor user is at
 */
public class Request {
    private int sourceFloor;
    private int destinationFloor;
    private int timeEntered;
    private boolean direction;

    /**
     * Constructor for the request which generates a random source
     * and destination floor
     * The random values are between 1 and the number of floors in the building, inclusive.
     *
     * @param numberOfFloors
     * The number of floors in the building.
     */
    public Request(int numberOfFloors){
        sourceFloor = (int) (Math.random()*numberOfFloors) +1;
        destinationFloor = (int) (Math.random()*numberOfFloors) +1;

        if(sourceFloor - destinationFloor>0)
            direction = false;
        else direction =true;

    }

    /**
     * This method is only used for the extra credit part of the assignment
     * It is used to determine the direction in which passenger wants to go.
     * True for up and false for down.
     *
     * @return the direction in which the user wants to go
     */
    public boolean getDirection() {
        return direction;
    }

    /**
     *
     * @return the accessor method for the source floor.
     */
    public int getSourceFloor() {
        return sourceFloor;
    }

    /**
     *
     * @param sourceFloor
     * The mutator method for the source floor.
     */
    public void setSourceFloor(int sourceFloor) {
        this.sourceFloor = sourceFloor;
    }

    /**
     *
     * @return
     * the accessor method for the destination floor
     */
    public int getDestinationFloor() {
        return destinationFloor;
    }

    /**
     *
     * @param destinationFloor
     * the mutator method for the destination.
     */
    public void setDestinationFloor(int destinationFloor) {
        this.destinationFloor = destinationFloor;
    }

    /**
     *
     * @return
     * the time at which the request was placed.
     */
    public int getTimeEntered() {
        return timeEntered;
    }

    /**
     *
     * @param timeEntered
     * accessor method for the time entered method.
     */
    public void setTimeEntered(int timeEntered) {
        this.timeEntered = timeEntered;
    }
}
