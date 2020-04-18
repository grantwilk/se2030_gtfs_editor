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
    private int sequence;

    /**
     * @param feed
     * @param id
     * @param stop
     * @param sequence
     */
    public StopTime(Feed feed, String id, Stop stop, int sequence) {
        // TODO - this constructor is not fully implemented!
        super(new StopTimeID(id));
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