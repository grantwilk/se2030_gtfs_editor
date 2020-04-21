package gtfsapp.gui.main;

import gtfsapp.file.*;
import gtfsapp.gui.GTFSController;
import gtfsapp.gui.dialog.error.GTFSErrorDialogController;
import gtfsapp.gui.dialog.error.GTFSErrorType;
import gtfsapp.gui.main.components.associations.tile.GTFSAssociationsTileController;
import gtfsapp.gui.main.components.selectedelement.attribute.GTFSSelectedElementAttributeController;
import gtfsapp.gui.map.GTFSMapController;
import gtfsapp.id.GTFSID;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Grant Wilk
 * @version 1.0
 * @created 15-Apr-2020 1:20:18 PM
 */
public class GTFSMainController extends GTFSController {

    /**
     * The color displayed in the selected element panel if there is no selected element
     */
    public static final String NULL_SELECTED_ELEMENT_COLOR = "#C0C0C0";

    /**
     * The title displayed in the selected element panel if there is no selected element
     */
    private static final String NULL_SELECTED_ELEMENT_TITLE = "NULL";

    /**
     * The subtitle displayed in the selected element panel if there is no selected element
     */
    private static final String NULL_SELECTED_ELEMENT_SUBTITLE = "Nothing to see here!";

    /**
     * List of all routes associated with the selected element
     */
    private List<Route> associatedRoutes = new ArrayList<>();

    /**
     * List of all trips associated with the selected element
     */
    private List<Trip> associatedTrips = new ArrayList<>();

    /**
     * List of all stops associated with the selected element
     */
    private List<Stop> associatedStops = new ArrayList<>();

    /**
     * List of all stop times associated with the selected element
     */
    private List<StopTime> associatedStopTimes = new ArrayList<>();

    /**
     * The controller's GTFS file
     */
    private GTFSFile gtfsFile;

    /**
     * The controller's child map controller
     */
    private GTFSMapController mapController;

    /**
     * The controller's currently selected element
     */
    private GTFSElement selectedElement = null;

    /**
     * The main panel/root
     */
    @FXML
    private HBox mainPanel;

    /**
     * The root of the info panel
     */
    @FXML
    private VBox infoPanel;

    /**
     * The root of the file header on the info panel
     */
    @FXML
    private HBox fileHeaderPanel;

    /**
     * The root of the search panel on the info panel
     */
    @FXML
    private VBox searchPanel;

    /**
     * The search field/box in the search panel
     */
    @FXML
    private TextField searchField;

    /**
     * The root of the selected element panel on the info panel
     */
    @FXML
    private VBox selectedElementPanel;

    /**
     * The pane that displays the selected element's color
     */
    @FXML
    private Pane selectedElementColor;

    /**
     * The label that displays the title of the selected element
     */
    @FXML
    private Label selectedElementTitle;

    /**
     * The label that displays the subtitle of the selected element
     */
    @FXML
    private Label selectedElementSubtitle;

    /**
     * The separator between the selected element's title and the attributes container
     */
    @FXML
    private Line selectedElementAttributesSeparator;

    /**
     * The container that holds the dynamically generated selected element attributes
     */
    @FXML
    private VBox selectedElementAttributesContainer;

    /**
     * The root of the associations panel on the info panel
     */
    @FXML
    private Pane associationsPanel;

    /**
     * The tab pane that holds the association type tabs
     */
    @FXML
    private TabPane associationsTabPane;

    /**
     * The container that holds the dynamically generated associated routes
     */
    @FXML
    private VBox associatedRoutesContainer;

    /**
     * The container that holds the dynamically generated associated trips
     */
    @FXML
    private VBox associatedTripsContainer;

    /**
     * The container that holds the dynamically generated associated stop times
     */
    @FXML
    private VBox associatedStopTimesContainer;

    /**
     * The container that holds the dynamically generated associated stops
     */
    @FXML
    private VBox associatedStopsContainer;

    /**
     * FXML initialization function
     */
    @FXML
    public void initialize() {
        updateInfoPanel();
    }

    /**
     * Invokes the system's file chooser and loads a GTFS file from the computer's file system
     */
    public void loadFile() {

        // invoke a file chooser
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open GTFS File Set");

        // set the initial directory as the project directory
        // TODO - remove this before going live, this is just for convenience
        fileChooser.setInitialDirectory(new File(Paths.get(".").toAbsolutePath().normalize().toString()));

        // configure extension filter
        FileChooser.ExtensionFilter txtFilter =
                new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(txtFilter);

        // get file from the chooser
        // TODO - implement opening a single .zip file of all GTFS files instead of selecting multiple
        List<File> files = fileChooser.showOpenMultipleDialog(new Stage());

        // if at least one file was selected
        if (!(files == null || files.isEmpty())) {
            try {

                // deselect the selected element
                deselectElement();

                // clear all existing IDs
                GTFSID.clear();

                // create the GTFS file from the files
                gtfsFile = new GTFSFile(files);

                // attempt to parse the files
                gtfsFile.load();

                // update GUI's associated elements
                updateAssociations();

                // update info panel GUI
                updateInfoPanel();

                // update the map GUI
                // mapController.updateMap();

            } catch (IOException e) {
                invokeErrorDialog(GTFSErrorType.EXCEPTION, "IO Exception", e.getMessage());
            }
        }

    }

    /**
     * Invokes the systems' file chooser and saves a GTFS file to the computer's file system
     */
    public void saveFile() {
        // TODO - needs implementation eventually
        throw new UnsupportedOperationException();
    }

    /**
     * Invokes an edit dialog for the selected element
     */
    public void invokeEditDialog() {
        // TODO - needs implementation eventually
        throw new UnsupportedOperationException();
    }

    /**
     * Invokes an error dialog with a type, title, and message
     *
     * @param errorType    the type of error
     * @param errorTitle   the error title
     * @param errorMessage the error message
     */
    public void invokeErrorDialog(GTFSErrorType errorType, String errorTitle, String errorMessage) {

        try {
            // load error dialog FXML
            FXMLLoader loader = new FXMLLoader(
                    GTFSController.class.getResource("dialog/error/fxml/error-dialog.fxml")
            );

            // get the tile root (highest-level container)
            Parent root = loader.load();

            // get controller
            GTFSErrorDialogController errorDialogController = loader.getController();

            // create a new stage and scene
            Stage errorStage = new Stage();
            Scene errorScene = new Scene(root);

            // set error attributes
            errorDialogController.setErrorType(errorType);
            errorDialogController.setErrorTitle(errorTitle);
            errorDialogController.setErrorMessage(errorMessage);
            errorDialogController.setStage(errorStage);
            errorDialogController.setScene(errorScene);

            // set stage attributes
            errorStage.setScene(errorScene);
            errorStage.setTitle(errorTitle);

            // show the stage
            errorStage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
            // TODO - can anything else be done here?
        }

    }

    /**
     * Deselects the currently selected element
     */
    public void deselectElement() {
        setSelectedElement(null);
    }

    /**
     * Searches for an element within the currently loaded GTFS feed that matches the string entered in the GUI's
     * search field
     */
    public void searchForElement() {
        // TODO - needs implementation eventually
        throw new UnsupportedOperationException();
    }

    /**
     * Updates the GUI's info panel and all of its subcomponents
     */
    public void updateInfoPanel() {

        try {
            updateSearchPanel();
            updateAssociationsPanel();
            updateSelectedElementPanel();
        } catch (IOException e) {
            invokeErrorDialog(GTFSErrorType.EXCEPTION, "IO Exception", e.getMessage());
        }

    }

    /**
     * Updates the GUI's search panel
     */
    public void updateSearchPanel() {
        // clear the search field
        searchField.setText("");
    }

    /**
     * Updates the GUI's selected element panel
     */
    public void updateSelectedElementPanel() throws IOException {

        // if there is no selected element
        if (selectedElement == null) {

            // set the title, subtitle, and color to the null color
            selectedElementTitle.setText(NULL_SELECTED_ELEMENT_TITLE);
            selectedElementSubtitle.setText(NULL_SELECTED_ELEMENT_SUBTITLE);
            selectedElementColor.setStyle("-fx-background-color: " + NULL_SELECTED_ELEMENT_COLOR);

            // hide the separator and the attributes container
            selectedElementAttributesSeparator.setVisible(false);
            selectedElementAttributesContainer.setVisible(false);
            selectedElementAttributesContainer.setManaged(false);

        }

        // if there is a selected element
        else {

            // set the title, subtitle, and color to the selected element's values
            // TODO - get element color and display that instead of red
            selectedElementTitle.setText(selectedElement.getTitle().toUpperCase());
            selectedElementSubtitle.setText(selectedElement.getSubtitle());
            selectedElementColor.setStyle("-fx-background-color: red");

            // show the separator and the attributes container
            selectedElementAttributesSeparator.setVisible(true);
            selectedElementAttributesContainer.setVisible(true);
            selectedElementAttributesContainer.setManaged(true);

        }

        // update the selected element's attributes
        updateSelectedElementAttributes();

    }

    /**
     * Updates the selected element panel's attributes component for the GUI
     */
    public void updateSelectedElementAttributes() throws IOException {

        // clear all of the container's children
        selectedElementAttributesContainer.getChildren().clear();

        // if there is a selected element, add all of its attributes to the list of attributes
        if (selectedElement != null) {

            // get the selected element's attributes
            HashMap<String, String> attributes = selectedElement.getAttributes();

            // iterate through all attribute entries
            for (Map.Entry<String, String> entry : attributes.entrySet()) {

                // load selected element attribute's FXML
                FXMLLoader loader = new FXMLLoader(
                        getClass().getResource(
                                "components/selectedelement/attribute/fxml/selected-element-attribute.fxml"
                        )
                );

                // get the tile root (highest-level container)
                Parent root = loader.load();

                // get controller
                GTFSSelectedElementAttributeController attributeController = loader.getController();

                // get attribute title and subtitle from entry
                String title = entry.getKey();
                String subtitle = entry.getValue();

                // configure controller attributes
                attributeController.setName(title);
                attributeController.getValue(subtitle);

                // add the attribute to the GUI
                selectedElementAttributesContainer.getChildren().addAll(root);

            }

        }

    }

    /**
     * Updates the controller's associated elements lists
     */
    public void updateAssociations() {

        // if there is no selected object, display all routes, trips, times, and stops
        if (selectedElement == null) {

            // if there is no file loaded, reset all of the associations arrays
            if (gtfsFile == null) {
                associatedRoutes = new ArrayList<>();
                associatedTrips = new ArrayList<>();
                associatedStopTimes = new ArrayList<>();
                associatedStops = new ArrayList<>();
            }

            // if there is a feed loaded, get all of the associations arrays from it
            else {
                Feed feed = gtfsFile.getFeed();
                associatedRoutes = feed.getRoutes();
                associatedTrips = feed.getTrips();
                associatedStopTimes = feed.getStopTimes();
                associatedStops = feed.getStops();
            }

        }

        // otherwise, find associations and update the class variables
        else {
             associatedRoutes = findAssociatedRoutes();
             associatedTrips = findAssociatedTrips();
             associatedStopTimes = findAssociatedStopTimes();
             associatedStops = findAssociatedStops();
        }

    }

    /**
     * Updates the GUI's associations panel
     */
    public void updateAssociationsPanel() throws IOException {
        // update each tab of the associations panel
        updateAssociationsTab(associatedRoutes, associatedRoutesContainer);
        updateAssociationsTab(associatedTrips, associatedTripsContainer);
        updateAssociationsTab(associatedStopTimes, associatedStopTimesContainer);
        updateAssociationsTab(associatedStops, associatedStopsContainer);
    }

    /**
     * Converts all elements into an associations tile and places them in an associations container
     *
     * @param elements  the elements to add
     * @param container the container to place the tiles in
     * @throws IOException if the GUI fails to load an FXML file
     */
    public void updateAssociationsTab(List<? extends GTFSElement> elements, Pane container) throws IOException {

        // clear all of the children from the tab
        container.getChildren().clear();

        // convert each element to a tile and add it to the tab
        for (GTFSElement element : elements) {

            // load associations tile FXML
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("components/associations/tile/fxml/associations-tile.fxml")
            );

            // get the tile root (highest-level container)
            Parent root = loader.load();

            // get controller
            GTFSAssociationsTileController tileController = loader.getController();

            // configure controller attributes
            tileController.setMainController(this);
            tileController.setElement(element);
            tileController.setTitle(element.getTitle());
            tileController.setSubtitle(element.getSubtitle());

            // add the tile to the GUI
            container.getChildren().addAll(root);
        }

    }

    /**
     * Finds all of the routes associated with the selected element
     *
     * @return an array list of routes associated with the selected element
     */
    private List<Route> findAssociatedRoutes() {

        // our new list of associations
        List<Route> associations;

        // if the element is a trip
        if (selectedElement instanceof Trip) {
            associations = ((Trip) selectedElement).getContainingRoutes();
        }

        // if the element is a stop time
        else if (selectedElement instanceof StopTime) {
            associations = ((StopTime) selectedElement).getContainingRoutes();
        }

        // if the element is a stop
        else if (selectedElement instanceof Stop) {
            associations = ((Stop) selectedElement).getContainingRoutes();
        }

        // otherwise return empty array list
        else {
            associations = new ArrayList<>();
        }

        return associations;

    }

    /**
     * Finds all of the trips associated with the selected element
     *
     * @return an array list of trips associated with the selected element
     */
    private List<Trip> findAssociatedTrips() {

        // our new list of associations
        List<Trip> associations;

        // if the element is a route
        if (selectedElement instanceof Route) {
            associations = ((Route) selectedElement).getTrips();
        }

        // if the element is a stop time
        else if (selectedElement instanceof StopTime) {
            associations = new ArrayList<>();
            associations.add(((StopTime) selectedElement).getTrip());
        }

        // if the element is a stop
        else if (selectedElement instanceof Stop) {
            associations = ((Stop) selectedElement).getContainingTrips();
        }

        // otherwise return empty array list
        else {
            associations = new ArrayList<>();
        }

        return associations;

    }

    /**
     * Finds all of the stop times associated with the selected element
     *
     * @return an array list of stop times associated with the selected element
     */
    private List<StopTime> findAssociatedStopTimes() {

        // our new list of associations
        List<StopTime> associations;

        // if the element is a route
        if (selectedElement instanceof Route) {
            associations = ((Route) selectedElement).getStopTimes();
        }

        // if the element is a trip
        else if (selectedElement instanceof Trip) {
            associations = ((Trip) selectedElement).getStopTimes();
        }

        // if the element is a stop
        else if (selectedElement instanceof Stop) {
            associations = ((Stop) selectedElement).getContainingStopTimes();
        }

        // otherwise return empty array list
        else {
            associations = new ArrayList<>();
        }

        return associations;

    }

    /**
     * Finds all of the stops associated with the selected element
     *
     * @return an array list of stops associated with the selected element
     */
    private List<Stop> findAssociatedStops() {

        // our new list of associations
        List<Stop> associations;

        // if the element is a route
        if (selectedElement instanceof Route) {
            associations = ((Route) selectedElement).getStops();
        }

        // if the element is a trip
        else if (selectedElement instanceof Trip) {
            associations = ((Trip) selectedElement).getStops();
        }

        // if the element is a stop time
        else if (selectedElement instanceof StopTime) {
            associations = new ArrayList<>();
            associations.add(((StopTime) selectedElement).getStop());
        }

        // otherwise return empty array list
        else {
            associations = new ArrayList<>();
        }

        return associations;

    }

    /**
     * Gets the selected element's associated routes
     *
     * @return the selected element's associated routes
     */
    public List<Route> getAssociatedRoutes() {
        return associatedRoutes;
    }

    /**
     * Gets the selected element's associated trips
     *
     * @return the selected element's associated trips
     */
    public List<Trip> getAssociatedTrips() {
        return associatedTrips;
    }

    /**
     * Gets the selected element's associated stop times
     *
     * @return the selected element's associated stop times
     */
    public List<StopTime> getAssociatedStopTimes() {
        return associatedStopTimes;
    }

    /**
     * Gets the selected element's associated stops
     *
     * @return the selected element's associated stops
     */
    public List<Stop> getAssociatedStops() {
        return associatedStops;
    }

    /**
     * Gets the controller's GTFS file
     *
     * @return the controller's GTFS file
     */
    public GTFSFile getGTFSFile() {
        return gtfsFile;
    }

    /**
     * Gets the controller's map controller
     *
     * @return the controller's map controller
     */
    public GTFSMapController getMapController() {
        return mapController;
    }

    /**
     * Gets the controller's selected element
     *
     * @return the controller's selected element
     */
    public GTFSElement getSelectedElement() {
        return selectedElement;
    }

    /**
     * Sets the GUI's selected element
     *
     * @param selectedElement the element
     */
    public void setSelectedElement(GTFSElement selectedElement) {
        this.selectedElement = selectedElement;
        updateAssociations();
        updateInfoPanel();
    }

    /**
     * Converts a JavaFX color to a hex string
     *
     * @param color the color to convert
     * @return the color as a hex string
     */
    private String colorToHex(Color color) {
        return String.format("#%02X%02X%02X",
                (int) (color.getRed() * 255),
                (int) (color.getGreen() * 255),
                (int) (color.getBlue() * 255));
    }

}
