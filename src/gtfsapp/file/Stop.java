package gtfsapp.file;

import gtfsapp.id.RouteID;
import gtfsapp.id.StopID;
import gtfsapp.id.StopTimeID;
import gtfsapp.id.TripID;

import javafx.geometry.Point2D;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author Mason Schlax
 * @version 1.0
 * @created 15-Apr-2020 1:20:18 PM
 */
public class Stop extends GTFSElement {

    /**
     * The feed that the stop belongs to
     */
    private final Feed feed;

    /**
     * The location of the stop in latitude and longitude
     */
    private Point2D location;

    /**
     * The name of the stop
     */
    private String name;

    /**
     * The description of the stop
     */
    private String desc;

    /**
     * The URL of the stop
     */
    private String url;

    /**
     * Constructor for the stop object with an id and feed as parameters
     *
     * @param id   for the stop
     * @param feed for the stop
     */
    public Stop(Feed feed, String id) {
        super(new StopID(id));
        this.feed = feed;
    }

    /**
     * @return
     */
    public boolean isActive() {
        // TODO - needs implementation eventually
        throw new UnsupportedOperationException();
    }

    /**
     * **Not fully implemented**
     * Uses the feed the stop belongs to to get the routeIDs
     *
     * @return a list of the RouteIDs
     */
    public ArrayList<RouteID> getContainingRouteIDs() {
        // TODO - needs implementation eventually
        throw new UnsupportedOperationException();
    }

    /**
     * Returns the routes
     *
     * @return all of the routes
     */
    public ArrayList<Route> getContainingRoutes() {
        // TODO - needs implementation eventually
        throw new UnsupportedOperationException();
    }

    /**
     * @return
     */
    public ArrayList<StopTimeID> getContainingStopTimeIDs() {
        // TODO - needs implementation eventually
        throw new UnsupportedOperationException();
    }

    /**
     * @return
     */
    public ArrayList<StopTime> getContainingStopTimes() {
        // TODO - needs implementation eventually
        throw new UnsupportedOperationException();
    }

    /**
     * @return
     */
    public ArrayList<TripID> getContainingTripIDs() {
        // TODO - needs implementation eventually
        throw new UnsupportedOperationException();
    }

    /**
     * @return
     */
    public ArrayList<Trip> getContainingTrips() {
        // TODO - needs implementation eventually
        throw new UnsupportedOperationException();
    }

    /**
     * Not yet implemented
     *
     * @return
     */
    public StopTime getNextStopTime() {
        // TODO - needs implementation eventually
        throw new UnsupportedOperationException();
    }

    /**
     * Not yet implemented
     *
     * @return
     */
    public Trip getNextTrip() {
        // TODO - needs implementation eventually
        throw new UnsupportedOperationException();
    }

    /**
     * @return
     */
    public StopTime getPreviousStopTime() {
        // TODO - needs implementation eventually
        throw new UnsupportedOperationException();
    }

    /**
     * @return
     */
    public Trip getPreviousTrip() {
        // TODO - needs implementation eventually
        throw new UnsupportedOperationException();
    }

    /**
     * Getter for the location of the stop
     *
     * @return the location
     */
    public Point2D getLocation() {
        return location;
    }

    /**
     * Sets the location of the stop
     *
     * @param location the map location for the stop
     */
    public void setLocation(Point2D location) {
        this.location = location;
    }

    /**
     * Getter for the name of the stop
     *
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
     * Geter for the description of the stop
     *
     * @return the description
     */
    public String getDesc() {
        return desc;
    }

    /**
     * Sets the description of the stop
     */
    public void setDesc(String desc) {
        this.desc = desc;
    }

    /**
     * Getter for the URL of the stop
     *
     * @return the URL
     */
    public String getURL() {
        return url;
    }

    /**
     * Sets the URL of the stop
     */
    public void setURL(String url) {
        this.url = url;
    }

    /**
     * Getter for the feed the stop belongs to
     *
     * @return the feed
     */
    public Feed getFeed() {
        return feed;
    }

    /**
     * Gets the stop's title to be displayed in the GUI
     *
     * @return the stop's title
     */
    @Override
    public String getTitle() {
        return "Stop " + getID().getIDString();
    }

    /**
     * Gets the stop's subtitle to be displayed in the GUI
     *
     * @return the stop's subtitle
     */
    @Override
    public String getSubtitle() {
        // TODO - remove placeholder
        return "Lorem ipsum dolor";
    }

    /**
     * Gets the stop's attributes to be displayed in the GUI
     *
     * @return a HashMap<Attribute Title, Attribute Value> of the stop's attributes
     */
    @Override
    public HashMap<String, String> getAttributes() {
        // TODO - remove placeholders
        HashMap<String, String> attributes = new HashMap<>();
        attributes.put("Location", "Lorem ipsum dolor");
        attributes.put("Next Trip", "Lorem ipsum dolor");
        attributes.put("Last Trip", "Lorem ipsum dolor");
        return attributes;
    }

}

