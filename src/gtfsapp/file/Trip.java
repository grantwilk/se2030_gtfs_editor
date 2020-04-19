package gtfsapp.file;

import gtfsapp.id.RouteID;
import gtfsapp.id.StopID;
import gtfsapp.id.StopTimeID;

import gtfsapp.id.TripID;
import javafx.geometry.Point2D;

import java.lang.reflect.Array;
import java.util.*;

/**
 * @author Mason Schlax
 * @version 1.0
 * @created 15-Apr-2020 1:20:18 PM
 */
public class Trip extends GTFSElement {

    private Feed feed;
    private HashMap<StopID, Stop> stops;
    private HashMap<StopTimeID, StopTime> stopTimes;
    private String headSign;

    /**
     * Constructor for the trip object with an id and feed as parameters
     * @param id for the trip
     * @param feed for the trip
     */
    public Trip(Feed feed, String id) {
        super(new TripID(id));
        this.feed = feed;
    }

    /**
     * ???Not sure If I did this right, I really struggled with maps in Data structures
     * Puts all of the stopTimes passed in, into the hash map stopTimes
     * @param stopTimes to be added to the stopTimes Hashmaps
     */
    public void addAllStopTimes(ArrayList<StopTime> stopTimes) {

        // TODO - Mason, a really easy way to do this is to use the HashMap's built in .addAll() method!
        // TODO - With that, you shouldn't have to use more than one line of code. -Grant

        for(int i = 0; i < stopTimes.size(); i++){
            this.stopTimes.putAll((Map<? extends StopTimeID, ? extends StopTime>) stopTimes);

        }

    }

    /**
     * @param stopTime
     */
    public void addStopTime(StopTime stopTime) {

    }

    /**
     * Clears all of the stop times from the hash map
     * @reutrn void
     */
    public void clearStopTimes() {
        stopTimes.clear();
    }

    /**
     * Searches the hash map of stops, by using a stop ID, to see if that stop
     * is on the trip
     * @param id of the stop
     */
    public boolean containsStop(StopID id) {
        return stops.containsValue(id);
    }

    /**
     * Searches the hash map of stop times, by using a stopTimeID to see if that stop time
     * is on the trip
     * @param id of the stop time
     */
    public boolean containsStopTime(StopTimeID id) {
        return stopTimes.containsKey(id);
    }

    /**
     * Not yet implemented
     * @return
     */
    public Stop getActiveStop() {
        throw new UnsupportedOperationException();
    }

    /**
     * Not yet implemented
     * @return
     */
    public StopTime getActiveStopTime() {
        throw new UnsupportedOperationException();
    }

    /**
     * Unsupported
     * @return
     */
    public double getAvgSpeed() {
        throw new UnsupportedOperationException();
        //return 0;
    }

    /**
     * Unsupported
     * @return
     */
    public Point2D getBusPosition() {
        throw new UnsupportedOperationException();
        //return null;
    }

    /**
     *
     * @return
     */
    public ArrayList<RouteID> getContainingRouteIDs() {
        return null;
    }

    /**
     *
     * @return
     */
    public ArrayList<Route> getContainingRoutes() {
        return null;
    }

    /**
     * Not yet implemented
     * @return disntace between stops
     */
    public double getDistance() {
        throw new UnsupportedOperationException();
    }

    /**
     * Not yet implemented
     * @return time between the stops
     */
    public double getDuration() {
        throw new UnsupportedOperationException();
    }

    /**
     * Getter for the feed for the trip
     * @return the feed the trip is in
     */
    public Feed getFeed() {
        return this.feed;
    }

    /**
     * Not yet implemented
     * @return
     */
    public Stop getNextStop() {
        throw new UnsupportedOperationException();
    }

    /**
     * Not yet implemeted
     * @return
     */
    public StopTime getNextStopTime() {
        throw new UnsupportedOperationException();
    }

    /**
     *
     * @return
     */
    public Stop getPreviousStop() {
        throw new UnsupportedOperationException();
    }

    /**
     *
     * @return
     */
    public StopTime getPreviousStopTime() {
        throw new UnsupportedOperationException();
    }

    /**
     * Returns the stop for the given stopID on the trip
     * @param stop StopID for the wanted stop
     */
    public Stop getStopByID(StopID stop) {
        return feed.getStopByID(stop);
    }

    /**
     *
     * @return
     */
    public ArrayList<StopID> getStopIDs() {
        throw new UnsupportedOperationException();
    }

    /**
     *
     * @return
     */
    public ArrayList<Stop> getStops() {
        return null;
    }

    /**
     * Gets the stop time of the requested stop from the ID
     * @param id of the stop time wanted
     */
    public StopTime getStopTimeByID(StopTimeID id) {
        return stopTimes.get(id);
    }

    /**
     * Returns the StopTimeIDS for this trip
     * @return an array list of the stopTimeIDs
     */
    public ArrayList<StopTimeID> getStopTimeIDs() {

        Set<StopTimeID> stopTimeIDSet = stopTimes.keySet();
        ArrayList<StopTimeID> stopTimeIDS = null;
        stopTimeIDS.addAll(stopTimeIDSet);

        return stopTimeIDS;
    }

    /**
     * Gets an ArrayList of StopTimes for the trip
     * @return the ArrayList of stopTimes
     */
    public ArrayList<StopTime> getStopTimes() {
        Collection<StopTime> stopTimeSet =stopTimes.values();
        ArrayList<StopTime> stopTimeArrayList = null;
        stopTimeArrayList.addAll(stopTimeSet);
        return stopTimeArrayList;
    }

    /**
     * Not yet implemented
     * @return
     */
    public boolean isActive() {
        throw new UnsupportedOperationException();
    }

    /**
     * Removes the stop time from the stopTimes hash map
     * @param stopTime to be removed
     * @return the stopTime removed
     */
    public StopTime removeStopTime(StopTime stopTime) {
        return stopTimes.remove((StopTimeID) stopTime.getID());
    }

    /**
     * Removes the Stop time that was previously at the id passed
     * @param id of the stop time to be removed
     * @return the StopTimeID removed
     */
    public StopTime removeStopTimeByID(StopTimeID id) {
        return stopTimes.remove(id);
    }

    /**
     * Setter for the headSign for the trip
     * @param headSign the headSign to be assigned
     */
    public void setHeadSign(String headSign) {
        this.headSign = headSign;
    }

    /**
     * Getter for the headSign of the trip
     * @return the headSign
     */
    public String getHeadSign() {
        return headSign;
    }

}