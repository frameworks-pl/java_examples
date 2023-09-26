package pl.frameworks.java_examples.threads;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;


public class ThreadsTests {

    public static final AtomicLong atomicSum = new AtomicLong();

    private final Sum sumComponent = new Sum();

    @BeforeEach
    public void beforeEach() {
        sumComponent.resetSum();
    }

    @Test
    public void testSimpleThreads() {

        List<SimpleThread> threads = new ArrayList<>();

        int sum = 0;
        for (int i = 0; i < 30; i++) {
            System.out.println(String.format("Adding thread with value=%d", i));
            SimpleThread simpleThread = new SimpleThread(this.sumComponent, i);
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

        List<SimpleThread> threads = new ArrayList<>();
        for (int i=0; i < 10; i++) {
            SimpleThread simpleThread = new SimpleThread(this.sumComponent, i);
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
