package gtfsapp.file;

import gtfsapp.id.RouteID;
import gtfsapp.id.StopID;
import gtfsapp.id.TripID;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

/**
 * @author Michael Primeau, Colton Rivard, Grant Wilk, Mason Schlax
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

    /**
     * The internal feed
     */
    private Feed feed;

    /**
     * The "trips.txt" file
     */
    private File tripFile;

    /**
     * The "routes.txt" file
     */
    private File routeFile;

    /**
     * The "stops.txt" file
     */
    private File stopFile;

    /**
     * The "stop_times.txt" file
     */
    private File stopTimesFile;

    /**
     * Creates a new GTFS File
     *
     * @param files a list of GTFS files to be loaded and parsed
     */
    public GTFSFile(List<File> files) throws IOException {
        // TODO - make GTFS file accept a singular ZIP file instead of multiple TXT files
        extractFiles(files);
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

        // get list of lines for each file
        List<String> stopLines = Files.readAllLines(stopFile.toPath());
        List<String> stopTimeLines = Files.readAllLines(stopTimesFile.toPath());
        List<String> routeLines = Files.readAllLines(routeFile.toPath());
        List<String> tripLines = Files.readAllLines(tripFile.toPath());

        // validate files
        validateStops(stopLines);
        validateStopTimes(stopTimeLines);
        validateTrips(tripLines);
        validateRoutes(routeLines);

        // parse the files (must be in this order!)
        HashMap<String, Stop> stops = parseStops(stopLines);
        HashMap<String, Route> routes = parseRoutes(routeLines);
        HashMap<String, Trip> trips = parseTrips(routes,tripLines);
        HashMap<String, StopTime> stopTimes = parseStopTimes(trips, stops,stopTimeLines);

        // add our GTFS elements to our feed
        feed.addAllRoutes(new ArrayList<>(routes.values()));
        feed.addAllTrips(new ArrayList<>(trips.values()));
        feed.addAllStopTimes(new ArrayList<>(stopTimes.values()));
        feed.addAllStops(new ArrayList<>(stops.values()));

    }

    /**
     * Exports a GTFS file to a specified directory
     */
    public void save(Path path) {
        // TODO - needs implementation eventually
        throw new UnsupportedOperationException();
    }

    /**
     * Gets the GTFS feed
     *
     * @return the GTFS feed
     */
    public Feed getFeed() {
        return feed;
    }

    /**
     * Extracts the four required GTFS files from a list of files and assigns them to the GTFS File's file attributes
     *
     * @param files a list of files
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

    private static boolean validateRoutes(List<String> lines) throws  IOException {

        // get format for file
        List<String> format = tokenizeLine(lines.get(0));

        // check if format contains route_id field
        if(!format.contains("route_id")) {
            throw new IOException();
        }

        // Check each line for proper information
        for (int i = 1; i < lines.size() - 1; i++) {
            // tokenize current line
            List<String> currentLine = tokenizeLine(lines.get(i));

            // make sure all expected elements are there
            if(currentLine.size() != format.size()) {
                throw new IOException("Missing one or more required GTFS attributes in \"routes.txt\".");
            }

            // check if route id is present
            int routeIdIndex = format.indexOf("route_id");
            String routeID = currentLine.get( routeIdIndex);
            if(routeID.isEmpty()) {
                throw new IOException("One or more invalid GTFS attributes in file \"routes.txt\".");
            }

            // check if route id already exists
            if(RouteID.exists(routeID)) {
                throw new IOException("One or more duplicate GTFS attributes in file \"routes.txt\".");
            }
        }

        return true;
    }

    /**
     * Parse through stops file to check that all data is valid
     * @param lines List of each line in the stops file
     * @return True if the file is valid
     * @throws IOException Thrown if there is invalid data in the file
     */
    public static boolean validateStops(List<String> lines) throws IOException {

        // get format for file
        List<String> format = tokenizeLine(lines.get(0));

        // check if format contains stop_id field
        if(!format.contains("stop_id")) {
            throw new IOException("Missing attribute \"stop_id\"");
        }

        // Check each line for proper information
        for (int i = 1; i < lines.size() - 1; i++) {
            // tokenize current line
            List<String> currentLine = tokenizeLine(lines.get(i));

            // make sure all expected elements are there
            if(currentLine.size() != format.size()) {
                throw new IOException("Missing one or more required GTFS attributes in file \"stops.txt\".");
            }

            // check if stop id is present
            int stopIdIndex = format.indexOf("stop_id");
            String stopID = currentLine.get(stopIdIndex);
            if(stopID.isEmpty()) {
                throw new IOException("One or more invalid GTFS attributes in file \"stops.txt\".");
            }

            // check if stop id already exists
            if(StopID.exists(stopID)) {
                throw new IOException("One or more duplicate GTFS attributes in file \"stops.txt\".");
            }
        }

        return true;
    }

    /**
     * Parse through stop times file to check that all data is valid
     * @param lines List of each line in the stop times file
     * @return True if the file is valid
     * @throws IOException Thrown if there is invalid data in the file
     */
    public static boolean validateStopTimes(List<String> lines) throws IOException {

        // get format for file
        List<String> format = tokenizeLine(lines.get(0));

        // check for required fields in format line
        if(!format.contains("trip_id") || !format.contains("stop_id") || !format.contains("stop_sequence")) {
            throw new IOException("Missing one or more required attributes in first line of \"stops_times.txt\"");
        }

        for (int i = 1; i < lines.size() - 1; i++) {
            // tokenize current line
            List<String> currentLine = tokenizeLine(lines.get(i));

            // check if line has required number of elements
            if(currentLine.size() != format.size()) {
                throw new IOException("Missing one or more attributes in line of \"stops_times.txt\"");
            }

            // check if all required attributes for line are present
            String tripID = currentLine.get(format.indexOf("trip_id"));
            String stopID = currentLine.get(format.indexOf("stop_id"));
            String stopSequence = currentLine.get(format.indexOf("stop_sequence"));
            if(tripID.isEmpty()) {
                throw new IOException("Missing attribute \"trip_id\" in line of \"stops_times.txt\"");
            }
            if(stopID.isEmpty()) {
                throw new IOException("Missing attribute \"stop_id\" in line of \"stops_times.txt\"");
            }
            if(stopSequence.isEmpty()) {
                throw new IOException("Missing attribute \"stop_sequence\" in line of \"stops_times.txt\"");
            }
        }

        return true;
    }

    /**
     * Parse through Trips file to check that all data is valid
     * @param lines List of each line in the stops file
     * @return True if the file is valid
     * @throws IOException Thrown if there is invalid data in the file
     */
    public static boolean validateTrips(List<String> lines) throws IOException {

        // get format for file
        List<String> format = tokenizeLine(lines.get(0));

        // check if format contains stop_id field
        if(!format.contains("trip_id")) {
            throw new IOException();
        }

        // Check each line for proper information
        for (int i = 1; i < lines.size() - 1; i++) {
            // tokenize current line
            List<String> currentLine = tokenizeLine(lines.get(i));

            // make sure all expected elements are there
            if(currentLine.size() != format.size()) {
                throw new IOException("Missing one or more required GTFS attributes in file \"trips.txt\".");
            }

            // check if stop id is present
            int tripIdIndex = format.indexOf("trip_id");
            String tripID = currentLine.get(tripIdIndex);
            if(tripID.isEmpty()) {
                throw new IOException("One or more invalid GTFS attributes in file \"trips.txt\".");
            }

            // check if stop id already exists
            if(StopID.exists(tripID)) {
                throw new IOException("One or more duplicate GTFS attributes in file \"tripss.txt\".");
            }
        }

        return true;
    }

    /**
     * Converts a hex color string to a Java FX color
     *
     * @param hex the hex color string to parse
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
     *
     * @param timeString the time string
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

    /**
     * Tokenizes a line from a CSV file using ',' as a delimiter
     *
     * @param line the line to tokenize
     * @return a list of string tokens
     */
    private static List<String> tokenizeLine(String line) {
        return Arrays.asList(line.split(",", -1));
    }


    /**
     * Parses trips from the GTFS trips file
     *
     * @param routes the list of routes that the trips should be linked to
     * @return the list of trips
     */
    private HashMap<String, Trip> parseTrips(HashMap<String, Route> routes, List<String> lines) {
        // Get the format of the attributes for the file
        List<String> format = tokenizeLine(lines.get(0));

        // Create hash map of trips to be returned
        HashMap<String, Trip> trips = new HashMap<>();

        for(int i = 1; i < lines.size(); i++) {
            // Create new hash map to create a trip from this line
            HashMap<String, String> tripFields = new HashMap<>();

            // Get line in file
            List<String> currentLine = tokenizeLine(lines.get(i));

            // Add each attribute in line to hash map
            for(int j = 0; j < format.size(); j++) {
                tripFields.put(format.get(i),currentLine.get(i));
            }

            // Create new trip for this line
            Trip trip;
            String tripID;
            tripID = tripFields.get("trip_id");
            trip = new Trip(feed, tripID);


            // Add trip to its route
            if(tripFields.containsKey("route_id")) {
                String routeID = tripFields.get("route_id");
                Route route;
                if (!routeID.isEmpty()) {
                    route = routes.get(routeID);
                    route.addTrip(trip);
                }
            }

            // Set trip headsign
            if(tripFields.containsKey("trip_headsign")) {
                String headSign = tripFields.get("trip_headsign");
                if (!headSign.isEmpty()) {
                    trip.setHeadSign(headSign);
                }
            }

            // Add trip to return hash map
            trips.put(tripID,trip);
        }
        return trips;
    }

    /**
     * Parses routes from the GTFS routes file
     *
     * @return the parsed routes as a list
     */
    private HashMap<String, Route> parseRoutes(List<String> lines) {
        // get format of the file
        List<String> format = tokenizeLine(lines.get(0));
        lines.remove(0);

        // create a new list of routes
        HashMap<String, Route> routes = new HashMap<>();

        for(int i = 0; i < lines.size(); i++) {
            // create a new hash map for the attributes of the route for this line
            HashMap<String, String> routeFields = new HashMap<>();

            // get next line from file
            List<String> currentLine = tokenizeLine(lines.get(i));

            // put all route attributes into hash map
            for(int j = 0; j < format.size(); j++) {
                routeFields.put(format.get(i),currentLine.get(i));
            }

            // get route ID and route type
            String routeID = routeFields.get("route_id");
            String routeType = routeFields.get("route_type");
            int routeTypeValue = Integer.parseInt(routeType);

            // create new route
            Route route = new Route(feed, routeID, RouteType.values()[routeTypeValue]);

            // set route short name
            String shortName = routeFields.get("route_short_name");
            if(!shortName.isEmpty()) {
                route.setShortName(shortName);
            }

            // set long name
            String longName = routeFields.get("route_long_name");
            if(!longName.isEmpty()) {
                route.setLongName(longName);
            }

            // set description
            String description = routeFields.get("route_desc");
            if(!description.isEmpty()) {
                route.setDesc(description);
            }

            // set url
            String url = routeFields.get("route_url");
            if(!url.isEmpty()) {
                route.setURL(url);
            }

            // set color
            String color = routeFields.get("route_color");
            if(!color.isEmpty()) {
                route.setColor(hexToColor(color));
            }

            // set text color
            String textColor = routeFields.get("route_text_color");
            if(!textColor.isEmpty()) {
                route.setTextColor(hexToColor(textColor));
            }

            // add route to return hash map
            routes.put(routeID, route);
        }

        return routes;
    }

    private HashMap<String, StopTime> parseStopTimes(HashMap<String, Trip> trips, HashMap<String, Stop> stops, List<String> lines) {
        // get format of the file
        List<String> format = tokenizeLine(lines.get(0));
        lines.remove(0);

        // create a new list of stop times
        HashMap<String, StopTime> stopTimes = new HashMap<>();

        for(int i = 0; i < lines.size(); i++) {
            // create a new hash map for the attributes of the route for this line
            HashMap<String, String> stopTimeFields = new HashMap<>();

            // get next line from file
            List<String> currentLine = tokenizeLine(lines.get(i));

            // put all route attributes into hash map
            for(int j = 0; j < format.size(); j++) {
                stopTimeFields.put(format.get(i),currentLine.get(i));
            }

            // Get required attributes for stop time
            String stopID = stopTimeFields.get("stop_id");
            int sequence = Integer.parseInt(stopTimeFields.get("stop_sequence"));
            Stop stop = stops.get(stopID);

            StopTime stopTime = new StopTime(feed, stop, sequence);

            // set arrival time
            String arrivalTime = stopTimeFields.get("arrival_time");
            if(!arrivalTime.isEmpty()) {
                Date date = timeStringToTime(arrivalTime);
                stopTime.setArrivalTime(date);
            }

            // set departure time
            String departureTime = stopTimeFields.get("departure_time");
            if(!arrivalTime.isEmpty()) {
                Date date = timeStringToTime(departureTime);
                stopTime.setDepartureTime(date);
            }

            // set headsign
            String headsign = stopTimeFields.get("stop_headsign");
            if(!headsign.isEmpty()) {
                stopTime.setHeadSign(headsign);
            }

            // get stop time id
            String stopTimeID = stopTime.getID().getIDString();

            // add stop time to return hash map
            stopTimes.put(stopTimeID, stopTime);
        }

        return stopTimes;
    }

    /**
     * Parses stops from the GTFS stops file adn returns them as a list
     *
     * @return the list of stops
     */
    private HashMap<String, Stop> parseStops(List<String> lines) {
        // get format of the file
        List<String> format = tokenizeLine(lines.get(0));
        lines.remove(0);

        // create a new list of stop times
        HashMap<String, Stop> stops = new HashMap<>();

        for(int i = 0; i < lines.size(); i++) {

            // create a new hash map for the attributes of the route for this line
            HashMap<String, String> stopFields = new HashMap<>();

            // get next line from file
            List<String> currentLine = tokenizeLine(lines.get(i));

            // put all route attributes into hash map
            for(int j = 0; j < format.size(); j++) {
                stopFields.put(format.get(i),currentLine.get(i));
            }

            // get stop id and create new stop
            String stopID = stopFields.get("stop_id");
            Stop stop = new Stop(feed, stopID);

            // set stop name
            if (stopFields.containsKey("stop_name")) {
                String stopName = stopFields.get("stop_name");
                if (!stopName.isEmpty()) {
                    stop.setName(stopName);
                }
            }

            // set stop description
            if (stopFields.containsKey("stop_desc")) {
                String stopDesc = stopFields.get("stop_desc");
                if (!stopDesc.isEmpty()) {
                    stop.setDesc(stopDesc);
                }
            }

            // set stop location
            String stopLat = stopFields.get("stop_lat");
            String stopLon = stopFields.get("stop_lon");
            if(!stopLat.isEmpty() && !stopLon.isEmpty()) {
                double lat = Double.parseDouble(stopLat);
                double lon = Double.parseDouble(stopLon);
                // TODO - replace Point2D with Location on merge w/ master
                stop.setLocation(new Point2D(lon, lat));
            }

            // set stop url
            if (stopFields.containsKey("stop_url")) {
                String url = stopFields.get("stop_url");
                if(!url.isEmpty()) {
                    stop.setURL(url);
                }
            }

            stops.put(stop.getID().getIDString(), stop);

        }

        return stops;
    }
}