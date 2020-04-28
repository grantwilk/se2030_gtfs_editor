package primeaum;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.fail;

public class LineMissingAttributeTest extends ValidateStopsTest {


    @Test
    void validateLineMissingStopID() {
        try {
            validateStops("line-missing-stopid.txt");
            fail("Did not throw exception when one was expected");
        } catch(IOException e) { }
    }

    @Test
    void validateLineMissingStopLat() {
        try {
            validateStops("line-missing-stop-lon.txt");
            fail("Did not throw exception when one was expected");
        } catch(IOException e) { }
    }

    @Test
    void validateLineMissingStopLon() {
        try {
            validateStops("line-missing-stop-lon.txt");
            fail("Did not throw exception when one was expected");
        } catch(IOException e) { }
    }

}
