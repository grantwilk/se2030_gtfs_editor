package gtfsapp.gui;

import javafx.application.Platform;
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
     * Closes the window
     */
    public void close() {
        stage.close();
    }

    /**
     * Quits the application
     */
    public void quit() {
        Platform.exit();
    }

    /**
     * Gets the controller's scene
     *
     * @return the controller's scene
     */
    public Scene getScene() {
        return scene;
    }

    /**
     * Gets the controller's stage
     *
     * @return the controller's stage
     */
    public Stage getStage() {
        return stage;
    }

    /**
     * Gets the title of the window
     *
     * @return the title of the window
     */
    public String getWindowTitle() {
        return stage.getTitle();
    }

    /**
     * Sets the controller's scene
     *
     * @param scene - the scene to set
     */
    public void setScene(Scene scene) {
        this.scene = scene;
    }

    /**
     * Sets the controllers stage
     *
     * @param stage - the stage to set
     */
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    /**
     * Sets the window's always on top property
     *
     * @param alwaysOnTop - true if the window should be always on top, false otherwise
     */
    public void setWindowAlwaysOnTop(boolean alwaysOnTop) {
        stage.setAlwaysOnTop(alwaysOnTop);
    }

    /**
     * Sets the window's resizable property
     *
     * @param isResizable - true if the window should be resizable, false otherwise
     */
    public void setWindowResizable(boolean isResizable) {
        stage.setResizable(isResizable);
    }

    /**
     * Sets the title of the window
     *
     * @param windowTitle - the title of the window
     */
    public void setWindowTitle(String windowTitle) {
        stage.setTitle(windowTitle);
    }

}