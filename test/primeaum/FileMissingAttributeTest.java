package primeaum;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.fail;

public class FileMissingAttributeTest extends ValidateStopsTest {


    /**
     * Run validation test with missing stop_id field in line format
     */
    @Test
    void validateFileMissingStopID() {
        try {
            validateStops("file-missing-stopid.txt");
            fail("Did not throw exception when one was expected");
        } catch(IOException e) { }
    }

    /**
     * Run validation test with missing stop_lat field in line format
     */
    @Test
    void validateFileMissingStopLat() {
        try {
            validateStops("file-missing-stop-lon.txt");
            fail("Did not throw exception when one was expected");
        } catch(IOException e) { }
    }

    /**
     * Run validation test with missing stop_lon field in line format
     */
    @Test
    void validateFileMissingStopLon() {
        try {
            validateStops("file-missing-stop-lon.txt");
            fail("Did not throw exception when one was expected");
        } catch(IOException e) { }
    }

}
