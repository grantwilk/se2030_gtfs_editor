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
     * Gets the selected element's associated routes and returns them
     * @return the selected element's associated routes
     */
    public ArrayList<Route> getAssociatedRoutes() {
        return associatedRoutes;
    }

    /**
     * Gets the selected element's associated trips and returns them
     * @return the selected element's associated trips
     */
    public ArrayList<Trip> getAssociatedTrips() {
        return associatedTrips;
    }

    /**
     * Gets the selected element's associated stop times and returns them
     * @return the selected element's associated stop times
     */
    public ArrayList<StopTime> getAssociatedStopTimes() {
        return associatedStopTimes;
    }

    /**
     * Gets the selected element's associated stops and returns them
     * @return the selected element's associated stops
     */
    public ArrayList<Stop> getAssociatedStops() {
        return associatedStops;
    }

    /**
     * Get's the controller's GTFS file and returns it
     * @return the controller's GTFS file
     */
    public GTFSFile getGTFSFile() {
        return file;
    }

    /**
     * Get's the controller's map controller and returns it
     * @return the controller's map controller
     */
    public GTFSMapController getMapController() {
        return mapController;
    }

    /**
     * Get's the controller's selected element and returns it
     * @return
     */
    public GTFSElement getSelectedElement() {
        return selectedElement;
    }

    /**
     * Invokes a blank error dialog
     */
    public void invokeEditDialog() {
        // TODO
    }

    /**
     * Invokes an error dialog with a message
     * @param msg the message to display in the error dialog
     */
    public void invokeErrorDialog(String msg) {
        // TODO
    }

    /**
     * Invokes the system's file chooser and loads a GTFS file from the computer's file system
     */
    public void loadFile() {
        // TODO
    }

    /**
     * Saves the currently loaded GTFS file to its original path on the computer's file system
     */
    public void saveAsFile() {
        // TODO
    }

    /**
     * Invokes the systems' file chooser and saves a GTFS file to the computer's file system
     */
    public void saveFile() {
        // TODO
    }

    /**
     * Searches for an element within the currently loaded GTFS feed that matches the string entered in the GUI's
     * search field
     */
    public void searchForElement() {
        // TODO
    }

    /**
     * Sets the GUI's selected element
     * @param selectedElement the element
     */
    public void setSelectedElement(GTFSElement selectedElement) {
        this.selectedElement = selectedElement;
        updateInfoPanel();
        // TODO - what else needs to be updated? associations?
    }

    /**
     * Updates the GUI's associations panel
     */
    public void updateAssociationsPanel() {
        // TODO
    }

    /**
     * Updates the GUI's info panel
     */
    public void updateInfoPanel() {
        updateAssociationsPanel();
        updateSelectedElementPanel();
        // TODO - does anything else need to be updated? search panel?
    }

    /**
     * Updates the GUI's selected element panel
     */
    public void updateSelectedElementPanel() {
        // TODO
    }

}