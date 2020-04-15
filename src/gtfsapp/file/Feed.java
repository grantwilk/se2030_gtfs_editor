package gtfsapp.file;

import gtfsapp.id.*;

import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author grant
 * @version 1.0
 * @created 15-Apr-2020 1:20:18 PM
 */
public class Feed extends GTFSElement {

    private FeedID id;
    private HashMap<RouteID, Route> routes;

    /**
     * @param id
     */
    public Feed(String id) {

    }

    /**
     * @param id
     * @param name
     */
    public Feed(String id, String name) {

    }

    /**
     * @param id
     * @param name
     * @param color
     */
    public Feed(String id, String name, Color color) {

    }

    /**
     * @param routes
     */
    public void addAllRoutes(ArrayList<Route> routes) {

    }

    /**
     * @param route
     */
    public void addRoute(Route route) {

    }

    public void clearRoutes() {

    }

    /**
     * @param id
     */
    public boolean containsRoute(RouteID id) {
        return false;
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
     * @param id
     */
    public boolean containsTrip(TripID id) {
        return false;
    }

    public String getName() {
        return "";
    }

    /**
     * @param id
     */
    public Route getRouteByID(RouteID id) {
        return null;
    }

    public ArrayList<RouteID> getRouteIDs() {
        return null;
    }

    public ArrayList<Route> getRoutes() {
        return null;
    }

    /**
     * @param id
     */
    public Stop getStopByID(StopID id) {
        return null;
    }

    public ArrayList<StopID> getStopIDs() {
        return null;
    }

    public ArrayList<Stop> getStops() {
        return null;
    }

    /**
     * @param id
     */
    public StopTime getStopTimeByID(StopTimeID id) {
        return null;
    }

    public ArrayList<StopTimeID> getStopTimeIDs() {
        return null;
    }

    public ArrayList<StopTime> getStopTimes() {
        return null;
    }

    /**
     * @param id
     */
    public Trip getTripByID(TripID id) {
        return null;
    }

    public ArrayList<TripID> getTripIDs() {
        return null;
    }

    public ArrayList<Trip> getTrips() {
        return null;
    }

    /**
     * @param route
     */
    public Route removeRoute(Route route) {
        return null;
    }

    /**
     * @param id
     */
    public Route removeRouteByID(RouteID id) {
        return null;
    }

    public void setColor() {

    }

    /**
     * @param name
     */
    public void setName(String name) {

    }

}