package pl.frameworks.java_examples;

import org.junit.jupiter.api.Test;
import pl.frameworks.java_examples.threads.ValueGenerator;
import pl.frameworks.java_examples.threads.ValueGeneratorImpl;

public class LambdasTests {

    static int generateSomething(ValueGenerator vg) {
        return vg.generate();
    }

    @Test
    public void testOldWay() {

        //Here we are calling implementation of an interface in old way:
        //that is, we create instance that implements the interface and then pass it to
        assert(0 == generateSomething(new ValueGeneratorImpl()));
    }

    @Test
    public void simpleLambda() {

        //lambda without arguments so ()
        assert(10 == generateSomething(() -> { return 10; }));

        //creating lambda of the ValueGenerator interface first and then passing it to generateSomething
        ValueGenerator vg = () -> { return 12;};
        assert(12 == generateSomething(vg));

        //one expression 2+10 so no need for curly brackets around body of lambda and also no need for return keyword
        assert(15 == generateSomething(() -> 5 + 10));

        //and now just the returned value without any actual operation inside lambda
        assert(20 == generateSomething(() -> 20));

    }
}
