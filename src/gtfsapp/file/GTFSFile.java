package gtfsapp.file;

import java.io.File;
import java.nio.file.Path;

/**
 * @author Michael Primeau and Grant Wilk
 * @version 1.0
 * @created 15-Apr-2020 1:20:18 PM
 */
public class GTFSFile {

    private Feed feed;
    private File file; // TODO Maybe this should be a list of files instead of one single file

    /**
     * @param path
     */
    public GTFSFile(Path path) {

    }

    /**
     *
     * @return
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

}