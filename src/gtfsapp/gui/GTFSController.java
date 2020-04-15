package gtfsapp.gui;

import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * @author grant
 * @version 1.0
 * @created 15-Apr-2020 1:20:18 PM
 */
public abstract class GTFSController {

	private Scene scene;
	private Stage stage;

	public GTFSController(){

	}

	public Scene getScene(){
		return null;
	}

	public Stage getStage(){
		return null;
	}

	/**
	 * 
	 * @param scene
	 */
	public void setScene(Scene scene){

	}

	/**
	 * 
	 * @param stage
	 */
	public void setStage(Stage stage){

	}

}