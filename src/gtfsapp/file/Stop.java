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
    private String code;
    private String name;
    private String desc;
    private String url;

    /**
     * Constructor for the stop object with an id and feed as parameters
     * @param id for the stop
     * @param feed for the stop
     */
    public Stop(Feed feed, String id) {
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

    /**
     *
     * @return
     */
    public String getCode() {
        return code;
    }

    /**
     *
     * @return
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @return
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return
     */
    public String getDesc() {
        return desc;
    }

    /**
     *
     * @return
     */
    public void setDesc(String desc) {
        this.desc = desc;
    }

    /**
     *
     * @return
     */
    public String getURL() {
        return url;
    }

    /**
     *
     * @return
     */
    public void setURL(String url) {
        this.url = url;
    }

}

