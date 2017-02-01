/**
 * Xia Lin
 * 110732381
 * xia.lin@stonybrook.edu
 * Assignment 4
 * CSE214-01
 * Charles Chen
 * Shilpi Bhattacharyya
 */
package homework4;

import java.util.ArrayList;

public class DownloadQueue {

    private int front;
    private int rear;
    private ArrayList<DownloadJob> queueList;
    /**
     * downloadQueue constructor
     * Set front and rear to empty
     * initialized the ArrayList of DownloadJob object
     */
    public DownloadQueue() {
        front = -1;
        rear = -1;
        queueList = new ArrayList<DownloadJob>();
    }
    /**
     * Add DownloadJob object to ArrayList
     * @param d 
     * the object to be add
     */
    public void enqueued(DownloadJob d) {
        if (rear == -1) {
            front = 0;
        }
        queueList.add(d);
        rear++;
    }
    /**
     * Remove DownloadJob object from ArrayList
     * @return
     * the removed object
     * @throws EmptyQueueException
     * if the list is empty
     */
    public DownloadJob dequeued() throws EmptyQueueException {
        if (rear == -1) {
            throw new EmptyQueueException();
        }
        DownloadJob djTemp = queueList.get(front);
        if (rear == 0) {
            queueList.remove(front--);
        } else {
            queueList.remove(front);
        }
        rear--;
        return djTemp;
    }
    /**
     * Look the object at front position
     * @return
     * the front position object
     * @throws EmptyQueueException
     * if the list is empty
     */
    public DownloadJob peek() throws EmptyQueueException {
        if (rear == -1) {
            throw new EmptyQueueException();
        }
        return queueList.get(front);
    }
    /**
     * Check if the list is empty
     * @return
     * True, if the list is empty. False otherwise
     */
    public boolean isEmpty() {
        if (queueList.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }
    /**
     * Print all information of the current Queue
     * @return
     * string of the all information
     */
    public String toString() {
        String print = "";
        for (int i = 0; i < queueList.size(); i++) {
            print += "[#" + queueList.get(i).getID() + ":" + queueList.get(i).getDownloadSize() + "Mb] ";
        }
        return print;
    }

}
