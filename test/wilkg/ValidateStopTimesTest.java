package wilkg;

import gtfsapp.file.GTFSFile;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.fail;

class ValidateStopTimesTest {

    /**
     * The root directory of the stop time validation samples
     */
    static final String STOP_TIME_VALIDATION_ROOT = "samples/stop-time-validation-samples/";

    /**
     * Helper method for validating a singular test file
     * @param fileName - the name of the file to test
     * @throws IOException when the test file fails to load
     */
    void validateTestFile(String fileName) throws IOException {

        // get the file path from the file name
        Path path = Paths.get(STOP_TIME_VALIDATION_ROOT + fileName);

        // read all lines from the file
        List<String> lines = Files.readAllLines(path);

        // validate the stop times
        GTFSFile.validateStopTimes(lines);

    }

    /**
     * Tests a valid stop times file with one trip, consecutive hourly stops with synchronous arrival and departure,
     * consecutive stops, and consecutive sequences that follow. There are no extra parameters.
     */
    @Test
    void validateSunnyDayOne() {

        // attempt to validate the test file
        try {
            validateTestFile("sunny-day-1.txt");
        }

        // catch IO exceptions
        catch (IOException e) {
            fail("Failed to load the test file.");
        }

    }

    /**
     * Tests a valid stop times file with multiple trips, non-consecutive hourly stops with synchronous arrival and departure, consecutive stops,
     * and consecutive sequences that follow. There are no extra parameters.
     */
    @Test
    void validateSunnyDayTwo() {

        // attempt to validate the test file
        try {
            validateTestFile("sunny-day-2.txt");
        }

        // catch IO exceptions
        catch (IOException e) {
            fail("Failed to load the test file.");
        }

    }

}