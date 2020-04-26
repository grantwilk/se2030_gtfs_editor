package gtfsapp.gui.dialog.edit.route;

import gtfsapp.gui.dialog.edit.GTFSEditDialogController;

public class GTFSEditRouteDialogController extends GTFSEditDialogController {

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
