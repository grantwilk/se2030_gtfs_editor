package gtfsapp.id;

import java.util.HashMap;

/**
 * @author Michael Primeau
 * @version 1.0
 * @created 15-Apr-2020 1:20:18 PM
 */
public class StopTimeID extends GTFSID {

    private static HashMap<String, StopTimeID> existingIDs;

    /**
     * Creates a Stop Time ID with a specified ID string
     * @param id - the ID string to be associated with this object
     */
    public StopTimeID(String id) {
        super(id);
        existingIDs.put(this.getIDString(), this);
    }

    /**
     * Creates a Stop Time ID with a unique procedurally generated ID string
     */
    public StopTimeID() {
        super(generateID("ST", existingIDs.keySet()));
        existingIDs.put(this.getIDString(), this);
    }

    /**
     * Checks if a Stop Time ID with a specified ID string exists
     * @param id - the ID string to test
     * @return true if the ID string exists, false otherwise
     */
    public static boolean exists(String id) {
        return existingIDs.containsKey(id);
    }

    /**
     * Checks if a Stop Time ID object already exists
     * @param id - the Stop Time ID object to test
     * @return true if the Stop Time ID exists, false otherwise
     */
    public static boolean exists(StopTimeID id) {
        return existingIDs.containsValue(id);
    }
}