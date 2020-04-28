package rivardc;

import gtfsapp.file.GTFSFile;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ValidateRoutesTest {

    static final String ROUTE_VALIDATION_PATH = "samples/route-validation-samples/";

    void validateTestFile(String fileName) throws IOException {

        // get the file path from the file name
        Path path = Paths.get(ROUTE_VALIDATION_PATH + fileName);

        // read all lines from the file
        List<String> lines = Files.readAllLines(path);

        // validate the stop times
        GTFSFile.validateRoutes(lines);

    }
    /**
    * tests to see if the routes file fully compiles
     **/
    @Test
    void validateSunnyDayOne() {

        // validate test file
        try {
            validateTestFile("route-sunny-day-1.txt");
        }

        // catch IO exceptions
        catch (IOException e) {
            fail("Failed to load the test file.");
        }

    }

    /**
     * tests to see if the RouteID parameter is missing
     */
    @Test
    void validateRainyDayOne() {

        // validate test file
        try {
            validateTestFile("route-rainy-day-1.txt");
            fail("Missing RouteID parameter");
        }

        // catch IO exceptions
        catch (IOException e) {
        }

    }

    /**
     * Checks missing values
     */
    @Test
    void validateRainyDayTwo() {

        // validate test file
        try {
            validateTestFile("route-rainy-day-2.txt");
            fail("Failed to realize important inormation was missing");
        }

        // catch IO exceptions
        catch (IOException e) {
        }

    }

    /**
     * looks for duplicate IDs
     */
    @Test
    void validateRainyDayThree() {

        // validate test file
        try {
            validateTestFile("route-rainy-day3.txt");
            fail("Failed to realize duplicate IDs");
        }

        // catch IO exceptions
        catch (IOException e) {
        }

    }

    /**
     * looks for additional parameters
     */
    @Test
    void validateRainyDayFour() {

        // validate test file
        try {
            validateTestFile("route-rainy-day4.txt");
            fail("Failed to realize additional parameters exist");
        }

        // catch IO exceptions
        catch (IOException e) {
        }

    }
    @Test
    void validateRainyDayFive() {

        // validate test file
        try {
            validateTestFile("route-rainy-day-5.txt");
            fail("Failed to realize additional parameters exist");
        }

        // catch IO exceptions
        catch (IOException e) {
        }

    }





}