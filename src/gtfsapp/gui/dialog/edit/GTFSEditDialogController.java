package gtfsapp.gui.dialog.edit;

import gtfsapp.file.GTFSElement;
import gtfsapp.gui.dialog.GTFSDialogController;
import gtfsapp.gui.main.GTFSMainController;

import java.util.List;

/**
 * @author Grant Wilk
 * @version 1.0
 * @created 15-Apr-2020 1:20:18 PM
 */
public abstract class GTFSEditDialogController extends GTFSDialogController {

    /**
     * The edit dialog's element
     */
    private GTFSElement element;

    /**
     * Sets the edit dialog's element
     * @param element - the edit dialog's element
     */
    public void setElement(GTFSElement element) {
        this.element = element;
        initializeEditFields();
    }

    /**
     * Applies the new attributes to the edit dialog's element
     */
    public void apply() {

        // apply the attributes to the current element
        applyOne(element);

        // update the info panel
        ((GTFSMainController) parentController).updateInfoPanel();

        // close the dialog
        close();

    }

    /**
     * Applies the new attributes in the edit dialog to multiple elements
     */
    public void applyMultiple(List<GTFSElement> elements) {

        // apply the attributes to all of the elements
        for (GTFSElement element : elements) {
            applyOne(element);
        }

        // update the info panel
        ((GTFSMainController) parentController).updateInfoPanel();

        // close the dialog
        close();

    }

    /**
     * Gets the edit dialog's element
     * @return the edit dialog's element
     */
    public GTFSElement getElement() {
        return element;
    }

    /**
     * Initializes the values of the fields in the edit dialog
     */
    public abstract void initializeEditFields();

    /**
     * Shows the select multiple window
     */
    public abstract void showSelectMultiple();

    /**
     * Applies the new attributes in the edit dialog to one element
     */
    public abstract void applyOne(GTFSElement element);



}