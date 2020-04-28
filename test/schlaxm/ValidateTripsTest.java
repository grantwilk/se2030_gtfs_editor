package schlaxm;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import gtfsapp.file.GTFSFile;
import org.junit.jupiter.api.Test;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class ValidateTripsTest {
    /**
     * Path for the folder with the test files
     */
    static final String TRIP_VALIDATION_PATH = "samples/trip-validation-samples/";


    /**
     * Helper method for validating a singular test file
     *
     * @param fileName - the name of the file to test
     * @throws IOException when the test file fails to load
     */
    void validateTestFile(String fileName) throws IOException {

        // get the file path from the file name
        Path path = Paths.get(TRIP_VALIDATION_PATH + fileName);

        // read all lines from the file
        List<String> lines = Files.readAllLines(path);

        // validate the stop times
        GTFSFile.validateTrips(lines);

    }

    /**
     * Tests the google provided sunny day scenario
     */
    @Test
    void validateSunnyDay() throws IOException {

        // attempt to validate the test file
        validateTestFile("trips-sunny-day-1.txt");

    }


    /**
     * Tests a trip file, missing the trip_id
     */
    @Test
    void validateMissingInformation() throws IOException {

        // attempt to validate the test file
        try {
            validateTestFile("trips-no-id.txt");
            fail("Exception not thrown");
        } catch (IOException e) {
        }

    }

    /**
     * Tests a trip file, missing data for the trip
     */
    @Test
    void validateNoData() throws IOException {

        // attempt to validate the test file
        try {
            validateTestFile("trips-no-data.txt");
            fail("Exception not thrown");
        }
        catch (IOException e) {        }
    }

    /**
     * Tests a trip file with a duplicate trip ID, done by seeing if the same trip has the same stop sequence number
     */
    @Test
    void validateDuplicate() throws IOException {

        // attempt to validate the test file
        try {
            validateTestFile("trips-duplicate-data.txt");
            fail("Exception not thrown");
        }
        catch (IOException e) {        }
    }

    /**
     * Tests a trip file to see if there is only one stop on it
     */
    @Test
    void validateSingleStop() throws IOException {

        // attempt to validate the test file
        try {
            validateTestFile("trips-one-stop.txt");
            fail("Exception not thrown");
        }
        catch (IOException e) {        }
    }

    /**
     * Tests a trip file to see if there is a service_id in it
     */
    @Test
    void validateNoSerivceId() throws IOException {

        // attempt to validate the test file
        try {
            validateTestFile("trips-no-service-id.txt");
            fail("Exception not thrown");
        }
        catch (IOException e) {        }
    }

    /**
     * Tests a trip file to see if there extra data not specified in the format line
     */
    @Test
    void validateExtraData() throws IOException {

        // attempt to validate the test file
        try {
            validateTestFile("trips-extra-data.txt");
            fail("Exception not thrown");
        }
        catch (IOException e) {        }
    }

}