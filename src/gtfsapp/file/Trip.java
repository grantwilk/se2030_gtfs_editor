package gtfsapp.file;

import gtfsapp.id.RouteID;
import gtfsapp.id.StopID;
import gtfsapp.id.StopTimeID;

import gtfsapp.id.TripID;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Mason Schlax
 * @version 1.0
 * @created 15-Apr-2020 1:20:18 PM
 */
public class Trip extends GTFSElement {

    private Feed feed;
    private HashMap<StopID, Stop> stops;
    private HashMap<StopTimeID, StopTime> stopTimes;

    /**
     *Constructor for the trip object with an id and feed as parameters
     * @param id for the trip
     * @param feed for the trip
     */
    public Trip(Feed feed, String id) {
        // TODO - this constructor is not fully implemented!
        super(new TripID(id));
        this.feed = feed;
    }

    /**
     * ???Not sure If I did this right, I really struggled with maps in Data structures
     * Puts all of the stopTimes passed in, into the hash map stopTimes
     * @param stopTimes to be added to the stopTimes Hashmaps
     */
    public void addAllStopTimes(ArrayList<StopTime> stopTimes) {

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
     *Clears all of the stop times from the hash map
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
     *
     * @return
     */
    public Stop getActiveStop() {

    }

    /**
     *
     * @return
     */
    public StopTime getActiveStopTime() {
        return null;
    }

    /**
     *Unsupported
     * @return
     */
    public double getAvgSpeed() {
        throw new UnsupportedOperationException();
        //return 0;
    }

    /**
     *Unsupported
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
     *
     * @return
     */
    public double getDistance() {
        return 0;
    }

    /**
     *
     * @return
     */
    public double getDuration() {
        return 0;
    }

    /**
     *
     * @return
     */
    public Feed getFeed() {
        return null;
    }

    /**
     *
     * @return
     */
    public Stop getNextStop() {
        return null;
    }

    /**
     *
     * @return
     */
    public StopTime getNextStopTime() {
        return null;
    }

    /**
     *
     * @return
     */
    public Stop getPreviousStop() {
        return null;
    }

    /**
     *
     * @return
     */
    public StopTime getPreviousStopTime() {
        return null;
    }

    /**
     * @param stop
     */
    public Stop getStopByID(StopID stop) {
        return null;
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
     * Gets the stop time of the requested stop from the ID
     * @param id of the stop time wanted
     */
    public StopTime getStopTimeByID(StopTimeID id) {
        return stopTimes.get(id);
    }

    /**
     *
     * @return
     */
    public ArrayList<StopTimeID> getStopTimeIDs() {
        ArrayList<StopTimeID> stopTimeIDS;
        for(int i = 0; i < stopTimes.size(); i++){

        }
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
     *
     * @return
     */
    public boolean isActive() {
        return false;
    }

    /**
     * Removes the stop time from the stopTimes hash map
     * @param stopTime to be removed
     * @return the stopTime removed
     */
    public StopTime removeStopTime(StopTime stopTime) {
        return stopTimes.remove(stopTime);
    }

    /**
     * removes the Stop time that was previously at the id passed
     * @param id of the stop time to be removed
     * @return the StopTimeID removed
     */
    public StopTime removeStopTimeByID(StopTimeID id) {
        return stopTimes.remove(id);
    }

}