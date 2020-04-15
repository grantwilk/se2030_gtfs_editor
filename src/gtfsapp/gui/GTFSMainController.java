package gtfsapp.gui;

import gtfsapp.file.*;

import java.util.ArrayList;

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

    /**
     *
     * @return
     */
    public ArrayList<Route> getAssociatedRoutes() {
        return null;
    }

    /**
     *
     * @return
     */
    public ArrayList<Stop> getAssociatedStops() {
        return null;
    }

    /**
     *
     * @return
     */
    public ArrayList<StopTime> getAssociatedStopTimes() {
        return null;
    }

    /**
     *
     * @return
     */
    public ArrayList<Trip> getAssociatedTrips() {
        return null;
    }

    /**
     *
     * @return
     */
    public GTFSFile getGTFSFile() {
        return null;
    }

    /**
     *
     * @return
     */
    public GTFSMapController getMapController() {
        return null;
    }

    /**
     *
     * @return
     */
    public GTFSElement getSelectedElement() {
        return null;
    }

    /**
     *
     */
    public void invokeEditDialog() {

    }

    /**
     * @param msg
     */
    public void invokeErrorDialog(String msg) {

    }

    /**
     *
     */
    public void loadFile() {

    }

    /**
     *
     */
    public void saveAsFile() {

    }

    /**
     *
     */
    public void saveFile() {

    }

    /**
     *
     */
    public void searchForElement() {

    }

    /**
     * @param selectedElement
     */
    public void setSelectedElement(GTFSElement selectedElement) {

    }

    /**
     *
     */
    public void updateAssociationsPanel() {

    }

    /**
     *
     */
    public void updateInfoPanel() {

    }

    /**
     *
     */
    public void updateSelectedElementPanel() {

    }

}