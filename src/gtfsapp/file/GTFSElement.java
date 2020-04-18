package gtfsapp.file;

import gtfsapp.id.*;

import javafx.scene.paint.Color;

/**
 * @author Mason Schlax
 * @version 1.0
 * @created 15-Apr-2020 1:20:18 PM
 */
public abstract class GTFSElement {

    private Color color;
    private GTFSID id;
    private String name;

    /**
     * @param id
     */
    public GTFSElement(GTFSID id) {
        // TODO - this constructor is not fully implemented!
    }

    /**
     *
     * @return
     */
    public Color getColor() {
        return null;
    }

    /**
     *
     * @return
     */
    public GTFSID getID() {
        return null;
    }

    /**
     *
     * @return
     */
    public String getName() {
        return "";
    }

    /**
     * @param color
     */
    public void setColor(Color color) {

    }

    /**
     * @param name
     */
    public void setName(String name) {

    }

}