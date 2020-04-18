package gtfsapp.file;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Michael Primeau and Grant Wilk
 * @version 1.0
 * @created 15-Apr-2020 1:20:18 PM
 */
public class GTFSFile {

    private Feed feed;
    private ArrayList<File> files;

    /** Constructor for GTFSFile
     * @param files List of GTFS files to be loaded and parsed
     */
    public GTFSFile(List<File> files) throws IOException {
        feed = new Feed("Feed1");
        isValid(files);
        this.files = (ArrayList<File>) files;
    }

    /**
     * Getter for Feed
     * @return Returns Feed for GTFS Application
     */
    public Feed getFeed() {
        return feed;
    }

    /**
     *
     */
    public void load() {

    }

    /**
     *
     */
    public void save() {

    }

    /**
     * Checks if the loaded set of files contains the correct files
     * @throws IOException Thrown if a required file is not found
     */
    private void isValid(List<File> files) throws IOException {
        if(files.size() != 4) {
            throw new IllegalArgumentException("Not all files have been loaded");
        }

        ArrayList<String> fileNames = new ArrayList<>();
        for(int i = 0; i < 4; i++) {
            fileNames.add(files.get(i).getPath().substring(files.get(i).getPath().lastIndexOf('/')));
        }

        if(Collections.frequency(fileNames,"stops.txt") != 1) {
            throw new IOException("No stops.txt file was found");
        }

        if(Collections.frequency(fileNames,"trips.txt") != 1) {
            throw new IOException("No trips.txt file was found");
        }

        if(Collections.frequency(fileNames,"stop_times.txt") != 1) {
            throw new IOException("No stop_times.txt file was found");
        }

        if(Collections.frequency(fileNames,"routes.txt") != 1) {
            throw new IOException("No routes.txt file was found");
        }
    }

}