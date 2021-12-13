package staticInformation;

import information.Date;

/**
 * Class to store the information of a query to a resource. It stores the resource consulted and the date
 */
public class UserQuery {
    private final String resource;
    private final Date date;

    /**
     * Constructor
     *
     * @param resource to save
     * @param date     to save
     */
    public UserQuery(String resource, Date date) {
        this.resource = resource;
        this.date = date;

    }

    /**
     * Method to get the resource
     *
     * @return String with the resource's name
     */
    public String getResource() {
        return resource;
    }

    /**
     * Method to get the date
     *
     * @return Date
     */
    public Date getDate() {
        return date;
    }
}
