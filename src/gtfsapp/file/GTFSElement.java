package gtfsapp.file;

import gtfsapp.id.*;

import java.util.HashMap;

/**
 * @author Mason Schlax
 * @version 1.0
 * @created 15-Apr-2020 1:20:18 PM
 */
public abstract class GTFSElement {

    private final GTFSID id;

    /**
     * Constructor for a GTFSElement that takes an id as its only parameter
     *
     * @param id for the element
     */
    public GTFSElement(GTFSID id) {
        this.id = id;
    }

    /**
     * Getter for the id of a GTFSElement
     *
     * @return The ID of the element
     */
    public GTFSID getID() {
        return id;
    }

    /**
     * Converts the GTFS element to a string
     */
    @Override
    public String toString() {
        return getTitle().toUpperCase();
    }

    /**
     * Gets the element's title to be displayed in the GUI
     *
     * @return the element's title
     */
    public abstract String getTitle();

    /**
     * Gets the element's subtitle to be displayed in the GUI
     *
     * @return the element's subtitle
     */
    public abstract String getSubtitle();

    /**
     * Gets the element's attributes to be displayed in the GUI
     *
     * @return a HashMap<Attribute Title, Attribute Value> of the element's attributes
     */
    public abstract HashMap<String, String> getAttributes();

}