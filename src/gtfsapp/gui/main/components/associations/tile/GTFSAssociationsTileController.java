package gtfsapp.gui.main.components.associations.tile;

import gtfsapp.file.GTFSElement;
import gtfsapp.gui.main.GTFSMainController;

/**
 * @author Grant Wilk
 * @version 1.0
 * @created 19-Apr-2020 12:00:00 AM
 */
public class GTFSAssociationsTileController {

    GTFSMainController mainController;
    GTFSElement element;

    /**
     * Sets the tile's element as the selected element
     */
    public void setAsSelectedElement() {
        mainController.setSelectedElement(element);
    }

    /**
     * Sets the main controller for the associations tile
     * @param mainController - the main controller
     */
    public void setMainController(GTFSMainController mainController) {
        this.mainController = mainController;
    }

    /**
     * Gets the associations tile's main controller
     * @return the associations tile's main controller
     */
    public GTFSMainController getMainController() {
        return mainController;
    }

    /**
     * Sets the tile's GTFS element
     * @param element - the element
     */
    public void setElement(GTFSElement element) {
        this.element = element;
    }

    /**
     * Gets the tile's GTFS element
     * @return the tile's GTFS element
     */
    public GTFSElement getElement() {
        return element;
    }
}
