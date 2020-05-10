package gtfsapp.id;

import java.util.Random;
import java.util.Set;

/**
 * @author Michael Primeau
 * @version 1.0
 * @created 15-Apr-2020 1:20:18 PM
 */
public abstract class GTFSID implements Comparable<GTFSID> {

    /**
     * The ID String of the ID
     */
    private final String id;

    /**
     * The random object for generating procedural IDs
     */
    private static final Random rand = new Random();

    /**
     * Constructor for GTFSID
     *
     * @param id String ID to be associated with this object
     */
    public GTFSID(String id) {
        this.id = id;
    }

    /**
     * Clears all ID strings from all ID subclasses
     */
    public static void clear() {
        FeedID.clear();
        RouteID.clear();
        TripID.clear();
        StopTimeID.clear();
        StopID.clear();
    }

    /**
     * Randomly generates a unique ID string from a prefix
     *
     * @param prefix            a prefix for the ID
     * @param existingIDStrings a set of existing unique ID strings
     * @return a unique randomly generated ID string
     */
    public static String generateID(String prefix, Set<String> existingIDStrings) {

        // initialize ID string
        String idString;

        // create a new ID in the format "<prefix><count>" until it is unique
        do {
            idString = String.format("%s%02d", prefix, rand.nextInt());
        } while (existingIDStrings.contains(idString));

        return idString;

    }

    /**
     * Gets the ID's ID string and returns it
     *
     * @return the ID's ID string
     */
    public String getIDString() {
        return id;
    }

    /**
     * Checks if one object is equal to this object
     *
     * @param obj the object to test
     * @return true if the objects are equal, false otherwise
     */
    @Override
    public boolean equals(Object obj) {

        boolean classMatch = false;
        boolean idStringMatch = false;

        // check to see if we actually have a GTFSID object
        if (obj instanceof GTFSID) {

            // check to make sure they have the same subclass
            classMatch = getClass().equals(obj.getClass());

            // check to make sure they have the same ID string
            idStringMatch = getIDString().equals(((GTFSID) obj).getIDString());

        }

        return classMatch && idStringMatch;
    }

    /**
     * Compares two IDs by their ID strings
     * @param id - the ID to compare to
     */
    @Override
    public int compareTo(GTFSID id) {
        return this.getIDString().compareTo(id.getIDString());
    }

    /**
     * Converts the ID into a string
     *
     * @return the ID as a string
     */
    @Override
    public String toString() {
        return "ID: " + id;
    }

}