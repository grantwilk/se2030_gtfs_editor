package gtfsapp.gui.dialog;

import gtfsapp.gui.GTFSController;

/**
 * @author Grant Wilk
 * @version 1.0
 * @created 15-Apr-2020 1:20:18 PM
 */
public abstract class GTFSDialogController extends GTFSController {

    /**
     * The parent controller that the dialog was invoked from
     */
    public GTFSController parentController;

    /**
     * Gets the dialog's parent controller
     *
     * @return the dialog's parent controller
     */
    public GTFSController getParentController() {
        return parentController;
    }

    /**
     * Sets the dialog's parent controller
     *
     * @param parentController the dialog's parent controller
     */
    public void setParentController(GTFSController parentController) {
        this.parentController = parentController;
    }

}