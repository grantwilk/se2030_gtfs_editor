package gtfsapp.gui.dialog.edit.stoptime;

import gtfsapp.file.Stop;
import gtfsapp.file.StopTime;
import gtfsapp.file.Trip;
import gtfsapp.gui.dialog.edit.GTFSEditDialogController;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

import java.util.ArrayList;
import java.util.Set;

public class GTFSEditStopTimeDialogController extends GTFSEditDialogController {

    /**
     * The number of milliseconds in a second
     */
    public static final int MILLIS_IN_SECOND = 1000;

    /**
     * The number of milliseconds in a minute
     */
    public static final int MILLIS_IN_MINUTE = MILLIS_IN_SECOND * 60;

    /**
     * The number of milliseconds in an hour
     */
    public static final int MILLIS_IN_HOUR = MILLIS_IN_MINUTE * 60;

    /**
     * Choice box for selecting the stop time's stop
     */
    @FXML
    private ChoiceBox<Stop> stopChoiceBox;

    /**
     * Choice box for selecting the stop time's trip
     */
    @FXML
    private ChoiceBox<Trip> tripChoiceBox;

    /**
     * Text field for updating the arrival time's hours
     */
    @FXML
    private TextField arrivalTimeHoursField;

    /**
     * Text field for updating the arrival time's minutes
     */
    @FXML
    private TextField arrivalTimeMinutesField;

    /**
     * Text field for updating the arrival time's seconds
     */
    @FXML
    private TextField arrivalTimeSecondsField;

    /**
     * Text field for updating the departure time's hours
     */
    @FXML
    private TextField departureTimeHoursField;

    /**
     * Text field for updating the departure time's minutes
     */
    @FXML
    private TextField departureTimeMinutesField;

    /**
     * Text field for updating the departure time's seconds
     */
    @FXML
    private TextField departureTimeSecondsField;

    /**
     * Text field for updating the stop time head sign
     */
    @FXML
    private TextField headSignField;

    /**
     * Initializes the values of the fields in the edit dialog
     */
    @Override
    public void initializeEditFields() {

        // get the currently selected stop time
        StopTime element = (StopTime) getElement();

        // get all of the stops in the feed
        Set<Stop> stops = mainController.getGTFSFile().getFeed().getStops();

        // clear the choice box's items and add all of our stops instead
        stopChoiceBox.getItems().clear();
        stopChoiceBox.getItems().addAll(stops);

        // set the stop time's current stop as the currently selected stop
        stopChoiceBox.setValue(element.getStop());

        // get all of the trips in the feed
        Set<Trip> trips = mainController.getGTFSFile().getFeed().getTrips();

        // clear the choice box's items and add all of our stops instead
        tripChoiceBox.getItems().clear();
        tripChoiceBox.getItems().addAll(trips);

        // set the stop time's current stop as the currently selected stop
        tripChoiceBox.setValue(element.getContainingTrip());

        // get the arrival time and its hour, minute, and second subcomponents
        long arrivalTime = element.getArrivalTime().getTime();

        int arrivalTimeHours = (int) Math.floor(arrivalTime / (double) MILLIS_IN_HOUR);
        arrivalTime -= arrivalTimeHours * MILLIS_IN_HOUR;

        int arrivalTimeMinutes = (int) Math.floor(arrivalTime / (double) MILLIS_IN_MINUTE);
        arrivalTime -= arrivalTimeMinutes * MILLIS_IN_MINUTE;

        int arrivalTimeSeconds = (int) Math.floor(arrivalTime / (double) MILLIS_IN_SECOND);

        // set arrival time text fields
        arrivalTimeHoursField.setText(String.format("%02d", arrivalTimeHours));
        arrivalTimeMinutesField.setText(String.format("%02d", arrivalTimeMinutes));
        arrivalTimeSecondsField.setText(String.format("%02d", arrivalTimeSeconds));

        // get the departure time and its hour, minute, and second subcomponents
        long departureTime = element.getDepartureTime().getTime();

        int departureTimeHours = (int) Math.floor(departureTime / (double) MILLIS_IN_HOUR);
        departureTime -= departureTimeHours * MILLIS_IN_HOUR;

        int departureTimeMinutes = (int) Math.floor(departureTime / (double) MILLIS_IN_MINUTE);
        departureTime -= departureTimeMinutes * MILLIS_IN_MINUTE;

        int departureTimeSeconds = (int) Math.floor(departureTime / (double) MILLIS_IN_SECOND);

        // set departure time text fields
        departureTimeHoursField.setText(String.format("%02d", departureTimeHours));
        departureTimeMinutesField.setText(String.format("%02d", departureTimeMinutes));
        departureTimeSecondsField.setText(String.format("%02d", departureTimeSeconds));

        // set departure time head sign field
        headSignField.setText(element.getHeadSign());

    }

    /**
     * Applies the new attributes in the edit dialog to the edit dialog's element
     */
    @Override
    public void apply() {

        // update the info panel
        mainController.updateInfoPanel();

        // close the dialog
        close();

    }

    /**
     * Applies the new attributes in the edit dialog to multiple elements
     */
    @Override
    public void applyMultiple() {
        throw new UnsupportedOperationException();
    }

}
