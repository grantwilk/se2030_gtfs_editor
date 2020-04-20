package gtfsapp.gui.dialog;

import gtfsapp.gui.GTFSController;
import gtfsapp.gui.main.GTFSMainController;

/**
 * @author Grant Wilk
 * @version 1.0
 * @created 15-Apr-2020 1:20:18 PM
 */
public abstract class GTFSDialogController extends GTFSController {

    /**
     * The main controller that the dialog was invoked from
     */
    public GTFSMainController mainController;

    /**
     * Gets the dialog's main controller
     * @return the dialog's main controller
     */
    public GTFSMainController getMainController() {
        return mainController;
    }

    /**
     * Sets the dialog's main controller
     * @param mainController - the dialog's main controller
     */
    public void setMainController(GTFSMainController mainController) {
        this.mainController = mainController;
    }

}