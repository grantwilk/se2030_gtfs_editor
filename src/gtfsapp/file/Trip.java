package gtfsapp.file;

import gtfsapp.id.*;

import javafx.geometry.Point2D;

import java.util.*;

/**
 * @author Mason Schlax
 * @version 1.0
 * @created 15-Apr-2020 1:20:18 PM
 */
public class Trip extends GTFSElement {

    /**
     * The feed the trip belongs to
     */
    private final Feed feed;

    /**
     * A map of all stop IDs and stops contained within this trip
     */
    private final HashMap<StopID, Stop> stops = new HashMap<>();

    /**
     * A map of all stop time IDs and stop times contained within this trip
     */
    private final HashMap<StopTimeID, StopTime> stopTimes = new HashMap<>();

    /**
     * The head sign for this trip
     */
    private String headSign;

    /**
     * Constructor for the trip object with an id and feed as parameters
     *
     * @param id   for the trip
     * @param feed for the trip
     */
    public Trip(Feed feed, String id) {
        super(new TripID(id));
        this.feed = feed;
    }

    /**
     * Clears all of the stop times from the internal hash map
     */
    public void clearStopTimes() {
        stopTimes.clear();
        stops.clear();
    }

    /**
     * @param stopTime
     */
    public void addStopTime(StopTime stopTime) {
        // TODO - this needs to be implemented!
    }

    /**
     * Adds a list of the stop times to the internal map of stop times
     *
     * @param stopTimes - the list of stop times
     */
    public void addAllStopTimes(ArrayList<StopTime> stopTimes) {

        // for each stop time in the array
        for (StopTime stopTime : stopTimes) {
            // add the list of stop times to the stop times map
            this.stopTimes.put((StopTimeID) stopTime.getID(), stopTime);
            // add the stop time's stop to the stops map
            stops.put((StopID) stopTime.getStop().getID(), stopTime.getStop());
        }

    }

    /**
     * Removes the stop time from the stopTimes hash map
     *
     * @param stopTime to be removed
     * @return the stopTime removed
     */
    public StopTime removeStopTime(StopTime stopTime) {
        return stopTimes.remove((StopTimeID) stopTime.getID());
    }

    /**
     * Removes the Stop time that was previously at the id passed
     *
     * @param id of the stop time to be removed
     * @return the StopTimeID removed
     */
    public StopTime removeStopTimeByID(StopTimeID id) {
        return stopTimes.remove(id);
    }

    /**
     * Checks to see if this trip contains a specific stop
     *
     * @param id - the ID of the stop
     */
    public boolean containsStop(StopID id) {
        return stops.containsKey(id);
    }

    /**
     * Checks to see if this trip contains a specific stop time
     *
     * @param id - the ID of the stop time
     */
    public boolean containsStopTime(StopTimeID id) {
        return stopTimes.containsKey(id);
    }

    /**
     * @return
     */
    public ArrayList<Route> getContainingRoutes() {
        // TODO - needs implementation eventually
        throw new UnsupportedOperationException();
    }

    /**
     * @return
     */
    public ArrayList<RouteID> getContainingRouteIDs() {
        // TODO - needs implementation eventually
        throw new UnsupportedOperationException();
    }

    /**
     * Gets a stop time contained within the trip by its ID
     *
     * @param id - the ID of the stop time
     */
    public StopTime getStopTimeByID(StopTimeID id) {
        return stopTimes.get(id);
    }

    /**
     * Gets all of the stop time IDs contained within the trip
     *
     * @return a list of stop time IDs contained within the trip
     */
    public ArrayList<StopTimeID> getStopTimeIDs() {
        Set<StopTimeID> stopTimeIDSet = stopTimes.keySet();
        return new ArrayList<>(stopTimeIDSet);
    }

    /**
     * Gets all of the stop times contained within the trip
     *
     * @return a list of stop times contained within the trip
     */
    public ArrayList<StopTime> getStopTimes() {
        Collection<StopTime> stopTimeSet = stopTimes.values();
        return new ArrayList<>(stopTimeSet);
    }

    /**
     * Gets a stop contained within the trip by its ID
     *
     * @param id - the ID of the stop
     */
    public Stop getStopByID(StopID id) {
        return stops.get(id);
    }

    /**
     * @return
     */
    public ArrayList<StopID> getStopIDs() {
        // TODO - This needs to be implemented!
        return null;
    }

    /**
     * @return
     */
    public ArrayList<Stop> getStops() {
        // TODO - This needs to be implemented!
        return null;
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
    public StopTime getPreviousStopTime() {
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
    public Stop getPreviousStop() {
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
    public Point2D getBusPosition() {
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
     * Getter for the head sign of the trip
     *
     * @return the head sign of the trip
     */
    public String getHeadSign() {
        return headSign;
    }

    /**
     * Setter for the head sign for the trip
     *
     * @param headSign - the headSign of the trip
     */
    public void setHeadSign(String headSign) {
        this.headSign = headSign;
    }

    /**
     * @return
     */
    public boolean isActive() {
        // TODO - needs implementation eventually
        throw new UnsupportedOperationException();
    }

    /**
     * Getter for the feed that contains this trip
     *
     * @return the feed that contains this trip
     */
    public Feed getFeed() {
        return feed;
    }

    /**
     * Gets the trip's title to be displayed in the GUI
     *
     * @return the trip's title
     */
    @Override
    public String getTitle() {
        return "Trip " + getID().getIDString();
    }

    /**
     * Gets the trip's subtitle to be displayed in the GUI
     *
     * @return the trip's subtitle
     */
    @Override
    public String getSubtitle() {
        // TODO - remove placeholders
        return "Lorem ipsum dolor!";
    }

    /**
     * Gets the trip's attributes to be displayed in the GUI
     *
     * @return a HashMap<Attribute Title, Attribute Value> of the trip's attributes
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