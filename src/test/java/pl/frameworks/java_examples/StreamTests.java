package pl.frameworks.java_examples;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.nio.file.*;

public class StreamTests {

    @Test
    public void rangeTest() {

        //doing sum from 1 to 10 with good old for loop
        int forLoopSum = 0;
        for (int i = 1; i < 10; i++) {
            forLoopSum += i;
        }

        //doing sum from 1 to 10 using IntStream (the same as in loop, 10 will NOT be included in range)
        int rangeSum = IntStream.range(1, 10).sum();

        assert(forLoopSum == rangeSum);
    }

    @Test
    public void skipTest() {

        //doing sum of 1 to 10 BUT skippping elements lower or queal than 5
        int forSkipSum = 0;
        for (int i = 1; i < 10; i++) {
            if (i > 5) {
                forSkipSum += i;
            }
        }

        //doing sum from 1 to 10 using IntStream and skip
        int streamSum = IntStream.range(1, 10).skip(5).sum();

        assert(forSkipSum == streamSum);

    }

    @Test
    public void sortAndFindFirstTest() {

        //sort a collection of names and then take first one
        Optional<String> first = Stream.of("John", "Zack", "Alberto")
                .sorted()
                .findFirst();

        assert("Alberto".compareTo(first.get()) == 0);

    }

    @Test
    public void filterTest() {

        //filter collection of names to those starting with 'J', then return them as sorted array
        String[] names = {"John", "Zack", "Jack", "Alberto"};
        Object[] sortedJNames = Arrays.stream(names)
                .filter(x -> x.startsWith("J"))
                .sorted()
                .toArray();


        assert(sortedJNames[0].toString().equals("Jack"));
        assert(sortedJNames[1].toString().equals("John"));

    }

    @Test
    public void mapTest() {

        List<Integer> numbers = new ArrayList<>() {{ add(10); add(20); add(30); }};
        Integer[] integers = numbers.stream()
                .map(x -> x * x)
                .toArray(Integer[]::new);

        assert(100 == integers[0]);
        assert(400 == integers[1]);
        assert(900 == integers[2]);

    }

    @Test
    public void streamFromFileTest() throws IOException, URISyntaxException {

        //"The returned stream contains a reference to an open file. The file is closed by closing the stream."
        //Therefore, lines object must be 'closed' after we are done
        URL testFileUri = getClass().getResource("/names.txt");
        Stream<String> lines = Files.lines(Paths.get(testFileUri.toURI()));

        //toArray is a 'terminal' operation, which means it closes
        String[] strings = lines.sorted().toArray(String[]::new);

        //explicit closing of the stream is not needed, BUT it would be needed if
        //there was no any terminal operation done before (like to Array)
        //lines.close();

        assert(strings[0].equals("Alberto"));
        assert(strings[1].equals("Ann"));
        assert(strings[2].equals("Jack"));
        assert(strings[3].equals("Kate"));
        assert(strings[4].equals("Zack"));

    }


    @Test
    public void collectTest() throws IOException, URISyntaxException {

        URL testFileUri = getClass().getResource("/names.txt");
        List<String> lines = Files.lines(Paths.get(testFileUri.toURI()))
                .filter(x -> x.contains("Z"))
                .collect(Collectors.toList());

        assert(lines.size() == 1);
        assert(lines.get(0).equals("Zack"));

    }

}
