package gtfsapp.util;

/**
 * Stores a 24-hour time in hours, minutes, and seconds
 * @author Grant Wilk
 */
public class Time implements Comparable<Time> {

    /**
     * The number of hours
     */
    private int hours;

    /**
     * The number of minutes
     */
    private int minutes;

    /**
     * The number of seconds
     */
    private int seconds;

    /**
     * The number of milliseconds in a second
     */
    private static final int MILLIS_IN_SECOND = 1000;

    /**
     * The number of milliseconds in a minute
     */
    private static final int MILLIS_IN_MINUTE = 60 * MILLIS_IN_SECOND;

    /**
     * The number of milliseconds in an hour
     */
    private static final int MILLIS_IN_HOUR = 60 * MILLIS_IN_MINUTE;

    /**
     * Regular expression for a time stamp in HH:MM:SS form
     */
    private static final String TIME_STAMP_REGEX = "^([4][0-7]|[0-3]?[0-9])[:][0-5]?[0-9][:][0-5]?[0-9]";

    /**
     * Creates a new time from a hours, minutes, and seconds
     * @param hours - the number of hours
     * @param minutes - the number of minutes
     * @param seconds - the number of seconds
     */
    public Time(int hours, int minutes, int seconds) {

        // validate that parameters fit 24 hour time w/ 24 hours of overrun
        if (hours > 48 || hours < 0) {
            throw new IllegalArgumentException("Invalid number of hours.");
        }
        if (minutes > 59 || minutes < 0) {
            throw new IllegalArgumentException("Invalid number of minutes.");
        }
        if (seconds > 59 || seconds < 0) {
            throw new IllegalArgumentException("Invalid number of seconds.");
        }

        // set attributes
        this.hours = hours;
        this.minutes = minutes;
        this.seconds = seconds;

    }

    /**
     * Creates a new time from a time string in HH:MM:SS form
     * @param timeString - the time formatted as a string in HH:MM:SS form
     */
    public Time(String timeString) {

        try {

            // throw an exception if there are no colons
            if (timeString.charAt(2) != ':' || timeString.charAt(5) != ':') {
                throw new IllegalArgumentException("Invalidly formatted time string.");
            }

            // parse hours minutes and seconds
            hours = Integer.parseInt(timeString.substring(0, 2));
            minutes = Integer.parseInt(timeString.substring(3, 5));
            seconds = Integer.parseInt(timeString.substring(6, 8));

            // throw an exception if there are an invalid number of hours, minutes, or seconds
            if (hours > 48 || minutes > 59 || seconds > 59) {
                throw new IllegalArgumentException("Invalidly formatted time string.");
            }

        }

        // throw illegal argument exception if an exception occurred while parsing hours, minutes, and seconds
        catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalidly formatted time string.");
        }

    }

    /**
     * Creates a new time from milliseconds
     * millis - the number of milliseconds from 00:00:00
     */
    public Time(long millis) {

        // convert millis to hours, minutes, and seconds
        int hours = (int) (millis / MILLIS_IN_HOUR);
        millis -= hours * MILLIS_IN_HOUR;

        int minutes = (int) (millis / MILLIS_IN_MINUTE);
        millis -= minutes * MILLIS_IN_MINUTE;

        int seconds = (int) (millis / MILLIS_IN_SECOND);

        // validate that parameters fit 24 hour time w/ 24 hours of overrun
        if (hours > 48 || hours < 0) {
            throw new IllegalArgumentException("Invalid number of hours.");
        }
        if (minutes > 59 || minutes < 0) {
            throw new IllegalArgumentException("Invalid number of minutes.");
        }
        if (seconds > 59 || seconds < 0) {
            throw new IllegalArgumentException("Invalid number of seconds.");
        }

        // set attributes
        this.hours = hours;
        this.minutes = minutes;
        this.seconds = seconds;

    }

    /**
     * Gets the number of hours
     * @return the number of hours
     */
    public int getHours() {
        return hours;
    }

    /**
     * Sets the number of hours
     * @param hours - the number of hours
     */
    public void setHours(int hours) {
        this.hours = hours;
    }

    /**
     * Gets the number of minutes
     * @return the number of minutes
     */
    public int getMinutes() {
        return minutes;
    }

    /**
     * Sets the number of minutes
     * @param minutes - the number of minutes
     */
    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    /**
     * Gets the number of seconds
     * @return the number of seconds
     */
    public int getSeconds() {
        return seconds;
    }

    /**
     * Sets the number of seconds
     * @param seconds - the number of seconds
     */
    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }

    /**
     * Gets the current time in milliseconds
     * @return the current time in milliseconds
     */
    public long getMillis() {
        return hours * MILLIS_IN_HOUR + minutes * MILLIS_IN_MINUTE + seconds * MILLIS_IN_SECOND;
    }

    /**
     * Gets the number of milliseconds in an hour
     * @return the number of milliseconds in an hour
     */
    public static int getMillisInHour() {
        return MILLIS_IN_HOUR;
    }

    /**
     * Gets the number of milliseconds in a minute
     * @return the number of milliseconds in a minute
     */
    public static int getMillisInMinute() {
        return MILLIS_IN_MINUTE;
    }

    /**
     * Gets the number of milliseconds in a second
     * @return the number of milliseconds in a second
     */
    public static int getMillisInSecond() {
        return MILLIS_IN_SECOND;
    }

    /**
     * Gets the regular expression for properly formatted time stamps
     * @return the regular expression for properly formatted time stamps
     */
    public static String getRegex() {
        return TIME_STAMP_REGEX;
    }

    /**
     * Determines whether two objects are equal
     * @param obj - the object to compare to
     */
    @Override
    public boolean equals(Object obj) {

        // return false if the object is not a time object
        if (!(obj instanceof Time)) {
            return false;
        }

        return this.getMillis() == ((Time) obj).getMillis();

    }

    /**
     * Compares this time to another
     * @param time - the time to compare to
     */
    @Override
    public int compareTo(Time time) {
        return Long.compare(this.getMillis(), time.getMillis());
    }

    /**
     * Converts this time to a string
     * @return this time as a string
     */
    @Override
    public String toString() {
        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }

}
