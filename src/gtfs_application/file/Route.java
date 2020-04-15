package gtfs_application.file;

import gtfs_application.id.StopID;
import gtfs_application.id.StopTimeID;
import gtfs_application.id.TripID;

/**
 * @author wilkg
 * @version 1.0
 * @created 15-Apr-2020 1:20:18 PM
 */
public class Route extends GTFSElement {

	private Feed feed;
	private HashMap<TripID, Trip> trips;
	public Trip m_Trip;
	public Feed m_Feed;

	public Route(){

	}

	public void finalize() throws Throwable {
		super.finalize();
	}

	/**
	 * 
	 * @param id
	 * @param feed
	 * @param name
	 * @param color
	 */
	public Route Route(String id, Feed feed, String name, Color color){
		return null;
	}

	/**
	 * 
	 * @param id
	 * @param feed
	 */
	public Route Route(String id, Feed feed){
		return null;
	}

	/**
	 * 
	 * @param id
	 * @param feed
	 * @param name
	 */
	public Route Route(String id, Feed feed, String name){
		return null;
	}

	/**
	 * 
	 * @param trips
	 */
	public void addAllTrips(ArrayList<Trip> trips){

	}

	/**
	 * 
	 * @param trip
	 */
	public void addTrip(Trip trip){

	}

	public void clearTrips(){

	}

	/**
	 * 
	 * @param id
	 */
	public boolean containsStop(StopID id){
		return false;
	}

	/**
	 * 
	 * @param id
	 */
	public boolean containsStopTime(StopTimeID id){
		return false;
	}

	/**
	 * 
	 * @param id
	 */
	public boolean containsTrip(TripID id){
		return false;
	}

	public Stop getActiveStop(){
		return null;
	}

	public StopTime getActiveStopTime(){
		return null;
	}

	public Trip getActiveTrip(){
		return null;
	}

	public double getAvgSpeed(){
		return 0;
	}

	public Coordinate getBusPosition(){
		return null;
	}

	public double getDistance(){
		return 0;
	}

	public double getDuration(){
		return 0;
	}

	public Feed getFeed(){
		return null;
	}

	public Stop getNextStop(){
		return null;
	}

	public StopTime getNextStopTime(){
		return null;
	}

	public Trip getNextTrip(){
		return null;
	}

	public Stop getPreviousStop(){
		return null;
	}

	public StopTime getPreviousStopTime(){
		return null;
	}

	public Trip getPreviousTrip(){
		return null;
	}

	/**
	 * 
	 * @param id
	 */
	public Stop getStopByID(StopID id){
		return null;
	}

	public ArrayList<StopID> getStopIDs(){
		return null;
	}

	public ArrayList<Stop> getStops(){
		return null;
	}

	/**
	 * 
	 * @param id
	 */
	public StopTime getStopTimeByID(StopTimeID id){
		return null;
	}

	public ArrayList<StopTimeID> getStopTimeIDs(){
		return null;
	}

	public ArrayList<StopTime> getStopTimes(){
		return null;
	}

	/**
	 * 
	 * @param id
	 */
	public Trip getTripByID(TripID id){
		return null;
	}

	public ArrayList<TripID> getTripIDs(){
		return null;
	}

	public ArrayList<Trip> getTrips(){
		return null;
	}

	public boolean isActive(){
		return false;
	}

	/**
	 * 
	 * @param trip
	 */
	public Trip removeTrip(Trip trip){
		return null;
	}

	/**
	 * 
	 * @param id
	 */
	public Trip removeTripByID(TripID id){
		return null;
	}

}