package pl.frameworks.java_examples;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.frameworks.java_examples.threads.AtomicLongThread;
import pl.frameworks.java_examples.threads.SimpleThread;
import pl.frameworks.java_examples.threads.Sum;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class ThreadsTests {

    public static final AtomicLong atomicSum = new AtomicLong();

    @Test
    public void testSimpleThreads() {

        List<SimpleThread> threads = new ArrayList<>();
        Sum sumComponent = new Sum();

        int sum = 0;
        for (int i = 0; i < 30; i++) {
            SimpleThread simpleThread = new SimpleThread(sumComponent, i);
            simpleThread.start();
            threads.add(simpleThread);
            sum += i;
        }

        try {
            for (SimpleThread simpleThread : threads) {
                simpleThread.join();
            }
        } catch (Exception e) {

        }

        assert(sum == sumComponent.getSum());

    }

    @Test
    public void testStopThread() {

        Sum sumComponent = new Sum();

        List<SimpleThread> threads = new ArrayList<>();
        for (int i=0; i < 10; i++) {
            SimpleThread simpleThread = new SimpleThread(sumComponent, i);
            simpleThread.start();
            threads.add(simpleThread);
        }

    }

    @Test
    public void testAtoimicLong() {

        List<AtomicLongThread> threads = new ArrayList<>();

        int sum = 0;
        for (int i = 0; i < 30; i++) {
            AtomicLongThread atomicLongThread = new AtomicLongThread(atomicSum, i);
            atomicLongThread.start();
            threads.add(atomicLongThread);
            sum += i;
        }

        try {
            for (AtomicLongThread atomicLongThread : threads) {
                atomicLongThread.join();
            }
        } catch (Exception e) {

        }

        assert(sum == atomicSum.get());

    }
}
