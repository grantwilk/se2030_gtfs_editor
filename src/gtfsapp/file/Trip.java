package gtfsapp.file;

import gtfsapp.id.*;
import gtfsapp.util.Location;

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
     * @param idString for the trip
     * @param feed     for the trip
     */
    public Trip(Feed feed, String idString) {
        super(new TripID(idString));
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
     * Adds a new stopTimeID and stopTime to the hash map containting both
     *
     * @param stopTime the stop time to be added
     */
    public void addStopTime(StopTime stopTime) {
        stopTimes.put((StopTimeID) stopTime.getID(), stopTime);
    }

    /**
     * Adds a list of the stop times to the internal map of stop times
     *
     * @param stopTimes the list of stop times
     */
    public void addAllStopTimes(List<StopTime> stopTimes) {

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
     * @param id the ID of the stop
     */
    public boolean containsStop(StopID id) {
        return stops.containsKey(id);
    }

    /**
     * Checks to see if this trip contains a specific stop time
     *
     * @param id the ID of the stop time
     */
    public boolean containsStopTime(StopTimeID id) {
        return stopTimes.containsKey(id);
    }

    /**
     * Gets a list of routes that contain this trip
     * @return a list of routes that contain this trip
     */
    public List<Route> getContainingRoutes() {

        List<Route> containingRoutes = new ArrayList<>();

        // check if this trip is contained in any of the routes in this feed
        for (Route route : feed.getRoutes()) {

            // if it is, add it to our list of containing routes
            if (route.getTrips().contains(this)) {
                containingRoutes.add(route);
            }

        }

        return containingRoutes;

    }

    /**
     * @return
     */
    public List<RouteID> getContainingRouteIDs() {
        // TODO - needs implementation eventually
        throw new UnsupportedOperationException();
    }

    /**
     * Gets a stop time contained within the trip by its ID
     *
     * @param id the ID of the stop time
     */
    public StopTime getStopTimeByID(StopTimeID id) {
        return stopTimes.get(id);
    }

    /**
     * Gets all of the stop time IDs contained within the trip
     *
     * @return a list of stop time IDs contained within the trip
     */
    public List<StopTimeID> getStopTimeIDs() {
        Set<StopTimeID> stopTimeIDSet = stopTimes.keySet();
        return new ArrayList<>(stopTimeIDSet);
    }

    /**
     * Gets all of the stop times contained within the trip
     *
     * @return a list of stop times contained within the trip
     */
    public List<StopTime> getStopTimes() {
        Collection<StopTime> stopTimeSet = stopTimes.values();
        return new ArrayList<>(stopTimeSet);
    }

    /**
     * Gets a stop contained within the trip by its ID
     *
     * @param id the ID of the stop
     */
    public Stop getStopByID(StopID id) {
        return stops.get(id);
    }

    /**
     * Gets all of the stop IDs contained within the trip
     *
     * @return a list of stop IDs contained within the trip
     */
    public List<StopID> getStopIDs() {
        Set<StopID> stopIDSet = stops.keySet();
        return new ArrayList<>(stopIDSet);
    }

    /**
     * Gets all of the stops contained within the trip
     *
     * @return a list of stops contained within the trip
     */
    public List<Stop> getStops() {
        Collection<Stop> stopsCollection = stops.values();
        return new ArrayList<>(stopsCollection);
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
     * Gets the average speed of the trip as a double through
     * using the getDistance and getDuration methods
     * @return the average speed of the trip
     */
    public double getAvgSpeed() {
        return getDistance()/getDuration();
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
        ArrayList<StopTime> distanceCalc = (ArrayList<StopTime>) this.getStopTimes();
        int lastLocation = distanceCalc.size();
        StopTime FirstDepartLoc;
        StopTime LastArriveLoc;
        FirstDepartLoc = distanceCalc.get(0);
        LastArriveLoc = distanceCalc.get(lastLocation -1);
        return 0;
    }

    /**
     * Method to get the duration of the trip by taking the difference between
     * the first depart time, and last arrival time
     * @return the duration of the trip
     */
    public double getDuration() {
        ArrayList<StopTime> durationCalc = (ArrayList<StopTime>) this.getStopTimes();
        int lastTime = durationCalc.size();
        StopTime FirstDepartTime;
        StopTime LastArriveTime;
        FirstDepartTime = durationCalc.get(0);
        LastArriveTime = durationCalc.get(lastTime -1);
        double tripStart = (double)FirstDepartTime.getDepartureTime().getTime();
        double tripEnd = (double)LastArriveTime.getDepartureTime().getTime();
        return tripEnd - tripStart;
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
     * @param headSign the headSign of the trip
     */
    public void setHeadSign(String headSign) {
        this.headSign = headSign;
    }

    /**
     * @return
     */
    public boolean isActive() {
        //TODO  - needs implementation eventually
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
        return "Lorem ipsum dolor";
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
        attributes.put("Average Speed", "" + getAvgSpeed());
        attributes.put("Distance", "" + getDistance());
        attributes.put("Duration", "" + getDuration());
        return attributes;
    }

}