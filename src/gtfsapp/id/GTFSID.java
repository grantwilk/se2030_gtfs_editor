package gtfsapp.id;

import java.util.HashSet;

/**
 * @author Michael Primeau
 * @version 1.0
 * @created 15-Apr-2020 1:20:18 PM
 */
public abstract class GTFSID {

    private static HashSet<GTFSID> existingIDs = new HashSet<>();
    private String id;


    /**
     * Constructor for GTFSID
     * @param id String ID to be associated with this object
     */
    public GTFSID(String id) {
        this.setID(id);

        // TODO
        // Not sure if this is allowed
        existingIDs.add(this);
    }

    /**
     * Checks if ID argument already exists and sets GTFSID id to the passed in argument
     * @param id String ID to set this objects ID to
     * @return True if the ID was successfully set
     */
    public boolean setID(String id) {
        for(GTFSID s:existingIDs) {
            if(s.id.equals(id)) {
                // Temporary Exception
                throw new IllegalArgumentException("ID already exists");
            }
        }
        this.id = id;
        return true;
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