package gtfsapp.file;

import gtfsapp.id.RouteID;
import gtfsapp.id.StopID;
import gtfsapp.id.StopTimeID;
import gtfsapp.id.TripID;

import javafx.scene.paint.Color;
import javafx.geometry.Point2D;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author Colton Rivard
 * @version 1.0
 * @created 15-Apr-2020 1:20:18 PM
 */
public class Route extends GTFSElement {

    private Feed feed;
    private RouteType routeType;
    private HashMap<TripID, Trip> trips;
    private String shortName;
    private String longName;
    private String url;
    private Color color;
    private Color textColor;

    /**
     *
     * @param id
     * @param feed
     * @param routeType
     * @param color
     */
    public Route(Feed feed, String id, RouteType routeType, Color color) {
        // TODO - this constructor is not fully implemented!
        super(new RouteID(id));
    }

    /**
     * @param trips
     */
    public void addAllTrips(ArrayList<Trip> trips) {

    }

    /**
     * @param trip
     */
    public void addTrip(Trip trip) {

    }

    public void clearTrips() {

    }

    /**
     * @param id
     */
    public boolean containsStop(StopID id) {
        return false;
    }

    /**
     * @param id
     */
    public boolean containsStopTime(StopTimeID id) {
        return false;
    }

    /**
     * @param id
     */
    public boolean containsTrip(TripID id) {
        return false;
    }

    public Stop getActiveStop() {
        return null;
    }

    public StopTime getActiveStopTime() {
        return null;
    }

    public Trip getActiveTrip() {
        return null;
    }

    public double getAvgSpeed() {
        return 0;
    }

    public Point2D getBusPosition() {
        return null;
    }

    public double getDistance() {
        return 0;
    }

    public double getDuration() {
        return 0;
    }

    public Feed getFeed() {
        return null;
    }

    public Stop getNextStop() {
        return null;
    }

    public StopTime getNextStopTime() {
        return null;
    }

    public Trip getNextTrip() {
        return null;
    }

    public Stop getPreviousStop() {
        return null;
    }

    public StopTime getPreviousStopTime() {
        return null;
    }

    public Trip getPreviousTrip() {
        return null;
    }

    /**
     * @param id
     */
    public Stop getStopByID(StopID id) {
        return null;
    }

    public ArrayList<StopID> getStopIDs() {
        return null;
    }

    public ArrayList<Stop> getStops() {
        return null;
    }

    /**
     * @param id
     */
    public StopTime getStopTimeByID(StopTimeID id) {
        return null;
    }

    public ArrayList<StopTimeID> getStopTimeIDs() {
        return null;
    }

    public ArrayList<StopTime> getStopTimes() {
        return null;
    }

    /**
     * @param id
     */
    public Trip getTripByID(TripID id) {
        return null;
    }

    public ArrayList<TripID> getTripIDs() {
        return null;
    }

    public ArrayList<Trip> getTrips() {
        return null;
    }

    public RouteType getRouteType() {
        return null;
    }

    public void setRouteType(RouteType routeType) {

    }

    public boolean isActive() {
        return false;
    }

    /**
     * @param trip
     */
    public Trip removeTrip(Trip trip) {
        return null;
    }

    /**
     * @param id
     */
    public Trip removeTripByID(TripID id) {
        return null;
    }

    /**
     *
     * @return
     */
    public String getShortName() {
        return shortName;
    }

    /**
     *
     * @param shortName
     */
    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    /**
     *
     * @return
     */
    public String getLongName() {
        return longName;
    }

    /**
     *
     * @param longName
     */
    public void setLongName(String longName) {
        this.longName = longName;
    }

    /**
     *
     * @return
     */
    public Color getColor() {
        return color;
    }

    /**
     *
     * @param color
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     *
     * @return
     */
    public Color getTextColor() {
        return textColor;
    }

    /**
     *
     * @param textColor
     */
    public void setTextColor(Color textColor) {
        this.textColor = textColor;
    }

    /**
     *
     * @return
     */
    public String getUrl() {
        return url;
    }

    /**
     *
     * @param url
     */
    public void setUrl(String url) {
        this.url = url;
    }
}