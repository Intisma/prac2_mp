package information;

public interface ADTsetResources {

    /**
     * Add query to the data structure
     */
    void addQuery();

    /**
     * Remove all the queries of a resource
     */
    void removeQueriesFromResource();

    /**
     * Remove all the queries of a resource  in a specific date
     */
    void removeQueriesFromResourceDate();

    /**
     * Return a list with the users who queried a certain resource
     */
    String[] getUsersFromResource();

    /**
     * Return a list with the users who queried a certain resource in a specific date
     */
    void getUsersFromResourceDate();

    /**
     * Return the most queried resource
     */
    String getMostQueriedResource();

    /**
     * Return a list with the resources queried by a user
     */
    String[] getResourcesFromUser();
}
