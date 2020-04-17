package gtfsapp.gui;

import javafx.stage.Stage;

/**
 * @author Grant Wilk
 * @version 1.0
 * @created 15-Apr-2020 1:20:18 PM
 */
public abstract class GTFSDialogController extends GTFSController {

    /**
     *
     */
    public void closeDialog() {
        // TODO
    }

    /**
     * Gets the title of the dialog controller's window and returns it
     * @return the title of the window
     */
    public String getWindowTitle() {
        Stage stage = super.getStage();
        return stage.getTitle();
    }

    /**
     * Sets the title of the dialog controller's window
     * @param windowTitle the title
     */
    public void setWindowTitle(String windowTitle) {
        Stage stage = super.getStage();
        stage.setTitle(windowTitle);
    }

}