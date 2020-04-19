package gtfsapp.file;

import gtfsapp.id.RouteID;
import gtfsapp.id.StopID;
import gtfsapp.id.StopTimeID;

import gtfsapp.id.TripID;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.HashMap;

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
     * @param id
     * @param feed
     */
    public Trip(String id, Feed feed) {
        // TODO - this constructor is not fully implemented!
        super(new TripID(id));
    }

    /**
     * @param stopTimes
     */
    public void addAllStopTimes(ArrayList<StopTime> stopTimes) {

    }

    /**
     * @param stopTime
     */
    public void addStopTime(StopTime stopTime) {

    }

    /**
     *
     */
    public void clearStopTimes() {

    }

    /**
     * @param id
     */
    public boolean containsStop(StopID id) {
        return false;
    }

    /**
     * @param id
     */
    public boolean containsStopTime(StopTimeID id) {
        return false;
    }

    /**
     *
     * @return
     */
    public Stop getActiveStop() {
        return null;
    }

    /**
     *
     * @return
     */
    public StopTime getActiveStopTime() {
        return null;
    }

    /**
     *
     * @return
     */
    public double getAvgSpeed() {
        return 0;
    }

    /**
     *
     * @return
     */
    public Point2D getBusPosition() {
        return null;
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
     * @param id
     */
    public StopTime getStopTimeByID(StopTimeID id) {
        return null;
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
     *
     * @return
     */
    public boolean isActive() {
        return false;
    }

    /**
     * @param stopTime
     */
    public StopTime removeStopTime(StopTime stopTime) {
        return null;
    }

    /**
     * @param id
     */
    public StopTime removeStopTimeByID(StopTimeID id) {
        return null;
    }

}