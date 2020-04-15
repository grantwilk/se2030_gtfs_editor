

/**
 * @author wilkg
 * @version 1.0
 * @created 15-Apr-2020 1:20:18 PM
 */
public class Trip extends GTFSElement {

	private Feed feed;
	private HashMap<StopID, Stop> stops;
	private HashMap<StopTimeID, StopTime> stopTimes;
	public StopTime m_StopTime;
	public Stop m_Stop;
	public Feed m_Feed;

	public Trip(){

	}

	public void finalize() throws Throwable {
		super.finalize();
	}

	/**
	 * 
	 * @param id
	 * @param feed
	 */
	public Trip Trip(String id, Feed feed){
		return null;
	}

	/**
	 * 
	 * @param id
	 * @param feed
	 * @param name
	 */
	public Trip Trip(String id, Feed feed, String name){
		return null;
	}

	/**
	 * 
	 * @param id
	 * @param feed
	 * @param name
	 * @param color
	 */
	public Trip Trip(String id, Feed feed, String name, Color color){
		return null;
	}

	/**
	 * 
	 * @param stopTimes
	 */
	public void addAllStopTimes(ArrayList<StopTime> stopTimes){

	}

	/**
	 * 
	 * @param stopTime
	 */
	public void addStopTime(StopTime stopTime){

	}

	public void clearStopTimes(){

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

	public Stop getActiveStop(){
		return null;
	}

	public StopTime getActiveStopTime(){
		return null;
	}

	public double getAvgSpeed(){
		return 0;
	}

	public Coordinate getBusPosition(){
		return null;
	}

	public ArrayList<RouteID> getContainingRouteIDs(){
		return null;
	}

	public ArrayList<Route> getContainingRoutes(){
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

	public Stop getPreviousStop(){
		return null;
	}

	public StopTime getPreviousStopTime(){
		return null;
	}

	/**
	 * 
	 * @param stop
	 */
	public Stop getStopByID(StopID stop){
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

	public boolean isActive(){
		return false;
	}

	/**
	 * 
	 * @param stopTime
	 */
	public StopTime removeStopTime(StopTime stopTime){
		return null;
	}

	/**
	 * 
	 * @param id
	 */
	public StopTime removeStopTimeByID(StopTimeID id){
		return null;
	}

}