package gtfsapp.gui.main.components.associations.tile;

import gtfsapp.file.GTFSElement;
import gtfsapp.gui.main.GTFSMainController;
import gtfsapp.util.Colors;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

/**
 * @author Grant Wilk
 * @version 1.0
 * @created 19-Apr-2020 12:00:00 AM
 */
public class GTFSAssociationsTileController {

    /**
     * The main controller housing this controller
     */
    private GTFSMainController mainController;

    /**
     * The element that this association tile is representing
     */
    private GTFSElement element;

    /**
     * The root of the associations tile
     */
    @FXML
    private GridPane associationsTile;

    /**
     * The color pane of the associations tile
     */
    @FXML
    private Pane tileColor;

    /**
     * The title of the associations tile
     */
    @FXML
    private Label tileTitle;

    /**
     * The subtitle of the associations tile
     */
    @FXML
    private Label tileSubtitle;

    /**
     * Sets the tile's element as the selected element
     */
    public void setAsSelectedElement() {
        mainController.setSelectedElement(element);
    }

    /**
     * Gets the tile's GTFS element
     * @return the tile's GTFS element
     */
    public GTFSElement getElement() {
        return element;
    }

    /**
     * Sets the tile's GTFS element
     * @param element the element
     */
    public void setElement(GTFSElement element) {

        // set the selected element
        this.element = element;

        // update the tile's appearance
        updateTile();

    }

    /**
     * Updates the appearance of the tile based on the currently selected element
     */
    private void updateTile() {
        updateColor();
        updateTitle();
        updateSubtitle();
    }

    /**
     * Updates the title of the tile based on the element
     */
    private void updateTitle() {
        tileTitle.setText(element.getTitle());
    }

    /**
     * Updates the subtitle of the tile based on the element
     */
    private void updateSubtitle() {
        tileSubtitle.setText(element.getSubtitle());
    }

    /**
     * Updates the color of the tile based on the element
     */
    private void updateColor() {

        // get the color of the element
        Color backgroundColor = element.getColor();
        Color borderColor = backgroundColor.darker();
        String backgroundColorString = Colors.toString(backgroundColor);
        String borderColorString = Colors.toString(borderColor);

        // update the color of the pane
        tileColor.setStyle(
                String.format("-fx-background-color: %s;", backgroundColorString) +
                        String.format("-fx-border-color: %s;", borderColorString)
        );

    }

    /**
     * Sets the main controller for the associations tile
     * @param mainController the main controller
     */
    public void setMainController(GTFSMainController mainController) {
        this.mainController = mainController;
    }


}
