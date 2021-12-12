package information;

public interface ADTsetResources {
    int size = 200000;

    /**
     * Add query to the data structure
     */
    void addQuery(Query query);

    /**
     * Remove all the queries of a resource
     */
    void removeQueriesFromResource(String resource);

    /**
     * Remove all the queries of a resource  in a specific date
     */
    void removeQueriesFromResourceDate(String resource, Date date);

    /**
     * Return a list with the users who queried a certain resource
     */
    String[] getUsersFromResource(String resource);

    /**
     * Return a list with the users who queried a certain resource in a specific date
     */
    String[] getUsersFromResourceDate(String resource, Date date);

    /**
     * Return the most queried resource
     */
    String getMostQueriedResource();

    /**
     * Return a list with the resources queried by a user
     */
    String[] getResourcesFromUser(String user);

    /**
     * Return a String to write in the file
     * @return String with the information
     */
    String toStringFile();
}
