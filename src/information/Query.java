package information;

public class Query {
    private String resource;
    private String user;
    private Date date;

    /**
     * Constructor
     *
     * @param resource
     * @param user
     * @param date
     */
    public Query(String resource, String user, Date date) {
        this.resource = resource;
        this.user = user;
        this.date = date;
    }

    public String getResource() {
        return resource;
    }

    public String getUser() {
        return user;
    }

    public Date getDate() {
        return date;
    }

    public String toString() {
        return "Query: R-" + resource + " | U-" + user + " | D-" + date.toString();
    }
}
