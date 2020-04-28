package primeaum;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.fail;

public class FileMissingAttributeTest extends ValidateStopsTest {


    @Test
    void validateFileMissingStopID() {
        try {
            validateStops("file-missing-stopid.txt");
            fail("Did not throw exception when one was expected");
        } catch(IOException e) { }
    }

    @Test
    void validateFileMissingStopLat() {
        try {
            validateStops("file-missing-stop-lon.txt");
            fail("Did not throw exception when one was expected");
        } catch(IOException e) { }
    }

    @Test
    void validateFileMissingStopLon() {
        try {
            validateStops("file-missing-stop-lon.txt");
            fail("Did not throw exception when one was expected");
        } catch(IOException e) { }
    }

}
