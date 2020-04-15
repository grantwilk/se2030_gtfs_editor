package gtfsapp.id;

import java.util.ArrayList;

/**
 * @author grant
 * @version 1.0
 * @created 15-Apr-2020 1:20:18 PM
 */
public abstract class GTFSID {

    private static ArrayList<GTFSID> existingIDs;
    private String id;


    /**
     * @param id
     */
    public GTFSID(String id) {

    }

    /**
     *
     */
    public GTFSID() {

    }

    /**
     * @param id
     */
    public static boolean exists(String id) {
        return false;
    }

}