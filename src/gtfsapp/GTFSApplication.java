package gtfsapp;

import gtfsapp.gui.GTFSMainController;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * @author Grant Wilk
 * @version 1.0
 * @created 15-Apr-2020 1:20:18 PM
 */
public class GTFSApplication extends Application {

	/**
	 * The maximum height of the main stage in pixels
	 */
	private static final double MAIN_STAGE_MINIMUM_WIDTH = 500;

	/**
	 * The minimum height of the main stage in pixels
	 */
	private static final double MAIN_STAGE_MINIMUM_HEIGHT = 500;

    /**
	 * The entry point for the application
     * @param mainStage - the stage that will be running the main GUI
     */
    public void start(Stage mainStage) throws IOException {

        // load the FXML file
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/fxml/mainGUI.fxml"));
        Parent root = loader.load();

        // configure basic stage attributes
		mainStage.setTitle("GTFS Editor");

		// create and set stage scene
		Scene mainScene = new Scene(root);
		mainStage.setScene(mainScene);

		// get and configure main controller
		GTFSMainController mainController = loader.getController();
		mainController.setStage(mainStage);
		mainController.setScene(mainScene);

		// show the stage
		mainStage.show();

    }

}