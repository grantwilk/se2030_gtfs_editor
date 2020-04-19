package gtfsapp.id;

import java.util.List;

/**
 * @author Michael Primeau
 * @version 1.0
 * @created 15-Apr-2020 1:20:18 PM
 */
public abstract class GTFSID {

    private final String id;

    /**
     * Constructor for GTFSID
     * @param id String ID to be associated with this object
     */
    public GTFSID(String id) {
        this.id = id;
    }

    /**
     * Gets the ID's ID string and returns it
     * @return the ID's ID string
     */
    public String getIDString() {
        return id;
    }

    /**
     * Procedurally generates a unique ID string from a prefix
     * @param prefix - a prefix for the ID
     * @param existingIDStrings - a list of existing ID strings
     * @return a unique procedurally generated ID string
     */
    public static String generateID(String prefix, List<String> existingIDStrings) {

        int count = 0;
        String id;

        // create a new ID in the format "<prefix><count>" until it is unique
        do {
            id = prefix + count++;
        } while (existingIDStrings.contains(id));

        return id;

    }

    /**
     * Checks if one object is equal to this object
     * @param obj - the object to test
     * @return true if the objects are equal, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        boolean stringMatch = id.equals(obj.toString());
        boolean classMatch = getClass().equals(obj.getClass());
        return stringMatch && classMatch;
    }

    /**
     * Converts the ID into a string
     * @return the ID as a string
     */
    @Override
    public String toString() {
        return "ID: " + id;
    }

}