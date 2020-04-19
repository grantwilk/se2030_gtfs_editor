package gtfsapp.file;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;

/**
 * @author Michael Primeau and Grant Wilk
 * @version 1.0
 * @created 15-Apr-2020 1:20:18 PM
 */
public class GTFSFile {

    /**
     * The number of milliseconds in a second
     */
    private static final int MILLIS_IN_SECOND = 1000;

    /**
     * The number of milliseconds in a minute
     */
    private static final int MILLIS_IN_MINUTE = 60 * MILLIS_IN_SECOND;

    /**
     * The number of milliseconds in an hour
     */
    private static final int MILLIS_IN_HOUR = 60 * MILLIS_IN_MINUTE;

    private Feed feed;
    private File tripFile;
    private File routeFile;
    private File stopFile;
    private File stopTimesFile;

    /**
     * Creates a new GTFS File
     * @param files - a list of GTFS files to be loaded and parsed
     */
    public GTFSFile(List<File> files) throws IOException {
        extractFiles(files);
    }

    /**
     * Gets the GTFS feed and returns it
     * @return the GTFS feed
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

        // create a new GTFS feed
        feed = new Feed();

        // parse the files (must be in this order!)
        HashMap<String, Stop> stops = parseStops();
        HashMap<String, Route> routes = parseRoutes();
        HashMap<String, Trip> trips = parseTrips(routes);
        HashMap<String, StopTime> stopTimes = parseStopTimes(trips, stops);

        // add our GTFS elements to our feed
        feed.addAllRoutes(new ArrayList<>(routes.values()));
        feed.addAllTrips(new ArrayList<>(trips.values()));
        feed.addAllStopTimes(new ArrayList<>(stopTimes.values()));
        feed.addAllStops(new ArrayList<>(stops.values()));

    }

    /**
     * Saves a GTFS file back to its original directory
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
    private HashMap<String, Route> parseRoutes() throws IOException {

        // get all lines from the file
        List<String> lines = Files.readAllLines(routeFile.toPath());

        // remove the first line because it contains a template
        lines.remove(0);

        // create a new list of routes
        HashMap<String, Route> routes = new HashMap<>();

        // for each line in the file
        for (String line : lines) {

            // split the line into comma separated tokens
            List<String> tokens = tokenizeLine(line);

            // extract all values
            String route_id = tokens.get(0);
            String agency_id = tokens.get(1);
            String route_short_name = tokens.get(2);
            String route_long_name = tokens.get(3);
            String route_desc = tokens.get(4);
            String route_type = tokens.get(5);
            String route_url = tokens.get(6);
            String route_color = tokens.get(7);
            String route_text_color = tokens.get(8);

            // get the route type and route color
            RouteType routeType = RouteType.values()[Integer.parseInt(route_type)];

            // create a new route
            Route route = new Route(feed, route_id, routeType);

            // set route name to short name
            if (!route_short_name.isEmpty()) {
                route.setShortName(route_short_name);
            }

            // set route name to long name
            if (!route_long_name.isEmpty()) {
                route.setLongName(route_long_name);
            }

            // set route description
            if (!route_desc.isEmpty()) {
                route.setDesc(route_desc);
            }

            // set route URL
            if(!route_url.isEmpty()) {
                route.setURL(route_url);
            }

            // set route color
            if (!route_color.isEmpty()) {
                route.setColor(hexToColor(route_color));
            }

            // set route text color
            if (!route_text_color.isEmpty()) {
                route.setColor(hexToColor(route_text_color));
            }

            // add our routes to the routes list
            routes.put(route_id, route);

        }

        return routes;
    }

    /**
     * Parses trips from the GTFS trips file and returns them as a list
     * @param routes - the list of routes that the trips should be linked to
     * @return the list of trips
     */
    private HashMap<String, Trip> parseTrips(HashMap<String, Route> routes) throws IOException {

        // get all lines from the file
        List<String> lines = Files.readAllLines(tripFile.toPath());

        // remove the first line because it contains a template
        lines.remove(0);

        // create a new list of trips
        HashMap<String, Trip> trips = new HashMap<>();

        // for each line in the file
        for (String line : lines) {

            // split the line into comma separated tokens
            List<String> tokens = tokenizeLine(line);

            // extract all values
            String route_id = tokens.get(0);
            String service_id = tokens.get(1);
            String trip_id = tokens.get(2);
            String trip_headsign = tokens.get(3);
            String direction_id = tokens.get(4);
            String block_id = tokens.get(5);
            String shape_id = tokens.get(6);

            // create a trip
            Trip trip = new Trip(feed, trip_id);

            // set trip headsign
            if (!trip_headsign.isEmpty()) {
                trip.setHeadSign(trip_headsign);
            }

            // add trip to route
            Route route = routes.get(route_id);
            route.addTrip(trip);

            // add trip to list of trips
            trips.put(trip_id, trip);

        }

        return trips;

    }

    /**
     * Parses stop times from the GTFS stop times file and returns them as a list
     * @param trips - the list of trips that the stop times should be linked to
     * @param stops - the list of stops that the stop times should be linked to
     * @return the list of stop times
     */
    private HashMap<String, StopTime> parseStopTimes(HashMap<String, Trip> trips, HashMap<String, Stop> stops) throws IOException {

        // get all lines from the file
        List<String> lines = Files.readAllLines(stopTimesFile.toPath());

        // remove the first line because it contains a template
        lines.remove(0);

        // create a new list of trips
        HashMap<String, StopTime> stopTimes = new HashMap<>();

        // for each line in the file
        for (String line : lines) {

            // split the line into comma separated tokens
            List<String> tokens = tokenizeLine(line);

            // extract all values
            String trip_id = tokens.get(0);
            String arrival_time = tokens.get(1);
            String departure_time = tokens.get(2);
            String stop_id = tokens.get(3);
            String stop_sequence = tokens.get(4);
            String stop_headsign = tokens.get(5);
            String pickup_type = tokens.get(6);
            String drop_off_time = tokens.get(7);
            String shape_dist_traveled = tokens.get(8);

            // get stop object
            Stop stop = stops.get(stop_id);

            // get stop sequence as int
            int sequence = Integer.parseInt(stop_sequence);

            // create a stop time
            StopTime stopTime = new StopTime(feed, stop, sequence);

            // set arrival time
            if (!arrival_time.isEmpty()) {
                Date arrivalTime = timeStringToTime(arrival_time);
                stopTime.setArrivalTime(arrivalTime);
            }

            // set departure time
            if (!departure_time.isEmpty()) {
                Date departureTime = timeStringToTime(departure_time);
                stopTime.setDepartureTime(departureTime);
            }

            // set stop time headsign
            if (!stop_headsign.isEmpty()) {
                stopTime.setHeadSign(stop_headsign);
            }

            // add stop time to trip
            Trip trip = trips.get(trip_id);
            trip.addStopTime(stopTime);

            // get stop time id string
            String stopTimeIDString = stopTime.getID().getIDString();

            // add stop time to list of stop times
            stopTimes.put(stopTimeIDString, stopTime);

        }

        return stopTimes;

    }

    /**
     * Parses stops from the GTFS stops file adn returns them as a list
     * @return the list of stops
     */
    private HashMap<String, Stop> parseStops() throws IOException {

        // get all lines from the file
        List<String> lines = Files.readAllLines(stopFile.toPath());

        // remove the first line because it contains a template
        lines.remove(0);

        // create a new list of stops
        HashMap<String, Stop> stops = new HashMap<>();

        // for each line in the file
        for (String line : lines) {

            // split the line into comma separated tokens
            List<String> tokens = tokenizeLine(line);

            // extract all values
            String stop_id = tokens.get(0);
            String stop_name = tokens.get(1);
            String stop_desc = tokens.get(2);
            String stop_lat = tokens.get(3);
            String stop_lon = tokens.get(4);
            String zone_id = tokens.get(5);
            String stop_url = tokens.get(6);

            // create a new stop
            Stop stop = new Stop(feed, stop_id);

            // set stop name
            if (!stop_name.isEmpty()) {
                stop.setName(stop_name);
            }

            // set stop description
            if (!stop_desc.isEmpty()) {
                stop.setDesc(stop_desc);
            }

            // set position if we have latitude and longitude
            if (!stop_lat.isEmpty() && !stop_lon.isEmpty()) {
                double lat = Double.parseDouble(stop_lat);
                double lon = Double.parseDouble(stop_lon);
                Point2D location = new Point2D(lon, lat);
                stop.setLocation(location);
            }

            // set stop url
            if (!stop_url.isEmpty()) {
                stop.setURL(stop_url);
            }

            // add our stops to the stops list
            stops.put(stop_id, stop);

        }

        return stops;

    }

    /**
     * Tokenizes a line from a CSV file using ',' as a delimiter
     * @param line - the line to tokenize
     * @return a list of string tokens
     */
    private List<String> tokenizeLine(String line)  {
        return Arrays.asList(line.split(",", -1));
    }

    /**
     * Converts a hex color string to a Java FX color
     * @param hex - the hex color string to parse
     * @return the converted color
     */
    private Color hexToColor(String hex) {

        // throw an exception if the hex color string is improperly formatted
        if (!hex.matches("^[0-9a-fA-F]{6}")) {
            throw new IllegalArgumentException("Color \"" + hex + "\" is improperly formatted.");
        }

        // parse colors and convert to doubles
        double red = Integer.parseInt(hex.substring(0, 2), 16) / 255.0;
        double green = Integer.parseInt(hex.substring(2, 4), 16) / 255.0;
        double blue = Integer.parseInt(hex.substring(4, 6), 16) / 255.0;

        // return new color
        return new Color(red, green, blue, 1.0);
    }

    /**
     * Converts a time string to a time object
     * @param timeString - the time string
     * @return a time object
     */
    private Date timeStringToTime(String timeString) {

        // throw an exception if the time string is improperly formatted
        if (!timeString.matches("^[0-9]{1,2}:[0-9]{1,2}:[0-9]{1,2}")) {
            throw new IllegalArgumentException("Time \"" + timeString + "\" is improperly formatted.");
        }

        // set up scanner
        Scanner timeScanner = new Scanner(timeString);
        timeScanner.useDelimiter(":");

        // parse hours, minutes, and seconds
        int hours = timeScanner.nextInt();
        int minutes = timeScanner.nextInt();
        int seconds = timeScanner.nextInt();

        // calculate millis
        long millis = hours * MILLIS_IN_HOUR + minutes * MILLIS_IN_MINUTE + seconds * MILLIS_IN_SECOND;

        // time zone offset
        // TODO - find a better way to accommodate time zone offsets that works for other time zones
        long timeZoneOffset = 6 * MILLIS_IN_HOUR;

        // return new date using millis and time zone offset
        return new Date(millis + timeZoneOffset);

    }
}