package gtfsapp.gui.main.components.selectedelement.attribute;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

/**
 * @author Grant Wilk
 * @version 1.0
 * @created 19-Apr-2020 12:05:19 PM
 */
public class GTFSSelectedElementAttributeController {

    /**
     * The root container of the attribute
     */
    @FXML
    VBox selectedElementAttribute;

    /**
     * The name of the attribute
     */
    @FXML
    Label attributeName;

    /**
     * The value of the attribute
     */
    @FXML
    Label attributeValue;

    /**
     * Gets the name of the attribute
     *
     * @return the name of the attribute
     */
    public String getName() {
        return attributeName.getText();
    }

    /**
     * Sets the name of the attribute
     *
     * @param name the name of the attribute
     */
    public void setName(String name) {
        attributeName.setText(name);
    }

    /**
     * Gets the value of the attribute
     *
     * @return the value of the attribute
     */
    public String getValue() {
        return attributeValue.getText();
    }

    /**
     * Sets the value of the attribute
     */
    public void getValue(String value) {
        attributeValue.setText(value);
    }


}
