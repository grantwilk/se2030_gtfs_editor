package gtfsapp.gui.dialog.edit.stoptime;

import gtfsapp.file.GTFSElement;
import gtfsapp.file.Stop;
import gtfsapp.file.StopTime;
import gtfsapp.file.Trip;
import gtfsapp.gui.dialog.edit.GTFSEditDialogController;
import gtfsapp.gui.main.GTFSMainController;
import gtfsapp.util.LimitedTextField;
import gtfsapp.util.Time;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

import java.util.ArrayList;
import java.util.List;

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
        List<Stop> stops = mainController.getGTFSFile().getFeed().getStops();

        // clear the choice box's items and add all of our stops instead
        stopChoiceBox.getItems().clear();
        stopChoiceBox.getItems().addAll(stops);

        // set the stop time's current stop as the currently selected stop
        stopChoiceBox.setValue(element.getStop());

        // get all of the trips in the feed
        List<Trip> trips = mainController.getGTFSFile().getFeed().getTrips();

        // clear the choice box's items and add all of our stops instead
        tripChoiceBox.getItems().clear();
        tripChoiceBox.getItems().addAll(trips);

        // set the stop time's current stop as the currently selected stop
        tripChoiceBox.setValue(element.getTrip());

        // get the departure time and arrival time
        Time arrivalTime = element.getArrivalTime();
        Time departureTime = element.getDepartureTime();

        // set departure time text fields
        arrivalTimeHoursField.setText(String.format("%02d", arrivalTime.getHours()));
        arrivalTimeMinutesField.setText(String.format("%02d", arrivalTime.getMinutes()));
        arrivalTimeSecondsField.setText(String.format("%02d", arrivalTime.getSeconds()));

        // set departure time text fields
        departureTimeHoursField.setText(String.format("%02d", departureTime.getHours()));
        departureTimeMinutesField.setText(String.format("%02d", departureTime.getMinutes()));
        departureTimeSecondsField.setText(String.format("%02d", departureTime.getSeconds()));

        // set head sign field
        headSignField.setText(element.getHeadSign());

    }

    /**
     * Gets all elements that are similar to the edit dialog's GTFS element
     */
    @Override
    public List<GTFSElement> getSimilar() {

        // get the stop times from the parent controller
        List<StopTime> stopTimes = ((GTFSMainController) parentController).getGTFSFile().getFeed().getStopTimes();

        // get our dialog's element
        StopTime element = (StopTime) getElement();

        // create a list of similar stop times
        List<GTFSElement> similar = new ArrayList<>();

        // add all stop times with the same arrival time to our list of similar stop times
        for (StopTime stopTime : stopTimes) {
            if (stopTime.getStop().equals(element.getStop())) {
                similar.add(stopTime);
            }
        }

        return similar;
    }

    /**
     * Applies the new attributes in the edit dialog to one element
     */
    @Override
    public void applyOne(GTFSElement element) {

        // get the main controller
        GTFSMainController mainController = (GTFSMainController) parentController;

        // get the stop time
        StopTime stopTime = (StopTime) element;

        // get the new stop
        Stop newStop = stopChoiceBox.getValue();

        // get the new trip
        Trip newTrip = tripChoiceBox.getValue();

        // get the new arrival time
        String arrivalHours = arrivalTimeHoursField.getText();
        String arrivalMinutes = arrivalTimeMinutesField.getText();
        String arrivalSeconds = arrivalTimeSecondsField.getText();
        String arrivalTimeString = String.format("%s:%s:%s", arrivalHours, arrivalMinutes, arrivalSeconds);
        Time arrivalTime = new Time(arrivalTimeString);

        // get the new departure time
        String departureHours = departureTimeHoursField.getText();
        String departureMinutes = departureTimeMinutesField.getText();
        String departureSeconds = departureTimeSecondsField.getText();
        String departureTimeString = String.format("%s:%s:%s", departureHours, departureMinutes, departureSeconds);
        Time departureTime = new Time(departureTimeString);

        // throw an exception if the arrival time occurs after the departure time
        if (arrivalTime.getMillis() > departureTime.getMillis()) {
            throw new IllegalArgumentException("Arrival time must occur before departure time.");
        }

        // update the trip
        if (!stopTime.getTrip().equals(newTrip)) {
            stopTime.getTrip().removeStopTime(stopTime);
            newTrip.addStopTime(stopTime);
        }

        // update the stop
        if (!stopTime.getStop().equals(newStop)) {
            stopTime.setStop(newStop);
        }

        // update the arrival and departure time
        stopTime.setArrivalTime(arrivalTime);
        stopTime.setDepartureTime(departureTime);

        // update the head sign
        stopTime.setHeadSign(headSignField.getText());


    }

}
