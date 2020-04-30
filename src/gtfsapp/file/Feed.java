package gtfsapp.file;

import gtfsapp.id.*;
import gtfsapp.util.Colors;
import javafx.scene.paint.Color;

import java.util.*;

/**
 * @author Colton Rivard
 * @version 1.0
 * @created 15-Apr-2020 1:20:18 PM
 */
public class Feed extends GTFSElement {

    /**
     * The default color of a feed
     */
    private static final Color DEFAULT_COLOR = Colors.fromString("#D0D0D0");

    /**
     * A map of all routes in the feed
     */
    private final HashMap<RouteID, Route> routes = new HashMap<>();

    /**
     * A map of all trips in the feed
     */
    private final HashMap<TripID, Trip> trips = new HashMap<>();

    /**
     * A map of all stop times in the feed
     */
    private final HashMap<StopTimeID, StopTime> stopTimes = new HashMap<>();

    /**
     * A map of all stops in the feed
     */
    private final HashMap<StopID, Stop> stops = new HashMap<>();

    /**
     * Creates a new feed with a procedurally generated ID
     */
    public Feed() {
        super(new FeedID());
    }

    /**
     * Adds a route to the feed
     *
     * @param route the route to add
     */
    public void addRoute(Route route) {
        routes.put((RouteID) route.getID(), route);
    }


    /**
     * Adds a list of routes to the feed
     *
     * @param routes the list of routes to add
     */
    public void addAllRoutes(List<Route> routes) {
        for (Route route : routes) {
            this.routes.put((RouteID) route.getID(), route);
        }
    }

    /**
     * Adds a trip to the feed
     *
     * @param trip the trip to add
     */
    public void addTrip(Trip trip) {
        trips.put((TripID) trip.getID(), trip);
    }

    /**
     * Adds a list of trips to the feed
     *
     * @param trips the list of trips to add
     */
    public void addAllTrips(List<Trip> trips) {
        for (Trip trip : trips) {
            this.trips.put((TripID) trip.getID(), trip);
        }

    }

    /**
     * Adds a stop time to the feed
     *
     * @param stopTime the stop time to add
     */
    public void addStopTime(StopTime stopTime) {
        stopTimes.put((StopTimeID) stopTime.getID(), stopTime);
    }

    /**
     * Adds a list of stop times to the feed
     *
     * @param stopTimes the list of stop times to add
     */
    public void addAllStopTimes(List<StopTime> stopTimes) {
        for (StopTime stopTime : stopTimes) {
            this.stopTimes.put((StopTimeID) stopTime.getID(), stopTime);
        }
    }

    /**
     * Adds a stop to the feed
     *
     * @param stop the stop to add
     */
    public void addStops(Stop stop) {
        stops.put((StopID) stop.getID(), stop);
    }

    /**
     * Adds a list of stops to the feed
     *
     * @param stops the list of stops to add
     */
    public void addAllStops(List<Stop> stops) {
        for (Stop stop : stops) {
            this.stops.put((StopID) stop.getID(), stop);
        }
    }

    /**
     * Clears all of the routes from the feed
     */
    public void clearRoutes() {
        routes.clear();
    }

    /**
     * Determines whether the feed contains a route
     *
     * @param id the ID of the route
     */
    public boolean containsRoute(RouteID id) {
        return routes.containsKey(id);
    }

    /**
     * Determines whether the feed contains a trip
     *
     * @param id the ID of the trip
     */
    public boolean containsTrip(TripID id) {
        return trips.containsKey(id);
    }

    /**
     * Determines whether the feed contains a stop time
     *
     * @param id the ID of the stop time
     */
    public boolean containsStopTime(StopTimeID id) {
        return stopTimes.containsKey(id);
    }

    /**
     * Determines whether the feed contains a stop
     *
     * @param id the ID of the stop
     */
    public boolean containsStop(StopID id) {
        return stops.containsKey(id);
    }

    /**
     * Gets a route from the feed by its ID
     *
     * @param id the ID of the route
     * @return the route if it exists, otherwise return null
     */
    public Route getRouteByID(RouteID id) {
        return routes.get(id);
    }

    /**
     * Gets a list of the route IDs within the feed
     * @return a list of route IDs within the feed
     */
    public List<RouteID> getRouteIDs() {
        return new ArrayList<>(routes.keySet());
    }

    /**
     * Gets a list of routes within the feed
     * @return a list of routes within the feed
     */
    public List<Route> getRoutes() {
        return new ArrayList<>(routes.values());
    }

    /**
     * Gets a trip from the feed by its ID
     * @param id the ID of the trip
     * @return the trip if it exists, otherwise return null
     */
    public Trip getTripByID(TripID id) {
        return trips.get(id);
    }

    /**
     * Gets a list of trip IDs within the feed
     *
     * @return a list of trip IDs within the feed
     */
    public List<TripID> getTripIDs() {
        return new ArrayList<>(trips.keySet());
    }

    /**
     * Get a list of trips within the feed

     * @return a list of trips within the feed
     */
    public List<Trip> getTrips() {
        return new ArrayList<>(trips.values());
    }

    /**
     * Gets a stop time from the feed by its ID
     *
     * @param id the ID of the stop time
     * @return the stop time if it exists, otherwise return null
     */
    public StopTime getStopTimeByID(StopTimeID id) {
        return stopTimes.get(id);

    }

    /**
     * Gets a list of stop time IDs within the feed
     *
     * @return a list of stop time IDs within the feed
     */
    public List<StopTimeID> getStopTimeIDs() {
        return new ArrayList<>(stopTimes.keySet());
    }

    /**
     * Gets a list of stop times within the feed
     *
     * @return a list of stop times within the feed
     */
    public List<StopTime> getStopTimes() {
        return new ArrayList<>(stopTimes.values());
    }

    /**
     * Gets a stop within the feed by its ID
     *
     * @param id the ID of the stop
     * @return the stop if it exists, otherwise return null
     */
    public Stop getStopByID(StopID id) {
        return stops.get(id);
    }

    /**
     * Gets all of the stop IDs within the feed
     *
     * @return a list of stop IDs within the feed
     */
    public List<StopID> getStopIDs() {
        return new ArrayList<>(stops.keySet());
    }

    /**
     * Gets all of the stops within the feed
     *
     * @return a list of stops within the feed
     */
    public List<Stop> getStops() {
        return new ArrayList<>(stops.values());
    }

    /**
     * Removes a route from the feed
     *
     * @param route the route to remove
     */
    public Route removeRoute(Route route) {
        return routes.remove((RouteID) route.getID());
    }

    /**
     * Removes a route from the feed by its ID
     *
     * @param id the ID of the route to remove
     */
    public Route removeRouteByID(RouteID id) {
        return routes.remove(id);
    }

    /**
     * Gets the feed's title to be displayed in the GUI
     * @return the feed's title
     */
    @Override
    public String getTitle() {
        return "Feed " + getID().getIDString();
    }

    /**
     * Gets the feed's subtitle to be displayed in the GUI
     * @return the feed's subtitle
     */
    @Override
    public String getSubtitle() {
        // TODO - remove placeholder
        return "Lorem ipsum dolor";
    }

    /**
     * Gets the feed's attributes to be displayed in the GUI
     * @return a HashMap<Attribute Title, Attribute Value> of the feed's attributes
     */
    @Override
    public Map<String, String> getAttributes() {
        Map<String, String> attributes = new LinkedHashMap<>();
        // TODO - remove placeholders
        attributes.put("Total Routes", "Lorem ipsum dolor");
        attributes.put("Total Trips", "Lorem ipsum dolor");
        attributes.put("Total Stops", "Lorem ipsum dolor");
        return attributes;
    }

    /**
     * Get's the feed's color
     * @return the feed's color
     */
    @Override
    public Color getColor() {
        return DEFAULT_COLOR;
    }
}