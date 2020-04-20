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
    public void addAllTrips(List<Trip> trips) {
        for(int x = 0; x <= trips.size(); x++){
            this.trips.put(new TripID(), trips.get(x));
        }
    }



    /**
     * @param trip
     */
    public void addTrip(Trip trip) {
        trips.put(new TripID(),trip);

    }

    public void clearTrips() {
        trips.clear();
    }

    /**
     * @param id
     */
    public boolean containsStop(StopID id) {
        return getStopIDs().contains(id);
    }

    /**
     * @param id
     */
    public boolean containsStopTime(StopTimeID id) {
        return getStopTimeIDs().contains(id);
    }

    /**
     * @param id
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

    /**
     * @param id
     */
    public Stop getStopByID(StopID id) {
        Stop stop = null;
        for (Trip trip : trips.values()) {
            if (trip.getStopByID(id) != null) {
                stop = trip.getStopByID(id);
            }
        }
        return stop;
    }

    public List<StopID> getStopIDs() {
        List<StopID> allStopIDs = new ArrayList<>();
        for (Trip trip : trips.values()) {
            allStopIDs.addAll(trip.getStopIDs());
        }
        return allStopIDs.stream().distinct().collect(Collectors.toList());
    }

    public List<Stop> getStops() {
        List<Stop> allStops = new ArrayList<>();
        for (Trip trip : trips.values()) {
            allStops.addAll(trip.getStops());
        }
        return allStops.stream().distinct().collect(Collectors.toList());
    }

    /**
     * @param id
     */
    public StopTime getStopTimeByID(StopTimeID id) {
        StopTime stopTime = null;
        for (Trip trip : trips.values()) {
            if (trip.getStopTimeByID(id) != null) {
                stopTime = trip.getStopTimeByID(id);
            }
        }
        return stopTime;
    }

    public List<StopTimeID> getStopTimeIDs() {
        List<StopTimeID> allStopTimeIDs = new ArrayList<>();
        for (Trip trip : trips.values()) {
            allStopTimeIDs.addAll(trip.getStopTimeIDs());
        }
        return allStopTimeIDs.stream().distinct().collect(Collectors.toList());
    }

    public List<StopTime> getStopTimes() {
        List<StopTime> allStopTimes = new ArrayList<>();
        for (Trip trip : trips.values()) {
            allStopTimes.addAll(trip.getStopTimes());
        }
        return allStopTimes.stream().distinct().collect(Collectors.toList());
    }

    /**
     * @param id
     */
    public Trip getTripByID(TripID id) {
        Trip removedTrip = trips.get(id);
        trips.remove(id);
        return removedTrip;
    }


    public List<TripID> getTripIDs() {
        Set<TripID> mySet = trips.keySet();
        List<TripID> tripsArray = new ArrayList<>(mySet);
        return tripsArray;

    }

    public List<Trip> getTrips() {
        Collection<Trip> mySet = trips.values();
        List<Trip> tripsArray = new ArrayList<>(mySet);
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
        Trip removedTrip = trips.get(trip.getID());
        trips.remove(trip.getID());
        return removedTrip;
    }

    /**
     * @param id
     */
    public Trip removeTripByID(TripID id) {
        Trip removedTrip = trips.get(id);
        trips.remove(id);
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