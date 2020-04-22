package gtfsapp.id;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Michael Primeau
 * @version 1.0
 * @created 15-Apr-2020 1:20:18 PM
 */
public class FeedID extends GTFSID {

    /**
     * A map of existing IDs that uses ID strings for keys and ID objects for values
     */
    private final static Set<String> existingIDStrings = new HashSet<>();

    /**
     * Constructor for FeedID
     *
     * @param id String id to be associated with this object
     */
    public FeedID(String id) {
        super(id);
        existingIDStrings.add(this.getIDString());
    }

    /**
     * Creates a Feed ID with a unique procedurally generated ID string
     */
    public FeedID() {
        super(generateID("F", existingIDStrings));
        existingIDStrings.add(this.getIDString());
    }

    /**
     * Clears all ID strings from the map of existing IDs
     */
    public static void clear() {
        existingIDStrings.clear();
    }

    /**
     * Checks if a Feed ID with a specified ID string exists
     *
     * @param id the ID string to test
     * @return true if the ID string exists, false otherwise
     */
    public static boolean exists(String id) {
        return existingIDStrings.contains(id);
    }

    /**
     * Checks if a Feed ID object already exists
     *
     * @param id the Feed ID object to test
     * @return true if the Feed ID exists, false otherwise
     */
    public static boolean exists(FeedID id) {
        return existingIDStrings.contains(id.getIDString());
    }

}