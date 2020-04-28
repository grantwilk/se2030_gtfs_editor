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
        GTFSFile.validateTrips(lines);

    }
    /**
    * tests to see if the routes file fully compiles
     **/
    @Test
    void validateSunnyDayOne() {

        // validate test file
        try {
            validateTestFile("routes-sunny-day-1.txt");
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
            validateTestFile("routes-rainy-day-1.txt");
        }

        // catch IO exceptions
        catch (IOException e) {
            fail("Missing RouteID parameter");
        }

    }

    /**
     * Checks missing values
     */
    @Test
    void validateRainyDayTwo() {

        // validate test file
        try {
            validateTestFile("routes-rainy-day-2.txt");
        }

        // catch IO exceptions
        catch (IOException e) {
            fail("Failed to realize important inormation was missing");
        }

    }

    /**
     * looks for duplicate IDs
     */
    @Test
    void validateRainyDayThree() {

        // validate test file
        try {
            validateTestFile("routes-rainy-day3.txt");
        }

        // catch IO exceptions
        catch (IOException e) {
            fail("Failed to realize duplicate IDs");
        }

    }

    /**
     * looks for additional parameters
     */
    @Test
    void validateRainyDayfour() {

        // validate test file
        try {
            validateTestFile("routes-rainy-day4.txt");
        }

        // catch IO exceptions
        catch (IOException e) {
            fail("Failed to realize additional parameters exist");
        }

    }






}