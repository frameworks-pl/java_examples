package pl.frameworks.java_examples.threads;


import org.springframework.stereotype.Component;
import java.util.concurrent.atomic.AtomicInteger;


public class Sum {

    protected volatile int sum;

    public synchronized void collect(int value) {
        sum += value;
        System.out.println(String.format("Adding value %d, total is now %d", value, sum));
    }

    public synchronized int getSum() {
        return sum;
    }

    public synchronized void resetSum() {
        sum = 0;
    }
}
