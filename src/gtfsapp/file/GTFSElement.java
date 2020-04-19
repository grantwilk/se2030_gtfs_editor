package gtfsapp.file;

import gtfsapp.id.*;

import javafx.scene.paint.Color;

/**
 * @author Mason Schlax
 * @version 1.0
 * @created 15-Apr-2020 1:20:18 PM
 */
public abstract class GTFSElement {

    private final GTFSID id;
    private String name;

    /**
     * Constructor for a GTFSElement that takes an id as its only parameter
     * @param id for the element
     */
    public GTFSElement(GTFSID id) {
        this.id = id;
    }

    /**
     * Getter for the id of a GTFSElement
     * @return The ID of the element
     */
    public GTFSID getID() {
        return id;
    }

    /**
     * Getter for the name of a GTFSElement
     * @return The name of the element
     */
    public String getName() {
        return name;
    }

    /**
     * Setter for the color of a GTFSElement
     * @param name of the element
     */
    public void setName(String name) {
        this.name = name;
    }

}