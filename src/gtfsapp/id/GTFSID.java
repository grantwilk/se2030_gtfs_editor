package gtfsapp.id;

import java.util.ArrayList;

/**
 * @author Michael Primeau
 * @version 1.0
 * @created 15-Apr-2020 1:20:18 PM
 */
public abstract class GTFSID {

    private static ArrayList<GTFSID> existingIDs = new ArrayList<>();
    private String id;


    /**
     * Constructor for GTFSID
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

    /**
     * Check if an ID already exists in the data structure
     * @param id ID to check for existence
     */
    public static boolean exists(GTFSID id) {

        // TODO - michael, you can use ArrayList.contains(Object O) here :) -Grant

        for(GTFSID gtfsid:existingIDs) {
            if(gtfsid.equals(id)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if one GTFSID is equal to another
     * @param id
     */
    public boolean equals(GTFSID id) {
        /*
        TODO:
        We need an equals method for the exists() method to work.
        We should compare both the ID and the type using ObjectOf()
        This is because IDs of different types can have the same ID string
        (e.g. StopID with ID string J12 != TripID with ID string J12)
         */
    }
}