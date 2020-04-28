package primeaum;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.fail;

public class InvalidDataTest extends ValidateStopsTest {


    /**
     * Run validation test with an invalid latitude
     */
    @Test
    void nonDoubleLat() {
        try {
            validateStops("invalid-data-lat.txt");
            fail("Did not throw exception when one was expected");
        } catch(IOException e) {

        } catch(NumberFormatException e) { }
    }

    /**
     * Run validation test with an invalud longitude
     */
    @Test
    void nonDoubleLon() {
        try {
            validateStops("invalid-data-lon.txt");
            fail("Did not throw exception when one was expected");
        } catch(IOException e) {

        } catch(NumberFormatException e) { }
    }

    /**
     * Run validation test with duplicate stop ids
     */
    @Test
    void duplicateStopID() {
        try {
            validateStops("invalid-data-duplicate-id");
            fail("Did not throw exception when one was expected");
        } catch(IOException e) { }
    }

    /**
     * Run validation test with stop_name in format line but attribute is missing in successive line
     */
    @Test
    void expectedStopName() {
        try {
            validateStops("invalid-data-missing-stop-name");
            fail("Did not throw exception when one was expected");
        } catch(IOException e) { }
    }

}
