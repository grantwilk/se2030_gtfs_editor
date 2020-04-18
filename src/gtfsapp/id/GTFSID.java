package gtfsapp.id;

import java.util.HashSet;

/**
 * @author Michael Primeau
 * @version 1.0
 * @created 15-Apr-2020 1:20:18 PM
 */
public class GTFSID {

    private static HashSet<GTFSID> existingIDs = new HashSet<>();
    private String id;


    /**
     * Constructor for GTFSID
     * @param id String id to be associated with this object
     */
    public GTFSID(String id) {
        this.id = id;

        for(GTFSID s:existingIDs) {
            if(s.id.equals(id)) {
                // Temporary Exception
                throw new IllegalArgumentException("ID already exists");
            }
        }

        // TODO
        // Not sure if this is allowed
        existingIDs.add(this);
    }

    /**
     * Check if an ID already exists in the data structure
     * @param id ID to check for existence
     */
    public static boolean exists(GTFSID id) {
        return existingIDs.contains(id);
    }

    /**
     * Checks if one GTFSID is equal to another
     * @param id
     */
    public boolean equals(GTFSID id) {
        return (this.id.equals(id.id) && (this.getClass() == id.getClass()));
    }
}