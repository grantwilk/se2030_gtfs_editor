package gtfsapp.gui.dialog.edit.stoptime;

import gtfsapp.file.GTFSElement;
import gtfsapp.file.Stop;
import gtfsapp.file.StopTime;
import gtfsapp.file.Trip;
import gtfsapp.gui.GTFSController;
import gtfsapp.gui.dialog.edit.GTFSEditDialogController;
import gtfsapp.gui.dialog.error.GTFSErrorType;
import gtfsapp.gui.dialog.select.GTFSSelectDialogController;
import gtfsapp.gui.main.GTFSMainController;
import gtfsapp.util.LimitedTextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class GTFSEditStopTimeDialogController extends GTFSEditDialogController {

    /**
     * Regular expression for a 24 hour time
     */
    public static final String HOURS_REGEX = "^([2][0-3]|[0-1]?[0-9])";

    /**
     * Regular expression for minutes
     */
    public static final String MINUTES_REGEX = "^([0-5]?[0-9])";

    /**
     * Regular expression for seconds
     */
    public static final String SECONDS_REGEX = MINUTES_REGEX;

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
    private LimitedTextField arrivalTimeHoursField;

    /**
     * Text field for updating the arrival time's minutes
     */
    @FXML
    private LimitedTextField arrivalTimeMinutesField;

    /**
     * Text field for updating the arrival time's seconds
     */
    @FXML
    private LimitedTextField arrivalTimeSecondsField;

    /**
     * Text field for updating the departure time's hours
     */
    @FXML
    private LimitedTextField departureTimeHoursField;

    /**
     * Text field for updating the departure time's minutes
     */
    @FXML
    private LimitedTextField departureTimeMinutesField;

    /**
     * Text field for updating the departure time's seconds
     */
    @FXML
    private LimitedTextField departureTimeSecondsField;

    /**
     * Text field for updating the stop time head sign
     */
    @FXML
    private TextField headSignField;

    /**
     * Initializes the edit dialog
     */
    @FXML
    public void initialize() {

        // set arrival time regex restrictions
        arrivalTimeHoursField.setRestrict(HOURS_REGEX);
        arrivalTimeMinutesField.setRestrict(MINUTES_REGEX);
        arrivalTimeSecondsField.setRestrict(SECONDS_REGEX);

        // set departure time regex restrictions
        departureTimeHoursField.setRestrict(HOURS_REGEX);
        departureTimeMinutesField.setRestrict(MINUTES_REGEX);
        departureTimeSecondsField.setRestrict(SECONDS_REGEX);

    }

    /**
     * Initializes the values of the fields in the edit dialog
     */
    @Override
    public void initializeEditFields() {

        // get the currently selected stop time
        StopTime element = (StopTime) getElement();

        // get the parent as a main controller
        GTFSMainController mainController = (GTFSMainController) parentController;

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
     * Gets all elements that are similar to the edit dialog's GTFS element
     */
    @Override
    public List<GTFSElement> getSimilar() {
        throw new UnsupportedOperationException();
    }

    /**
     * Applies the new attributes in the edit dialog to one element
     */
    @Override
    public void applyOne(GTFSElement element) {

        // get the parent as a main controller
        GTFSMainController mainController = (GTFSMainController) parentController;

        // get the stop time
        StopTime stopTime = (StopTime) element;

        // update the stop time's stop
        Stop newStop = stopChoiceBox.getValue();
        Stop oldStop = stopTime.getStop();
        if (!oldStop.equals(newStop)) {
            stopTime.setStop(newStop);
        }

        // update the stop time's trip
        Trip newTrip = tripChoiceBox.getValue();
        Trip oldTrip = stopTime.getContainingTrip();
        if (!oldTrip.equals(newTrip)) {
            oldTrip.removeStopTime(stopTime);
            newTrip.addStopTime(stopTime);
        }

        // update the stop time's head sign
        stopTime.setHeadSign(headSignField.getText());

        try {

            // update the stop time's arrival time
            long arrivalTimeHourMillis = Integer.parseInt(arrivalTimeHoursField.getText()) * MILLIS_IN_HOUR;
            long arrivalTimeMinuteMillis = Integer.parseInt(arrivalTimeMinutesField.getText()) * MILLIS_IN_MINUTE;
            long arrivalTimeSecondMillis = Integer.parseInt(arrivalTimeSecondsField.getText()) * MILLIS_IN_SECOND;
            stopTime.getArrivalTime().setTime(
                    arrivalTimeHourMillis + arrivalTimeMinuteMillis + arrivalTimeSecondMillis
            );

            // update the stop time's departure time
            long departureTimeHourMillis = Integer.parseInt(departureTimeHoursField.getText()) * MILLIS_IN_HOUR;
            long departureTimeMinuteMillis = Integer.parseInt(departureTimeMinutesField.getText()) * MILLIS_IN_MINUTE;
            long departureTimeSecondMillis = Integer.parseInt(departureTimeSecondsField.getText()) * MILLIS_IN_SECOND;
            stopTime.getDepartureTime().setTime(
                    departureTimeHourMillis + departureTimeMinuteMillis + departureTimeSecondMillis
            );

        }

        // catch invalid time entries
        catch (NumberFormatException e) {
            mainController.invokeErrorDialog(
                    GTFSErrorType.EXCEPTION,
                    "Invalid Time",
                    "One or more times were entered incorrectly."
            );
        }
    }

}
