/**
 * Suraj Sharma
 * Id # 109606910
 * Homework 3 - Queues
 *
 * This class elevator handles the elevator and all the functions done by it.
 * It also takes care whether the elevator is idle or heading towards source or destination.
 */
public class Elevator {

    private int currentFloor;
    private int elevatorState;
    private Request request;
    private boolean direction;
    private int dest;

    public static final int IDLE = 0;
    public static final int TO_SOURCE =1;
    public static final int TO_DESTINATION = -1;

    /**
     * The default constructor for the elevator.
     * The boolean variable direction is only used for the extra credit part of the assingment
     * to take care of the direction of the elevator.
     */
    public Elevator(){
        request =null;
        elevatorState = IDLE;
        currentFloor = 1;
        direction = true;
    }

    /**
     *  A custom method created to take care of the direction elevator is going in
     * @return
     * the direction of the elevator.
     * true = up and false = down.
     */
    public boolean isDirection() {
        return direction;
    }

    /**
     *
     * @param direction
     * mutator method for the direction.
     */
    public void setDirection(boolean direction) {
        this.direction = direction;
    }

    /**
     * The variable destination is only used for the extra credit part of the assignment
     * to handle multiple destinations.
     *
     * @return
     * The final destination of the elevator.
     *
     */
    public int getDest() {
        return dest;
    }

    /**
     * The mutator method for the destination.
     * @param dest
     *
     */
    public void setDest(int dest) {
        this.dest = dest;
    }

    /**
     * The accessor method for the variable currentFloor
     * @return
     * the current floor elevator is at.
     */
    public int getCurrentFloor() {
        return currentFloor;
    }

    /**
     * The mutator method for the variable currentFloor.
     * @param currentFloor
     */
    public void setCurrentFloor(int currentFloor) {
        this.currentFloor = currentFloor;
    }

    /**
     *
     * @return
     * the state currently elevator is in.
     */
    public int getElevatorState() {
        return elevatorState;
    }

    /**
     * The acccesor method for the elevator's current state
     *
     * @param elevatorState
     *
     */
    public void setElevatorState(int elevatorState) {
        this.elevatorState = elevatorState;
    }

    /**
     *
     * @return
     * the current request the elevator is holding.
     */
    public Request getRequest() {
        return request;
    }

    /**
     * The accesor method for the request.
     * @param request
     */
    public void setRequest(Request request) {
        this.request = request;
    }
}
