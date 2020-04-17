package gtfsapp.gui;

import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * @author Grant Wilk
 * @version 1.0
 * @created 15-Apr-2020 1:20:18 PM
 */
public abstract class GTFSController {

    private Scene scene;
    private Stage stage;

    /**
     * Gets the controller's scene and returns it
     * @return the controller's scene
     */
    public Scene getScene() {
        return scene;
    }

    /**
     * Gets the controller's stage and returns it
     * @return the controller's stage
     */
    public Stage getStage() {
        return stage;
    }

    /**
     * Sets the controller's scene
     * @param scene - the scene to set
     */
    public void setScene(Scene scene) {
        this.scene = scene;
    }

    /**
     * Sets the controllers stage
     * @param stage - the stage to set
     */
    public void setStage(Stage stage) {
        this.stage = stage;
    }

}