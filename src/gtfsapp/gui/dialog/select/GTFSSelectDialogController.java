package gtfsapp.gui.dialog.select;

import gtfsapp.file.GTFSElement;
import gtfsapp.gui.dialog.GTFSDialogController;
import gtfsapp.gui.dialog.edit.GTFSEditDialogController;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

import java.util.List;

/**
 * @author Grant Wilk
 * @created 04/27/2020
 */
public class GTFSSelectDialogController extends GTFSDialogController {

    @FXML
    private ListView<GTFSElement> selectedElements;

    @FXML
    private ListView<GTFSElement> unselectedElements;

    /**
     * Adds a list of elements to the unselected elements GUI list
     * @param elements - the list of elements to add
     */
    public void addElements(List<GTFSElement> elements) {
        for (GTFSElement element : elements) {
            if (!(unselectedElements.getItems().contains(element) || selectedElements.getItems().contains(element))) {
                unselectedElements.getItems().add(element);
            }
        }
    }

    /**
     * Moves the currently selected items from the unselected elements GUI list to the selected elements GUI list
     */
    @FXML
    private void selectSome() {

        // get the currently selected items from the list of unselected elements
        GTFSElement selectedItem = unselectedElements.getSelectionModel().getSelectedItem();

        // add the items to the selected elements list
        selectedElements.getItems().add(selectedItem);

        // remove the items from the unselected elements list
        unselectedElements.getItems().remove(selectedItem);

    }

    /**
     * Moves all of the items from the unselected elements GUI list to the selected elements GUI list
     */
    @FXML
    private void selectAll() {
        List<GTFSElement> items = unselectedElements.getItems();
        selectedElements.getItems().addAll(items);
        unselectedElements.getItems().removeAll(items);
    }

    /**
     * Moves the currently selected items from the selected elements GUI list to the unselected elements GUI list
     */
    @FXML
    private void deselectSome() {

        // get the currently selected items from the list of unselected elements
        GTFSElement selectedItem = selectedElements.getSelectionModel().getSelectedItem();

        // add the items to the unselected elements list
        unselectedElements.getItems().add(selectedItem);

        // remove the items from the selected elements list
        selectedElements.getItems().remove(selectedItem);

    }

    /**
     * Moves all of the items from the selected elements GUI list to the unselected elements GUI list
     */
    @FXML
    private void deselectAll() {
        List<GTFSElement> items = selectedElements.getItems();
        unselectedElements.getItems().addAll(items);
        selectedElements.getItems().removeAll(items);
    }

    @FXML
    private void applyMultiple() {
        ((GTFSEditDialogController) parentController).applyMultiple(selectedElements.getItems());
        close();
    }

}
