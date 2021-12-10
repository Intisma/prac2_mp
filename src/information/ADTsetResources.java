package information;

import java.util.ArrayList;
import java.util.List;

public interface ADTsetResources {

    /**
     * Add query to the data structure
     */
    void addQuery(String date, String hour, String alias, String resource);

    /**
     * Remove all the queries of a resource
     */
    void removeQueriesFromResource(String resource);

    /**
     * Remove all the queries of a resource  in a specific date
     */
    void removeQueriesFromResourceDate(String time, String res);

    /**
     * Return a list with the users who queried a certain resource
     */
    ArrayList<String> getUsersFromResource(String resource);

    /**
     * Return a list with the users who queried a certain resource in a specific date
     */
    ArrayList<String> getUsersFromResourceDate(String date, String resource);

    /**
     * Return the most queried resource
     */
    String getMostQueriedResource();

    /**
     * Return a list with the resources queried by a user
     * @return
     */
    ArrayList<String> getResourcesFromUser(String name);
}
