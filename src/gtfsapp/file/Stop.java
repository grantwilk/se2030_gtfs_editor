package gtfsapp.file;

import gtfsapp.id.RouteID;
import gtfsapp.id.StopID;
import gtfsapp.id.StopTimeID;
import gtfsapp.id.TripID;
import gtfsapp.util.Colors;
import gtfsapp.util.Location;
import gtfsapp.util.Time;
import javafx.scene.paint.Color;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Mason Schlax
 * @version 1.0
 * @created 15-Apr-2020 1:20:18 PM
 */
public class Stop extends GTFSElement {

    /**
     * The default color of a stop
     */
    private static final Color DEFAULT_COLOR = Colors.fromString("#D0D0D0");

    /**
     * Initial value for the difference in time for the next stop
     */
    private static final long NEXT_STOP_MAX_TIME = 1000000000;

    /**
     * Initial value for the difference in time for the previous stop
     */
    private static final long PREV_STOP_MAX_TIME = -1000000000;

    /**
     * The feed that the stop belongs to
     */
    private final Feed feed;

    /**
     * The location of the stop in latitude and longitude
     */
    private Location location;

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
     * @param idString for the stop
     * @param feed     for the stop
     */
    public Stop(Feed feed, String idString) {
        super(new StopID(idString));
        this.feed = feed;
    }

    /**
     * Takes the current time in milliseconds, and checks if the first stopTime arrival is less than the current time
     * and the last stopTime departure is greater than the current time
     * @return True if the stop is active, false if not
     */
    public boolean isActive() {

        long currentTime = System.currentTimeMillis();
        List<StopTime> stopTimeList = this.getContainingStopTimes();
        long lowTime = stopTimeList.get(0).getArrivalTime().getMillis();
        long highTime = stopTimeList.get(0).getDepartureTime().getMillis();
        for(int i = 0; i < stopTimeList.size()-1; i++){
            if(stopTimeList.get(i).getArrivalTime().getMillis() < lowTime) {
                lowTime = stopTimeList.get(i).getArrivalTime().getMillis();
            }
            if(stopTimeList.get(i).getDepartureTime().getMillis() > highTime){
                highTime = stopTimeList.get(i).getDepartureTime().getMillis();
            }

        }
        if(lowTime < currentTime && currentTime < highTime){
            return true;
        } else {
            return false;
        }
    }

    /**
     * Gets a list of the IDs of all routes that contain this stop
     * @return a list of the IDs of all routes that contain this stop
     */
    public List<RouteID> getContainingRouteIDs() {
        return getContainingRoutes().stream()
                .map(e -> (RouteID) e.getID())
                .collect(Collectors.toList());
    }

    /**
     * Gets a list of all routes that contain this stop
     * @return a list of all routes that contain this stop
     */
    public List<Route> getContainingRoutes() {

        List<Route> containingRoutes = new ArrayList<>();

        // for each route in the feed
        for (Route route : feed.getRoutes()) {

            // check to see if the route's trips and our containing trips have anything in common
            if (!Collections.disjoint(route.getTrips(), getContainingTrips())) {

                // if they do, add the route to our set of containing route
                containingRoutes.add(route);

            }
        }

        return containingRoutes;

    }

    /**
     * Gets a list of the IDs of all stop times that contain this stop
     * @return a list of the IDs of all stop times that contain this stop
     */
    public List<StopTimeID> getContainingStopTimeIDs() {
        return getContainingStopTimes().stream()
                .map(e -> (StopTimeID) e.getID())
                .collect(Collectors.toList());
    }

    /**
     * Gets a list of all stop times that contain this stop
     * @return a list of all stop times that contain this stop
     */
    public List<StopTime> getContainingStopTimes() {

        List<StopTime> containingStopTimes = new ArrayList<>();

        // for each stop time
        for (StopTime stopTime : feed.getStopTimes()) {

            // if the trip contains our stop ID
            if (stopTime.getStop().getID().equals(getID())) {

                // add the stop time to our set of containing stop times
                containingStopTimes.add(stopTime);

            }

        }

        return containingStopTimes;

    }

    /**
     * Gets a set of the IDs of all trips that contain this stop
     *
     * @return a set of the IDs of all trips that contain this stop
     */
    public List<TripID> getContainingTripIDs() {
        return getContainingTrips().stream()
                .map(e -> (TripID) e.getID())
                .collect(Collectors.toList());
    }

    /**
     * Gets a set of all trips that contain this stop
     *
     * @return a set of all trips that contain this stop
     */
    public List<Trip> getContainingTrips() {

        List<Trip> containingTrips = new ArrayList<>();

        // for each trip in the feed
        for (Trip trip : feed.getTrips()) {

            // check to see if the trips stop times and our containing stop times have anything in common
            if (!Collections.disjoint(trip.getStopTimes(), getContainingStopTimes())) {

                // if they do, add the trip to our set of containing trips
                containingTrips.add(trip);

            }
        }

        return containingTrips;
    }

    /**
     * Gets a list of the stop times for this stop, and compares them to the current time
     * It takes the closest value to the current time and returns that value
     *
     * @return the StopTime for the next closest stop
     */
    public StopTime getNextStopTime() {
        Time currentTime = new Time(System.currentTimeMillis());
        List<StopTime> stopTimeList = getContainingStopTimes();
        StopTime nextStopTime = null;
        long timeDiff = 0;
        long lowDiff = NEXT_STOP_MAX_TIME;
        for(StopTime stopTime: stopTimeList){
            Time nextArrival = (stopTime.getArrivalTime());
            if(currentTime.compareTo(nextArrival) < 0){
                timeDiff = nextArrival.getMillis() - currentTime.getMillis();
            }
            if(timeDiff < lowDiff){
                nextStopTime = stopTime;
            }
        }
        return nextStopTime;
    }

    /**
     * Takes the next stopTime and finds the trip associated with that stop time
     *
     * @return the next trip to go to this stop
     */
    public Trip getNextTrip() {
        return getNextStopTime().getTrip();
    }

    /**
     * Gets a list of the stop times for this stop, and compares them to the current time
     * It takes the closest value before the current time and returns that value
     *
     * @return the StopTime for the last elapsed stopTime
     */
    public StopTime getPreviousStopTime() {
        Time currentTime = new Time(System.currentTimeMillis());
        List<StopTime> stopTimeList = getContainingStopTimes();
        StopTime prevStopTime = null;
        long timeDiff = 0;
        long lowDiff = PREV_STOP_MAX_TIME;
        for(StopTime stopTime: stopTimeList){
            Time nextArrival = (stopTime.getArrivalTime());
            if(currentTime.compareTo(nextArrival) < 0){
                timeDiff = nextArrival.getMillis() - currentTime.getMillis();
            }
            if(timeDiff > lowDiff){
                prevStopTime = stopTime;
            }
        }
        return prevStopTime;
    }


    /**
     * Takes the previous stopTime and finds the trip associated with that stop time
     *
     * @return the previous trip to stop at this stop
     */
    public Trip getPreviousTrip() {
        return getPreviousStopTime().getTrip();
    }

    /**
     * Getter for the location of the stop
     *
     * @return the location
     */
    public Location getLocation() {
        return location;
    }

    /**
     * Sets the location of the stop
     *
     * @param location the map location for the stop
     */
    public void setLocation(Location location) {
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
     * @return the stop's title
     */
    @Override
    public String getTitle() {
        return name;
    }

    /**
     * Gets the stop's subtitle to be displayed in the GUI
     * @return the stop's subtitle
     */
    @Override
    public String getSubtitle() {
        return "Stop " + getID().getIDString();
    }

    /**
     * Gets the stop's attributes to be displayed in the GUI
     * @return a Map<Attribute Title, Attribute Value> of the stop's attributes
     */
    @Override
    public Map<String, String> getAttributes() {
        Map<String, String> attributes = new LinkedHashMap<>();
        attributes.put("Location", getLocation().toString());
        attributes.put("Next Trip", getNextTrip().getID().getIDString());
        attributes.put("Last Trip", "Lorem ipsum dolor");
        return attributes;
    }

    /**
     * Gets the stop's color
     * @return the stop's color
     */
    @Override
    public Color getColor() {
        return DEFAULT_COLOR;
    }

}

