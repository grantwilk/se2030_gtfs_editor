package gtfsapp.gui.main.components.associations.tile;

import gtfsapp.file.GTFSElement;
import gtfsapp.gui.main.GTFSMainController;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

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
     *
     * @return the tile's GTFS element
     */
    public GTFSElement getElement() {
        return element;
    }

    /**
     * Sets the tile's GTFS element
     *
     * @param element - the element
     */
    public void setElement(GTFSElement element) {
        this.element = element;
    }

    /**
     * Gets the title of the tile
     *
     * @return the title of the tile
     */
    public String getTitle() {
        return tileTitle.getText();
    }

    /**
     * Sets the title of the tile
     *
     * @param title - the title of the tile
     */
    public void setTitle(String title) {
        tileTitle.setText(title);
    }

    /**
     * Gets the subtitle of the tile
     *
     * @return the subtitle of the tile
     */
    public String getSubtitle() {
        return tileSubtitle.getText();
    }

    /**
     * Sets the subtitle of the tile
     *
     * @param subtitle - the of of the tile
     */
    public void setSubtitle(String subtitle) {
        tileSubtitle.setText(subtitle);
    }

    /**
     * Gets the associations tile's main controller
     *
     * @return the associations tile's main controller
     */
    public GTFSMainController getMainController() {
        return mainController;
    }

    /**
     * Sets the main controller for the associations tile
     *
     * @param mainController - the main controller
     */
    public void setMainController(GTFSMainController mainController) {
        this.mainController = mainController;
    }


}
