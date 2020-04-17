package gtfsapp.gui;

import gtfsapp.file.GTFSElement;
import gtfsapp.file.Stop;

import java.util.ArrayList;

/**
 * @author grant
 * @version 1.0
 * @created 15-Apr-2020 1:20:18 PM
 */
public class GTFSMapController extends GTFSController {

    private ArrayList<Stop> associatedStops;
    private GTFSMainController mainController;
    private GTFSElement selectedElement;

    /**
     * Get's the map's main controller and returns it
     * @return the map's main controller
     */
    public GTFSMainController getMainController() {
        return mainController;
    }

    // TODO - do we need a setMainController()?

    /**
     * Gets the map controller's selected element and returns it
     * @return the map controller's selected element
     */
    public GTFSElement getSelectedElement() {
        return selectedElement;
    }

    /**
     *
     */
    public void invokeEditDialog() {
        throw new UnsupportedOperationException();
    }

    /**
     *
     * @param msg
     */
    public void invokeErrorDialog(String msg) {
        throw new UnsupportedOperationException();
    }

    /**
     * Sets the map controller's selected element
     * @param selectedElement the element
     */
    public void setSelectedElement(GTFSElement selectedElement) {
        this.selectedElement = selectedElement;
    }

    /**
     * Updates the map's GUI
     */
    public void updateMap() {
        throw new UnsupportedOperationException();
    }

}