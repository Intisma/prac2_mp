package timeMeasurer;

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
     * Constructor
     *
     * @param timeAddQuery                   to initialize
     * @param timeRemoveResource             to initialize
     * @param timeRemoveResourceDate         to initialize
     * @param timeUsersFromResource          to initialize
     * @param timeUsersFromResourceDate      to initialize
     * @param timeUsersFromResourceDateRange to initialize
     * @param timeMostQueriedResource        to initialize
     * @param timeResourcesFromUser          to initialize
     * @param timeUserConsultedResource      to initialize
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
     * Constructor
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
     * Get this time
     *
     * @return this time
     */
    public long getTimeAddQuery() {
        return timeAddQuery;
    }

    /**
     * Get this time
     *
     * @return this time
     */
    public long getTimeRemoveResource() {
        return timeRemoveResource;
    }

    /**
     * Get this time
     *
     * @return this time
     */
    public long getTimeRemoveResourceDate() {
        return timeRemoveResourceDate;
    }

    /**
     * Get this time
     *
     * @return this time
     */
    public long getTimeUsersFromResourceDateRange() {
        return timeUsersFromResourceDateRange;
    }

    /**
     * Get this time
     *
     * @return this time
     */
    public long getTimeUsersFromResource() {
        return timeUsersFromResource;
    }

    /**
     * Get this time
     *
     * @return this time
     */
    public long getTimeUsersFromResourceDate() {
        return timeUsersFromResourceDate;
    }

    /**
     * Get this time
     *
     * @return this time
     */
    public long getTimeMostQueriedResource() {
        return timeMostQueriedResource;
    }

    /**
     * Get this time
     *
     * @return this time
     */
    public long getTimeResourcesFromUser() {
        return timeResourcesFromUser;
    }

    /**
     * Get this time
     *
     * @return this time
     */
    public long getTimeUserConsultedResource() {
        return timeUserConsultedResource;
    }

    /**
     * Method to add two times
     *
     * @param time to add
     * @return new time with the added resources
     */
    public Time add(Time time) {
        return new Time(size + time.getSize(), timeAddQuery + time.getTimeAddQuery(), timeRemoveResource + time.getTimeRemoveResource(), timeRemoveResourceDate + time.getTimeRemoveResourceDate(),
                timeUsersFromResource + time.getTimeUsersFromResource(), timeUsersFromResourceDate + time.getTimeUsersFromResourceDate(), timeUsersFromResourceDateRange + time.getTimeUsersFromResourceDateRange(),
                timeMostQueriedResource + time.getTimeMostQueriedResource(), timeResourcesFromUser + time.getTimeResourcesFromUser(), timeUserConsultedResource + time.getTimeUserConsultedResource());
    }

    public Time divide(long number) {
        return new Time(size / number, timeAddQuery / number, timeRemoveResource / number, timeRemoveResourceDate / number,
                timeUsersFromResource / number, timeUsersFromResourceDate / number, timeUsersFromResourceDateRange / number,
                timeMostQueriedResource / number, timeResourcesFromUser / number, timeUserConsultedResource / number);
    }

    /**
     * Get String with the info of the class to write in file
     *
     * @return String with the information
     */
    public String toString() {
        return size + "," + timeAddQuery + "," + timeRemoveResource + "," + timeRemoveResourceDate + "," + timeUsersFromResource + "," + timeUsersFromResourceDate + "," + timeUsersFromResourceDateRange + "," + timeMostQueriedResource + "," + timeResourcesFromUser + "," + timeUserConsultedResource + "\n";
    }
}
