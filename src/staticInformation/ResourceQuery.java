package staticInformation;

import information.Date;

/**
 * Class to store the information related to a query of a resource. It contains the user's name and date of the query
 */
public class ResourceQuery {
    private final String user;
    private final Date date;

    /**
     * Constructor
     *
     * @param user to save
     * @param date to save
     */
    public ResourceQuery(String user, Date date) {
        this.user = user;
        this.date = date;

    }

    /**
     * Method to get the user
     *
     * @return String with the user's name
     */
    public String getUser() {
        return user;
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
