package gtfsapp.util;

/**
 * A class for storing and performing computations on coordinates
 * @author Grant Wilk
 */
public class Location {

    /**
     * The number of statute (regular) miles per nautical mile
     */
    private static final double STATUTE_MILES_PER_NAUTICAL_MILE = 1.15077945;

    /**
     * The latitude of the location
     */
    private double latitude;

    /**
     * The longitude of the location
     */
    private double longitude;

    /**
     * Creates a new location from the a latitude and longitude
     * @param latitude - the latitude of the location
     * @param longitude - the longitude of the location
     */
    public Location(double latitude, double longitude) {
        this.latitude  = latitude;
        this.longitude = longitude;
    }

    /**
     * Finds the distance between two locations
     * @param location - the second location to find the distance to
     * @return distance between the locations in miles
     */
    public double distanceTo(Location location) {

        // conversion from lat/long to statute miles source from:
        // https://introcs.cs.princeton.edu/java/44st/Location.java.html

        // convert latitudes and longitudes to radians
        double lat1 = Math.toRadians(this.latitude);
        double lon1 = Math.toRadians(this.longitude);
        double lat2 = Math.toRadians(location.latitude);
        double lon2 = Math.toRadians(location.longitude);

        // great circle distance in radians, using law of cosines formula
        double angle = Math.acos(Math.sin(lat1) * Math.sin(lat2)
                + Math.cos(lat1) * Math.cos(lat2) * Math.cos(lon1 - lon2));

        // get the amount of nautical miles between the points
        double nauticalMiles = 60 * Math.toDegrees(angle);

        // convert the nautical miles to statute miles and return it
        return STATUTE_MILES_PER_NAUTICAL_MILE * nauticalMiles;
    }

    /**
     * Sets the latitude of the location
     * @param latitude - the latitude of the location
     */
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    /**
     * Sets the longitude of the location
     * @param longitude - the longitude of the location
     */
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    /**
     * Gets the longitude of the location
     * @return the longitude of the location
     */
    public double getLongitude() {
        return longitude;
    }

    /**
     * Gets the latitude of the location
     * @return the latitude of the location
     */
    public double getLatitude() {
        return latitude;
    }

    /**
     * Converts the location to a string
     * @return the location as a string
     */
    @Override
    public String toString() {

        String latString = String.format("%.4f%s", Math.abs(latitude), latitude >= 0 ? "°N" : "°S");
        String lonString = String.format("%.4f%s", Math.abs(longitude), longitude >= 0 ? "°E" : "°W");

        return latString + ", " + lonString;
    }

}