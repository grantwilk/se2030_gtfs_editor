package gtfsapp.file;

import gtfsapp.id.RouteID;
import gtfsapp.id.StopID;
import gtfsapp.id.StopTimeID;
import gtfsapp.id.TripID;
import gtfsapp.util.Location;
import javafx.scene.paint.Color;

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
    private final Feed feed;
    /**
     * The trips that occur within this route
     */
    private final HashMap<TripID, Trip> trips = new HashMap<>();
    /**
     * The type of transportation used on the route
     */
    private RouteType routeType;
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
     * Constructor for a Route
     *
     * @param id        ID associated with this route
     * @param feed      Reference to Feed object
     * @param routeType Route type for this specific route
     */
    public Route(Feed feed, String id, RouteType routeType) {
        super(new RouteID(id));
        this.feed = feed;
        this.routeType = routeType;
    }

    /**
     * Adds a list of trips to this route
     *
     * @param trips the list of trips to add
     */
    public void addAllTrips(List<Trip> trips) {
        for (Trip trip : trips) {
            this.trips.put((TripID) trip.getID(), trip);
        }
    }

    /**
     * Adds a trip to the route
     *
     * @param trip the trip to add
     */
    public void addTrip(Trip trip) {
        trips.put((TripID) trip.getID(), trip);
    }

    /**
     * Removes all trips from the route
     */
    public void clearTrips() {
        trips.clear();
    }

    /**
     * Determines whether a trip exists in this route
     *
     * @param id the trip ID to test
     */
    public boolean containsTrip(TripID id) {
        return trips.containsKey(id);
    }

    /**
     * Determines whether a stop time exists in this route
     *
     * @param id the stop time ID to test
     */
    public boolean containsStopTime(StopTimeID id) {
        return getStopTimeIDs().contains(id);
    }

    /**
     * Determines whether a stop exists in this route
     *
     * @param id the stop ID to test
     */
    public boolean containsStop(StopID id) {
        return getStopIDs().contains(id);
    }

    /**
     * @return
     */
    public Trip getActiveTrip() {
        // TODO - needs implementation eventually
        throw new UnsupportedOperationException();
    }

    /**
     * @return
     */
    public double getAvgSpeed() {
        // TODO - needs implementation eventually
        throw new UnsupportedOperationException();
    }

    /**
     * @return
     */
    public Location getBusPosition() {
        // TODO - needs implementation eventually
        throw new UnsupportedOperationException();
    }

    /**
     * @return
     */
    public double getDistance() {
        // TODO - needs implementation eventually
        throw new UnsupportedOperationException();
    }

    /**
     * @return
     */
    public double getDuration() {
        // TODO - needs implementation eventually
        throw new UnsupportedOperationException();
    }

    /**
     * @return
     */
    public Stop getNextStop() {
        // TODO - needs implementation eventually
        throw new UnsupportedOperationException();
    }

    /**
     * @return
     */
    public StopTime getNextStopTime() {
        // TODO - needs implementation eventually
        throw new UnsupportedOperationException();
    }

    /**
     * @return
     */
    public Trip getNextTrip() {
        // TODO - needs implementation eventually
        throw new UnsupportedOperationException();
    }

    /**
     * @return
     */
    public Stop getPreviousStop() {
        // TODO - needs implementation eventually
        throw new UnsupportedOperationException();
    }

    /**
     * @return
     */
    public StopTime getPreviousStopTime() {
        // TODO - needs implementation eventually
        throw new UnsupportedOperationException();
    }

    /**
     * @return
     */
    public Trip getPreviousTrip() {
        // TODO - needs implementation eventually
        throw new UnsupportedOperationException();
    }

    /**
     * Gets the feed that this route belongs to
     *
     * @return the feed that this route belongs to
     */
    public Feed getFeed() {
        return feed;
    }

    /**
     * Get specific stop from this route
     *
     * @param id Stop id to check for
     */
    public Stop getStopByID(StopID id) {

        Stop stop = null;

        // iterate through all trips to find the stop ID
        for (Trip trip : trips.values()) {
            if (trip.getStopByID(id) != null) {
                stop = trip.getStopByID(id);
                break;
            }
        }

        return stop;

    }

    /**
     * Get a list of the stop ids contained within the route
     * @return a list of all stop ids in this route
     */
    public List<StopID> getStopIDs() {
        return getStops().stream()
                         .map(e -> (StopID) e.getID())
                         .collect(Collectors.toList());
    }

    /**
     * Get a list of stops contained within the route
     *
     * @return list of all stops in this route
     */
    public List<Stop> getStops() {

        List<Stop> stops = new ArrayList<>();

        // iterate through all trips and add all distinct stops to the list
        for (Trip trip : trips.values()) {
            stops.addAll(trip.getStops());
        }

        return stops;

    }

    /**
     * Get a stop time contained in route by using stop_time id
     *
     * @param id stop_time id to search for in this route
     */
    public StopTime getStopTimeByID(StopTimeID id) {

        StopTime stopTime = null;

        // iterate through each trip and check for the given stop_time id
        for (Trip trip : trips.values()) {
            if (trip.getStopTimeByID(id) != null) {
                stopTime = trip.getStopTimeByID(id);
                break;
            }
        }

        return stopTime;

    }

    /**
     * Returns the id of all stop times contained in this route
     * @return list of all stop time ids in this route
     */
    public List<StopTimeID> getStopTimeIDs() {
        return getStopTimes().stream()
                             .map(e -> (StopTimeID) e.getID())
                             .collect(Collectors.toList());
    }

    /**
     * Gets a list of all stop times contained within the route
     * @return a list of all stop times contained within this route
     */
    public List<StopTime> getStopTimes() {

        List<StopTime> stopTimes = new ArrayList<>();

        // iterate through all trips and add all distinct stop times to the list
        for (Trip trip : trips.values()) {
            stopTimes.addAll(trip.getStopTimes());
        }

        return stopTimes;

    }

    /**
     * Get a specific trip in this route by trip id
     *
     * @param id Trip id to search for
     */
    public Trip getTripByID(TripID id) {
        return trips.get(id);
    }

    /**
     * Get a list of trip ids contained in this route
     * @return a list of trip ids in this route
     */
    public List<TripID> getTripIDs() {
        return new ArrayList<>(trips.keySet());

    }

    /**
     * Get a list of all trips contained in this route
     *
     * @return a list of all trips in this route
     */
    public List<Trip> getTrips() {
        return new ArrayList<>(trips.values());
    }

    /**
     * Gets the type of transportation used on the route
     *
     * @return the type of transportation used on the route
     */
    public RouteType getRouteType() {
        return routeType;
    }

    /**
     * Sets the type of transportation used on the route
     *
     * @param routeType the type of transportation used on the route
     */
    public void setRouteType(RouteType routeType) {
        this.routeType = routeType;
    }

    /**
     * @return
     */
    public boolean isActive() {
        // TODO - needs implementation eventually
        throw new UnsupportedOperationException();
    }

    /**
     * Remove a trip from this route
     *
     * @param trip Trip to remove from the route
     */
    public Trip removeTrip(Trip trip) {
        return trips.remove((TripID) trip.getID());
    }

    /**
     * Remove a trip from this route
     *
     * @param id ID of the trip to remove
     */
    public Trip removeTripByID(TripID id) {
        return trips.remove(id);
    }

    /**
     * Getter for route short name
     *
     * @return Short name of route
     */
    public String getShortName() {
        return shortName;
    }

    /**
     * Setter for route short name
     *
     * @param shortName Short name to set for this route
     */
    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    /**
     * Getter for route long name
     *
     * @return Long name of route
     */
    public String getLongName() {
        return longName;
    }

    /**
     * Setter for route long name
     *
     * @param longName Long name to set for this route
     */
    public void setLongName(String longName) {
        this.longName = longName;
    }

    /**
     * Getter for route color
     *
     * @return Color of this route
     */
    public Color getColor() {
        return color;
    }

    /**
     * Seter for route color
     *
     * @param color Color to set for this route
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * Getter for route URL
     *
     * @return URL of this route
     */
    public String getURL() {
        return url;
    }

    /**
     * Setter for route URL
     *
     * @param url URL to set for this route
     */
    public void setURL(String url) {
        this.url = url;
    }

    /**
     * Getter for route description
     *
     * @return Description of this route
     */
    public String getDesc() {
        return desc;
    }

    /**
     * Setter for route description
     *
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
        return getID().getIDString();
    }

    /**
     * Gets the route's subtitle to be displayed in the GUI
     * @return the route's subtitle
     */
    @Override
    public String getSubtitle() {
        if (longName != null && !longName.equals("")) {
            return getLongName();
        } else if (shortName != null && !shortName.equals("")) {
            return getShortName();
        } else {
            return "Unnamed Route";
        }
    }

    /**
     * Gets the route's attributes to be displayed in the GUI
     * @return a Map<Attribute Title, Attribute Value> of the route's attributes
     */
    @Override
    public Map<String, String> getAttributes() {
        Map<String, String> attributes = new LinkedHashMap<>();
        // TODO - remove placeholders
        attributes.put("Next Stop", "Lorem ipsum dolor");
        attributes.put("Last Stop", "Lorem ipsum dolor");
        return attributes;
    }
}