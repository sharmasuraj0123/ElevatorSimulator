
import java.util.Vector;

/**
 * Suraj Sharma
 * Id # 109606910
 * Homework 3 - Queues
 *
 * This class extends Vector Class in Java
 * Hence it can use all the methods defined in that class.
 * The additional methods enqueue and dequeue are added to make it a queue.
 */
public class RequestQueue extends Vector<Request>{

    /**
     *
     * @param newRequest
     * adds the element to the queue.
     */
    public void enqueue(Request newRequest){
        add(newRequest);

    }

    /**
     *
     * @return
     *removes the last element and returns it.
     */
    public Request dequeue(){
        return remove(0);
    }

}
