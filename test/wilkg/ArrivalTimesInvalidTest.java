package wilkg;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.fail;

public class ArrivalTimesInvalidTest extends ValidateStopTimesTest {

    /**
     * Tests an invalid stop times file with a stop time whose arrival time hours component is too large
     */
    @Test
    void validateArrivalTimeHoursTooLarge() throws IOException {

        // attempt to validate the test file
        try {
            validateTestFile("arrival-time-hours-too-large.txt");
            fail("Method did not throw expected exception.");
        }

        // catch the illegal argument exception thrown by the large number
        catch (IllegalArgumentException e) { }

    }

    /**
     * Tests an invalid stop times file with a stop time whose arrival time hours component is too small
     */
    @Test
    void validateArrivalTimeHoursTooSmall() throws IOException {

        // attempt to validate the test file
        try {
            validateTestFile("arrival-time-hours-too-small.txt");
            fail("Method did not throw expected exception.");
        }

        // catch the Illegal Argument Exception thrown by the small number
        catch (IllegalArgumentException e) { }

    }

    /**
     * Tests an invalid stop times file with a stop time whose arrival time hours component is not a number
     */
    @Test
    void validateArrivalTimeHoursNaN() throws IOException {

        // attempt to validate the test file
        try {
            validateTestFile("arrival-time-hours-nan.txt");
            fail("Method did not throw expected exception.");
        }

        // catch the number format exception thrown by the invalid number
        catch (NumberFormatException e) { }

    }

    /**
     * Tests an invalid stop times file with a stop time whose arrival time minutes component is too large
     */
    @Test
    void validateArrivalTimeMinutesTooLarge() throws IOException {

        // attempt to validate the test file
        try {
            validateTestFile("arrival-time-minutes-too-large.txt");
            fail("Method did not throw expected exception.");
        }

        // catch the illegal argument exception thrown by the large number
        catch (IllegalArgumentException e) { }

    }

    /**
     * Tests an invalid stop times file with a stop time whose arrival time minutes component is too small
     */
    @Test
    void validateArrivalTimeMinutesTooSmall() throws IOException {

        // attempt to validate the test file
        try {
            validateTestFile("arrival-time-minutes-too-small.txt");
            fail("Method did not throw expected exception.");
        }

        // catch the Illegal Argument Exception thrown by the small number
        catch (IllegalArgumentException e) { }

    }

    /**
     * Tests an invalid stop times file with a stop time whose arrival time minutes component is not a number
     */
    @Test
    void validateArrivalTimeMinutesNaN() throws IOException {

        // attempt to validate the test file
        try {
            validateTestFile("arrival-time-minutes-nan.txt");
            fail("Method did not throw expected exception.");
        }

        // catch the number format exception thrown by the invalid number
        catch (NumberFormatException e) { }

    }

    /**
     * Tests an invalid stop times file with a stop time whose arrival time seconds component is too large
     */
    @Test
    void validateArrivalTimeSecondsTooLarge() throws IOException {

        // attempt to validate the test file
        try {
            validateTestFile("arrival-time-seconds-too-large.txt");
            fail("Method did not throw expected exception.");
        }

        // catch the illegal argument exception thrown by the large number
        catch (IllegalArgumentException e) { }

    }

    /**
     * Tests an invalid stop times file with a stop time whose arrival time seconds component is too small
     */
    @Test
    void validateArrivalTimeSecondsTooSmall() throws IOException {

        // attempt to validate the test file
        try {
            validateTestFile("arrival-time-seconds-too-small.txt");
            fail("Method did not throw expected exception.");
        }

        // catch the Illegal Argument Exception thrown by the small number
        catch (IllegalArgumentException e) { }

    }

    /**
     * Tests an invalid stop times file with a stop time whose arrival time seconds component is not a number
     */
    @Test
    void validateArrivalTimeSecondsNaN() throws IOException {

        // attempt to validate the test file
        try {
            validateTestFile("arrival-time-seconds-nan.txt");
            fail("Method did not throw expected exception.");
        }

        // catch the number format exception thrown by the invalid number
        catch (NumberFormatException e) { }

    }

}
