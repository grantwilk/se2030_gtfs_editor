

/**
 * @author wilkg
 * @version 1.0
 * @created 15-Apr-2020 1:20:18 PM
 */
public class StopTime extends GTFSElement {

	private Date arrivalTime;
	private Date departureTime;
	private Feed feed;
	private Stop stop;
	private Trip trip;
	public Stop m_Stop;
	public Trip m_Trip;
	public Feed m_Feed;

	public StopTime(){

	}

	public void finalize() throws Throwable {
		super.finalize();
	}

	/**
	 * 
	 * @param id
	 * @param feed
	 * @param trip
	 * @param stop
	 * @param arrivalTime
	 * @param departureTime
	 */
	public Stop StopTime(String id, Feed feed, Trip trip, Stop stop, Date arrivalTime, Date departureTime){
		return null;
	}

	/**
	 * 
	 * @param id
	 * @param feed
	 * @param trip
	 * @param stop
	 * @param arrivalTime
	 * @param departureTime
	 * @param name
	 */
	public Stop StopTime(String id, Feed feed, Trip trip, Stop stop, Date arrivalTime, Date departureTime, String name){
		return null;
	}

	/**
	 * 
	 * @param id
	 * @param feed
	 * @param trip
	 * @param stop
	 * @param arrivalTime
	 * @param departureTime
	 * @param color
	 */
	public Stop StopTime(String id, Feed feed, Trip trip, Stop stop, Date arrivalTime, Date departureTime, Color color){
		return null;
	}

	/**
	 * 
	 * @param id
	 * @param feed
	 * @param trip
	 * @param stop
	 * @param arrivalTime
	 * @param departureTime
	 * @param name
	 * @param color
	 */
	public Stop StopTime(String id, Feed feed, Trip trip, Stop stop, Date arrivalTime, Date departureTime, String name, Color color){
		return null;
	}

	/**
	 * 
	 * @param stop
	 */
	public boolean containsStop(Stop stop){
		return false;
	}

	public Date getArrivalTime(){
		return null;
	}

	public ArrayList<RouteID> getContainingRouteIDs(){
		return null;
	}

	public ArrayList<Route> getContainingRoutes(){
		return null;
	}

	public Date getDepartureTime(){
		return null;
	}

	public Feed getFeed(){
		return null;
	}

	public Stop getStop(){
		return null;
	}

	public Trip getTrip(){
		return null;
	}

	public boolean isActive(){
		return false;
	}

	/**
	 * 
	 * @param arrivalTime
	 */
	public void setArrivalTime(Date arrivalTime){

	}

	/**
	 * 
	 * @param departureTime
	 */
	public void setDepartureTime(Date departureTime){

	}

	/**
	 * 
	 * @param stop
	 */
	public void setStop(Stop stop){

	}

}