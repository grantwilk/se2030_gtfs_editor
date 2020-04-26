package gtfsapp.gui.dialog.edit.stoptime;

import gtfsapp.gui.dialog.edit.GTFSEditDialogController;

public class GTFSEditStopTimeDialogController extends GTFSEditDialogController {

    /**
     * Updates the attributes associated with the edit dialog's elements
     */
    @Override
    public void updateAttributes() {

        // update the info panel
        mainController.updateInfoPanel();

        // close the dialog
        close();

    }

}
