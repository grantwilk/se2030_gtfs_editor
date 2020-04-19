package gtfsapp.id;

import java.util.Random;

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

        // TODO - DONT RELY ON RANDOMS!!!
        Random rand = new Random();

        return prefix + rand.nextInt(10000);

    }
}