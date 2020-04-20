package gtfsapp.gui.dialog.error;

import gtfsapp.gui.dialog.GTFSDialogController;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;

/**
 * @author Grant Wilk
 * @version 1.0
 * @created 15-Apr-2020 1:20:18 PM
 */
public class GTFSErrorDialogController extends GTFSDialogController {

	/**
	 * The color of a warning error
	 */
	private static final String ERROR_COLOR_WARNING = "#E6B162";

	/**
	 * The color of an exception error
	 */
	private static final String ERROR_COLOR_EXCEPTION = "#E07E53";

	/**
	 * The color of a fatal error
	 */
	private static final String ERROR_COLOR_FATAL = "#CD5D4C";

	/**
	 * The color of an unknown error
	 */
	private static final String ERROR_COLOR_UNKNOWN = "#C0C0C0";

	/**
	 * The label displaying the error class
	 */
	@FXML
	private Label errorTitle;

	/**
	 * The label displaying the error message
	 */
	@FXML
	private Label errorMessage;

	/**
	 * The ellipse displaying the color of the error
	 */
	@FXML
	private Ellipse errorTypeIndicator;

	/**
	 * The type of the error
	 */
	private GTFSErrorType errorType;

	/**
	 * Gets the error dialog's error type
	 * @return the type of the error
	 */
	public GTFSErrorType getErrorType() {
		return errorType;
	}

	/**
	 * Sets the error dialog's error type
	 * @param errorType - the type of the error
	 */
	public void setErrorType(GTFSErrorType errorType) {

		String hexColorString;

		// switch on error type to get the color
		switch (errorType) {
			case WARNING:
				hexColorString = ERROR_COLOR_WARNING;
				break;
			case EXCEPTION:
				hexColorString = ERROR_COLOR_EXCEPTION;
				break;
			case FATAL:
				hexColorString = ERROR_COLOR_FATAL;
				break;
			default:
				hexColorString = ERROR_COLOR_UNKNOWN;

		}

		// set the error type indicator's color
		errorTypeIndicator.setStyle("-fx-fill: " + hexColorString);

		// set the error type attribute
		this.errorType = errorType;

	}

	/**
	 * Gets the error dialog's error title
	 * @return the error dialog's error title
	 */
	public String getErrorTitle() {
		return errorTitle.getText();
	}

	/**
	 * Sets the error dialog's error title
	 * @param title - the error's title
	 */
	public void setErrorTitle(String title) {
		errorTitle.setText(title.toUpperCase());
	}

	/**
	 * Sets the error dialog's error message
	 * @param message - the error's message
	 */
	public void setErrorMessage(String message) {
		errorMessage.setText(message);
	}

	/**
	 * Gets the error dialog's error message
	 * @return the error dialog's error message
	 */
	public String getErrorMessage() {
		return errorMessage.getText();
	}

}