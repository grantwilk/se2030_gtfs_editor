package gtfsapp.gui.dialog.select;

import gtfsapp.file.GTFSElement;
import gtfsapp.gui.dialog.GTFSDialogController;
import gtfsapp.gui.dialog.edit.GTFSEditDialogController;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Grant Wilk
 * @created 04/27/2020
 */
public class GTFSSelectDialogController extends GTFSDialogController {

    /**
     * The base element that is always selected
     */
    GTFSElement baseElement;

    /**
     * The list view of selected elements (right)
     */
    @FXML
    private ListView<GTFSElement> selectedElements;

    /**
     * The list view of unselected elements (left)
     */
    @FXML
    private ListView<GTFSElement> unselectedElements;

    /**
     * Sets the base element of the selection dialog
     * @param element - the element to set as the base
     */
    public void setBaseElement(GTFSElement element) {
        baseElement = element;
        selectedElements.getItems().add(baseElement);
    }

    /**
     * Adds a list of elements to the unselected elements GUI list
     * @param elements - the list of elements to add
     */
    public void addElements(List<GTFSElement> elements) {

        // sort the elements
        elements = elements.stream().sorted().collect(Collectors.toList());

        // add the elements to the unselected elements list if they are not our base element
        for (GTFSElement element : elements) {
            if (!unselectedElements.getItems().contains(element) &&
                    !selectedElements.getItems().contains(element) &&
                    !element.equals(baseElement)
            ) {
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

        // if there is a selected item
        if (selectedItem != null) {

            // add the items to the selected elements list
            selectedElements.getItems().add(selectedItem);

            // remove the items from the unselected elements list
            unselectedElements.getItems().remove(selectedItem);

            // sort the selected elements list
            sortListView(selectedElements);

        }

    }

    /**
     * Moves all of the items from the unselected elements GUI list to the selected elements GUI list
     */
    @FXML
    private void selectAll() {

        // move the items from one list to the other
        List<GTFSElement> items = unselectedElements.getItems();
        selectedElements.getItems().addAll(items);
        unselectedElements.getItems().removeAll(items);

        // sort the selected elements list
        sortListView(selectedElements);

    }

    /**
     * Selects all elements similar to the base element
     */
    @FXML
    private void selectSimilar() {

        // deselect everything that is currently selected
        deselectAll();

        // get the similar elements
        List<GTFSElement> similarElements = ((GTFSEditDialogController) parentController).getSimilar();

        // remove the similar elements from the unselected elements list
        unselectedElements.getItems().removeAll(similarElements);

        // add the elements to our selected elements if it is not already included
        for (GTFSElement element : similarElements) {
            if (!selectedElements.getItems().contains(element)) {
                selectedElements.getItems().add(element);
            }
        }

        // sort the selected elements list
        sortListView(selectedElements);

    }

    /**
     * Moves the currently selected items from the selected elements GUI list to the unselected elements GUI list
     */
    @FXML
    private void deselectOne() {

        // get the currently selected items from the list of unselected elements
        GTFSElement selectedItem = selectedElements.getSelectionModel().getSelectedItem();

        // if the selected element is not our base element and is not null
        if (selectedItem != null && !selectedItem.equals(baseElement)) {

            // add the items to the unselected elements list
            unselectedElements.getItems().add(selectedItem);

            // remove the items from the selected elements list
            selectedElements.getItems().remove(selectedItem);

            // sort the unselected elements list
            sortListView(unselectedElements);

        }

    }

    /**
     * Moves all of the items from the selected elements GUI list to the unselected elements GUI list
     */
    @FXML
    private void deselectAll() {
        // get all of the elements from our selected elements GUI list
        List<GTFSElement> items = selectedElements.getItems();

        // move the items to the unselected elements list
        unselectedElements.getItems().addAll(items);
        selectedElements.getItems().removeAll(items);

        // move the base element back over to the selected side
        selectedElements.getItems().add(baseElement);
        unselectedElements.getItems().remove(baseElement);

        // sort the unselected elements list
        sortListView(unselectedElements);

    }

    /**
     * Applies the attributes to the selected elements
     */
    @FXML
    private void applyMultiple() {
        ((GTFSEditDialogController) parentController).applyMultiple(selectedElements.getItems());
        close();
    }

    /**
     * Sorts the items in a list view
     * @param listView - the list view to sort
     */
    private void sortListView(ListView<GTFSElement> listView) {

        // sort the items
        List<GTFSElement> sortedItems = listView.getItems().stream().sorted().collect(Collectors.toList());

        // clear the list and add the new sorted list
        listView.getItems().clear();
        listView.getItems().addAll(sortedItems);

    }

}
