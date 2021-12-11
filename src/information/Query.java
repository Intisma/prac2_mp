package information;

public class Query {
    private final String resource;
    private final String user;
    private final Date date;

    /**
     * Constructor
     *
     * @param resource of the query
     * @param user     of the query
     * @param date     of the query
     */
    public Query(String resource, String user, Date date) {
        this.resource = resource;
        this.user = user;
        this.date = date;
    }

    /**
     * Get the resource of the query
     *
     * @return the resource of the query
     */
    public String getResource() {
        return resource;
    }

    /**
     * Get the user of the query
     *
     * @return the user of the query
     */
    public String getUser() {
        return user;
    }

    /**
     * Get the date of the query
     *
     * @return the date of the query
     */
    public Date getDate() {
        return date;
    }

    /**
     * method toString
     *
     * @return String with the information of the class
     */
    public String toString() {
        return "Query: Resource-" + resource + " | User-" + user + " | Date-" + date.toString();
    }
}
