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
     * Extract each of the four GTFS files from the user selected files
     * @param files List of files to extract GTFS files from
     */
    private void extractFiles(List<File> files) throws IOException{
        for(File f:files) {
            String name = f.getPath().substring(f.getPath().lastIndexOf('/'));
            if(name.equals("stops.txt") && stopFile == null) {
                stopFile = f;
            } else if(name.equals("routes.txt") && routeFile == null) {
                routeFile = f;
            } else if(name.equals("stop_times.txt") && stopTimesFile == null) {
                stopTimesFile = f;
            } else if(name.equals("trips.txt") && tripFile == null) {
                tripFile = f;
            }
        }

        if(stopFile == null) {
            throw new IOException("No stops.txt file was found");
        } else if(routeFile == null) {
            throw new IOException("No routes.txt file was found");
        }else if(stopTimesFile == null) {
            throw new IOException("No stop_times.txt file was found");
        } else if(tripFile == null) {
            throw new IOException("No trips.txt file was found");
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
                lineScanner.close();
            }
        } catch(FileNotFoundException e) { };

    }

}