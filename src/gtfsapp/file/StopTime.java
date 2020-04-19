package gtfsapp.file;

import gtfsapp.id.RouteID;

import gtfsapp.id.StopTimeID;

import java.sql.Time;
import java.util.ArrayList;

/**
 * @author Mason Schlax
 * @version 1.0
 * @created 15-Apr-2020 1:20:18 PM
 */
public class StopTime extends GTFSElement {

    private Time arrivalTime;
    private Time departureTime;
    private Feed feed;
    private Stop stop;
    private int sequence;
    private String headSign;

    /**
     * @param feed
     * @param stop
     * @param sequence
     */
    public StopTime(Feed feed, Stop stop, int sequence) {
        // TODO - this constructor is not fully implemented!
        super(new StopTimeID());
    }

    /**
     *
     * @return
     */
    public Time getArrivalTime() {
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
    public Time getDepartureTime() {
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
    public void setArrivalTime(Time arrivalTime) {

    }

    /**
     * @param departureTime
     */
    public void setDepartureTime(Time departureTime) {

    }

    /**
     * @param stop
     */
    public void setStop(Stop stop) {

    }

    /**
     *
     * @param headSign
     */
    public void setHeadSign(String headSign) {
        this.headSign = headSign;
    }

    /**
     *
     * @return
     */
    public String getHeadSign() {
        return headSign;
    }
}