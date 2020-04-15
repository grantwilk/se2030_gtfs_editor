package gtfsapp.file;

import gtfsapp.id.RouteID;
import gtfsapp.id.StopTimeID;
import gtfsapp.id.TripID;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;

import java.util.ArrayList;

/**
 * @author wilkg
 * @version 1.0
 * @created 15-Apr-2020 1:20:18 PM
 */
public class Stop extends GTFSElement {

	private Feed feed;
	private Point2D location;
	public Feed m_Feed;

	public Stop(){

	}

	/**
	 * 
	 * @param id
	 * @param feed
	 */
	public Stop Stop(String id, Feed feed){
		return null;
	}

	/**
	 * 
	 * @param id
	 * @param feed
	 * @param name
	 */
	public Stop Stop(String id, Feed feed, String name){
		return null;
	}

	/**
	 * 
	 * @param id
	 * @param feed
	 * @param name
	 * @param color
	 */
	public Stop Stop(String id, Feed feed, String name, Color color){
		return null;
	}

	public StopTime getActiveStopTime(){
		return null;
	}

	public ArrayList<RouteID> getContainingRouteIDs(){
		return null;
	}

	public ArrayList<Route> getContainingRoutes(){
		return null;
	}

	public ArrayList<StopTimeID> getContainingStopTimeIDs(){
		return null;
	}

	public ArrayList<StopTime> getContainingStopTimes(){
		return null;
	}

	public ArrayList<TripID> getContainingTripIDs(){
		return null;
	}

	public ArrayList<Trip> getContainingTrips(){
		return null;
	}

	public Feed getFeed(){
		return null;
	}

	public Point2D getLocation(){
		return null;
	}

	public StopTime getNextStopTime(){
		return null;
	}

	public Trip getNextTrip(){
		return null;
	}

	public StopTime getPreviousStopTime(){
		return null;
	}

	public Trip getPreviousTrip(){
		return null;
	}

	public boolean isActive(){
		return false;
	}

	/**
	 * 
	 * @param location
	 */
	public void setLocation(Point2D location){

	}

}