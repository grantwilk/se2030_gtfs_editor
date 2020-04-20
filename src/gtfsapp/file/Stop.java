package gtfsapp.file;

import gtfsapp.id.RouteID;
import gtfsapp.id.StopID;
import gtfsapp.id.StopTimeID;
import gtfsapp.id.TripID;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
     * Gets the active stop time
     * Currently unsupported
     * @return
     */
    //TODO
    public StopTime getActiveStopTime() {
        throw new UnsupportedOperationException();
        //return null;
    }

    /**
     * **Not fully implemented**
     * Uses the feed the stop belongs to to get the routeIDs
     * @return a list of the RouteIDs
     */
    //TODO
    public ArrayList<RouteID> getContainingRouteIDs() {

         return feed.getRouteIDs();
    }

    /**
     *Returns the routes
     * @return all of the routes
     */
    //TODO
    public ArrayList<Route> getContainingRoutes() {
        return feed.getRoutes();

    }

    /**
     *
     * @return
     */
    //TODO
    public ArrayList<StopTimeID> getContainingStopTimeIDs() {
        return null;
    }

    /**
     *
     * @return
     */
    //TODO
    public ArrayList<StopTime> getContainingStopTimes() {
        return null;
    }

    /**
     *
     * @return
     */
    //TODO
    public ArrayList<TripID> getContainingTripIDs() {
        return null;
    }

    /**
     *
     * @return
     */
    //TODO
    public ArrayList<Trip> getContainingTrips() {
        return null;
    }

    /**
     * Getter for the feed the stop belongs to
     * @return the feed
     */
    public Feed getFeed() {
        return this.feed;
    }

    /**
     * Getter for the location of the stop
     * @return the location
     */
    public Point2D getLocation() {
        return this.location;
    }

    /**
     * Not yet implemented
     * @return
     */
    //TODO
    public StopTime getNextStopTime() {
        throw new UnsupportedOperationException();
       //return null;
    }

    /**
     * Not yet implemented
     * @return
     */
    //TODO
    public Trip getNextTrip() {
        throw new UnsupportedOperationException();
        //return null;
    }

    /**
     * Not yet implemented
     * @return
     */
    //TODO
    public StopTime getPreviousStopTime() {

        throw new UnsupportedOperationException();
        //return null;
    }

    /**
     * Not yet implemented
     * @return
     */
    //TODO
    public Trip getPreviousTrip() {
        throw new UnsupportedOperationException();
        //return null;
    }

    /**
     * Not yet implemented
     * @return
     */
    //TODO
    public boolean isActive() {
        throw new UnsupportedOperationException();
        //return false;
    }

    /**
     * Sets the location of the stop
     * @param location the map location for the stop
     */
    public void setLocation(Point2D location) {
        this.location = location;
    }

    /**
     * Getter for the code for the stop
     * @return the code
     */
    public String getCode() {
        return code;
    }

    /**
     * Sets the code for the stop
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * Getter for the name of the stop
     * @return name of the stop
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name for the stop
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the description for the stop
     * @return the description
     */
    public String getDesc() {
        return desc;
    }

    /**
     * Sets the description for the stop
     */
    public void setDesc(String desc) {
        this.desc = desc;
    }

    /**
     * Getter for the url
     * @return the url
     */
    public String getURL() {
        return url;
    }

    /**
     * Sets the url of the stop
     */
    public void setURL(String url) {
        this.url = url;
    }

    /**
     * Gets the stop's title to be displayed in the GUI
     * @return the stop's title
     */
    @Override
    public String getTitle() {
        return "Stop " + getID().getIDString();
    }

    /**
     * Gets the stop's subtitle to be displayed in the GUI
     * @return the stop's subtitle
     */
    @Override
    public String getSubtitle() {
        // TODO - remove placeholder
        return "Lorem ipsum dolor";
    }

    /**
     * Gets the stop's attributes to be displayed in the GUI
     * @return a HashMap<Attribute Title, Attribute Value> of the stop's attributes
     */
    @Override
    public HashMap<String, String> getAttributes() {
        HashMap<String, String> attributes = new HashMap<>();
        // TODO - remove placeholders
        attributes.put("Location", "Lorem ipsum dolor");
        attributes.put("Next Trip", "Lorem ipsum dolor");
        attributes.put("Last Trip", "Lorem ipsum dolor");
        return attributes;
    }

}

