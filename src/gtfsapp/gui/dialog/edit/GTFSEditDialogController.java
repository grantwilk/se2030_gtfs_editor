package gtfsapp.gui.dialog.edit;

import gtfsapp.file.*;
import gtfsapp.gui.GTFSController;
import gtfsapp.gui.dialog.GTFSDialogController;
import gtfsapp.gui.dialog.error.GTFSErrorType;
import gtfsapp.gui.dialog.select.GTFSSelectDialogController;
import gtfsapp.gui.main.GTFSMainController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
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
     * Gets the edit dialog's element
     * @return the edit dialog's element
     */
    public GTFSElement getElement() {
        return element;
    }

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
     * Applies the new attributes in the edit dialog to one element
     */
    public abstract void applyOne(GTFSElement element);

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
     * Shows the select multiple window
     */
    public void showSelectMultiple() {
        // get the parent as a main controller
        GTFSMainController mainController = (GTFSMainController) parentController;

        try {

            // load the select dialog FXML
            FXMLLoader loader = new FXMLLoader(
                    GTFSController.class.getResource("dialog/select/fxml/select-dialog.fxml")
            );

            // get the root
            Parent root = loader.load();

            // get controller
            GTFSSelectDialogController selectController = loader.getController();

            // create a new stage and scene
            Stage selectStage = new Stage();
            Scene selectScene = new Scene(root);

            // set selection dialog attributes
            selectController.setParentController(this);
            selectController.setScene(selectScene);
            selectController.setStage(selectStage);

            // add all of the elements to our selection list
            selectController.setBaseElement(element);

            List<GTFSElement> elements;

            if (element instanceof Route) {
                elements = new ArrayList<>(mainController.getGTFSFile().getFeed().getRoutes());
            } else if (element instanceof Trip) {
                elements = new ArrayList<>(mainController.getGTFSFile().getFeed().getTrips());
            } else if (element instanceof StopTime) {
                elements = new ArrayList<>(mainController.getGTFSFile().getFeed().getStopTimes());
            } else if (element instanceof Stop) {
                elements = new ArrayList<>(mainController.getGTFSFile().getFeed().getStops());
            } else {
                throw new IllegalArgumentException("Unknown element type.");
            }

            selectController.addElements(elements);

            // set stage attributes
            selectStage.setScene(selectScene);
            selectStage.setTitle("Select Multiple");
            selectStage.setResizable(false);
            selectStage.initModality(Modality.APPLICATION_MODAL);
            selectStage.initOwner(getScene().getWindow());

            // show stage
            selectStage.showAndWait();


        }

        // catch IO exceptions
        catch (IOException e) {
            mainController.invokeErrorDialog(
                    GTFSErrorType.EXCEPTION,
                    "Could Not Load GUI",
                    "An error occurred while loading the GUI."
            );
        }

        // catch illegal argument exceptions
        catch (IllegalArgumentException e) {
            mainController.invokeErrorDialog(
                    GTFSErrorType.EXCEPTION,
                    "Illegal Argument",
                    e.getMessage()
            );
        }
    }

    /**
     * Initializes the values of the fields in the edit dialog
     */
    public abstract void initializeEditFields();

    /**
     * Gets all elements that are similar to the edit dialog's GTFS element
     */
    public abstract List<GTFSElement> getSimilar();


}