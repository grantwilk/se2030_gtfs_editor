package gtfsapp.file;

import gtfsapp.id.*;

import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author Colton Rivard
 * @version 1.0
 * @created 15-Apr-2020 1:20:18 PM
 */
public class Feed extends GTFSElement {

    private HashMap<RouteID, Route> routes;
    private HashMap<TripID, Trip> trips;
    private HashMap<StopTimeID, StopTime> stopTimes;
    private HashMap<StopID, Stop> stops;
    private String id;
    private String name;
    private Color color;
    /**
     * Creates a new feed with a specified ID
     * @param id
     */
    public Feed(String id) {
        super(new FeedID(id));
    }

    /**
     * Creates a new feed with a procedurally generated ID
     */
    public Feed() {
        super(new FeedID());
    }

    /**
     * @param id
     * @param name
     */
    public Feed(String id, String name) {
        // TODO - this constructor is not fully implemented!
        this(id);
        this(name);
    }

    /**
     * @param id
     * @param name
     * @param color
     */
    public Feed(String id, String name, Color color) {
        // TODO - this constructor is not fully implemented!
        this(id);
        this(name);
        this(color);
    }

    /**
     * @param routes
     */
    public void addAllRoutes(List<Route> routes) {
        for (int num:routes){
            this.routes.put(,routes(num));
        }

    }

    /**
     * @param route
     */
    public void addRoute(Route route) {
    this.routes.put(,route);
    }

    /**
     *
     * @param trips
     */
    public void addAllTrips(List<Trip> trips){
        for (int num:trips){
            this.trips.put(,trips(num));
        }

    }

    /**
     *
     * @param trip
     */
    public void addTrip(Trip trip) {
        this.routes.put(,route);

    }

    /**
     *
     * @param stopTimes
     */
    public void addAllStopTimes(List<StopTime> stopTimes){

    }

    /**
     *
     * @param stopTime
     */
    public void addStopTime(StopTime stopTime) {

    }

    /**
     *
     * @param stops
     */
    public void addAllStops(List<Stop> stops){

    }

    /**
     *
     * @param stop
     */
    public void addStops(Stop stop) {

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
        boolean contains = false;
        if(routes.containskey(id)){
            contains = true
        }
        return contains;
    }

    /**
     * @param id
     */
    public boolean containsStop(StopID id) {
        boolean contains = false;
        if(routes.containskey(id)){
            contains = true
        }
        return contains;
    }

    /**
     * @param id
     */
    public boolean containsStopTime(StopTimeID id) {
        boolean contains = false;
        if(routes.containskey(id)){
            contains = true
        }
        return contains;
    }

    /**
     * @param id
     */
    public boolean containsTrip(TripID id) {
        boolean contains = false;
        if(routes.containskey(id)){
            contains = true
        }
        return contains;
    }

    /**
     *
     * @return
     */
    public String getName() {
        return name;
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
        ArrayList<RouteID> routeIDArrayList = new ArrayList;
        return null;
    }

    /**
     *
     * @return
     */
    public ArrayList<Route> getRoutes() {
        return null;
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
        return null;
    }

    /**
     *
     * @return
     */
    public ArrayList<Stop> getStops() {
        return null;
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
        return null;
    }

    /**
     *
     * @return
     */
    public ArrayList<StopTime> getStopTimes() {
        return null;
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
        return null;
    }

    /**
     *
     * @return
     */
    public ArrayList<Trip> getTrips() {
        return null;
    }

    /**
     * @param route
     */
    public Route removeRoute(Route route) {
        Route removedRoute = routes.get(route.getRouteID);
        routes.remove(route.getRouteID);
        return removedRoute;
    }

    /**
     * @param id
     */
    public Route removeRouteByID(RouteID id) {
        Route removedRoute = routes.get(id);
        routes.remove(id);
        return removedRoute;
    }

    /**
     *
     */
    public void setColor(Color color) {
        this.color = color;


    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name;

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