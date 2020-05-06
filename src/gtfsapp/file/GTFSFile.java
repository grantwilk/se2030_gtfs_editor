package gtfsapp.file;

import gtfsapp.id.RouteID;
import gtfsapp.id.StopID;
import gtfsapp.util.Colors;
import gtfsapp.util.Location;
import gtfsapp.util.Time;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
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
     * Regular expression for a GTFS ID
     */
    private static final String ID_REGEX = "^.+";

    /**
     * Regular expression for an unsigned integer
     */
    private static final String UNSIGNED_INT_REGEX = "^[0-9]+";

    /**
     * List of stopIDS from stops.txt
     */
    private static ArrayList<String> stopIDS;

    /**
     * List of tripIDS from trips.txt
     */
    private static List<String> tripIDs;

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
     * The "sunny-day-one.txt" file
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
            throw new IOException("Required GTFS file \"sunny-day-one.txt\" was not found.");
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
        validateTrips(tripLines);
        validateStopTimes(stopTimeLines);
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

                // if the file name is sunny-day-one.txt
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

    public static void validateRoutes(List<String> lines) throws  IOException {

        ArrayList<String> routeIDS = new ArrayList<>();

        // get format for file
        List<String> format = tokenizeLine(lines.get(0));

        // check if format contains route_id field
        if(!format.contains("route_id")) {
            throw new IOException();

        }

        // Check each line for proper information
        for (int i = 1; i < lines.size(); i++) {
            // tokenize current line
            List<String> currentLine = tokenizeLine(lines.get(i));

            // make sure all expected elements are there
            if(currentLine.size() != format.size()) {
                throw new IOException("Missing one or more required GTFS attributes in \"routes.txt\".");
            }

            // check if route id is present
            int routeIdIndex = format.indexOf("route_id");
            String routeID = currentLine.get(routeIdIndex);
            if(routeID.isEmpty()) {
                throw new IOException("ROUTE_ID IS MISSING \"routes.txt\".");
            }

            // check if route id already exists
            if(RouteID.exists(routeID)) {
                throw new IOException("One or more duplicate GTFS attributes in file \"routes.txt\".");
            }
            // looks for duplicate ID
            if(routeIDS.contains(routeID)) {
                throw new IOException("One or more duplicate GTFS attributes in \"stops.txt\".");
            }
            routeIDS.add(routeID);

            //Checks if the color is in the correct format
            int routeColorIndex = format.indexOf("route_color");
            String routeColor = currentLine.get(routeColorIndex);
            if(!Colors.isValidString(routeColor)){
                throw new IOException("Invalidly formatted color in \"stops.txt\".");
            }

        }

    }

    /**
     * Parse through stops file to check that all data is valid
     * @param lines List of each line in the stops file
     * @throws IOException Thrown if there is invalid data in the file
     */
    public static void validateStops(List<String> lines) throws IOException, NumberFormatException {

        // get format for file
        List<String> format = tokenizeLine(lines.get(0));

        // check if format contains stop_id field
        if(!format.contains("stop_id")) {
            throw new IOException("Missing attribute \"stop_id\" in in \"stops.txt\"");
        }

        // check for stop latitude
        if(!format.contains("stop_lat")) {
            throw new IOException("Missing attribute \"stop_lat\" in in \"stops.txt\"");
        }

        // check for stop longitude
        if(!format.contains("stop_lon")) {
            throw new IOException("Missing attribute \"stop_lon\" in in \"stops.txt\"");
        }

        // create list of all stop ids in this file
        stopIDS = new ArrayList<>();

        // Check each line for proper information
        for (int i = 1; i < lines.size(); i++) {
            // tokenize current line
            List<String> currentLine = tokenizeLine(lines.get(i));

            // make sure all expected elements are there
            if(currentLine.size() != format.size()) {
                throw new IOException("Missing one or more required GTFS attributes in \"stops.txt\"");
            }

            // check if stop id is present
            int stopIdIndex = format.indexOf("stop_id");
            String stopID = currentLine.get(stopIdIndex);
            if(stopID.isEmpty()) {
                throw new IOException("One or more invalid GTFS attributes in \"stops.txt\".");
            }

            // check if stop id already exists in file
            if(stopIDS.contains(stopID)) {
                throw new IOException("One or more duplicate GTFS attributes in \"stops.txt\".");
            }
            stopIDS.add(stopID);

            // check if stop id already exists in program
            if(StopID.exists(stopID)) {
                throw new IOException("One or more duplicate GTFS attributes in \"stops.txt\".");
            }

            // check for stop latitude and longitude
            String lat = currentLine.get(format.indexOf("stop_lat"));
            String lon = currentLine.get(format.indexOf("stop_lon"));
            if(lat.isEmpty() || lon.isEmpty()) {
                throw new IOException("One or more invalid GTFS attributes in \"stops.txt\".");
            }
            // check to make sure stop latitude and longitude are doubles
            double stopLat = Double.parseDouble(lat);
            double stopLon = Double.parseDouble(lon);
        }
    }

    /**
     * Parse through stop times file to check that all data is valid
     * @param lines List of each line in the stop times file
     */
    public static void validateStopTimes(List<String> lines) {

        // get format for file
        List<String> format = tokenizeLine(lines.get(0));

        // check that all required fields are present in format line
        if (
                !format.contains("trip_id") ||
                !format.contains("stop_id") ||
                !format.contains("stop_sequence") ||
                !format.contains("arrival_time") ||
                !format.contains("departure_time")
        ) {
            throw new IllegalArgumentException("Missing one or more required attributes in \"stops_times.txt\".");
        }

        // keep track of all currently validated stop times
        ArrayList<Map<String, String>> stopTimes = new ArrayList<>();

        for (int i = 1; i < lines.size(); i++) {

            // tokenize line
            List<String> line = tokenizeLine(lines.get(i));

            // throw an exception if there are more elements in the line than there are format elements
            if (line.size() > format.size()) {
                throw new IllegalArgumentException();
            }

            // map fields into a hash map
            Map<String, String> stopTimeFields = new HashMap<>();
            for (int j = 0; j < line.size(); j++) {
                stopTimeFields.put(format.get(j), line.get(j));
            }

            // get all of the values from the hash map
            String tripID = stopTimeFields.get("trip_id");
            String stopID = stopTimeFields.get("stop_id");
            String stopSequence = stopTimeFields.get("stop_sequence");
            String arrivalTime = stopTimeFields.get("arrival_time");
            String departureTime = stopTimeFields.get("departure_time");

            // throw an exception if any required attributes are null
            if (tripID == null) {
                throw new IllegalArgumentException("Missing one or more required attributes in \"stops_times.txt\".");
            }
            if (stopID == null) {
                throw new IllegalArgumentException("Missing one or more required attributes in \"stops_times.txt\".");
            }
            if (stopSequence == null) {
                throw new IllegalArgumentException("Missing one or more required attributes in \"stops_times.txt\".");
            }
            if (arrivalTime == null) {
                throw new IllegalArgumentException("Missing one or more required attributes in \"stops_times.txt\".");
            }
            if (departureTime == null) {
                throw new IllegalArgumentException("Missing one or more required attributes in \"stops_times.txt\".");
            }

            // throw an exception if any required attributes are invalidly formatted
            if (!tripID.matches(ID_REGEX)) {
                throw new IllegalArgumentException("Invalidly formatted trip ID in \"stop_times.txt\".");
            }
            if (!stopID.matches(ID_REGEX)) {
                throw new IllegalArgumentException("Invalidly formatted stop ID in \"stop_times.txt\".");
            }
            if (!stopSequence.matches(UNSIGNED_INT_REGEX)) {
                throw new IllegalArgumentException("Invalidly formatted stop sequence in \"stop_times.txt\".");
            }
            if (!arrivalTime.matches(Time.getRegex())) {
                throw new IllegalArgumentException("Invalidly formatted arrival time in \"stop_times.txt\".");
            }
            if (!departureTime.matches(Time.getRegex())) {
                throw new IllegalArgumentException("Invalidly formatted departure time in \"stop_times.txt\".");
            }
            if(!stopIDS.contains(stopID)) {
                throw new IllegalArgumentException("Stop time includes stop_id that is never referenced in \"stops.txt\".");
            }
            if(!tripIDs.contains(tripID)) {
                throw new IllegalArgumentException("Stop time includes trip_id that is never referenced in \"trips.txt\".");
            }

            // check if arrival time is before departure time
            Time arrival = new Time(arrivalTime);
            Time departure = new Time(departureTime);
            if(arrival.getMillis() > departure.getMillis()) {
                throw new IllegalArgumentException("Invalidly formatted arrival and departure time in \"stop_time.txt\"");
            }

            // check if trip id already exists in file
            ArrayList<Map<String, String>> trip = new ArrayList<>();
            for(Map<String, String> stopTime:stopTimes) {
                if(stopTime.get("trip_id").equals(tripID)) {
                    // if it does already exist get all stop times with same trip id
                    trip.add(stopTime);
                }
            }

            // compare arrival time of current stop time with all associated stop times
            for(Map<String, String> compareTrip:trip) {
                Time compareArrivalTime = new Time(compareTrip.get("arrival_time"));
                Time currentArrivalTime = new Time(stopTimeFields.get("arrival_time"));
                Time compareDepartureTime = new Time(compareTrip.get("departure_time"));
                Time currentDepartureTime = new Time(stopTimeFields.get("departure_time"));
                int compareSequence = Integer.parseInt(compareTrip.get("stop_sequence"));
                int currentSequence = Integer.parseInt(stopTimeFields.get("stop_sequence"));

                // check sequences and arrival and departure times for correct ordering
                if(compareSequence < currentSequence) {
                    if(!(compareArrivalTime.getMillis() < currentArrivalTime.getMillis())) {
                        throw new IllegalArgumentException("Invalidly formatted arrival and departure time in \"stop_time.txt\"");
                    }
                    if(!(compareDepartureTime.getMillis() < currentDepartureTime.getMillis())) {
                        throw new IllegalArgumentException("Invalidly formatted arrival and departure time in \"stop_time.txt\"");
                    }
                } else if(compareSequence > currentSequence) {
                    if(!(compareArrivalTime.getMillis() > currentArrivalTime.getMillis())) {
                        throw new IllegalArgumentException("Invalidly formatted arrival and departure time in \"stop_time.txt\"");
                    }
                    if(!(compareDepartureTime.getMillis() > currentDepartureTime.getMillis())) {
                        throw new IllegalArgumentException("Invalidly formatted arrival and departure time in \"stop_time.txt\"");
                    }
                } else {
                    throw new IllegalArgumentException("Duplicate stop sequences for a trip in \"stop_time.txt\"");
                }
            }

            // add stop time to current line to list of all validated stop times
            stopTimes.add(stopTimeFields);

        }

    }

    /**
     * Parse through Trips file to check that all data is valid
     * @param lines List of each line in the stops file
     * @return True if the file is valid
     * @throws IOException Thrown if there is invalid data in the file
     */
    public static void validateTrips(List<String> lines) throws IOException {

        // get format for file
        List<String> format = tokenizeLine(lines.get(0));

        // check if format contains trip_id field and route_id field
        if(!format.contains("trip_id") || !format.contains("route_id")||!format.contains("service_id")) {
            throw new IOException("Missing one or more required attributes in first line of \"trip.txt\".");
        }

        tripIDs = new ArrayList<>();

        // Check each line for proper information
        for (int i = 1; i < lines.size(); i++) {

            // tokenize current line
            List<String> line = tokenizeLine(lines.get(i));

            // throw an exception if there are more elements in the line than there are format elements
            if (line.size() > format.size()) {
                throw new IllegalArgumentException("Too many elements in file \"trips.txt\".");
            }

            // map fields into a hash map
            Map<String, String> tripFields = new HashMap<>();
            for (int j = 0; j < line.size(); j++) {
                tripFields.put(format.get(j), line.get(j));
            }

            // get all of the values from the hash map
            String routeID = tripFields.get("route_id");
            String tripID = tripFields.get("trip_id");

            // throw an exception if any required attributes are null
            if (routeID == null) {
                throw new IllegalArgumentException("Missing one or more required attributes in \"stops_times.txt\".");
            }
            if (tripID == null) {
                throw new IllegalArgumentException("Missing one or more required attributes in \"stops_times.txt\".");
            }

            // throw an exception if any required attributes are invalidly formatted
            if (!routeID.matches(ID_REGEX)) {
                throw new IllegalArgumentException("Invalidly formatted route ID in \"stop_times.txt\".");
            }
            if (!tripID.matches(ID_REGEX)) {
                throw new IllegalArgumentException("Invalidly formatted trip ID in \"stop_times.txt\".");
            }

            // throw an exception if the trip ID already exists
            if (tripIDs.contains(tripID)) {
                throw new IllegalArgumentException("One or more IDs occur multiple times in \"stop_times.txt\".");
            }

            // add the trip IDs
            tripIDs.add(tripID);

        }

    }

    /**
     * Tokenizes a line from a CSV file using ',' as a delimiter
     *
     * @param line the line to tokenize
     * @return a list of string tokens
     */
    private static List<String> tokenizeLine(String line) {
        List<String> tokens = Arrays.asList(line.split(",", -1));
        ArrayList<String> returnList = new ArrayList<>();
        for(int i = 0; i < tokens.size(); i++) {
            if(tokens.get(i).indexOf("\"") == 0 && tokens.get(i).lastIndexOf("\"") == 0) {
                String concatenatedString = tokens.get(i);
                int j = i;
                while (tokens.get(j).indexOf("\"") != (tokens.get(j).length() - 1)) {
                    concatenatedString = concatenatedString + tokens.get(j);
                }
                returnList.add(concatenatedString);
                i = j;
            } else {
                returnList.add(tokens.get(i));
            }
        }
        return returnList;
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
            List<String> line = tokenizeLine(lines.get(i));

            // Add each attribute in line to hash map
            for(int j = 0; j < line.size(); j++) {
                tripFields.put(format.get(j),line.get(j));
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
            List<String> line = tokenizeLine(lines.get(i));

            // put all route attributes into hash map
            for(int j = 0; j < line.size(); j++) {
                routeFields.put(format.get(j),line.get(j));
            }

            // get route ID and route type
            String routeID = routeFields.get("route_id");
            String routeType = routeFields.get("route_type");
            int routeTypeValue = Integer.parseInt(routeType);

            // create new route
            Route route = new Route(feed, routeID, RouteType.values()[routeTypeValue]);

            // set route short name
            String shortName = routeFields.get("route_short_name");
            route.setShortName(shortName);

            // set long name
            String longName = routeFields.get("route_long_name");
            route.setLongName(longName);

            // set description
            String description = routeFields.get("route_desc");
            route.setDesc(description);

            // set url
            String url = routeFields.get("route_url");
            route.setURL(url);

            // set color
            String color = routeFields.get("route_color");
            route.setColor(Colors.fromString(color));

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
            List<String> line = tokenizeLine(lines.get(i));

            // put all route attributes into hash map
            for(int j = 0; j < line.size(); j++) {
                stopTimeFields.put(format.get(j),line.get(j));
            }

            // Get required attributes for stop time
            String stopID = stopTimeFields.get("stop_id");
            Time arrivalTime = new Time(stopTimeFields.get("arrival_time"));
            Time departureTime = new Time(stopTimeFields.get("departure_time"));
            Stop stop = stops.get(stopID);

            // create a new stop time
            StopTime stopTime = new StopTime(feed, stop, arrivalTime, departureTime);

            // Add stop time to its trip
            Trip trip = trips.get(stopTimeFields.get("trip_id"));
            trip.addStopTime(stopTime);

            // set headsign
            String headsign = stopTimeFields.get("stop_headsign");
            stopTime.setHeadSign(headsign);

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

            // create a new hash map for the attributes of the stop for this line
            HashMap<String, String> stopFields = new HashMap<>();

            // get next line from file
            List<String> line = tokenizeLine(lines.get(i));

            // put all stop attributes into hash map
            for(int j = 0; j < line.size(); j++) {
                stopFields.put(format.get(j),line.get(j));
            }

            // get stop id and create new stop
            String stopID = stopFields.get("stop_id");
            Stop stop = new Stop(feed, stopID);

            // set stop name
            String stopName = stopFields.get("stop_name");
            stop.setName(stopName);

            // set stop description
            String stopDesc = stopFields.get("stop_desc");
            stop.setDesc(stopDesc);

            // set stop location
            String stopLat = stopFields.get("stop_lat");
            String stopLon = stopFields.get("stop_lon");
            if(!stopLat.isEmpty() && !stopLon.isEmpty()) {
                double lat = Double.parseDouble(stopLat);
                double lon = Double.parseDouble(stopLon);
                stop.setLocation(new Location(lat, lon));
            }

            // set stop url
            String url = stopFields.get("stop_url");
            stop.setURL(url);

            stops.put(stop.getID().getIDString(), stop);

        }

        return stops;
    }
}