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
     * The number of milliseconds in an hour
     */
    private static final double MILLIS_IN_HOUR = 3600000;

    /**
     * The feed the trip belongs to
     */
    private final Feed feed;

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
        for (StopTime stopTime : stopTimes) {
            this.stopTimes.put((StopTimeID) stopTime.getID(), stopTime);
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
        return getStopIDs().contains(id);
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
        if (getStopIDs().contains(id)){
            for (Stop stop : getStops()) {
                if (stop.getID().equals(id)) {
                    return stop;
                }
            }
        }
        return null;
    }

    /**
     * Gets all of the stop IDs contained within the trip
     *
     * @return a list of stop IDs contained within the trip
     */
    public Set<StopID> getStopIDs() {
        Set<StopID> stopIDs = new HashSet<>();
        for (StopTime stopTime : stopTimes.values()) {
            stopIDs.add((StopID) stopTime.getID());
        }
        return stopIDs;
    }

    /**
     * Gets all of the stops contained within the trip
     *
     * @return a list of stops contained within the trip
     */
    public Set<Stop> getStops() {
        Set<Stop> stops = new HashSet<>();
        for (StopTime stopTime : stopTimes.values()) {
            stops.add(stopTime.getStop());
        }
        return stops;
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
     * Gets the average speed of a trip in miles per hour
     *
     * @return the average speed of the trip in miles per hour
     */
    public double getAvgSpeed() {

        // get the distance in miles and the duration in hours
        double distanceMiles = getDistance();
        double durationHours = getDuration() / MILLIS_IN_HOUR;

        // return the average speed of the trip in miles per hour
        return distanceMiles / durationHours;
    }

    /**
     * @return
     */
    public Location getBusPosition() {
        // TODO - needs implementation eventually
        throw new UnsupportedOperationException();
    }

    /**
     * Puts all of the stops in a sorted list, then finds the distance in miles between
     * consecutive stops
     *
     * @return The distance between stops in miles
     */
    public double getDistance() {

        // get a list of all stops in order of arrival times
        List<StopTime> stopTimes = new ArrayList<>(getStopTimes());
        stopTimes = stopTimes.stream().sorted().collect(Collectors.toList());
        List<Stop> stop = stopTimes.stream().map(StopTime::getStop).collect(Collectors.toList());

        //number of stops on the trip
        int numStops = stop.size()-1;
        double distanceTraveled = 0;

        //starting at the first index, and going to the second to last, the difference between the stops is calculated in miles
        for(int i = 0; i < numStops; i++){
            Location locationOne = stop.get(i).getLocation();
            Location locationTwo = stop.get(i+1).getLocation();
            distanceTraveled += locationOne.distanceTo(locationTwo);
        }

        return distanceTraveled;
    }

    /**
     * Gets the duration of the trip in milliseconds
     *
     * @return the duration of the trip in milliseconds
     */
    public long getDuration() {
        // get a list of all stop times in order of arrival times
        List<StopTime> stopTimes = new ArrayList<>(getStopTimes());
        stopTimes = stopTimes.stream().sorted().collect(Collectors.toList());

        // get the first departure time and the last arrival time
        // TODO - find duration without stops
        Date firstDepartureTime = stopTimes.get(0).getDepartureTime();
        Date lastArrivalTime = stopTimes.get(stopTimes.size() - 1).getArrivalTime();

        // return the duration in milliseconds
        return lastArrivalTime.getTime() - firstDepartureTime.getTime();
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
        attributes.put("Average Speed", String.format("%.02f mph", getAvgSpeed()));
        attributes.put("Distance", String.format("%.02f miles", getDistance()));
        attributes.put("Duration", String.format("%.02f hours", getDuration() / MILLIS_IN_HOUR));
        return attributes;
    }

}