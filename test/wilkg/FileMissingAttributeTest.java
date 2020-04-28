package wilkg;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.fail;

public class FileMissingAttributeTest extends ValidateStopTimesTest {

    /**
     * Tests an invalid stop times file that is missing the trip ID attribute
     */
    @Test
    void fileMissingTripID() {

        // attempt to validate the test file
        try {
            validateTestFile("file-missing-trip-id.txt");
            fail("Method did not throw expected exception.");
        }

        // catch the IO exception thrown by the missing attribute
        catch (IOException e) { }

    }

    /**
     * Tests an invalid stop times file that is missing the arrival time attribute
     */
    @Test
    void fileMissingArrivalTime() {

        // attempt to validate the test file
        try {
            validateTestFile("file-missing-arrival-time.txt");
            fail("Method did not throw expected exception.");
        }

        // catch the IO exception thrown by the missing attribute
        catch (IOException e) { }

    }

    /**
     * Tests an invalid stop times file that is missing the departure time attribute
     */
    @Test
    void fileMissingDepartureTime() {

        // attempt to validate the test file
        try {
            validateTestFile("file-missing-departure-time.txt");
            fail("Method did not throw expected exception.");
        }

        // catch the IO exception thrown by the missing attribute
        catch (IOException e) { }

    }

    /**
     * Tests an invalid stop times file that is missing the stop ID attribute
     */
    @Test
    void fileMissingStopID() {

        // attempt to validate the test file
        try {
            validateTestFile("file-missing-stop-id.txt");
            fail("Method did not throw expected exception.");
        }

        // catch the IO exception thrown by the missing attribute
        catch (IOException e) { }

    }

    /**
     * Tests an invalid stop times file that is missing the stop sequence attribute
     */
    @Test
    void fileMissingStopSequence() {

        // attempt to validate the test file
        try {
            validateTestFile("file-missing-stop-sequence.txt");
            fail("Method did not throw expected exception.");
        }

        // catch the IO exception thrown by the missing attribute
        catch (IOException e) { }

    }

}
