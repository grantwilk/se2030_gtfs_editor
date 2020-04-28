package primeaum;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.fail;

public class LineMissingAttributeTest extends ValidateStopsTest {


    @Test
    void validateLineMissingStopID() {
        try {
            validateStops("missing-stopid.txt");
            fail("Did not throw exception when missing stop id");
        } catch(IOException e) { }
    }

    @Test
    void validateLineMissingStopLat() {

    }

}
