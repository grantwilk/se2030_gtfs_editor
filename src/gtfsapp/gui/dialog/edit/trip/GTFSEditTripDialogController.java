package gtfsapp.gui.dialog.edit.trip;

import gtfsapp.file.GTFSElement;
import gtfsapp.file.Trip;
import gtfsapp.gui.dialog.edit.GTFSEditDialogController;
import gtfsapp.gui.main.GTFSMainController;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class GTFSEditTripDialogController extends GTFSEditDialogController {

    @FXML
    private TextField headSignField;


    /**
     * Initializes the values of the fields in the edit dialog
     */
    @Override
    public void initializeEditFields() {
        Trip trip = (Trip) getElement();
        headSignField.setText(trip.getHeadSign());
    }

    /**
     * Gets all elements that are similar to the edit dialog's GTFS element
     */
    @Override
    public List<GTFSElement> getSimilar() {

        GTFSMainController mainController = (GTFSMainController) parentController;
        Set<Trip> trips = mainController.getGTFSFile().getFeed().getTrips();
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
     * @param element
     */
    @Override
    public void applyOne(GTFSElement element) {
        ((Trip) element).setHeadSign(headSignField.getText());
    }
}
