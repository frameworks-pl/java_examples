package pl.frameworks.java_examples.threads;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest //thnaks to this annotation ApplicationContext will be loaded
public class ThreadsTests {

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private Sum sumComponent;

    @BeforeEach
    public void beforeEach() {
        applicationContext.getBean(Sum.class).resetSum();
    }

    @Test
    public void testSimpleThreads() {

        List<SimpleThread> threads = new ArrayList<>();

        int sum = 0;
        for (int i = 0; i < 30; i++) {
            SimpleThread simpleThread = new SimpleThread(applicationContext.getBean(Sum.class), i);
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

        //given
        List<SimpleThread> threads = new ArrayList<>();
        int sum = 0;
        for (int i=0; i < 10; i++) {
            SimpleThread simpleThread = new SimpleThread(applicationContext.getBean(Sum.class), i);
            simpleThread.start();
            threads.add(simpleThread);
        }

        //when
        for (SimpleThread thread : threads) {
            thread.requestStop();
        }

        //then
        assert(sum == 0);

    }
}
