package primeaum;

import gtfsapp.file.GTFSFile;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

class ValidateStopsTest {

    static final String STOPS_VALIDATION_ROOT = "samples/stop-validation-samples/";


    void validateStops(String fileName) throws IOException {
        // get the file path from the file name
        Path path = Paths.get(STOPS_VALIDATION_ROOT + fileName);

        // read all lines from the file
        List<String> lines = Files.readAllLines(path);

        // validate the stop times
        GTFSFile.validateStops(lines);
    }

    @Test
    void sunnyDayOne() throws IOException {
        validateStops("sunny-day-one.txt");
    }

    @Test
    void sunnyDayTwo() throws IOException {
        validateStops("sunny-day-two.txt");
    }
}