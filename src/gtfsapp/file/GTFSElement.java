package gtfsapp.file;

import gtfsapp.id.*;

import javafx.scene.paint.Color;

/**
 * @author grant, Mason Schlax
 * @version 1.0
 * @created 15-Apr-2020 1:20:18 PM
 */
public abstract class GTFSElement {

    private Color color;
    private GTFSID id;
    private String name;

    /**
     * Constructor for a GTFSElement that takes an id as its only parameter
     * @param id for the element
     */
    public GTFSElement(GTFSID id) {

        this.id = id;

    }

    /**
     * Constructor for a GTFSElement that takes an id and a color as its parameters
     * @param id for the element
     * @param color for the element
     */
    public GTFSElement(GTFSID id, Color color) {

        this.id = id;
        this.color = color;

    }

    /**
     * Constructor for a GTFSElement that takes an id and a name as its parameters
     * @param id for the element
     * @param name for the element
     */
    public GTFSElement(GTFSID id, String name) {

        this.id = id;
        this.name = name;

    }

    /**
     * Constructor for a GTFSElement that takes an id, a name, and a color as its parameters
     * @param id for the element
     * @param name for the element
     * @param color for the element
     */
    public GTFSElement(GTFSID id, String name, Color color) {

        this.id = id;
        this.name = name;
        this.color = color;

    }

    /**
     *Getter for the color of a GTFSElement
     * @return The color of the element
     */
    public Color getColor() {
        return this.color;
    }

    /**
     *Getter for the id of a GTFSElement
     * @return The ID of the element
     */
    public GTFSID getID() {
        return this.id;
    }

    /**
     *Getter for the name of a GTFSElement
     * @return The name of the element
     */
    public String getName() {

        return this.name.toString();
    }

    /**
     * Setter for the color of a GTFSElement
     * @param color of the element
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * Setter for the color of a GTFSElement
     * @param name of the element
     */
    public void setName(String name) {
        this.name = name;
    }

}