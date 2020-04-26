package gtfsapp.gui.dialog.edit;

import gtfsapp.file.GTFSElement;
import gtfsapp.gui.dialog.GTFSDialogController;

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
     * Applies the new attributes in the edit dialog to the edit dialog's element
     */
    public abstract void apply();

    /**
     * Applies the new attributes in the edit dialog to multiple elements
     */
    public abstract void applyMultiple();

}