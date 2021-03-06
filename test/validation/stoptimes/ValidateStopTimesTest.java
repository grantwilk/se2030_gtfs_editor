package validation.stoptimes;

import gtfsapp.file.GTFSFile;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

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
     * consecutive stops, and consecutive sequences that follow order of arrival time. There are no extra parameters.
     */
    @Test
    void sunnyDayOne() throws IOException {

        // attempt to validate the test file
        validateTestFile("sunny-day-1.txt");

    }

    /**
     * Tests a valid stop times file that has its attributes rearranged out of the typical order
     */
    @Test
    void disorderedAttributes() throws IOException {

        // attempt to validate the test file
        validateTestFile("disordered-attributes.txt");

    }

    /**
     * Tests a valid stop times file that has extra format attributes
     */
    @Test
    void extraFormatElement() throws IOException {

        // attempt to validate the test file
        validateTestFile("extra-format-element.txt");

    }

}