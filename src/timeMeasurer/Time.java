package timeMeasurer;

/**
 * This class is in charge of storing the time measures of all the methods of the ADTsetResources for one size.
 * Getter methods are implemented to access these times out of the class. The class counts with two operation methods
 * that add and divide to Time objects. These exist to facilitate calculating the mean values of the Times class. The
 * last toString method is used to get the structure information in String format ready to be printed to a file.
 */
public class Time {
    private long size = 0;
    private long timeAddQuery = 0;
    private long timeRemoveResource = 0;
    private long timeRemoveResourceDate = 0;
    private long timeUsersFromResourceDateRange = 0;
    private long timeUsersFromResource = 0;
    private long timeUsersFromResourceDate = 0;
    private long timeMostQueriedResource = 0;
    private long timeResourcesFromUser = 0;
    private long timeUserConsultedResource = 0;

    /**
     * @param size                           to initialize the corresponding variable
     * @param timeAddQuery                   to initialize the corresponding variable
     * @param timeRemoveResource             to initialize the corresponding variable
     * @param timeRemoveResourceDate         to initialize the corresponding variable
     * @param timeUsersFromResource          to initialize the corresponding variable
     * @param timeUsersFromResourceDate      to initialize the corresponding variable
     * @param timeUsersFromResourceDateRange to initialize the corresponding variable
     * @param timeMostQueriedResource        to initialize the corresponding variable
     * @param timeResourcesFromUser          to initialize the corresponding variable
     * @param timeUserConsultedResource      to initialize the corresponding variable
     */
    public Time(long size, long timeAddQuery, long timeRemoveResource, long timeRemoveResourceDate, long timeUsersFromResource, long timeUsersFromResourceDate, long timeUsersFromResourceDateRange, long timeMostQueriedResource, long timeResourcesFromUser, long timeUserConsultedResource) {
        this.size = size;
        this.timeAddQuery = timeAddQuery;
        this.timeRemoveResource = timeRemoveResource;
        this.timeRemoveResourceDate = timeRemoveResourceDate;
        this.timeUsersFromResource = timeUsersFromResource;
        this.timeUsersFromResourceDate = timeUsersFromResourceDate;
        this.timeUsersFromResourceDateRange = timeUsersFromResourceDateRange;
        this.timeMostQueriedResource = timeMostQueriedResource;
        this.timeResourcesFromUser = timeResourcesFromUser;
        this.timeUserConsultedResource = timeUserConsultedResource;
    }

    /**
     * Empty constructor
     */
    public Time() {

    }

    /**
     * Get size of the set used to calculate the times
     *
     * @return size of the set used to calculate the times
     */
    public long getSize() {
        return size;
    }

    /**
     * Get the measured time to add a query
     *
     * @return this timeAddQuery
     */
    public long getTimeAddQuery() {
        return timeAddQuery;
    }

    /**
     * Get the measured time to remove queries from an introduced resource
     *
     * @return this timeRemoveResource
     */
    public long getTimeRemoveResource() {
        return timeRemoveResource;
    }

    /**
     * Get the measured time to remove queries from an introduced resource and date
     *
     * @return this timeRemoveResourceDate
     */
    public long getTimeRemoveResourceDate() {
        return timeRemoveResourceDate;
    }

    /**
     * Get the measured time to get the users from a resource
     *
     * @return this timeUsersFromResource
     */
    public long getTimeUsersFromResource() {
        return timeUsersFromResource;
    }

    /**
     * Get the measured time to get the users from a resource in a given date
     *
     * @return this timeUsersFromResourceDate
     */
    public long getTimeUsersFromResourceDate() {
        return timeUsersFromResourceDate;
    }

    /**
     * Get the measured time to get the users from a resource in a given date range
     *
     * @return this timeUsersFromResourceDateRange
     */
    public long getTimeUsersFromResourceDateRange() {
        return timeUsersFromResourceDateRange;
    }

    /**
     * Get the measured time to get the most queried resource
     *
     * @return this timeMostQueriedResource
     */
    public long getTimeMostQueriedResource() {
        return timeMostQueriedResource;
    }

    /**
     * Get the measured time to get the resources consulted from a user
     *
     * @return this timeResourcesFromUser
     */
    public long getTimeResourcesFromUser() {
        return timeResourcesFromUser;
    }

    /**
     * Get the measured time to know if a given user has consulted a given resource
     *
     * @return this timeUserConsultedResource
     */
    public long getTimeUserConsultedResource() {
        return timeUserConsultedResource;
    }

    /**
     * Method to add the values of two Time objects. It adds every time with the other object complementary
     *
     * @param time to add
     * @return new Time object with the added resources
     */
    public Time add(Time time) {
        return new Time(size + time.getSize(), timeAddQuery + time.getTimeAddQuery(), timeRemoveResource + time.getTimeRemoveResource(), timeRemoveResourceDate + time.getTimeRemoveResourceDate(),
                timeUsersFromResource + time.getTimeUsersFromResource(), timeUsersFromResourceDate + time.getTimeUsersFromResourceDate(), timeUsersFromResourceDateRange + time.getTimeUsersFromResourceDateRange(),
                timeMostQueriedResource + time.getTimeMostQueriedResource(), timeResourcesFromUser + time.getTimeResourcesFromUser(), timeUserConsultedResource + time.getTimeUserConsultedResource());
    }

    /**
     * Method to divide the values of one Time object with an introduced long number
     *
     * @param number to act as the divider
     * @return new Time object with the divided times results
     */
    public Time divide(long number) {
        return new Time(size / number, timeAddQuery / number, timeRemoveResource / number, timeRemoveResourceDate / number,
                timeUsersFromResource / number, timeUsersFromResourceDate / number, timeUsersFromResourceDateRange / number,
                timeMostQueriedResource / number, timeResourcesFromUser / number, timeUserConsultedResource / number);
    }

    /**
     * This method transforms the data of the Time object to a String format ready to be printed to a file.
     *
     * @return String with the information ready to be printed to a file
     */
    public String toString() {
        return size + "," + timeAddQuery + "," + timeRemoveResource + "," + timeRemoveResourceDate + "," + timeUsersFromResource + "," + timeUsersFromResourceDate + "," + timeUsersFromResourceDateRange + "," + timeMostQueriedResource + "," + timeResourcesFromUser + "," + timeUserConsultedResource + "\n";
    }
}
