package gtfsapp.gui.dialog.edit.trip;

import gtfsapp.file.GTFSElement;
import gtfsapp.file.Route;
import gtfsapp.file.Trip;
import gtfsapp.gui.dialog.edit.GTFSEditDialogController;
import gtfsapp.gui.main.GTFSMainController;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

import java.util.ArrayList;
import java.util.List;

public class GTFSEditTripDialogController extends GTFSEditDialogController {

    /**
     * Choice box for selecting the trips's route
     */
    @FXML
    private ChoiceBox<Route> routeChoiceBox;

    /**
     * Text field for setting the trip's head sign
     */
    @FXML
    private TextField headSignField;


    /**
     * Initializes the values of the fields in the edit dialog
     */
    @Override
    public void initializeEditFields() {

        // get the dialog's trip
        Trip trip = (Trip) getElement();

        // get the parent as a main controller
        GTFSMainController mainController = (GTFSMainController) parentController;

        // get all of the routes in the feed
        List<Route> routes = mainController.getGTFSFile().getFeed().getRoutes();

        // clear the choice box's items and add all of our stops instead
        routeChoiceBox.getItems().clear();
        routeChoiceBox.getItems().addAll(routes);

        // set the stop time's current stop as the currently selected stop
        routeChoiceBox.setValue(trip.getRoute());

        // set headsign
        headSignField.setText(trip.getHeadSign());
    }

    /**
     * Gets all elements that are similar to the edit dialog's GTFS element
     */
    @Override
    public List<GTFSElement> getSimilar() {

        GTFSMainController mainController = (GTFSMainController) parentController;
        List<Trip> trips = mainController.getGTFSFile().getFeed().getTrips();
        Trip element = (Trip) getElement();

        List<GTFSElement> similar = new ArrayList<>();

        // add all trips with the same stops as our current trip to our list
        for (Trip trip : trips) {
            if (trip.getStops().equals(element.getStops())) {
                similar.add(trip);
            }
        }

        return similar;

    }

    /**
     * Applies the new attributes in the edit dialog to one element
     *
     * @param element - the element to apply attributes to
     */
    @Override
    public void applyOne(GTFSElement element) {

        // get the dialog's trip
        Trip trip = (Trip) element;

        // update trip's route
        Route newRoute = routeChoiceBox.getValue();
        Route oldRoute = trip.getRoute();
        if (!oldRoute.equals(newRoute)) {
            newRoute.addTrip(trip);
            oldRoute.removeTrip(trip);
        }

        // update the trip's head sign
        ((Trip) element).setHeadSign(headSignField.getText());
    }
}
