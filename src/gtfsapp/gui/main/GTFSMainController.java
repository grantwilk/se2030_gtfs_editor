package gtfsapp.gui.main;

import gtfsapp.file.*;
import gtfsapp.gui.GTFSController;
import gtfsapp.gui.map.GTFSMapController;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
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
import java.util.ArrayList;
import java.util.List;

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
        fileChooser.setTitle("Open GTFS File");

        // TODO - do we select a .zip file containing the GTFS files or do we select multiple GTFS files?

        // get file from the chooser
        List<File> files = fileChooser.showOpenMultipleDialog(new Stage());

        // throw an exception if no file is found
        if (files.isEmpty()) {
            throw new FileNotFoundException("File could not be found.");
        }

        // create the GTFS file and load its contents
        gtfsFile = new GTFSFile(files);
        gtfsFile.load();

        // update associated elements
        updateAssociatedElements();

        // update info panel GUI
        updateInfoPanel();

        // update the map GUI
        // mapController.updateMap();

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
        updateInfoPanel();
        // TODO - what else needs to be updated? associations?
    }

    public void updateAssociatedElements() {

        // get the GTFS feed from the file
        Feed feed = gtfsFile.getFeed();

        // clear the existing associations
        associatedRoutes = new ArrayList<>();
        associatedTrips = new ArrayList<>();
        associatedStopTimes = new ArrayList<>();
        associatedStops = new ArrayList<>();

        // if there is no selected object, display all routes, trips, times, and stops
        if (selectedElement == null) {
            associatedRoutes = feed.getRoutes();
            associatedTrips = feed.getTrips();
            associatedStopTimes = feed.getStopTimes();
            associatedStops = feed.getStops();
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
    public void updateAssociationsPanel() {
        updateAssociatedRoutes();
        updateAssociatedTrips();
        updateAssociatedStopTimes();
        updateAssociatedStops();
    }

    /**
     * Updates the GUI's info panel
     */
    public void updateInfoPanel() {
        updateAssociationsPanel();
        updateSelectedElementPanel();
        // TODO - does anything else need to be updated? search panel?
    }

    /**
     * Updates the GUI's selected element panel
     */
    public void updateSelectedElementPanel() {

        // set the color
        String colorHex = colorToHex(selectedElement.getColor());
        selectedElementColor.setStyle("-fx-background-color: " + colorHex);

        // set the title
        if (selectedElement instanceof Route) {
            selectedElementTitle.setText("Route " + selectedElement.getID().toString());
        } else if (selectedElement instanceof Trip) {
            selectedElementTitle.setText("Trip " + selectedElement.getID().toString());
        } else if (selectedElement instanceof StopTime) {
            selectedElementTitle.setText("Time " + selectedElement.getID().toString());
        } else if (selectedElement instanceof Stop) {
            selectedElementTitle.setText("Stop " + selectedElement.getID().toString());
        }

        // set the subtitle
        selectedElementSubtitle.setText(selectedElement.getName());

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
     * Updates the associated routes in the GUI's associations panel
     */
    private void updateAssociatedRoutes() {
        updateAssociationTab(associatedRoutesContainer, associatedRoutes, "Route");
    }

    /**
     * Updates the associated trips in the GUI's associations panel
     */
    private void updateAssociatedTrips() {
        updateAssociationTab(associatedTripsContainer, associatedTrips, "Trip");
    }

    /**
     * Updates the associated stop times in the GUI's associations panel
     */
    private void updateAssociatedStopTimes() {
        updateAssociationTab(associatedStopTimesContainer, associatedStopTimes, "Time");
    }

    /**
     * Updates the associated stops in the GUI's associations panel
     */
    private void updateAssociatedStops() {
        updateAssociationTab(associatedStopsContainer, associatedStops, "Stop");
    }

    /**
     * Updates an association tab
     * @param container - the GUI container that the tab uses to display its associations
     * @param associatedElements - the list of associated GTFSElements
     * @param labelPrefix - prefix for the placehodler label
     */
    private void updateAssociationTab(Pane container, ArrayList<? extends GTFSElement> associatedElements, String labelPrefix) {

        // is the wildcard in the associated elements parameter appropriate here?

        // get the container's children
        ObservableList<Node> children = container.getChildren();

        // remove all of the existing children
        children.clear();

        // add labels to the containers children to represent each element
        // TODO - replace labels with tiles for each element
        for (GTFSElement element : associatedElements) {
            Label label = new Label();
            label.setText(labelPrefix + " " + element.getID().toString());
            children.add(label);
        }

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
