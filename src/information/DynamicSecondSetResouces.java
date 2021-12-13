package information;

public class DynamicSecondSetResouces implements ADTsetResources{

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
        boolean firstUs =false, firstResorurce = false;
        if (numUsers == 0) {
            NodeUsers user = new NodeUsers(query.getUser(), new NodeQueries(query));
            firstUser = user;
            lastUser = user;
            firstUs = true;
            numUsers++;
        }
        if (numRes == 0) {
            NodeResource resource = new NodeResource(query.getResource(), new NodeQueries(query));
            firstRes = resource;
            lastRes = resource;
            firstResorurce = true;
            numRes++;
        }
        NodeQueries nodoQuery = new NodeQueries(query);
        NodeResource actual = firstRes;
        int i=0;
        boolean found = false;
        //busqueda de recursos
        while ((!found) && (i<numRes)) {
            if (actual.getResource().equals(query.getResource())) {
                found=true;
            }
            i++;
        } //
        //si hemos encontrado el recurso lo colocamos en la lista de recurso que le toque
        if (found && !firstResorurce) {
            if (actual.getFirstQuery()==null) {
                actual.setFirstQuery(nodoQuery);
            }
            else {
                NodeQueries actualQuery = actual.getFirstQuery();
                NodeQueries tempPrev = actualQuery;
                while (query.getDate().moreRecentThan(actualQuery.getQuery().getDate()) && (actualQuery.getNextQueryRes()!=null)) { //no actualquery!=null sino que haremos que el siguiente sea null, ver si entonces tneemos que cambiar algo de lo de después
                    tempPrev = actualQuery;
                    actualQuery = actualQuery.getNextQueryRes();
                }
                nodoQuery.setNextQueryRes(actualQuery);
                tempPrev.setNextQueryRes(nodoQuery);
            }
        }
        //no existe recurso en la lista, lo creamos
        else {
            NodeResource newRes = new NodeResource(query.getResource(), nodoQuery);
            lastRes.setNextRes(newRes);
            lastRes = newRes;
        }

        //busqueda de USUARIOS
        found = false;
        NodeUsers currentUser = firstUser;
        while (!found && (i<numUsers)) {
        if (currentUser.getUser().equals(query.getUser())) {
                found=true;
            }
        i++;
        }
        //si hemos encontrado el recurso lo colocamos en la lista de recurso que le toque
        if (found && firstUs) {
            if (currentUser.getFirstQuery()==null) {
                currentUser.setFirstQuery(nodoQuery);
            }
            else {
                NodeQueries actualQuery = currentUser.getFirstQuery();
                NodeQueries tempPrev = actualQuery;
                while (query.getDate().moreRecentThan(actualQuery.getQuery().getDate()) && (actualQuery.getNextQueryUser()!=null)) {
                    tempPrev = actualQuery;
                    actualQuery = actualQuery.getNextQueryUser();
                }
                nodoQuery.setNextQueryUser(actualQuery);
                tempPrev.setNextQueryUser(nodoQuery);
            }
        }
        //no existe usuario en la lista, lo creamos
        else {
            NodeUsers newUser = new NodeUsers(query.getUser(), nodoQuery);
            lastUser.setNextUser(newUser);
            lastUser = newUser;
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
     * @param date info to remove
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
     * @param date info to search
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

    public String toString() {
        StringBuilder result = new StringBuilder();
        //result.append("firstUser: " + firstUser);
        NodeQueries actual = firstUser.getFirstQuery();
        NodeUsers actualUser = firstUser;
        for (int i=0; i<numUsers; i++) {
            while (actual.getNextQueryRes() != null) {
                result.append(actual.getQuery().toString());
                actual = actual.getNextQueryRes();
            }
            actualUser = actualUser.getNextUser();
            actual = actualUser.getFirstQuery();
        }
        return result.toString();
    }
}
