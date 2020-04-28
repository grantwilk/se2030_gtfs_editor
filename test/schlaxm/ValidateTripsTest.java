package schlaxm;

import gtfsapp.file.GTFSFile;
import org.junit.jupiter.api.Test;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class ValidateTripsTest {
    /**
     * Path for the folder with the test files
     */
    static final String TRIP_VALIDATION_PATH = "samples/trip-validation-samples/";


    /**
     * Helper method for validating a singular test file
     * @param fileName - the name of the file to test
     * @throws IOException when the test file fails to load
     */
    void validateTestFile(String fileName) throws IOException {

        // get the file path from the file name
        Path path = Paths.get(TRIP_VALIDATION_PATH + fileName);

        // read all lines from the file
        List<String> lines = Files.readAllLines(path);

        // validate the stop times
        GTFSFile.validateTrips(lines);

    }

    /**
     * Tests a valid trip file with one trip
     */
    @Test
    void validateSunnyDayOne() {

        // attempt to validate the test file
        try {
            validateTestFile("trips-sunny-day-1.txt");
        }

        // catch IO exceptions
        catch (IOException e) {
            fail("Failed to load the test file.");
        }

    }

    /**
     * Tests a valid trip file with multiple trips
     */
    @Test
    void validateSunnyDayTwo() {

        // attempt to validate the test file
        try {
            validateTestFile("trips-sunny-day-2.txt");
        }

        // catch IO exceptions
        catch (IOException e) {
            fail("Failed to load the test file.");
        }

    }

    /**
     * Tests a trip file with no trips
     * should fail
     */
    @Test
    void validateRainyDayOne() {

        // attempt to validate the test file
        try {
            validateTestFile("trips-rainy-day.txt");
        }

        // catch IO exceptions
        catch (IOException e) {
            fail("No trip was designated in the file.");
        }

    }

    /**
     * Tests a trip file with valid tokens, but no included data
     */
    @Test
    void validateSunnyDayThree() {

        // attempt to validate the test file
        try {
            validateTestFile("trips-no-data.txt");
        }

        // catch IO exceptions
        catch (IOException e) {
            fail("Failed to load the test file.");
        }

    }



}