package gtfsapp.file;

import gtfsapp.id.RouteID;

import gtfsapp.id.StopTimeID;

import java.sql.Time;
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
    private String headSign;

    /**
     * Constructor for StopTime object, with three parameters
     * @param feed for the stopTime
     * @param stop for the StopTime
     * @param sequence of the stopTimes
     */
    public StopTime(Feed feed, Stop stop, int sequence) {
        // TODO - this constructor is not fully implemented!
        super(new StopTimeID());
        this.feed = feed;
        this.stop = stop;
        this.sequence = sequence;
    }

    /**
     *Getter for the arrival times
     * @return the arrival times
     */
    public Date getArrivalTime() {
        return arrivalTime;
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
     *Getter for the departure times for the stop
     * @return the departure time
     */
    public Date getDepartureTime() {
        return this.departureTime;
    }

    /**
     * Getter for the feed the stop is in
     * @return the Feed object
     */
    public Feed getFeed() {
        return this.feed;
    }

    /**
     * Getter for the stop the stop time belongs to
     * @return the stop
     */
    public Stop getStop() {
        return this.stop;
    }

    /**
     * ??Not sure about this one, but its late and imma just put it there for now??
     * Returns the trip this stop time belongs to
     * @return The trip
     */
    public Trip getTrip() {
        return this.getTrip();
    }

    /**
     *
     * @return
     */
    public boolean isActive() {
        return false;
    }

    /**
     * Sets the arrival time for the stop
     * @param arrivalTime the time for the next bus to arrive
     */
    public void setArrivalTime(Date arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    /**
     * Sets the departure time for the stop
     * @param departureTime the time for the next bus to leave
     */
    public void setDepartureTime(Date departureTime) {
        this.departureTime = departureTime;
    }

    /**
     * Sets the stop the stopTime belongs to
     * @param stop The stop
     */
    public void setStop(Stop stop) {
        this.stop = stop;
    }

    /**
     * Sets the headsign for the stopTime
     * @param headSign for the stopTime in question
     */
    public void setHeadSign(String headSign) {
        this.headSign = headSign;
    }

    /**
     * Getter for the headsign for the current stopTime
     * @return the headSign as a string
     */
    public String getHeadSign() {
        return headSign;
    }

    /**
     * Gets the sequence of the stop
     * @return the sequence of the stop
     */
    public int getSequence() {
        return sequence;
    }

    /**
     * Sets the sequence of the stop
     * @param sequence - the sequence of the stop
     */
    public void setSequence(int sequence) {
        this.sequence = sequence;
    }
}