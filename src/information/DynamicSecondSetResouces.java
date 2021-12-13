package information;

public class DynamicSecondSetResouces implements ADTsetResources{

    private NodeQueries firstRes;
    private NodeUsers firstUser;
    private NodeQueries lastRes;
    private NodeUsers lastUser;
    private int numUsers;
    private int numRes;

    public DynamicSecondSetResouces() {
        this.firstRes = null;
        this.firstUser = null;
        this.lastRes = null;
        this.lastUser = null;
        this.numRes = 0;
        this.numUsers = 0;
    }

    /**
     * Add query to the data structure
     *
     * @param query info to add
     */
    @Override
    public void addQuery(Query query) {


    }

    /**
     * Remove all the queries of a resource
     *
     * @param resource info to remove
     */
    @Override
    public void removeQueriesFromResource(String resource) {

    }

    /**
     * Remove all the queries of a resource  in a specific date
     *
     * @param resource info to remove
     * @param date info to remove
     */
    @Override
    public void removeQueriesFromResourceDate(String resource, Date date) {

    }

    /**
     * Return a list with the users who queried a certain resource
     *
     * @param resource info to search
     */
    @Override
    public String[] getUsersFromResource(String resource) {
        return new String[0];
    }

    /**
     * Return a list with the users who queried a certain resource in a specific date
     *
     * @param resource info to search
     * @param date info to search
     */
    @Override
    public String[] getUsersFromResourceDate(String resource, Date date) {
        return new String[0];
    }

    /**
     * Return the most queried resource
     */
    @Override
    public String getMostQueriedResource() {
        return null;
    }

    /**
     * Return a list with the resources queried by a user
     *
     * @param user info to search
     */
    @Override
    public String[] getResourcesFromUser(String user) {
        return new String[0];
    }

    /**
     * Return a list with the user who queried a resource in a given date range
     *
     * @param resource to check
     * @param start    date of the range
     * @param end      date of the range
     * @return String[] with the resources
     */
    @Override
    public String[] getUsersFromResourceDateRange(String resource, Date start, Date end) {
        return new String[0];
    }

    /**
     * Return a String to write in the file
     *
     * @return String with the information
     */
    @Override
    public String toStringFile() {
        return null;
    }
}
