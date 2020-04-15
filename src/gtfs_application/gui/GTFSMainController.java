package gtfs_application.gui;

/**
 * @author grant
 * @version 1.0
 * @created 15-Apr-2020 1:20:18 PM
 */
public class GTFSMainController extends GTFSController {

	private ArrayList<Route> associatedRoutes;
	private ArrayList<Stop> associatedStops;
	private ArrayList<StopTime> associatedStopTimes;
	private ArrayList<Trip> associatedTrips;
	private GTFSFile file;
	private GTFSMapController mapController;
	private GTFSElement selectedElement;
	public GTFSMapController m_GTFSMapController;
	public GTFSElement m_GTFSElement;
	public GTFSFile m_GTFSFile;

	public GTFSMainController(){

	}

	public void finalize() throws Throwable {
		super.finalize();
	}

	public ArrayList<Route> getAssociatedRoutes(){
		return null;
	}

	public ArrayList<Stop> getAssociatedStops(){
		return null;
	}

	public ArrayList<StopTime> getAssociatedStopTimes(){
		return null;
	}

	public ArrayList<Trip> getAssociatedTrips(){
		return null;
	}

	public GTFSFile getGTFSFile(){
		return null;
	}

	public GTFSMapController getMapController(){
		return null;
	}

	public GTFSElement getSelectedElement(){
		return null;
	}

	public void invokeEditDialog(){

	}

	/**
	 * 
	 * @param msg
	 */
	public void invokeErrorDialog(String msg){

	}

	public void loadFile(){

	}

	public void saveAsFile(){

	}

	public void saveFile(){

	}

	public void searchForElement(){

	}

	/**
	 * 
	 * @param selectedElement
	 */
	public void setSelectedElement(GTFSElement selectedElement){

	}

	public void updateAssociationsPanel(){

	}

	public void updateInfoPanel(){

	}

	public void updateSelectedElementPanel(){

	}

}