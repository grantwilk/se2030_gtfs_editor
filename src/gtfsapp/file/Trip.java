package gtfsapp.file;

import gtfsapp.id.RouteID;
import gtfsapp.id.StopID;
import gtfsapp.id.StopTimeID;
import gtfsapp.id.TripID;
import gtfsapp.util.Location;
import gtfsapp.util.Time;
import javafx.scene.paint.Color;

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
     * Gets the route that contains this trip
     *
     * @return the route that contains this trip
     */
    public Route getRoute() {

        // for each route in our feed
        for (Route route : feed.getRoutes()) {

            // if the route's trips contains our trip, return the route
            if (route.getTrips().contains(this)) {
                return route;
            }

        }

        // return null if nothing is found
        return null;

    }

    /**
     * Gets a list of the IDs of the routes that contain this trip
     *
     * @return a list of the IDS of the routes that contain this trip
     */
    public RouteID getContainingRouteID() {
        return (RouteID) getRoute().getID();
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
     * @return a list of stop time IDs contained within the trip
     */
    public List<StopTimeID> getStopTimeIDs() {
        return new ArrayList<>(stopTimes.keySet());
    }

    /**
     * Gets all of the stop times contained within the trip
     *
     * @return a list of stop times contained within the trip
     */
    public List<StopTime> getStopTimes() {
        return new ArrayList<>(stopTimes.values());
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
    public List<StopID> getStopIDs() {
        List<StopID> stopIDs = new ArrayList<>();
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
    public List<Stop> getStops() {
        List<Stop> stops = new ArrayList<>();
        for (StopTime stopTime : stopTimes.values()) {
            stops.add(stopTime.getStop());
        }
        return stops;
    }

    /**
     * Finds the next stopTime on the trip by using a list of stoptimes and the system clock
     * @return the next stopTime on the trip
     */
    public StopTime getNextStopTime() {
        //current time of the system in milliseconds
        Time currentTime = new Time(System.currentTimeMillis());
        //List of the stop times on the trip
        ArrayList<StopTime> stopTimeList = (ArrayList)getStopTimes();
        //the next stop to be returned
        StopTime nextStopTime = null;
        //comparison values
        int timeDiff = 0;
        int lowDiff = -1000;
        for(int i =0; i< stopTimeList.size()-1; i++){
            //checks if the stop time is less than the current time, if so, do nothing
            if(currentTime.compareTo(stopTimeList.get(i).getArrivalTime())>0){
                nextStopTime =nextStopTime;
            } else {
                //if the stop time is after the current time, set the difference to that value
                timeDiff = currentTime.compareTo(stopTimeList.get(i).getArrivalTime());
            }
            //If the timeDiff is greater than the low difference (closer to 0/the current time)
            //set the next stop to the stop associated with the time diff
            if(timeDiff > lowDiff){
                nextStopTime = stopTimeList.get(i);
            }
        }
        return nextStopTime;
    }

    /**
     * @return
     */
    public StopTime getPreviousStopTime() {
        // TODO - needs implementation eventually
        throw new UnsupportedOperationException();
    }

    /**
     * Finds the next stop on the trip by using a list of stop times and the system clock
     * @return the next stop on the trip
     */
    public Stop getNextStop() {
        //current time of the system in milliseconds
        Time currentTime = new Time(System.currentTimeMillis());
        //List of the stop times on the trip
        ArrayList<StopTime> stopTimeList = (ArrayList)getStopTimes();
        //the next stop to be returned
        Stop nextStop = null;
        //comparison values
        int timeDiff = 0;
        int lowDiff = -100000000;
        for(int i =0; i< stopTimeList.size()-1; i++){
            //checks if the stop time is less than the current time, if so, do nothing
            if(currentTime.compareTo(stopTimeList.get(i).getArrivalTime())>0){
                nextStop =nextStop;
            } else {
                //if the stop time is after the current time, set the difference to that value
                timeDiff = currentTime.compareTo(stopTimeList.get(i).getArrivalTime());
            }
            //If the timeDiff is greater than the low difference (closer to 0/the current time)
            //set the next stop to the stop associated with the time diff
            if(timeDiff > lowDiff){
                nextStop = stopTimeList.get(i).getStop();
            }
        }
        return nextStop;
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
        double durationHours = getDuration() / (double) Time.getMillisInHour();

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
        Time firstDepartureTime = stopTimes.get(0).getDepartureTime();
        Time lastArrivalTime = stopTimes.get(stopTimes.size() - 1).getArrivalTime();

        // return the duration in milliseconds
        return lastArrivalTime.getMillis() - firstDepartureTime.getMillis();
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
        double tripStart = (double) FirstDepartTime.getDepartureTime().getMillis();
        //Gets the time value for the second StopTime
        double tripEnd = (double) LastArriveTime.getDepartureTime().getMillis();

        return System.currentTimeMillis() < tripEnd && System.currentTimeMillis() > tripStart;

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
     * @return the trip's title
     */
    @Override
    public String getTitle() {
        return getID().getIDString();
    }

    /**
     * Gets the trip's subtitle to be displayed in the GUI
     * @return the trip's subtitle
     */
    @Override
    public String getSubtitle() {
        if (headSign != null && !headSign.equals("")) {
            return getHeadSign();
        } else {
            return "Unnamed Trip";
        }
    }

    /**
     * Gets the trip's attributes to be displayed in the GUI
     * @return a Map<Attribute Title, Attribute Value> of the trip's attributes
     */
    @Override
    public Map<String, String> getAttributes() {
        Map<String, String> attributes = new LinkedHashMap<>();
        attributes.put("Distance", String.format("%.02f miles", getDistance()));
        attributes.put("Duration", String.format("%.02f hours", getDuration() / (double) Time.getMillisInHour()));
        attributes.put("Average Speed", String.format("%.02f mph", getAvgSpeed()));
        return attributes;
    }

    /**
     * Gets the trip's color
     * @return the trip's color
     */
    @Override
    public Color getColor() {
        return getRoute().getColor();
    }

}