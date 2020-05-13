package gtfsapp.gui.dialog.edit.route;

import gtfsapp.file.GTFSElement;
import gtfsapp.file.Route;
import gtfsapp.file.RouteType;
import gtfsapp.gui.dialog.edit.GTFSEditDialogController;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.util.List;

public class GTFSEditRouteDialogController extends GTFSEditDialogController {

    /**
     * The choice box for the route's type
     */
    @FXML
    ChoiceBox<RouteType> routeTypeChoiceBox;

    /**
     * The text field for the route's short name
     */
    @FXML
    TextField shortNameField;

    /**
     * The text field for the route's long name
     */
    @FXML
    TextField longNameField;

    /**
     * The text field for the route's description
     */
    @FXML
    TextArea descriptionField;

    /**
     * The text field for the route's url
     */
    @FXML
    TextField urlField;

    /**
     * The color picker for the route's color
     */
    @FXML
    ColorPicker routeColorPicker;

    /**
     * Initializes the values of the fields in the edit dialog
     */
    @Override
    public void initializeEditFields() {

        // get the route
        Route route = (Route) getElement();

        // add enum to route type choice box
        routeTypeChoiceBox.getItems().addAll(RouteType.values());

        // initialize fields
        routeTypeChoiceBox.setValue(route.getRouteType());
        shortNameField.setText(route.getShortName());
        longNameField.setText(route.getLongName());
        descriptionField.setText(route.getDesc());
        urlField.setText(route.getURL());
        routeColorPicker.setValue(route.getColor());

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
     *
     * @param element - the element to apply attributes to
     */
    @Override
    public void applyOne(GTFSElement element) {

        // convert the element to a route
        Route route = (Route) element;

        // set attributes of the route equal to the edit field's values
        route.setRouteType(routeTypeChoiceBox.getValue());
        route.setShortName(shortNameField.getText());
        route.setLongName(longNameField.getText());
        route.setDesc(descriptionField.getText());
        route.setURL(urlField.getText());
        route.setColor(routeColorPicker.getValue());

    }
}
