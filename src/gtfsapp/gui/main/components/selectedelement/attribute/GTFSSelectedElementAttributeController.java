package gtfsapp.gui.main.components.selectedelement.attribute;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class GTFSSelectedElementAttributeController {

    @FXML
    VBox selectedElementAttribute;

    @FXML
    Label attributeTitle;

    @FXML
    Label attributeSubtitle;

    /**
     * Gets the title of the attribute
     * @return the title of the attribute
     */
    public String getTitle() {
        return attributeTitle.getText();
    }

    /**
     * Sets the title of the attribute
     * @param title - the title of the attribute
     */
    public void setTitle(String title) {
        attributeTitle.setText(title);
    }

    /**
     * Gets the subtitle of the attribute
     * @return the subtitle of the attribute
     */
    public String getSubtitle() {
        return attributeSubtitle.getText();
    }

    /**
     * Sets the subtitle of the attribute
     */
    public void setSubtitle(String subtitle) {
        attributeSubtitle.setText(subtitle);
    }


}
