package gtfsapp.gui.main;

import gtfsapp.file.*;
import gtfsapp.gui.GTFSController;
import gtfsapp.gui.main.components.associations.tile.GTFSAssociationsTileController;
import gtfsapp.gui.main.components.selectedelement.attribute.GTFSSelectedElementAttributeController;
import gtfsapp.gui.map.GTFSMapController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
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

    private ArrayList<Route> associatedRoutes;
    private ArrayList<Stop> associatedStops;
    private ArrayList<StopTime> associatedStopTimes;
    private ArrayList<Trip> associatedTrips;
    private GTFSFile gtfsFile;
    private GTFSMapController mapController;
    private GTFSElement selectedElement = null;

    /**
     * FXML attributes for primary GUI elements
     */
    @FXML
    private HBox mainPanel;

    @FXML
    private VBox infoPanel;

    @FXML
    private HBox fileHeaderPanel;

    @FXML
    private GridPane infoPanelBody;

    /**
     * FXML attributes for the search panel
     */
    @FXML
    private VBox searchPanel;

    @FXML
    private TextField searchField;

    /**
     * FXML attributes for the selected elements panel
     */
    @FXML
    private VBox selectedElementPanel;

    @FXML
    private Pane selectedElementColor;

    @FXML
    private Label selectedElementTitle;

    @FXML
    private Label selectedElementSubtitle;

    @FXML
    private VBox selectedElementAttributesContainer;

    /**
     * FXML attributes for the associations panel
     */
    @FXML
    private Pane associationsPanel;

    @FXML
    private TabPane associationsTabPane;

    @FXML
    private VBox associatedRoutesContainer;

    @FXML
    private VBox associatedTripsContainer;

    @FXML
    private VBox associatedStopTimesContainer;

    @FXML
    private VBox associatedStopsContainer;

    /**
     * The title displayed in the selected element panel if there is no selected element
     */
    private static final String NULL_SELECTED_ELEMENT_TITLE = "NULL";

    /**
     * The subtitle displayed in the selected element panel if there is no selected element
     */
    private static final String NULL_SELECTED_ELEMENT_SUBTITLE = "Nothing to see here!";

    /**
     * The color displayed in the selected element panel if there is no selected element
     */
    public static final String NULL_SELECTED_ELEMENT_COLOR = "#C0C0C0";

    /**
     * Gets the selected element's associated routes and returns them
     * @return the selected element's associated routes
     */
    public ArrayList<Route> getAssociatedRoutes() {
        return associatedRoutes;
    }

    /**
     * Gets the selected element's associated trips and returns them
     * @return the selected element's associated trips
     */
    public ArrayList<Trip> getAssociatedTrips() {
        return associatedTrips;
    }

    /**
     * Gets the selected element's associated stop times and returns them
     * @return the selected element's associated stop times
     */
    public ArrayList<StopTime> getAssociatedStopTimes() {
        return associatedStopTimes;
    }

    /**
     * Gets the selected element's associated stops and returns them
     * @return the selected element's associated stops
     */
    public ArrayList<Stop> getAssociatedStops() {
        return associatedStops;
    }

    /**
     * Gets the controller's GTFS file and returns it
     * @return the controller's GTFS file
     */
    public GTFSFile getGTFSFile() {
        return gtfsFile;
    }

    /**
     * Gets the controller's map controller and returns it
     * @return the controller's map controller
     */
    public GTFSMapController getMapController() {
        return mapController;
    }

    /**
     * Gets the controller's selected element and returns it
     * @return
     */
    public GTFSElement getSelectedElement() {
        return selectedElement;
    }

    /**
     * Invokes an edit dialog for the selected element
     */
    public void invokeEditDialog() {
        throw new UnsupportedOperationException();
    }

    /**
     * Invokes an error dialog with a message
     * @param msg the message to display in the error dialog
     */
    public void invokeErrorDialog(String msg) {
        // TODO
    }

    /**
     * Invokes the system's file chooser and loads a GTFS file from the computer's file system
     */
    public void loadFile() throws FileNotFoundException {

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
                // create the GTFS file from the files
                gtfsFile = new GTFSFile(files);

                // attempt to parse the files
                gtfsFile.load();

                // update GUI's associated elements
                updateAssociatedElements();

                // update info panel GUI
                updateInfoPanel();

                // update the map GUI
                // mapController.updateMap();

            } catch (IOException e) {
                // TODO - invoke error dialog with exception method here
                e.printStackTrace();
            }
        }

    }

    /**
     * Saves the currently loaded GTFS file to its original path on the computer's file system
     */
    public void saveAsFile() {
        throw new UnsupportedOperationException();
    }

    /**
     * Invokes the systems' file chooser and saves a GTFS file to the computer's file system
     */
    public void saveFile() {
        throw new UnsupportedOperationException();
    }

    /**
     * Searches for an element within the currently loaded GTFS feed that matches the string entered in the GUI's
     * search field
     */
    public void searchForElement() {
        throw new UnsupportedOperationException();
    }

    /**
     * Sets the GUI's selected element
     * @param selectedElement the element
     */
    public void setSelectedElement(GTFSElement selectedElement) {
        this.selectedElement = selectedElement;
        updateAssociatedElements();
        updateInfoPanel();
    }

    public void updateAssociatedElements() {

        // get the GTFS feed from the file
        Feed feed = gtfsFile.getFeed();

        // if there is no selected object, display all routes, trips, times, and stops
        if (selectedElement == null) {
            associatedRoutes = feed.getRoutes();
            associatedTrips = feed.getTrips();
            associatedStopTimes = feed.getStopTimes();
            associatedStops = feed.getStops();
        }

        // otherwise, find associations and update the class variables
        else {
            // TODO - uncomment this once all GTFS element methods are implemented!
            // associatedRoutes = findAssociatedRoutes();
            // associatedTrips = findAssociatedTrips();
            // associatedStopTimes = findAssociatedStopTimes();
            // associatedStops = findAssociatedStops();
        }

        // TODO - remove this once Feed is implemented!
        associatedRoutes = new ArrayList<>();
        associatedTrips = new ArrayList<>();
        associatedStopTimes = new ArrayList<>();
        associatedStops = new ArrayList<>();

        // TODO - remove this! temporary route.
        Route route = new Route(feed, "jeff!", RouteType.SUBWAY);
        associatedRoutes.add(route);

        Trip trip = new Trip(feed, "steve!");
        associatedTrips.add(trip);

        Stop stop = new Stop(feed, "luigi!");
        associatedStops.add(stop);

        StopTime stopTime = new StopTime(feed, stop, 0);
        associatedStopTimes.add(stopTime);

    }

    /**
     * Updates the GUI's associations panel
     */
    public void updateAssociationsPanel() throws IOException {
        updateAssociationsTab(associatedRoutes, associatedRoutesContainer);
        updateAssociationsTab(associatedTrips, associatedTripsContainer);
        updateAssociationsTab(associatedStopTimes, associatedStopTimesContainer);
        updateAssociationsTab(associatedStops, associatedStopsContainer);
    }

    /**
     * Converts all elements into an associations tile and places them in an associations container
     * @param elements - the elements to add
     * @param container - the container to place the tiles in
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
     * Updates the GUI's info panel
     */
    public void updateInfoPanel() {

        try {
            updateSearchPanel();
            updateAssociationsPanel();
            updateSelectedElementPanel();
        } catch (IOException e) {
            // TODO - trigger error dialog
            e.printStackTrace();
        }

    }

    /**
     * Updates the GUI's selected element panel
     */
    public void updateSelectedElementPanel() throws IOException {

        // set the selected elements title, subtitle, and color
        if (selectedElement == null) {
            selectedElementTitle.setText(NULL_SELECTED_ELEMENT_TITLE);
            selectedElementSubtitle.setText(NULL_SELECTED_ELEMENT_SUBTITLE);
            selectedElementColor.setStyle("-fx-background-color: " + NULL_SELECTED_ELEMENT_COLOR);
        } else {
            selectedElementTitle.setText(selectedElement.getTitle().toUpperCase());
            selectedElementSubtitle.setText(selectedElement.getSubtitle());
            // TODO - get element color and display that instead of red
            selectedElementColor.setStyle("-fx-background-color: red");

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
                attributeController.setTitle(title);
                attributeController.setSubtitle(subtitle);

                // add the attribute to the GUI
                selectedElementAttributesContainer.getChildren().addAll(root);

            }

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
     * Finds all of the routes associated with the selected element and returns them
     * @return an array list of routes associated with the selected element
     */
    private ArrayList<Route> findAssociatedRoutes() {

        // our new list of associations
        ArrayList<Route> associations;

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
     * Finds all of the trips associated with the selected element and returns them
     * @return an array list of trips associated with the selected element
     */
    private ArrayList<Trip> findAssociatedTrips() {

        // our new list of associations
        ArrayList<Trip> associations;

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
     * Finds all of the stop times associated with the selected element and returns them
     * @return an array list of stop times associated with the selected element
     */
    private ArrayList<StopTime> findAssociatedStopTimes() {

        // our new list of associations
        ArrayList<StopTime> associations;

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
     * Finds all of the stops associated with the selected element and returns them
     * @return an array list of stops associated with the selected element
     */
    private ArrayList<Stop> findAssociatedStops() {

        // our new list of associations
        ArrayList<Stop> associations;

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
     * Converts a JavaFX color to a hex string
     * @param color the color to convert
     * @return the color as a hex string
     */
    private String colorToHex(Color color) {
        return String.format( "#%02X%02X%02X",
                (int)( color.getRed() * 255 ),
                (int)( color.getGreen() * 255 ),
                (int)( color.getBlue() * 255 ) );
    }

}
