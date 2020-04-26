package gtfsapp.gui.dialog.edit.route;

import gtfsapp.gui.dialog.edit.GTFSEditDialogController;

public class GTFSEditRouteDialogController extends GTFSEditDialogController {

    /**
     * Applies the new attributes in the edit dialog to the edit dialog's element
     */
    @Override
    public void apply() {

        // update the info panel
        mainController.updateInfoPanel();

        // close the dialog
        close();

    }

    /**
     * Applies the new attributes in the edit dialog to multiple elements
     */
    @Override
    public void applyMultiple() {
        throw new UnsupportedOperationException();
    }

}
