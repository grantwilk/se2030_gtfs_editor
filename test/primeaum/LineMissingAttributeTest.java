package primeaum;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.fail;

public class LineMissingAttributeTest extends ValidateStopsTest {


    /**
     * Run validation test with line missing stop_id attribute
     */
    @Test
    void validateLineMissingStopID() {
        try {
            validateStops("line-missing-stopid.txt");
            fail("Did not throw exception when one was expected");
        } catch(IOException e) { }
    }

    /**
     * Run validation test with line missing stop_lat attribute
     */
    @Test
    void validateLineMissingStopLat() {
        try {
            validateStops("line-missing-stop-lon.txt");
            fail("Did not throw exception when one was expected");
        } catch(IOException e) { }
    }

    /**
     * Run validation test with line missing stop_lon attribute
     */
    @Test
    void validateLineMissingStopLon() {
        try {
            validateStops("line-missing-stop-lon.txt");
            fail("Did not throw exception when one was expected");
        } catch(IOException e) { }
    }

}
