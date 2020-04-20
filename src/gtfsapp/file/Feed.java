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
     * @param id
     */

    public Feed(String id) {
        super(new FeedID(id));
    }


    /**
     * @param routes
     */
    public void addAllRoutes(List<Route> routes) {
        for(int x = 0; x <= routes.size(); x++){
            this.routes.put(routes.get(x).getRouteID(), routes.get(x));
        }

    }

    /**
     * @param route
     */
    public void addRoute(Route route) {
        routes.put(route.getRouteID(),route);

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
    public ArrayList<RouteID> getRouteIDs() {
        Set<RouteID> mySet = routes.keySet();
        ArrayList<RouteID> routeIDArray = new ArrayList<>(mySet);
        return routeIDArray;

    }

    /**
     *
     * @return
     */
    public ArrayList<Route> getRoutes() {
        Collection<Route> mySet = routes.values();
        ArrayList<Route> routeArray = new ArrayList<>(mySet);
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
    public ArrayList<StopID> getStopIDs() {
        Set<StopID> mySet = stops.keySet();
        ArrayList<StopID> stopArray = new ArrayList<>(mySet);
        return stopArray;

    }

    /**
     *
     * @return
     */
    public ArrayList<Stop> getStops() {
        Collection<Stop> mySet = stops.values();
        ArrayList<Stop> stopArray = new ArrayList<>(mySet);
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
    public ArrayList<StopTimeID> getStopTimeIDs() {
        Set<StopTimeID> mySet = stopTimes.keySet();
        ArrayList<StopTimeID> stopTimeArray = new ArrayList<>(mySet);
        return stopTimeArray;
    }

    /**
     *
     * @return
     */
    public ArrayList<StopTime> getStopTimes() {
        Collection<StopTime> mySet = stopTimes.values();
        ArrayList<StopTime> stopTimeArray = new ArrayList<>(mySet);
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
    public ArrayList<TripID> getTripIDs() {
        Set<TripID> mySet = trips.keySet();
        ArrayList<TripID> TripIDArray = new ArrayList<>(mySet);
        return TripIDArray;
    }

    /**
     *
     * @return
     */
    public ArrayList<Trip> getTrips() {
        Collection<Trip> mySet = trips.values();
        ArrayList<Trip> TripArray = new ArrayList<>(mySet);
        return TripArray;
    }

    /**
     * @param route
     */
    public Route removeRoute(Route route) {
        Route removedRoute = routes.get(route.getRouteID());
        routes.remove(route.getRouteID());
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