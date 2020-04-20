package gtfsapp.id;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Michael Primeau
 * @version 1.0
 * @created 15-Apr-2020 1:20:18 PM
 */
public class StopTimeID extends GTFSID {

    /**
     * A map of existing IDs that uses ID strings for keys and ID objects for values
     */
    private final static Set<String> existingIDStrings = new HashSet<>();

    /**
     * Creates a Stop Time ID with a specified ID string
     *
     * @param id the ID string to be associated with this object
     */
    public StopTimeID(String id) {
        super(id);
        existingIDStrings.add(this.getIDString());
    }

    /**
     * Creates a Stop Time ID with a unique procedurally generated ID string
     */
    public StopTimeID() {
        super(generateID("ST", existingIDStrings));
        existingIDStrings.add(this.getIDString());
    }

    /**
     * Checks if a Stop Time ID with a specified ID string exists
     *
     * @param id the ID string to test
     * @return true if the ID string exists, false otherwise
     */
    public static boolean exists(String id) {
        return existingIDStrings.contains(id);
    }

    /**
     * Checks if a Stop Time ID object already exists
     *
     * @param id the Stop Time ID object to test
     * @return true if the Stop Time ID exists, false otherwise
     */
    public static boolean exists(StopTimeID id) {
        return existingIDStrings.contains(id.getIDString());
    }

}