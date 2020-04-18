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
     * Extracts the four required GTFS files from a list of files and assigns them to the classes file attributes
     * @param files - a list of files
     */
    private void extractFiles(List<File> files) throws IOException {

        // for each file in our list of files
        for (File file : files) {

            // get the files name
            String fileName = file.getName();

            // check file name and assign it to the proper file if it has not already been assigned
            switch (fileName) {

                // if the file name is routes.txt
                case "routes.txt":
                    if (routeFile != null) {
                        throw new IOException("Found multiple \"" + fileName + "\" files.");
                    }
                    routeFile = file;
                    break;

                // if the file name is trips.txt
                case "trips.txt":
                    if (tripFile != null) {
                        throw new IOException("Found multiple \"" + fileName + "\" files.");
                    }
                    tripFile = file;
                    break;

                // if the file name is stop_times.txt
                case "stop_times.txt":
                    if (stopTimesFile != null) {
                        throw new IOException("Found multiple \"" + fileName + "\" files.");
                    }
                    stopTimesFile = file;
                    break;

                // if the file name is stops.txt
                case "stops.txt":
                    if (stopFile != null) {
                        throw new IOException("Found multiple \"" + fileName + "\" files.");
                    }
                    stopFile = file;
                    break;

                // throw an exception if the file name is not recognized
                default:
                    throw new IOException("Unrecognized file name \"" + fileName + "\".");
            }
        }

        // throw an exception if any of our files are still unassigned
        if (routeFile == null) {
            throw new IOException("No file \"routes.txt\" was specified.");
        } else if (tripFile == null) {
            throw new IOException("No file \"trips.txt\" was specified.");
        } else if (stopTimesFile == null) {
            throw new IOException("No file \"stop_times.txt\" was specified.");
        } else if (stopFile == null) {
            throw new IOException("No file \"stops.txt\" was specified.");
        }

    }


}