package gtfsapp.file;

import gtfsapp.id.RouteID;

import gtfsapp.id.StopTimeID;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Date;

/**
 * @author Mason Schlax
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
        // TODO - this constructor is not fully implemented!
        super(new StopTimeID(id));
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
        // TODO - this constructor is not fully implemented!
        this(id, null, null, null, null, null);
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
        // TODO - this constructor is not fully implemented!
        this(id, null, null, null, null, null);
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
        // TODO - this constructor is not fully implemented!
        this(id, null, null, null, null, null);
    }

    /**
     * @param stop
     */
    public boolean containsStop(Stop stop) {
        return false;
    }

    /**
     *
     * @return
     */
    public Date getArrivalTime() {
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
    public Date getDepartureTime() {
        return null;
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
    public Stop getStop() {
        return null;
    }

    /**
     *
     * @return
     */
    public Trip getTrip() {
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