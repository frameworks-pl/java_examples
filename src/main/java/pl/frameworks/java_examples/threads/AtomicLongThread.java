package pl.frameworks.java_examples.threads;

import java.util.concurrent.atomic.AtomicLong;

public class AtomicLongThread extends Thread {

    protected AtomicLong atomicLong;

    protected volatile int value;

    public AtomicLongThread(AtomicLong atomicLong, int value) {
        this.atomicLong = atomicLong;
        this.value = value;
    }

    @Override
    public void run() {

        synchronized(atomicLong) {
            atomicLong.set(atomicLong.get() + value);
        }
    }

}
