package gtfsapp.file;

import gtfsapp.id.*;

import javafx.scene.paint.Color;

/**
 * @author grant
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

    }

    /**
     * @param id
     * @param color
     */
    public GTFSElement(GTFSID id, Color color) {

    }

    /**
     * @param id
     * @param name
     */
    public GTFSElement(GTFSID id, String name) {

    }

    /**
     * @param id
     * @param name
     * @param color
     */
    public GTFSElement(GTFSID id, String name, Color color) {

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