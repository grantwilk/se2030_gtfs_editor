package wilkg;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.fail;

public class StopSequenceInvalidTest extends ValidateStopTimesTest {

    /**
     * Tests an invalid stop times file with a stop time whose stop sequence attribute is not a number
     */
    @Test
    void validateStopSequenceTooSmall() throws IOException {

        // attempt to validate the test file
        try {
            validateTestFile("stop-sequence-too-small.txt");
            fail("Method did not throw expected exception.");
        }

        // catch the illegal argument exception thrown by the small number
        catch (IllegalArgumentException e) { }

    }

    /**
     * Tests an invalid stop times file with a stop time whose stop sequence attribute is not a number
     */
    @Test
    void validateStopSequenceNaN() throws IOException {

        // attempt to validate the test file
        try {
            validateTestFile("stop-sequence-nan.txt");
            fail("Method did not throw expected exception.");
        }

        // catch the number format exception thrown by the invalid number
        catch (NumberFormatException e) { }

    }

}
