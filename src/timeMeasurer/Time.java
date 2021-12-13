package timeMeasurer;

public class Time {
    private final int size;
    private final long timeAddQuery;
    private final long timeRemoveResource;
    private final long timeRemoveResourceDate;
    private final long timeUsersFromResource;
    private final long timeUsersFromResourceDate;
    private final long timeMostQueriedResource;
    private final long timeResourcesFromUser;

    /**
     * Constructor
     *
     * @param timeAddQuery              to initialize
     * @param timeRemoveResource        to initialize
     * @param timeRemoveResourceDate    to initialize
     * @param timeUsersFromResource     to initialize
     * @param timeUsersFromResourceDate to initialize
     * @param timeMostQueriedResource   to initialize
     * @param timeResourcesFromUser     to initialize
     */
    public Time(int size, long timeAddQuery, long timeRemoveResource, long timeRemoveResourceDate, long timeUsersFromResource, long timeUsersFromResourceDate, long timeMostQueriedResource, long timeResourcesFromUser) {
        this.size = size;
        this.timeAddQuery = timeAddQuery;
        this.timeRemoveResource = timeRemoveResource;
        this.timeRemoveResourceDate = timeRemoveResourceDate;
        this.timeUsersFromResource = timeUsersFromResource;
        this.timeUsersFromResourceDate = timeUsersFromResourceDate;
        this.timeMostQueriedResource = timeMostQueriedResource;
        this.timeResourcesFromUser = timeResourcesFromUser;
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
     * Get String with the info of the class to write in file
     *
     * @return String with the information
     */
    public String toString() {
        return size + "," + timeAddQuery + "," + timeRemoveResource + "," + timeRemoveResourceDate + "," + timeUsersFromResource + "," + timeUsersFromResourceDate + "," + timeMostQueriedResource + "," + timeResourcesFromUser + "\n";
    }
}
