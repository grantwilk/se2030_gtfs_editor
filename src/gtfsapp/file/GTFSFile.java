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
 * @author Michael Primeau, Colton Rivard, Grant Wilk
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

    private boolean validateRoutes() throws IOException {

        // gets name of the file
        String filename = routeFile.getName();

        //get all lines from file
        List<String> lines = Files.readAllLines(routeFile.toPath());

        //creates keys from the first line of code
        List<String> format = tokenizeLine(lines.get(0));

        for(int i = 1; 1 < lines.size(); i++) {
            HashMap<String,String> validationKey = new HashMap<String, String>();
            List<String> currentLine = tokenizeLine(lines.get(i));

            if(format.size() != currentLine.size()){
                throw new IOException("The amount of elements is not equal to how big the key is");
            }


        }

        // temp return
        return true;
    }

    /**
     * Parses routes from the GTFS routes file
     *
     * @return the parsed routes as a list
     */
    private HashMap<String, Route> parseRoutes() throws IOException {

        // get the name of the file for exceptions
        String fileName = routeFile.getName();

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

            // check to see if there are enough tokens to get required information
            if (tokens.size() < 6) {
                throw new IOException("Missing one or more required GTFS attributes in file \"" + fileName + "\".");
            }

            // extract required values
            String routeID = tokens.get(0);
            String routeType = tokens.get(5);

            // throw an exception if the route ID is empty
            if (routeID.isEmpty()) {
                throw new IOException("One or more invalid GTFS attributes in file \"" + fileName + "\".");
            }

            // throw an exception if route ID already exists
            if (RouteID.exists(routeID)) {
                throw new IOException("One or more duplicate GTFS attributes in file \"" + fileName + "\".");
            }

            // throw an exception if route type is empty
            if (routeType.isEmpty()) {
                throw new IOException("One or more invalid GTFS attributes in file \"" + fileName + "\".");
            }

            // throw an exception if route type is not a number
            int routeTypeValue;
            try {
                routeTypeValue = Integer.parseInt(routeType);
            } catch (NumberFormatException e) {
                throw new IOException("One or more invalid GTFS attributes in file \"" + fileName + "\".");
            }

            // throw an exception if route type is not a valid route type enum
            if (routeTypeValue > RouteType.values().length) {
                throw new IOException("One or more invalid GTFS attributes in file \"" + fileName + "\".");
            }

            // create a new route
            Route route = new Route(feed, routeID, RouteType.values()[routeTypeValue]);

            // extract extra values

            // get and set agency ID (ignored)
            // String agencyID = tokens.get(1);

            // get and set route short name
            String routeShortName = tokens.get(2);
            if (!routeShortName.isEmpty()) {
                route.setShortName(routeShortName);
            }

            // get  and set route long name
            String routeLongName = tokens.get(3);
            if (!routeLongName.isEmpty()) {
                route.setLongName(routeLongName);
            }

            // get and set route description
            String routeDesc = tokens.get(4);
            if (!routeDesc.isEmpty()) {
                route.setDesc(routeDesc);
            }

            // get and set route URL
            if (tokens.size() > 6) {
                String routeURL = tokens.get(6);
                if (!routeURL.isEmpty()) {
                    route.setURL(routeURL);
                }
            }

            // get and set route color
            if (tokens.size() > 7) {
                String routeColor = tokens.get(7);
                if (!routeColor.isEmpty()) {
                    route.setColor(hexToColor(routeColor));
                }
            }

            // get and set route text color
            if (tokens.size() > 8) {
                String routeTextColor = tokens.get(8);
                if (!routeTextColor.isEmpty()) {
                    route.setTextColor(hexToColor(routeTextColor));
                }
            }

            // add our routes to the routes list
            routes.put(routeID, route);

        }

        return routes;

    }

//    private HashMap<String, Trip> parseTrips(HashMap<String, Route> routes) throws IOException {
//
//        // get the name of the file for exceptions
//        String fileName = tripFile.getName();
//
//        // get all lines from the file
//        List<String> lines = Files.readAllLines(tripFile.toPath());
//
//        // remove the first line because it contains a template
//        lines.remove(0);
//
//        // create a new list of trips
//        HashMap<String, Trip> trips = new HashMap<>();
//
//        // for each line in the file
//        for (String line : lines) {
//
//            // split the line into comma separated tokens
//            List<String> tokens = tokenizeLine(line);
//
//            if (tokens.size() < 3) {
//                throw new IOException("Missing one or more required GTFS attributes in file \"" + fileName + "\".");
//            }
//
//            // extract required values
//            String routeID = tokens.get(0);
//            String tripID = tokens.get(2);
//
//            // throw an exception if the route ID is empty
//            if (routeID.isEmpty()) {
//                throw new IOException("One or more invalid GTFS attributes in file \"" + fileName + "\".");
//            }
//
//            // throw an exception if route ID does not exists
//            if (!RouteID.exists(routeID)) {
//                throw new IOException("One or more missing dependent data in file \"" + fileName + "\".");
//            }
//
//            // throw an exception if the trip ID is empty
//            if (tripID.isEmpty()) {
//                throw new IOException("One or more invalid GTFS attributes in file \"" + fileName + "\".");
//            }
//
//            // throw an exception if trip ID already exists
//            if (TripID.exists(tripID)) {
//                throw new IOException("One or more duplicate GTFS attributes in file \"" + fileName + "\".");
//            }
//
//            // create a trip and add it to the route
//            Trip trip = new Trip(feed, tripID);
//            Route route = routes.get(routeID);
//            route.addTrip(trip);
//
//            // extract extra values
//
//            // get and set service ID (ignored)
//            // String serviceID = tokens.get(1);
//
//            // get and set head sign
//            if (tokens.size() > 3) {
//                String headSign = tokens.get(3);
//                if (!headSign.isEmpty()) {
//                    trip.setHeadSign(headSign);
//                }
//            }
//
//            // get and set direction ID (ignored)
//            // String directionID = tokens.get(4);
//
//            // get and set block ID (ignored)
//            // String blockID = tokens.get(5);
//
//            // get and set shape ID (ignored)
//            // String shapeID = tokens.get(6);
//
//            // add trip to list of trips
//            trips.put(tripID, trip);
//
//        }
//
//        return trips;
//
//    }

    /**
     * Parses stop times from the GTFS stop times file
     *
     * @param trips the list of trips that the stop times should be linked to
     * @param stops the list of stops that the stop times should be linked to
     * @return the list of stop times
     */
    private HashMap<String, StopTime> parseStopTimes(HashMap<String, Trip> trips, HashMap<String, Stop> stops) throws IOException {

        // get the name of the file for exceptions
        String fileName = stopTimesFile.getName();

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

            // check to see if there are enough tokens to get required information
            if (tokens.size() < 5) {
                throw new IOException("Missing one or more required GTFS attributes in file \"" + fileName + "\".");
            }

            // extract all values
            String tripID = tokens.get(0);
            String stopID = tokens.get(3);
            String stopSequence = tokens.get(4);

            // throw an exception if trip ID is empty
            if (tripID.isEmpty()) {
                throw new IOException("One or more invalid GTFS attributes in file \"" + fileName + "\".");
            }

            // throw an exception if trip ID does not exist
            if (!TripID.exists(tripID)) {
                throw new IOException("One or more duplicate GTFS attributes in file \"" + fileName + "\".");
            }

            // throw an exception if stop ID is empty
            if (stopID.isEmpty()) {
                throw new IOException("One or more invalid GTFS attributes in file \"" + fileName + "\".");
            }

            // throw an exception if stop ID does not exist
            if (!StopID.exists(stopID)) {
                throw new IOException("One or more missing dependent data in file \"" + fileName + "\".");
            }

            int sequence;
            try {
                sequence = Integer.parseInt(stopSequence);
            } catch (NumberFormatException e) {
                throw new IOException("One or more invalid GTFS attributes in file \"" + fileName + "\".");
            }

            // get stop object
            Stop stop = stops.get(stopID);

            // create a stop time
            StopTime stopTime = new StopTime(feed, stop, sequence);

            // extract extra values

            // get and set arrival time
            String arrivalTime = tokens.get(1);
            if (!arrivalTime.isEmpty()) {
                stopTime.setArrivalTime(timeStringToTime(arrivalTime));
            }

            // get and set departure time
            String departureTime = tokens.get(2);
            if (!departureTime.isEmpty()) {
                stopTime.setDepartureTime(timeStringToTime(departureTime));
            }

            // get and set head sign
            if (tokens.size() > 5) {
                String headSign = tokens.get(5);
                if (!headSign.isEmpty()) {
                    stopTime.setHeadSign(headSign);
                }
            }

            // get and set pickup type (ignored)
            // String pickupType = tokens.get(6);

            // get and set drop off time (ignored)
            // String dropOffTime = tokens.get(7);

            // get and set shape distance traveled (ignored)
            // String shapeDistTraveled = tokens.get(8);

            // get stop time id string
            String stopTimeIDString = stopTime.getID().getIDString();

            // add stop time to list of stop times
            stopTimes.put(stopTimeIDString, stopTime);

        }

        return stopTimes;

    }

    /**
     * Parses stops from the GTFS stops file adn returns them as a list
     *
     * @return the list of stops
     */
    private HashMap<String, Stop> parseStops() throws IOException {

        // get the name of the file for exceptions
        String fileName = stopFile.getName();

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

            // check to see if there are enough tokens to get required information
            if (tokens.size() < 1) {
                throw new IOException("Missing one or more required GTFS attributes in file \"" + fileName + "\".");
            }

            // extract all values
            String stopID = tokens.get(0);

            // throw an exception if stop ID is empty
            if (stopID.isEmpty()) {
                throw new IOException("One or more invalid GTFS attributes in file \"" + fileName + "\".");
            }

            // throw an exception if stop ID already exists
            if (StopID.exists(stopID)) {
                throw new IOException("One or more duplicate GTFS attributes in file \"" + fileName + "\".");
            }

            // create a new stop
            Stop stop = new Stop(feed, stopID);

            // get and set stop name
            if (tokens.size() > 1) {
                String stopName = tokens.get(1);
                if (!stopName.isEmpty()) {
                    stop.setName(stopName);
                }
            }

            // get and set stop description
            if (tokens.size() > 2) {
                String stopDesc = tokens.get(2);
                if (!stopDesc.isEmpty()) {
                    stop.setDesc(stopDesc);
                }
            }

            // get and set location
            if (tokens.size() > 3) {

                String stopLat = tokens.get(3);

                // if longitude also exists
                if (tokens.size() > 4) {

                    String stopLon = tokens.get(4);

                    // if both are not empty
                    if (!stopLat.isEmpty() && !stopLon.isEmpty()) {

                        // parse both and set as location
                        try {

                            double lat = Double.parseDouble(stopLat);
                            double lon = Double.parseDouble(stopLon);

                            stop.setLocation(new Point2D(lon, lat));

                        } catch (NumberFormatException e) {
                            throw new IOException("One or more invalid GTFS attributes in file \"" + fileName + "\".");
                        }
                    } else {
                        throw new IOException("One or more missing dependent data in file \"" + fileName + "\".");
                    }
                } else {
                    throw new IOException("One or more missing dependent data in file \"" + fileName + "\".");
                }
            }

            // get and set zone ID (ignored)
            // String zoneID = tokens.get(5);

            // get and set stop URL
            if (tokens.size() > 6) {
                String stopURL = tokens.get(6);
                if (!stopURL.isEmpty()) {
                    stop.setURL(stopURL);
                }
            }

            // add our stops to the stops list
            stops.put(stopID, stop);

        }

        return stops;

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
    private List<String> tokenizeLine(String line) {
        return Arrays.asList(line.split(",", -1));
    }


    /**
     * Parses trips from the GTFS trips file
     *
     * @param routes the list of routes that the trips should be linked to
     * @return the list of trips
     */
    private HashMap<String, Trip> parseTrips(HashMap<String, Route> routes) throws IOException {
        // get all lines from the file
        List<String> lines = Files.readAllLines(tripFile.toPath());

        // Get the format of the attributes for the file
        List<String> format = tokenizeLine(lines.get(0));

        // Create hash map of trips to be returned
        HashMap<String, Trip> trips = new HashMap<>();

        for(int i = 1; i < lines.size(); i++) {
            // Create new hash map to create a trip from this line
            HashMap<String, String> tripFields = new HashMap<>();

            // Get next line from file
            List<String> currentLine = tokenizeLine(lines.get(i));

            // Add each attribute in line to hash map
            for(int j = 0; j < format.size(); j++) {
                tripFields.put(format.get(i),currentLine.get(i));
            }

            // Create new trip for this line
            String tripID = tripFields.get("trip_id");
            Trip trip = new Trip(feed, tripID);

            // Add trip to its route
            String routeID = tripFields.get("route_id");
            Route route = routes.get(routeID);
            route.addTrip(trip);

            // Set trip headsign if headsign field is not empty
            String headSign = tripFields.get("trip_headsign");
            if (!headSign.isEmpty()) {
                trip.setHeadSign(headSign);
            }

            // Add trip to return hash map
            trips.put(tripID,trip);

        }
        return trips;
    }

    private HashMap<String, Route> parse() throws IOException {
        // get all lines from the file
        List<String> lines = Files.readAllLines(routeFile.toPath());

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
}