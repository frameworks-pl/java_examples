package pl.frameworks.java_examples;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class CollectionsTests {

    @Test
    public void testFinalList() {

        //final collection is still mutable (it just cannot be reinitialized to different object)
        final List<String> finalList = new ArrayList<>();
        finalList.add("John");
        finalList.add("Jack");

        assert(finalList.size() == 2);

    }
}
