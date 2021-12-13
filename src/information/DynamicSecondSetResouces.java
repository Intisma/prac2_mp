package information;

public class DynamicSecondSetResouces implements ADTsetResources {

    private NodeResource firstRes;
    private NodeUsers firstUser;
    private NodeResource lastRes;
    private NodeUsers lastUser;
    private int numUsers;
    private int numRes;

    public DynamicSecondSetResouces() {
        this.firstRes = null;
        this.firstUser = null;
        this.lastRes = null;
        this.lastUser = null;
        this.numRes = 0;
        this.numUsers = 0;
    }

    /**
     * Add query to the data structure
     *
     * @param query info to add
     */
    @Override
    public void addQuery(Query query) {

        boolean foundU = false, foundR = false;
        NodeQueries q = new NodeQueries(query);
        NodeUsers user;
        NodeResource resource;
        if (numUsers == 0) {
            user = new NodeUsers(query.getUser(), q);
            numUsers++;
            firstUser = user;
            lastUser = user;
        } else {
            user = firstUser;
            for (int i = 0; i < numUsers && !foundU; i++) {
                if (user.getUser().equals(query.getUser())) foundU = true;
                else user = user.getNextUser();
            }
            if (!foundU) {
                user = new NodeUsers(query.getUser(), q);
                lastUser.setNextUser(user);
                lastUser = user;
                numUsers++;
            }
        }
        if (numRes == 0) {
            resource = new NodeResource(query.getResource(), q);
            numRes++;
            firstRes = resource;
            lastRes = resource;
        } else {
            resource = firstRes;
            for (int i = 0; i < numRes && !foundR; i++) {
                if (resource.getResource().equals(query.getResource())) foundR = true;
                else resource = resource.getNextRes();
            }
            if (!foundR) {
                resource = new NodeResource(query.getResource(), q);
                lastRes.setNextRes(resource);
                lastRes = resource;
                numRes++;
            }
        }

        if (foundU) {
            NodeQueries aux = user.getFirstQuery();
            while (aux.getQuery().getDate().moreRecentThan(query.getDate()) && (aux.getNextQueryUser() != null)) {
                aux = aux.getNextQueryUser();
            }
            q.setNextQueryUser(aux.getNextQueryUser());
            aux.setNextQueryUser(q);
        }
        if (foundR) {
            NodeQueries aux2 = resource.getFirstRes();
            while (aux2.getQuery().getDate().moreRecentThan(query.getDate()) && (aux2.getNextQueryRes() != null)) {
                aux2 = aux2.getNextQueryRes();
            }
            q.setNextQueryRes(aux2.getNextQueryRes());
            aux2.setNextQueryRes(q);
        }

    }

    /**
     * Remove all the queries of a resource
     *
     * @param resource info to remove
     */
    @Override
    public void removeQueriesFromResource(String resource) {

    }

    /**
     * Remove all the queries of a resource  in a specific date
     *
     * @param resource info to remove
     * @param date     info to remove
     */
    @Override
    public void removeQueriesFromResourceDate(String resource, Date date) {

    }

    /**
     * Return a list with the users who queried a certain resource
     *
     * @param resource info to search
     */
    @Override
    public String[] getUsersFromResource(String resource) {
        return new String[0];
    }

    /**
     * Return a list with the users who queried a certain resource in a specific date
     *
     * @param resource info to search
     * @param date     info to search
     */
    @Override
    public String[] getUsersFromResourceDate(String resource, Date date) {
        return new String[0];
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
     *
     * @param user info to search
     */
    @Override
    public String[] getResourcesFromUser(String user) {
        return new String[0];
    }

    /**
     * Return a list with the user who queried a resource in a given date range
     *
     * @param resource to check
     * @param start    date of the range
     * @param end      date of the range
     * @return String[] with the resources
     */
    @Override
    public String[] getUsersFromResourceDateRange(String resource, Date start, Date end) {
        return new String[0];
    }

    /**
     * Return a String to write in the file
     *
     * @return String with the information
     */
    @Override
    public String toStringFile() {
        return null;
    }

    /**
     * Method to check if a user has consulted a resource
     *
     * @param resource to check
     * @param user     to check
     * @return true if the user has consulted the resource, false if not
     */
    @Override
    public boolean userHasConsultedResource(String resource, String user) {
        return false;
    }

    public String toString() {
        StringBuilder result = new StringBuilder();
        NodeQueries query;
        NodeUsers user = firstUser;
        result.append("Estructura Dinamica:");
        for (int i = 0; i < numUsers; i++) {
            result.append("\nUser: " + user.getUser());
            query = user.getFirstQuery();
            result.append("\nQueries: ");
            while (query != null) {
                result.append(query.getQuery().toString() + "\n");
                query = query.getNextQueryUser();
            }
            user = user.getNextUser();
        }
        return result.toString();
    }
}
