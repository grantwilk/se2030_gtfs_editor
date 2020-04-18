package gtfsapp.file;

import java.io.File;
import java.io.IOException;
import java.util.List;

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
     * Parses GTFS elements from a set of GTFS files, creates a new feed, and adds the parsed elements to it
     */
    public void load() throws IOException {

        // throw an exception if any of our files are still unassigned
        if (routeFile == null) {
            throw new IOException("Required GTFS file \"routes.txt\" was not found.");
        } else if (tripFile == null) {
            throw new IOException("Required GTFS file \"trips.txt\" was not found.");
        } else if (stopTimesFile == null) {
            throw new IOException("Required GTFS file \"stop_times.txt\" was not found.");
        } else if (stopFile == null) {
            throw new IOException("Required GTFS file \"stops.txt\" was not found.");
        }

        // parse the files
        List<Stop> stops = parseStops();
        List<Route> routes = parseRoutes();
        List<Trip> trips = parseTrips(routes);
        List<StopTime> stopTimes = parseStopTimes(trips, stops);

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
     * Extracts the four required GTFS files from a list of files and assigns them to the GTFS File's file attributes
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
                    throw new IOException("Unrecognized GTFS file \"" + fileName + "\".");
            }
        }
    }

    /**
     * Parses routes from the GTFS routes file and returns them as a list
     * @return the parsed routes as a list
     */
    private List<Route> parseRoutes() {
        return null;
    }

    /**
     * Parses trips from the GTFS trips file and returns them as a list
     * @param routes - the list of routes that the trips should be linked to
     * @return the list of trips
     */
    private List<Trip> parseTrips(List<Route> routes) {
        return null;
    }

    /**
     * Parses stop times from the GTFS stop times file and returns them as a list
     * @param trips - the list of trips that the stop times should be linked to
     * @param stops - the list of stops that the stop times should be linked to
     * @return the list of stop times
     */
    private List<StopTime> parseStopTimes(List<Trip> trips, List<Stop> stops) {
        return null;
    }

    /**
     * Parses stops from the GTFS stops file adn returns them as a list
     * @return the list of stops
     */
    private List<Stop> parseStops() {
        return null;
    }

}