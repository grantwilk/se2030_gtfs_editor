package gtfsapp.file;

import gtfsapp.id.*;


import gtfsapp.util.Location;


import java.util.*;
import java.util.stream.Collectors;

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
        stops.put((StopID) stopTime.getStop().getID(), stopTime.getStop());
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
     *
     * @return a list of routes that contain this trip
     */
    public Set<Route> getContainingRoutes() {

        Set<Route> containingRoutes = new HashSet<>();

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
     * Gets a list of the IDs of the routes that contain this trip
     *
     * @return a list of the IDS of the routes that contain this trip
     */
    public Set<RouteID> getContainingRouteIDs() {
        return getContainingRoutes().stream()
                .map(e -> (RouteID) e.getID())
                .collect(Collectors.toSet());
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
    public Set<StopTimeID> getStopTimeIDs() {
        return stopTimes.keySet();
    }

    /**
     * Gets all of the stop times contained within the trip
     *
     * @return a list of stop times contained within the trip
     */
    public Set<StopTime> getStopTimes() {
        return new HashSet<>(stopTimes.values());
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
    public Set<StopID> getStopIDs() {
        return stops.keySet();
    }

    /**
     * Gets all of the stops contained within the trip
     *
     * @return a list of stops contained within the trip
     */
    public Set<Stop> getStops() {
        return new HashSet<>(stops.values());
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
     *
     * @return the average speed of the trip in miles per hour
     */
    public double getAvgSpeed() {
        return getDistance() / getDuration();
    }

    /**
     * @return
     */
    public Location getBusPosition() {
        // TODO - needs implementation eventually
        throw new UnsupportedOperationException();
    }

    /**
     * Gets the first stop on the trip's location and makes a new location object with those coordinates
     * Then finds the distance to the last stop on the trip and returns it
     *
     * @return The distance between stops in miles
     */
    public double getDistance() {
        //Gets an array list of all of the stops
        ArrayList<Stop> distanceCalc = (ArrayList<Stop>) this.getStops();
        //Gets the number of stops in list
        int lastLocation = distanceCalc.size();
        Stop FirstDepartLoc;
        Stop LastArriveLoc;
        //Gets the first stop
        FirstDepartLoc = distanceCalc.get(0);
        //Gets the last stop
        LastArriveLoc = distanceCalc.get(lastLocation - 1);
        //Gets the location for the first stop
        Location firstStop = new Location(FirstDepartLoc.getLocation().getLatitude(), FirstDepartLoc.getLocation().getLongitude());
        //Returns the distance between the first and last stops
        return firstStop.distanceTo(LastArriveLoc.getLocation());
    }

    /**
     * Method to get the duration of the trip by taking the difference between
     * the first depart time, and last arrival time
     *
     * @return the duration of the trip
     */
    public double getDuration() {
        //Gets an ArrayList of the stop times
        ArrayList<StopTime> durationCalc = (ArrayList<StopTime>) this.getStopTimes();
        //Number of stop times in the ArrayList
        int lastTime = durationCalc.size();
        StopTime FirstDepartTime;
        StopTime LastArriveTime;
        //Gets the first StopTime
        FirstDepartTime = durationCalc.get(0);
        //Gets the second StopTime
        LastArriveTime = durationCalc.get(lastTime - 1);
        //Gets the time value for the first StopTime
        double tripStart = (double) FirstDepartTime.getDepartureTime().getTime();
        //Gets the time value for the second StopTime
        double tripEnd = (double) LastArriveTime.getDepartureTime().getTime();
        //Gets the total trip time
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
     * Checks to see if the current system clock in milliseconds is in between the start and end times of a trip
     *
     * @return if the trip is ongoing
     */
    public boolean isActive() {
        //Gets an ArrayList of the stop times
        ArrayList<StopTime> durationCalc = (ArrayList<StopTime>) this.getStopTimes();
        //Number of stop times in the ArrayList
        int lastTime = durationCalc.size();
        StopTime FirstDepartTime;
        StopTime LastArriveTime;
        //Gets the first StopTime
        FirstDepartTime = durationCalc.get(0);
        //Gets the second StopTime
        LastArriveTime = durationCalc.get(lastTime - 1);
        //Gets the time value for the first StopTime
        double tripStart = (double) FirstDepartTime.getDepartureTime().getTime();
        //Gets the time value for the second StopTime
        double tripEnd = (double) LastArriveTime.getDepartureTime().getTime();
        if (System.currentTimeMillis() < tripEnd && System.currentTimeMillis() > tripStart) {
            return true;
        } else {
            return false;
        }
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