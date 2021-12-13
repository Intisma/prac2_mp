package information;

/**
 * Class to store the data of a query
 */
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

    /**
     * Method to compare 2 queries by their date
     *
     * @param query to compare
     * @return 0 if equal date, -1 if this object date is more recent and 1 in all others cases
     */
    public int compareTo(Query query) {
        if (date.equals(query.getDate())) return 0;
        if (date.moreRecentThan(query.getDate())) return -1;
        return 1;
    }

    /**
     * Method to say if 2 queries are the same
     *
     * @param query to compare
     * @return true if equal, false if not
     */
    public boolean equals(Query query) {
        return resource.equals(query.getResource()) && user.equals(query.getUser()) && date.equals(query.getDate());
    }

    public String toStringFile() {
        return resource + "; " + date.toString();
    }

    public String toStringFile2() {
        return resource + "; " + user + "; " + date.toString();
    }
}
