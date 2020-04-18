package gtfsapp.file;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * @author Michael Primeau and Grant Wilk
 * @version 1.0
 * @created 15-Apr-2020 1:20:18 PM
 */
public class GTFSFile {

    private Feed feed;
    private File tripFile;
    private File routeFile;
    private File stopFile;
    private File stopTimesFile;

    /** Constructor for GTFSFile
     * @param files List of GTFS files to be loaded and parsed
     */
    public GTFSFile(List<File> files) throws IOException {
        extractFiles(files);
    }

    /**
     * Getter for Feed
     * @return Returns Feed for GTFS Application
     */
    public Feed getFeed() {
        return feed;
    }

    /**
     *
     */
    public void load() {

        // parse the files
        ArrayList<Stop> stops = parseStops();
        ArrayList<Route> routes = parseRoutes();
        ArrayList<Trip> trips = parseTrips(routes);
        ArrayList<StopTime> stopTimes = parseStopTimes(trips, stops);

        // create a new GTFS feed with all of our GTFS elements
        feed = new Feed("Feed1");
        feed.addAllStops(stops);
        feed.addAllRoutes(routes);
        feed.addAllTrips(trips);
        feed.addAllStopTimes(stopTimes);

    }

    /**
     *
     */
    public void save() {
        throw new UnsupportedOperationException();
    }

    /**
     * Checks if the loaded set of files contains the correct files
     * @throws IOException Thrown if a required file is not found
     */
    private void hasCorrectFiles(List<File> files) throws IOException {
        // check to see if we have four files
        if(files.size() != 4) {
            throw new IllegalArgumentException("Not all files have been loaded");
        }

        // separates file names and group in an array list
        List<String> fileNames = new ArrayList<>();
        for(int i = 0; i < 4; i++) {
            fileNames.add(files.get(i).getPath().substring(files.get(i).getPath().lastIndexOf('/')));
        }

        // verify that there is one instance of each file name
        if(Collections.frequency(fileNames,"stops.txt") != 1) {
            throw new IOException("No stops.txt file was found");
        }

        if(Collections.frequency(fileNames,"trips.txt") != 1) {
            throw new IOException("No trips.txt file was found");
        }

        if(Collections.frequency(fileNames,"stop_times.txt") != 1) {
            throw new IOException("No stop_times.txt file was found");
        }

        if(Collections.frequency(fileNames,"routes.txt") != 1) {
            throw new IOException("No routes.txt file was found");
        }
    }

    private void loadStopsFile() {
        File stopFile = null;
        for(int i = 0; i < files.size(); i++) {
            if(files.get(i).getPath().substring(files.get(i).getPath().lastIndexOf('/')).equals("stops.txt")) {
                stopFile = files.get(i);
            }
        }

        try {
            Scanner fileScanner = new Scanner(stopFile);
            Scanner lineScanner;
            while(fileScanner.hasNextLine()) {
                lineScanner = new Scanner(fileScanner.nextLine());
                lineScanner.useDelimiter(",");
                String stopId = lineScanner.next();
                // Ignore level_id
                lineScanner.next();
                String stopName = lineScanner.next();
                Double lat = Double.parseDouble(lineScanner.next());
                Double lon = Double.parseDouble(lineScanner.next());
                // Ignore location type
                lineScanner.next();
                lineScanner.close();
            }
        } catch(FileNotFoundException e) { };

    }

}