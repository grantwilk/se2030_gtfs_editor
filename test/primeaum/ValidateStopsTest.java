package primeaum;

import gtfsapp.file.GTFSFile;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.fail;

class ValidateStopsTest {

    static final String STOPS_VALIDATION_ROOT = "samples/stop-validation-samples/";


    /**
     * Runs validateStops method with given file name
     * @param fileName Test file to use for validation
     * @throws IOException Exception thrown by validation method
     */
    void validateStops(String fileName) throws IOException {
        // get the file path from the file name
        Path path = Paths.get(STOPS_VALIDATION_ROOT + fileName);

        // read all lines from the file
        List<String> lines = Files.readAllLines(path);

        // validate the stop times
        GTFSFile.validateStops(lines);
    }

    /**
     * First sunny day scenario with a correctly formatted file
     * @throws IOException Thrown if an error occured
     */
    @Test
    void sunnyDayOne() {
        try {
            validateStops("sunny-day-one.txt");
        } catch(IOException e) {
            fail("An exception was thrown when one was not expected.");
        }
    }

    /**
     * Second sunny day scenario with a correctly formatted file
     * @throws IOException Thrown if an error occured
     */
    @Test
    void sunnyDayTwo() {
        try {
            validateStops("sunny-day-two.txt");
        } catch(IOException e) {
            fail("An exception was thrown when one was not expected.");
        }
    }
}