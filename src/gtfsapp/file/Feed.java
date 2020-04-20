package gtfsapp.file;

import gtfsapp.id.*;

import java.util.*;

/**
 * @author Colton Rivard
 * @version 1.0
 * @created 15-Apr-2020 1:20:18 PM
 */
public class Feed extends GTFSElement {

    /**
     * A map of all routes in the feed
     */
    private HashMap<RouteID, Route> routes;

    /**
     * A map of all trips in the feed
     */
    private HashMap<TripID, Trip> trips;

    /**
     * A map of all stop times in the feed
     */
    private HashMap<StopTimeID, StopTime> stopTimes;

    /**
     * A map of all stops in the feed
     */
    private HashMap<StopID, Stop> stops;

    /**
     * Creates a new feed with a procedurally generated ID
     */
    public Feed() {
        super(new FeedID());
    }

    /**
     * Creates a new feed with a specified ID
     * @param idString - the ID string to create the Feed ID from
     */

    public Feed(String idString) {
        super(new FeedID(idString));
    }


    /**
     * Adds a list of routes to the feed
     * @param routes - the list of routes to add
     */
    public void addAllRoutes(List<Route> routes) {
        for(Route route : routes){
            this.routes.put((RouteID) route.getID(), route);
        }
    }

    /**
     * Adds a route to the feed
     * @param route 
     */
    public void addRoute(Route route) {
        routes.put(new RouteID(), route);

    }

    /**
     *
     * @param trips
     */
    public void addAllTrips(List<Trip> trips){
        for(int x = 0; x <= trips.size(); x++){
            this.trips.put(new TripID(), trips.get(x));
        }


    }

    /**
     *
     * @param trip
     */
    public void addTrip(Trip trip) {
        trips.put(new TripID(),trip);

    }

    /**
     *
     * @param stopTimes
     */
    public void addAllStopTimes(List<StopTime> stopTimes){
        for(int x = 0; x <= stopTimes.size(); x++){
            this.stopTimes.put(new StopTimeID(), stopTimes.get(x));
        }

    }

    /**
     *
     * @param stopTime
     */
    public void addStopTime(StopTime stopTime) {
        stopTimes.put(new StopTimeID(), stopTime);
    }

    /**
     *
     * @param stops
     */
    public void addAllStops(List<Stop> stops){
        for(int x = 0; x <= stops.size(); x++){
            this.stops.put(new StopID(), stops.get(x));
        }

    }

    /**
     *
     * @param stop
     */
    public void addStops(Stop stop) {
        stops.put(new StopID(), stop);
    }

    /**
     *
     */
    public void clearRoutes() {
        routes.clear();

    }

    /**
     * @param id
     */
    public boolean containsRoute(RouteID id) {
        return routes.containsKey(id);
    }

    /**
     * @param id
     */
    public boolean containsStop(StopID id) {
        return routes.containsKey(id);
    }

    /**
     * @param id
     */
    public boolean containsStopTime(StopTimeID id) {
        return routes.containsKey(id);
    }

    /**
     * @param id
     */
    public boolean containsTrip(TripID id) {
        return routes.containsKey(id);
    }

    /**
     * @param id
     */
    public Route getRouteByID(RouteID id) {
        return routes.get(id);
    }

    /**
     *
     * @return
     */
    public List<RouteID> getRouteIDs() {
        Set<RouteID> mySet = routes.keySet();
        List<RouteID> routeIDArray = new ArrayList<>(mySet);
        return routeIDArray;

    }

    /**
     *
     * @return
     */
    public List<Route> getRoutes() {
        Collection<Route> mySet = routes.values();
        List<Route> routeArray = new ArrayList<>(mySet);
        return routeArray;

    }

    /**
     * @param id
     */
    public Stop getStopByID(StopID id) {
        return stops.get(id);
    }

    /**
     *
     * @return
     */
    public List<StopID> getStopIDs() {
        Set<StopID> mySet = stops.keySet();
        List<StopID> stopArray = new ArrayList<>(mySet);
        return stopArray;

    }

    /**
     *
     * @return
     */
    public List<Stop> getStops() {
        Collection<Stop> mySet = stops.values();
        List<Stop> stopArray = new ArrayList<>(mySet);
        return stopArray;

    }

    /**
     * @param id
     */
    public StopTime getStopTimeByID(StopTimeID id) {
        return stopTimes.get(id);

    }

    /**
     *
     * @return
     */
    public List<StopTimeID> getStopTimeIDs() {
        Set<StopTimeID> mySet = stopTimes.keySet();
        List<StopTimeID> stopTimeArray = new ArrayList<>(mySet);
        return stopTimeArray;
    }

    /**
     *
     * @return
     */
    public List<StopTime> getStopTimes() {
        Collection<StopTime> mySet = stopTimes.values();
        List<StopTime> stopTimeArray = new ArrayList<>(mySet);
        return stopTimeArray;
    }

    /**
     * @param id
     */
    public Trip getTripByID(TripID id) {
        return trips.get(id);
    }

    /**
     *
     * @return
     */
    public List<TripID> getTripIDs() {
        Set<TripID> mySet = trips.keySet();
        List<TripID> TripIDArray = new ArrayList<>(mySet);
        return TripIDArray;
    }

    /**
     *
     * @return
     */
    public List<Trip> getTrips() {
        Collection<Trip> mySet = trips.values();
        List<Trip> TripArray = new ArrayList<>(mySet);
        return TripArray;
    }

    /**
     * @param route
     */
    public Route removeRoute(Route route) {
        Route removedRoute = routes.get(route.getID());
        routes.remove(route.getID());
        return removedRoute;
    }

    /**
     * @param id
     */
    public Route removeRouteByID(RouteID id) {
        Route removedRoute = routes.get(id);
        return removedRoute;
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
    public HashMap<String, String> getAttributes() {
        HashMap<String, String> attributes = new HashMap<>();
        // TODO - remove placeholders
        attributes.put("Total Routes", "Lorem ipsum dolor");
        attributes.put("Total Trips", "Lorem ipsum dolor");
        attributes.put("Total Stops", "Lorem ipsum dolor");
        return attributes;
    }

}