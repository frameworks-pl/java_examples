package pl.frameworks.java_examples.threads;

import org.springframework.beans.factory.annotation.Autowired;

public class SimpleThread extends Thread {

    //component holding the sum
    protected final Sum sum;

    //value we want to add to the sum
    protected final int value;

    //if true thread should terminate
    protected boolean stopRequested;


    public SimpleThread(Sum sum, int value) {
        this.sum = sum;
        this.value = value;
        this.stopRequested = false;
    }

    public synchronized void requestStop() {
        this.stopRequested = true;
    }

    public synchronized boolean isStopRequested() {
        return this.stopRequested;
    }

    public void run() {
        try {
            sleep(2000);
        } catch (InterruptedException e) {

        }

        if (isStopRequested() == false) {
            System.out.println(String.format("Running with value %d", value));
            sum.collect(this.value);
        }
    }
}
