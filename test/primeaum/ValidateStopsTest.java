package primeaum;

import gtfsapp.file.GTFSFile;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

class ValidateStopsTest {


    @BeforeEach
    void setUp() {

    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void validateRoutes() {

    }

    @Test
    void validateStops() {
        File file = new File("C:/Users/primeaum/Documents/SE2030Project/2020-project-team-delta/samples/rainy-day-1/stops.txt");
        List<File> files = new ArrayList<>();
        files.add(file);
        GTFSFile testFile;
        try {
            testFile = new GTFSFile(files);
            List<String> lines = Files.readAllLines(file.toPath());
            testFile.validateStops(lines);

        } catch(IOException e) { }


    }
}