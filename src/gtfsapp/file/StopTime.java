package gtfsapp.file;

import gtfsapp.id.RouteID;

import gtfsapp.id.StopTimeID;
import gtfsapp.id.TripID;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Mason Schlax
 * @version 1.0
 * @created 15-Apr-2020 1:20:18 PM
 */
public class StopTime extends GTFSElement {

    /**
     * The feed the stop time belongs to
     */
    private final Feed feed;
    /**
     * The time the trip arrives at the stop
     */
    private Date arrivalTime;
    /**
     * The time the trip departs from the stop
     */
    private Date departureTime;
    /**
     * The stop that the stop time occurs at
     */
    private Stop stop;

    /**
     * The sequence that the stop time occurs in (lower values preceed higher values)
     */
    private int sequence;

    /**
     * The head sign of the stop time
     */
    private String headSign;

    /**
     * Constructor for StopTime object, with three parameters
     *
     * @param feed     for the stopTime
     * @param stop     for the StopTime
     * @param sequence of the stopTimes
     */
    public StopTime(Feed feed, Stop stop, int sequence) {
        super(new StopTimeID());
        this.feed = feed;
        this.stop = stop;
        this.sequence = sequence;
    }

    /**
     * Gets a set of all trips that contain this stop time
     *
     * @return a set of all trips that contain this stop time
     */
    public Set<Trip> getContainingTrips() {

        Set<Trip> containingTrips = new HashSet<>();

        // for each trip in the feed
        for (Trip trip : feed.getTrips()) {
            if (trip.containsStopTime((StopTimeID) getID())) {
                containingTrips.add(trip);
            }
        }

        return containingTrips;

    }

    /**
     * Gets a set of the IDs of all trips that contain this stop time
     *
     * @return a set of the IDs of all trips that contain this stop time
     */
    public Set<TripID> getContainingTripIDs() {
        return getContainingTrips().stream()
                .map(e -> (TripID) e.getID())
                .collect(Collectors.toSet());
    }

    /**
     * Gets a set of all routes that contain this stop time
     *
     * @return a set of all routes that contain this stop time
     */
    public Set<Route> getContainingRoutes() {

        Set<Route> containingRoutes = new HashSet<>();

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
     * @return
     */
    public Set<RouteID> getContainingRouteIDs() {
        return getContainingRoutes().stream()
                .map(e -> (RouteID) e.getID())
                .collect(Collectors.toSet());
    }

    /**
     * Getter for the arrival times
     *
     * @return the arrival times
     */
    public Date getArrivalTime() {
        return arrivalTime;
    }

    /**
     * Sets the arrival time for the stop
     *
     * @param arrivalTime the time the trip arrives at the stop
     */
    public void setArrivalTime(Date arrivalTime) {
        this.arrivalTime = arrivalTime;

    }

    /**
     * Getter for the departure times for the stop
     *
     * @return the time the trip leaves the stop
     */
    public Date getDepartureTime() {
        return this.departureTime;
    }

    /**
     * Sets the departure time for the stop
     *
     * @param departureTime the time the trip leaves the stop
     */
    public void setDepartureTime(Date departureTime) {
        this.departureTime = departureTime;
    }

    /**
     * Getter for the stop this stop time occurs at
     *
     * @return the stop that this stop time occurs at
     */
    public Stop getStop() {
        return stop;
    }

    /**
     * Sets the stop that this stop time occurs at
     *
     * @param stop the stop that this stop time occurs at
     */
    public void setStop(Stop stop) {
        this.stop = stop;
    }

    /**
     * Getter for the head sign for the current stop time
     *
     * @return the head sign
     */
    public String getHeadSign() {
        return headSign;
    }

    /**
     * Sets the head sign for the stop time
     *
     * @param headSign the head sign
     */
    public void setHeadSign(String headSign) {
        this.headSign = headSign;
    }

    /**
     * Gets the sequence of the stop
     *
     * @return the sequence of the stop
     */
    public int getSequence() {
        return sequence;
    }

    /**
     * Sets the sequence of the stop
     *
     * @param sequence the sequence of the stop
     */
    public void setSequence(int sequence) {
        this.sequence = sequence;
    }

    /**
     * @return
     */
    public boolean isActive() {
        // TODO - needs implementation eventually
        throw new UnsupportedOperationException();
    }

    /**
     * Getter for the feed the stop belongs to
     *
     * @return the feed the stop belongs to
     */
    public Feed getFeed() {
        return feed;
    }

    /**
     * Gets the stop time's title to be displayed in the GUI
     *
     * @return the stop time's title
     */
    @Override
    public String getTitle() {
        return "Stop Time " + getID().getIDString();
    }

    /**
     * Gets the stop time's subtitle to be displayed in the GUI
     *
     * @return the stop time's subtitle
     */
    @Override
    public String getSubtitle() {
        // TODO - remove placeholder
        return "Lorem ipsum dolor";
    }

    /**
     * Gets the stop time's attributes to be displayed in the GUI
     *
     * @return a HashMap<Attribute Title, Attribute Value> of the stop time's attributes
     */
    @Override
    public HashMap<String, String> getAttributes() {
        HashMap<String, String> attributes = new HashMap<>();
        // TODO - remove placeholders
        attributes.put("Stop", "Lorem ipsum dolor");
        attributes.put("Arrival Time", "Lorem ipsum dolor");
        attributes.put("Departure Time", "Lorem ipsum dolor");
        return attributes;
    }

    /**
     * Determines when one stop time's arrival time occurs with relevance to another
     * @param stopTime - the stop time to compare to
     * @return -1 if the other stop time's arrival time occurs before this stop time's arrival time , 0 if the stop
     * time's arrival times occur simultaneously, or 1 if the other stop time's arrival time occurs after this stop
     * time's arrival time
     */
    public int compareArrivalTo(StopTime stopTime) {
        return getArrivalTime().compareTo(stopTime.getArrivalTime());
    }

    /**
     * Determines when one stop time's departure time occurs with relevance to another
     * @param stopTime - the stop time to compare to
     * @return -1 if the other stop time's departure time occurs before this stop time's departure time , 0 if the stop
     * time's departure times occur simultaneously, or 1 if the other stop time's departure time occurs after this stop
     * time's departure time
     */
    public int compareDepartureTo(StopTime stopTime) {
        return getDepartureTime().compareTo(stopTime.getDepartureTime());
    }

}