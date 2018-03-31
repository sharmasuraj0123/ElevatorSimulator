/**
 * Suraj Sharma
 * Id # 109606910
 * Homework 3 - Queues
 *
 * This class is used to handle the probability of request to be placed.
 * Has only one instance variable.
 */
public class BooleanSource {

    private double probability;

    /**
     *
     * @param p the probability of an event to occur.
     * @throws IllegalArgumentException
     * it the value entered by user in not within range.
     */
    public BooleanSource(double p) throws IllegalArgumentException {
        if (p < 0.0 || p > 1.0)
            throw new IllegalArgumentException("Probability out of range.");
        probability = p;
    }

    /**
     *
     * @return
     * Returns whether a request has arrived or not.
     */
    public boolean requestArrived() {
        return (Math.random() < probability);
    }

}
