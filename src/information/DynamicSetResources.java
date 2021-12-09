package information;

public class DynamicSetResources implements ADTsetResources{
    /**
     * Add query to the data structure
     */
    @Override
    public void addQuery() {

    }

    /**
     * Remove all the queries of a resource
     */
    @Override
    public void removeQueriesFromResource() {

    }

    /**
     * Remove all the queries of a resource  in a specific date
     */
    @Override
    public void removeQueriesFromResourceDate() {

    }

    /**
     * Return a list with the users who queried a certain resource
     */
    @Override
    public String[] getUsersFromResource() {
        return new String[0];
    }

    /**
     * Return a list with the users who queried a certain resource in a specific date
     */
    @Override
    public void getUsersFromResourceDate() {

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
     */
    @Override
    public String[] getResourcesFromUser() {
        return new String[0];
    }
}
