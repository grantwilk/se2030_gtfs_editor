package gtfsapp.file;

import gtfsapp.id.RouteID;
import gtfsapp.id.StopID;
import gtfsapp.id.StopTimeID;
import gtfsapp.id.TripID;

import javafx.scene.paint.Color;
import javafx.geometry.Point2D;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Colton Rivard
 * @version 1.0
 * @created 15-Apr-2020 1:20:18 PM
 */
public class Route extends GTFSElement {

    /**
     * The feed that contains this route
     */
    private Feed feed;

    /**
     * The type of transportation used on the route
     */
    private RouteType routeType;

    /**
     * The trips that occur within this route
     */
    private HashMap<TripID, Trip> trips;

    /**
     * The short name of the route
     */
    private String shortName;

    /**
     * The long name of the route
     */
    private String longName;

    /**
     * The description of the route
     */
    private String desc;

    /**
     * The URL of the route
     */
    private String url;

    /**
     * The color of the route
     */
    private Color color;

    /**
     * The text color of the route
     */
    private Color textColor;

    /**
     * Constructor for a Route
     * @param id ID associated with this route
     * @param feed Reference to Feed object
     * @param routeType Route type for this specific route
     */
    public Route(Feed feed, String id, RouteType routeType) {
        // TODO - this constructor is not fully implemented!
        super(new RouteID(id));
        this.feed = feed;
        this.routeType = routeType;
    }

    /** Adds a list of trips to this route
     * @param trips List of trips to add
     */
    public void addAllTrips(List<Trip> trips) {
        // iterate through list of trips and add them
        for(int x = 0; x <= trips.size(); x++){
            this.trips.put((TripID)trips.get(x).getID(), trips.get(x));
        }
    }



    /** Adds a trip to the hash map of trips for this route
     * @param trip Trip to add to hash map
     */
    public void addTrip(Trip trip) {
        // put trip into hash map
        trips.put((TripID)trip.getID(),trip);

    }

    /**
     * Clears hash map of all trips
     */
    public void clearTrips() {
        // clear hash map
        trips.clear();
    }

    /** Check if this route contains a specific stop
     * @param id Stop id to check for
     */
    public boolean containsStop(StopID id) {
        return getStopIDs().contains(id);
    }

    /** Checks route for a specific stop time
     * @param id Stop_time id to check for
     */
    public boolean containsStopTime(StopTimeID id) {
        return getStopTimeIDs().contains(id);
    }

    /** Check for a specific trip in this route
     * @param id Trip id to check for
     */
    public boolean containsTrip(TripID id) {
        return trips.containsKey(id);

    }

    public Stop getActiveStop() {
        return null;
    }

    public StopTime getActiveStopTime() {
        return null;
    }

    public Trip getActiveTrip() {
        return null;
    }

    public double getAvgSpeed() {
        return 0;
    }

    public Point2D getBusPosition() {
        return null;
    }

    public double getDistance() {
        return 0;
    }

    public double getDuration() {
        return 0;
    }

    public Feed getFeed() {
        return feed;
    }

    public Stop getNextStop() {
        return null;
    }

    public StopTime getNextStopTime() {
        return null;
    }

    public Trip getNextTrip() {
        return null;
    }

    public Stop getPreviousStop() {
        return null;
    }

    public StopTime getPreviousStopTime() {
        return null;
    }

    public Trip getPreviousTrip() {
        return null;
    }

    /** Get specific stop from this route
     * @param id Stop id to check for
     */
    public Stop getStopByID(StopID id) {
        Stop stop = null;
        // iterate through all trips to find given stop id
        for (Trip trip : trips.values()) {
            if (trip.getStopByID(id) != null) {
                stop = trip.getStopByID(id);
            }
        }
        return stop;
    }

    /**
     * Get a list of the stop ids contained within the route
     * @return List of all stop ids in this route
     */
    public List<StopID> getStopIDs() {
        List<StopID> allStopIDs = new ArrayList<>();
        // add all stop ids from each trip to a new array list
        for (Trip trip : trips.values()) {
            allStopIDs.addAll(trip.getStopIDs());
        }
        // return the array list with duplicates removed
        return allStopIDs.stream().distinct().collect(Collectors.toList());
    }

    /**
     * Get a list of stops contained within the route
     * @return List of all stops in this route
     */
    public List<Stop> getStops() {
        List<Stop> allStops = new ArrayList<>();
        // add all stops from each trip to a new array list
        for (Trip trip : trips.values()) {
            allStops.addAll(trip.getStops());
        }
        // return the array list with duplicates removed
        return allStops.stream().distinct().collect(Collectors.toList());
    }

    /**
     * Get a stop time contained in route by using stop_time id
     * @param id stop_time id to search for in this route
     */
    public StopTime getStopTimeByID(StopTimeID id) {
        StopTime stopTime = null;
        // iterate through each trip and check for the given stop_time id
        for (Trip trip : trips.values()) {
            if (trip.getStopTimeByID(id) != null) {
                stopTime = trip.getStopTimeByID(id);
            }
        }
        return stopTime;
    }

    /**
     * Returns the id of all stop_times contained in this route
     * @return List of all stop_time ids in this route
     */
    public List<StopTimeID> getStopTimeIDs() {
        List<StopTimeID> allStopTimeIDs = new ArrayList<>();
        // iterate through all trips and add stop_times ids to new array list
        for (Trip trip : trips.values()) {
            allStopTimeIDs.addAll(trip.getStopTimeIDs());
        }
        // return the array list with duplicates removed
        return allStopTimeIDs.stream().distinct().collect(Collectors.toList());
    }

    /**
     * Returns all the stop_times contained in this route
     * @return List of stop_times in this route
     */
    public List<StopTime> getStopTimes() {
        List<StopTime> allStopTimes = new ArrayList<>();
        // iterate through all trips and add stop_times to new array list
        for (Trip trip : trips.values()) {
            allStopTimes.addAll(trip.getStopTimes());
        }
        // return the array list with duplicates removed
        return allStopTimes.stream().distinct().collect(Collectors.toList());
    }

    /**
     * Get a specific trip in this route by trip id
     * @param id Trip id to search for
     */
    public Trip getTripByID(TripID id) {
        return trips.get(id);
    }

    /**
     * Get a list of trip ids contained in this route
     * @return List of trip ids in this route
     */
    public List<TripID> getTripIDs() {
        Set<TripID> tripIDS = trips.keySet();
        return new ArrayList<>(tripIDS);

    }

    /**
     * Get a list of all trips contained in this route
     * @return List of all trips in this route
     */
    public List<Trip> getTrips() {
        Collection<Trip> routeTrips = trips.values();
        return new ArrayList<>(routeTrips);
    }

    public RouteType getRouteType() {
        return null;
    }

    public void setRouteType(RouteType routeType) {

    }

    public boolean isActive() {
        return false;
    }

    /**
     * Remove a trip from this route
     * @param trip Trip to remove from the route
     */
    public Trip removeTrip(Trip trip) {
        // id of trip to remove
        TripID removedID = (TripID) trip.getID();
        // remove trip and return it
        return trips.remove(removedID);
    }

    /**
     * Remove a trip from this route
     * @param id ID of the trip to remove
     */
    public Trip removeTripByID(TripID id) {
        return trips.remove(id);
    }

    /**
     * Getter for route short name
     * @return Short name of route
     */
    public String getShortName() {
        return shortName;
    }

    /**
     * Setter for route short name
     * @param shortName Short name to set for this route
     */
    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    /**
     * Getter for route long name
     * @return Long name of route
     */
    public String getLongName() {
        return longName;
    }

    /**
     * Setter for route long name
     * @param longName Long name to set for this route
     */
    public void setLongName(String longName) {
        this.longName = longName;
    }

    /**
     * Getter for route color
     * @return Color of this route
     */
    public Color getColor() {
        return color;
    }

    /**
     * Seter for route color
     * @param color Color to set for this route
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * Getter for route text color
     * @return Color of this route text color
     */
    public Color getTextColor() {
        return textColor;
    }

    /**
     * Setter for route text color
     * @param textColor Color to set for the text color
     */
    public void setTextColor(Color textColor) {
        this.textColor = textColor;
    }

    /**
     * Getter for route URL
     * @return URL of this route
     */
    public String getURL() {
        return url;
    }

    /**
     * Setter for route URL
     * @param url URL to set for this route
     */
    public void setURL(String url) {
        this.url = url;
    }

    /**
     * Getter for route description
     * @return Description of this route
     */
    public String getDesc() {
        return desc;
    }

    /**
     * Setter for route description
     * @param desc Description to set for this route
     */
    public void setDesc(String desc) {
        this.desc = desc;
    }

    /**
     * Gets the route's title to be displayed in the GUI
     * @return the route's title
     */
    @Override
    public String getTitle() {
        return "Route " + getID().getIDString();
    }

    /**
     * Gets the route's subtitle to be displayed in the GUI
     * @return the route's subtitle
     */
    @Override
    public String getSubtitle() {
        // TODO - remove placeholder
        return "Lorem ipsum dolor";
    }

    /**
     * Gets the route's attributes to be displayed in the GUI
     * @return a HashMap<Attribute Title, Attribute Value> of the route's attributes
     */
    @Override
    public HashMap<String, String> getAttributes() {
        HashMap<String, String> attributes = new HashMap<>();
        // TODO - remove placeholders
        attributes.put("Next Stop", "Lorem ipsum dolor");
        attributes.put("Last Stop", "Lorem ipsum dolor");
        return attributes;
    }
}