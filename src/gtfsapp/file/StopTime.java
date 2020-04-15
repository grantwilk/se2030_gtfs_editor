package gtfsapp.file;

import gtfsapp.id.RouteID;

import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Date;

/**
 * @author wilkg
 * @version 1.0
 * @created 15-Apr-2020 1:20:18 PM
 */
public class StopTime extends GTFSElement {

    private Date arrivalTime;
    private Date departureTime;
    private Feed feed;
    private Stop stop;
    private Trip trip;

    /**
     * @param id
     * @param feed
     * @param trip
     * @param stop
     * @param arrivalTime
     * @param departureTime
     */
    public StopTime(String id, Feed feed, Trip trip, Stop stop, Date arrivalTime, Date departureTime) {

    }

    /**
     * @param id
     * @param feed
     * @param trip
     * @param stop
     * @param arrivalTime
     * @param departureTime
     * @param name
     */
    public StopTime(String id, Feed feed, Trip trip, Stop stop, Date arrivalTime, Date departureTime, String name) {

    }

    /**
     * @param id
     * @param feed
     * @param trip
     * @param stop
     * @param arrivalTime
     * @param departureTime
     * @param color
     */
    public StopTime(String id, Feed feed, Trip trip, Stop stop, Date arrivalTime, Date departureTime, Color color) {

    }

    /**
     * @param id
     * @param feed
     * @param trip
     * @param stop
     * @param arrivalTime
     * @param departureTime
     * @param name
     * @param color
     */
    public StopTime(String id, Feed feed, Trip trip, Stop stop, Date arrivalTime, Date departureTime, String name, Color color) {

    }

    /**
     * @param stop
     */
    public boolean containsStop(Stop stop) {
        return false;
    }

    public Date getArrivalTime() {
        return null;
    }

    public ArrayList<RouteID> getContainingRouteIDs() {
        return null;
    }

    public ArrayList<Route> getContainingRoutes() {
        return null;
    }

    public Date getDepartureTime() {
        return null;
    }

    public Feed getFeed() {
        return null;
    }

    public Stop getStop() {
        return null;
    }

    public Trip getTrip() {
        return null;
    }

    public boolean isActive() {
        return false;
    }

    /**
     * @param arrivalTime
     */
    public void setArrivalTime(Date arrivalTime) {

    }

    /**
     * @param departureTime
     */
    public void setDepartureTime(Date departureTime) {

    }

    /**
     * @param stop
     */
    public void setStop(Stop stop) {

    }

}