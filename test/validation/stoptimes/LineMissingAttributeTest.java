package validation.stoptimes;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.fail;

public class LineMissingAttributeTest extends ValidateStopTimesTest {

    /**
     * Tests an invalid stop times file that has one line missing the trip ID attribute
     */
    @Test
    void lineMissingTripID() throws IOException {

        // attempt to validate the test file
        try {
            validateTestFile("line-missing-trip-id.txt");
            fail("Method did not throw expected exception.");
        }

        // catch the exception thrown by the missing attribute
        catch (IllegalArgumentException e) { }

    }

    /**
     * Tests an invalid stop times file that has one line missing the arrival time attribute
     */
    @Test
    void lineMissingArrivalTime() throws IOException {

        // attempt to validate the test file
        try {
            validateTestFile("line-missing-arrival-time.txt");
            fail("Method did not throw expected exception.");
        }

        // catch the IO exception thrown by the missing attribute
        catch (IllegalArgumentException e) { }

    }

    /**
     * Tests an invalid stop times file that has one line missing the departure time attribute
     */
    @Test
    void lineMissingDepartureTime() throws IOException {

        // attempt to validate the test file
        try {
            validateTestFile("line-missing-departure-time.txt");
            fail("Method did not throw expected exception.");
        }

        // catch the IO exception thrown by the missing attribute
        catch (IllegalArgumentException e) { }

    }

    /**
     * Tests an invalid stop times file that has one line missing the stop ID attribute
     */
    @Test
    void lineMissingStopID() throws IOException {

        // attempt to validate the test file
        try {
            validateTestFile("line-missing-stop-id.txt");
            fail("Method did not throw expected exception.");
        }

        // catch the IO exception thrown by the missing attribute
        catch (IllegalArgumentException e) { }

    }

    /**
     * Tests an invalid stop times file that has one line missing the stop sequence attribute
     */
    @Test
    void lineMissingStopSequence() throws IOException {

        // attempt to validate the test file
        try {
            validateTestFile("line-missing-stop-sequence.txt");
            fail("Method did not throw expected exception.");
        }

        // catch the IO exception thrown by the missing attribute
        catch (IllegalArgumentException e) { }

    }

}
