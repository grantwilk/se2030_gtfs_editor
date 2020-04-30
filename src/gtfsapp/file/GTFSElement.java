package gtfsapp.file;

import gtfsapp.id.*;
import javafx.scene.paint.Color;

import java.util.HashMap;

/**
 * @author Mason Schlax
 * @version 1.0
 * @created 15-Apr-2020 1:20:18 PM
 */
public abstract class GTFSElement implements Comparable<GTFSElement> {

    /**
     * The element's unique ID
     */
    private final GTFSID id;

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
     * Converts the GTFS element to a string
     */
    @Override
    public String toString() {
        return getTitle().toUpperCase();
    }

    /**
     * Checks to see whether two elements are equal
     */
    @Override
    public boolean equals(Object obj) {

        // return false if the object is not GTFS element
        if (!(obj instanceof GTFSElement)) {
            return false;
        }

        // otherwise, return whether the IDs are equal
        return (this.getID().equals(((GTFSElement) obj).getID()));

    }

    /**
     * Compares two elements by their IDs
     * @param element - the element to compare to
     */
    @Override
    public int compareTo(GTFSElement element) {
        return this.getID().compareTo(element.getID());
    }

    /**
     * Gets the element's title to be displayed in the GUI
     * @return the element's title
     */
    public abstract String getTitle();

    /**
     * Gets the element's subtitle to be displayed in the GUI
     * @return the element's subtitle
     */
    public abstract String getSubtitle();

    /**
     * Gets the element's attributes to be displayed in the GUI
     * @return a HashMap<Attribute Title, Attribute Value> of the element's attributes
     */
    public abstract HashMap<String, String> getAttributes();

    /**
     * Gets the element's colors to be displayed in the GUI
     * @return the element's color
     */
    public abstract Color getColor();

}