package gtfsapp.id;

import java.util.ArrayList;

/**
 * @author grant, michael
 * @version 1.0
 * @created 15-Apr-2020 1:20:18 PM
 */
public abstract class GTFSID {

    private static ArrayList<GTFSID> existingIDs = new ArrayList<>();
    private String id;


    /** Constructor for GTFSID
     * @param id String id to be associated with this object
     */
    public GTFSID(String id) {
        if(exists(id)) {
            throw new IllegalArgumentException("id already exists");
        }
        this.id = id;

        // TODO
        // Not sure if this is allowed
        existingIDs.add(this);
    }

    /** Check if an id already exists in the data structure
     * @param id String id to check for existence
     */
    public static boolean exists(String id) {
        for(GTFSID gtfsid:existingIDs) {
            if(gtfsid.id.equals(id)) {
                return true;
            }
        }
        return false;
    }

}