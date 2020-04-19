package gtfsapp.id;

/**
 * @author Michael Primeau
 * @version 1.0
 * @created 15-Apr-2020 1:20:18 PM
 */
public class StopTimeID extends GTFSID {

    /**
     * Constructor for StopTimeID
     * @param id String id to be associated with this object
     */
    public StopTimeID(String id) {
        super(id);
    }

    /**
     * Generates a random stop time ID
     */
    public StopTimeID() {
        super(generateID("ST"));
    }

    /**
     * Procedurally generates a unique ID string
     * @param prefix - a prefix for the ID
     * @return a unique ID string
     */
    public static String generateID(String prefix) {

        int count = 0;
        StopTimeID id;

        // increment count and test every ID until we are unique
        do {
            id = new StopTimeID(prefix + count++);
        } while (exists(id));

        return id.toString();
    }
}