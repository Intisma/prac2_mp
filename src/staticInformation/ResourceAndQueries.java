package staticInformation;

import information.Date;
import information.Query;

public class ResourceAndQueries {
    private final String resource;
    private final ResourceQueries resourceQueries = new ResourceQueries();

    /**
     * Constructor
     *
     * @param information to save
     */
    public ResourceAndQueries(Query information) {
        resource = information.getResource();
        resourceQueries.addQuery(information.getUser(), information.getDate());
    }

    /**
     * Method to get the queries of the resource
     *
     * @return ResourceQuery
     */
    public ResourceQueries getQueries() {
        return resourceQueries;
    }

    /**
     * Method to add another query
     *
     * @param query to add
     */
    public void addQuery(Query query) {
        resourceQueries.addQuery(query.getUser(), query.getDate());

    }

    /**
     * Method to remove all the queries of the resource in a given date
     *
     * @param date to check
     * @return boolean indicating if the resource has no queries left
     */
    public boolean removeQueries(Date date) {
        return resourceQueries.removeQueries(date);
    }

    /**
     * Method to get the users who queried this resource
     *
     * @return list of String with the users
     */
    public String[] getUsers() {
        return resourceQueries.getUsers();
    }

    /**
     * Method ti get the users who queried the resource in the given date
     *
     * @param date to check
     * @return list of String with the users
     */
    public String[] getUsersDate(Date date) {
        return resourceQueries.getUsersDate(date);
    }

    /**
     * Get the resource
     *
     * @return the resource
     */
    public String getResource() {
        return resource;
    }

    /**
     * Method to check if a user has queried this resource
     *
     * @param user to check
     * @return true if the user has queried the resource. False if not
     */
    public boolean isUser(String user) {
        return resourceQueries.isUser(user);
    }

    /**
     * Method to get the users who queried the resource in a given date range
     *
     * @param start of the range
     * @param end   of the range
     * @return list of the users
     */
    public String[] getUsersDateRange(Date start, Date end) {
        return resourceQueries.getUsersDateRange(start, end);
    }

    /**
     * Add to a list of resources of a user if the resource has been queried by him
     */
    public void getResourcesFromUser(UserQueries userQueries) {
        resourceQueries.getResourcesFromUser(userQueries, resource);
    }

    /**
     * Method toString
     *
     * @return String with the information
     */
    public String toString() {
        return "ResourceAndQueries: " + resource + "\n" + resourceQueries;
    }

    /**
     * Method to get a String used to write the results in a file
     *
     * @return String with the information
     */
    public String toStringFile() {
        return "Resource: " + resource + "\n" + resourceQueries.toStringFile();
    }
}
