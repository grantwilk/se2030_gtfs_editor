package gtfsapp.file;

import gtfsapp.id.RouteID;
import gtfsapp.id.StopID;
import gtfsapp.id.StopTimeID;
import gtfsapp.id.TripID;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;

import java.util.ArrayList;

/**
 * @author Mason Schlax
 * @version 1.0
 * @created 15-Apr-2020 1:20:18 PM
 */
public class Stop extends GTFSElement {

    private Feed feed;
    private Point2D location;

    /**
     * Constructor for the stop object with an id and feed as parameters
     * @param id for the stop
     * @param feed for the stop
     */
    public Stop(String id, Feed feed) {
        // TODO - this constructor is not fully implemented!
        super(new StopID(id));
        this.feed = feed;
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
    public ArrayList<StopTimeID> getContainingStopTimeIDs() {
        return null;
    }

    /**
     *
     * @return
     */
    public ArrayList<StopTime> getContainingStopTimes() {
        return null;
    }

    /**
     *
     * @return
     */
    public ArrayList<TripID> getContainingTripIDs() {
        return null;
    }

    /**
     *
     * @return
     */
    public ArrayList<Trip> getContainingTrips() {
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
    public Point2D getLocation() {
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
    public Trip getNextTrip() {
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
     *
     * @return
     */
    public Trip getPreviousTrip() {
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
     * @param location
     */
    public void setLocation(Point2D location) {

    }

}