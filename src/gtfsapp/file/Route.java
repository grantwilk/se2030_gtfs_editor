package gtfsapp.file;

import gtfsapp.id.RouteID;
import gtfsapp.id.StopID;
import gtfsapp.id.StopTimeID;
import gtfsapp.id.TripID;

import javafx.scene.paint.Color;
import javafx.geometry.Point2D;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author Colton Rivard
 * @version 1.0
 * @created 15-Apr-2020 1:20:18 PM
 */
public class Route extends GTFSElement {

    private Feed feed;
    private RouteType routeType;
    private HashMap<TripID, Trip> trips;
    private HashMap<TripID, Stop> stops;
    private HashMap<StopTimeID, StopTime> stopTimes;
    private String shortName;
    private String longName;
    private String desc;
    private String url;
    private Color color;
    private Color textColor;
    private RouteID id;

    /**
     *
     * @param id
     * @param feed
     * @param routeType
     */
    public Route(Feed feed, String id, RouteType routeType) {
        // TODO - this constructor is not fully implemented!
        super(new RouteID(id));
    }

    /**
     * @param trips
     */
    public void addAllTrips(ArrayList<Trip> trips) {
        for (int num:trips){
            this.trips.put(,trips(num));
        }

    }

    /**
     * @param trip
     */
    public void addTrip(Trip trip) {
        trips.put(trip.getID(),trip);

    }

    public void clearTrips() {
        trips.clear();

    }

    /**
     * @param id
     */
    public boolean containsStop(StopID id) {

        boolean contains = false;
        if (stops.containskey(id)) {
            contains = true
        }
        return contains;
    }

    /**
     * @param id
     */
    public boolean containsStopTime(StopTimeID id) {
        boolean contains = false;
        if(stopTimes.containskey(id)){
            contains = true
        }
        return contains;
    }

    /**
     * @param id
     */
    public boolean containsTrip(TripID id) {
        boolean contains = false;
        if(trips.containskey(id)){
            contains = true
        }
        return contains;

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
    public RouteID getRouteID(){
        return id;
    }

    /**
     * @param id
     */
    public Stop getStopByID(StopID id) {
        Stop removedStop = stops.get(id);
        stops.remove(id);
        return removedStop;
    }

    public ArrayList<StopID> getStopIDs() {
        set<Object> mySet = stops.keySet();
        ArrayList<StopID> stopArray = new ArrayList<>(mySet);
        return stopArray;


    }

    public ArrayList<Stop> getStops() {
        set<Object> mySet = stops.values();
        ArrayList<Stop> stopArray = new ArrayList<>(mySet);
        return stopArray;
    }

    /**
     * @param id
     */
    public StopTime getStopTimeByID(StopTimeID id) {
        Stop removedStopTime = stopTimes.get(id);
        stopTimes.remove(id);
        return removedStopTime;
    }

    public ArrayList<StopTimeID> getStopTimeIDs() {
        set<Object> mySet = stopTimes.keySet();
        ArrayList<StopTimeID> stopTimesArray = new ArrayList<>(mySet);
        return stopTimesArray;
    }

    public ArrayList<StopTime> getStopTimes() {
        set<Object> mySet = stopTimes.values();
        ArrayList<StopTime> stopTimesArray = new ArrayList<>(mySet);
        return stopTimesArray;
    }

    /**
     * @param id
     */
    public Trip getTripByID(TripID id) {
        Trip removedTrip = trips.get(id);
        trips.remove(id);
        return removedTrip;
    }


    public ArrayList<TripID> getTripIDs() {
        set<Object> mySet = trips.keySet();
        ArrayList<Trip> tripsArray = new ArrayList<>(mySet);
        return tripsArray;

    }

    public ArrayList<Trip> getTrips() {
        set<Object> mySet = trips.values();
        ArrayList<Trip> tripsArray = new ArrayList<>(mySet);
        return tripsArray;
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
     * @param trip
     */
    public Trip removeTrip(Trip trip) {
        Route removedTrip = routes.get(trip.getTripID);
        trips.remove(trip.getTripID);
        return removedTrip;
    }

    /**
     * @param id
     */
    public Trip removeTripByID(TripID id) {
        Route removedTrip = trips.get(id);
        routes.remove(id);
        return removedTrip;
    }

    /**
     *
     * @return
     */
    public String getShortName() {
        return shortName;
    }

    /**
     *
     * @param shortName
     */
    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    /**
     *
     * @return
     */
    public String getLongName() {
        return longName;
    }

    /**
     *
     * @param longName
     */
    public void setLongName(String longName) {
        this.longName = longName;
    }

    /**
     *
     * @return
     */
    public Color getColor() {
        return color;
    }

    /**
     *
     * @param color
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     *
     * @return
     */
    public Color getTextColor() {
        return textColor;
    }

    /**
     *
     * @param textColor
     */
    public void setTextColor(Color textColor) {
        this.textColor = textColor;
    }

    /**
     *
     * @return
     */
    public String getURL() {
        return url;
    }

    /**
     *
     * @param url
     */
    public void setURL(String url) {
        this.url = url;
    }
    /**
     *
     * @return
     */
    public String getDesc() {
        return desc;
    }

    /**
     *
     * @param desc
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