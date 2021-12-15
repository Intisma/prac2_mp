package information;

import dynamicInformation.NodeQuery;
import dynamicInformation.NodeResource;
import dynamicInformation.NodeUsers;

public class DynamicSecondSetResources implements ADTsetResources {

    private NodeResource firstRes;
    private NodeUsers firstUser;
    private NodeResource lastRes;
    private NodeUsers lastUser;
    private int numUsers;
    private int numRes;

    public DynamicSecondSetResources() {
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
        NodeQuery q = new NodeQuery(query);
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
            NodeQuery aux = user.getFirstQuery();
            while (aux.getQuery().getDate().moreRecentThan(query.getDate()) && (aux.getNextQueryUser() != null)) {
                aux = aux.getNextQueryUser();
            }
            q.setNextQueryUser(aux.getNextQueryUser());
            q.setPrevQueryUser(aux);
            aux.setNextQueryUser(q);
        }
        if (foundR) {
            NodeQuery aux2 = resource.getFirstQuery();
            while (aux2.getQuery().getDate().moreRecentThan(query.getDate()) && (aux2.getNextQueryRes() != null)) {
                aux2 = aux2.getNextQueryRes();
            }
            q.setNextQueryRes(aux2.getNextQueryRes());
            resource.setCont(resource.getCont() + 1);

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
        if (numRes == 0) return;
        NodeResource auxFirst = firstRes;
        NodeResource node = firstRes;
        NodeQuery query = null, prev, next;
        boolean found = false, foundUser = false;
        //If the resource is the first one
        if (node.getResource().equals(resource)) {
            found = true;
            query = node.getFirstQuery();
        }
        //Loop searching for resource
        while ((node.getNextRes() != null) && (!found)) {
            if (node.getNextRes().getResource().equals(resource)) {
                found = true;
                query = node.getNextRes().getFirstQuery();
            }
            node = node.getNextRes();
        }
        //If the resource exists delete from user list
        if (found) {

            while (query != null) {
                prev = query.getPrevQueryUser();
                next = query.getNextQueryUser();
                if (query.getNextQueryUser() == null) query.getPrevQueryUser().setNextQueryUser(null);
                else query.getNextQueryUser().setPrevQueryUser(prev);
                if (query.getPrevQueryUser() == null) {
                    query.getNextQueryUser().setPrevQueryUser(null);
                    NodeUsers user = firstUser, aux = firstUser;
                    while ((user.getNextUser() != null) && !foundUser) {
                        if (user.getUser().equals(query.getQuery().getUser())) {
                            foundUser = true;
                            aux = user;
                        }
                        user = user.getNextUser();
                    }
                    aux.setFirstQuery(query.getNextQueryUser());
                } else query.getPrevQueryUser().setNextQueryUser(next);
                query = query.getNextQueryRes();
            }
            node.setFirstQuery(null);
            node.setCont(0);
        } else System.out.println("Resource doesn't exists yet!");
        firstRes = auxFirst; //
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
        String[] usersResource = new String[numUsers];
        boolean found = false;
        NodeResource aux = firstRes;
        NodeResource currentResource = firstRes;
        if (currentResource != null) {
            if (currentResource.getResource().equals(resource)) found = true;
        }
        if (currentResource.getNextRes() != null) {
            do {
                currentResource = currentResource.getNextRes();
                if (currentResource.getResource().equals(resource)) found = true;
            } while (currentResource.getNextRes() != null && !found);
        }
        //if resource exists
        if (found) {
            NodeQuery currentQuery = currentResource.getFirstQuery();
            int i = 0;
            while (currentQuery!= null && i < numUsers) {
                usersResource[i] = currentQuery.getQuery().getUser();
                currentQuery = currentQuery.getNextQueryRes();
                i++;
            }
        } else usersResource[0] = "None user consulted this recourse";
        firstRes = aux;
        return usersResource;
    }


    /**
     * Return a list with the users who queried a certain resource in a specific date
     *
     * @param resource info to search
     * @param date     info to search
     */
    @Override
    public String[] getUsersFromResourceDate(String resource, Date date) {
        String[] usersResource = new String[numUsers];
        boolean found = false;
        NodeResource currentResource = firstRes;
        NodeResource aux = firstRes;
        if (currentResource != null) {
            if (currentResource.getResource().equals(resource)) found = true;
        }
        if (currentResource.getNextRes() != null) {
            do {
                currentResource = currentResource.getNextRes();
                if (currentResource.getResource().equals(resource)) found = true;
            } while (currentResource.getNextRes() != null && !found);
        }
        //if resource exists
        if (found) {
            NodeQuery currentQuery = currentResource.getFirstQuery();
            int i = 0;
            while (currentQuery!= null && i < numUsers) {
                if (currentQuery.getQuery().getDate().equals(date)) {
                    usersResource[i] = currentQuery.getQuery().getUser();
                    i++;
                }
                currentQuery = currentQuery.getNextQueryRes();
            }
        } else usersResource[0] = "None user consulted this recourse.";
        firstRes = aux;
        return usersResource;
    }


    /**
     * Return the most queried resource
     */
    @Override
    public String getMostQueriedResource() {
        if (numRes == 0) return null;
        NodeResource mostResource;
        NodeResource aux = firstRes;
        NodeResource currentResource = firstRes;
        mostResource = currentResource;
        if (currentResource.getNextRes() != null) {
            do {
                currentResource = currentResource.getNextRes();
                if (currentResource.getCont() > mostResource.getCont()) mostResource = currentResource;
            } while (currentResource.getNextRes() != null);
        }
        firstRes = aux;
        return mostResource.getResource();
    }


    /**
     * Return a list with the resources queried by a user
     *
     * @param user info to search
     */
    @Override
    public String[] getResourcesFromUser(String user) {
        int max = numUsers * numRes * 5;
        String[] resourcesFromUser = new String[max];
        boolean found = false;
        NodeUsers aux = firstUser;
        NodeUsers currentUser = firstUser;
        if (currentUser != null) {
            if (currentUser.getUser().equals(user)) found = true;
        }
        if (currentUser.getNextUser() != null && !found) {
            do {
                currentUser = currentUser.getNextUser();
                if (currentUser.getUser().equals(user)) found = true;
            } while (currentUser.getNextUser() != null && !found);
        }
        if (found) {
            NodeQuery currentQuery = currentUser.getFirstQuery();
            int i = 0;
            while (currentQuery != null && i < max) {
                if (!belong(resourcesFromUser, currentQuery.getQuery().getResource())) {
                    resourcesFromUser[i] = currentQuery.getQuery().getResource();
                }
                currentQuery = currentQuery.getNextQueryUser();
                i++;
            }
        } else resourcesFromUser[0] = "No resources consulted";
        firstUser = aux;
        return resourcesFromUser;
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
        if (numRes == 0) return null;
        boolean found = false;
        String[] result = new String[numUsers];
        if (firstRes == null) return result;
        NodeResource aux = firstRes;
        NodeResource currentResource = firstRes;
        if (currentResource.getNextRes() != null) {
            do {
                //Loop to find the resource
                if (currentResource.getResource().equals(resource)) {
                    found = true;
                }
                if (!found) currentResource = currentResource.getNextRes();
            } while (currentResource.getNextRes() != null && !found);
            //Loop to note the users who queried the currentResource in a given data range (inRange)
            NodeQuery currentQuery = currentResource.getFirstQuery();
            for (int i = 0; i < currentResource.getCont(); i++) {
                if (currentQuery.getQuery().getDate().inRange(start, end) || currentQuery.getQuery().getDate().equals(start) || currentQuery.getQuery().getDate().equals(end)) {
                    if (!belong(result, currentQuery.getQuery().getUser())) {
                        result[i] = currentQuery.getQuery().getUser();
                    }
                }
                currentQuery = currentQuery.getNextQueryRes();
            }
        }
        firstRes = aux;
        return result;
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
        boolean found = false, foundResource = false;
        if (firstUser == null) return false;    //in case there is no users
        NodeUsers aux = firstUser;
        NodeUsers currentUser = firstUser;
        if (currentUser.getUser().equals(user)) found = true;
        if (currentUser.getNextUser() != null && !found) {
            do {
                currentUser = currentUser.getNextUser();
                if (currentUser.getUser().equals(user)) found = true;
            } while (currentUser.getNextUser() != null && !found);
        }
        if (found) {
            NodeQuery currentQuery = currentUser.getFirstQuery();
            try {
                while (currentQuery!= null && !foundResource) {
                    if (currentQuery.getQuery().getResource().equals(resource)) foundResource = true;
                    currentQuery = currentQuery.getNextQueryUser();
                }
            } catch (NullPointerException exception) {
                return foundResource;
            }
        }
        firstUser = aux;
        return foundResource;
    }


    /**
     * Method toString the DynamicSecondSetResources structure
     * @return string
     */
    public String toString() {
        StringBuilder result = new StringBuilder();
        NodeQuery query;
        NodeUsers user = firstUser;
        result.append("Estructura Dinamica:");
        for (int i = 0; i < numUsers; i++) {
            result.append("\nUser: ").append(user.getUser());
            query = user.getFirstQuery();
            result.append("\nQueries: ");
            while (query != null) {
                result.append(query.getQuery().toString()).append("\n");
                query = query.getNextQueryUser();
            }
            user = user.getNextUser();
        }
        return result.toString();
    }


    /**
     * Return a String to write in the file
     *
     * @return String with the information
     */
    public String toStringFile() {
        StringBuilder result = new StringBuilder();
        NodeQuery query;
        NodeUsers user = firstUser;
        for (int i = 0; i < numUsers; i++) {
            result.append("\nUser: " + user.getUser());
            query = user.getFirstQuery();
            result.append("\nQueries: \n");
            while (query != null) {
                result.append(query.toStringFile() + "\n");
                query = query.getNextQueryUser();
            }
            user = user.getNextUser();
        }
        return result.toString();
    }


    /**
     * Check if the String data belongs to list String[]
     *
     * @param list: data list
     * @param data: data to check if it is in the list
     * @return boolean
     */
    public boolean belong(String[] list, String data) {
        try {
            for (String s : list)
                if (s.equals(data)) {
                    return true;
                }
            return false;
        } catch (NullPointerException exception) {
            return false;
        }
    }

}
