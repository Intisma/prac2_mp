package information;

import dynamicInformation.NodeQuery;
import dynamicInformation.NodeResource;
import dynamicInformation.NodeUsers;

/**
 * Second implementation of the dynamic resource set
 */
public class DynamicSecondSetResources implements ADTsetResources {

    private NodeResource firstRes;
    private NodeUsers firstUser;
    private NodeResource lastRes;
    private NodeUsers lastUser;
    private int numUsers;
    private int numRes;

    /**
     * Constructor
     */
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
            NodeQuery auxPrev = null;
            if (aux == null) {
                user.setFirstQuery(q);
            } else {
                while ((aux != null) && aux.getQuery().getDate().moreRecentThan(query.getDate())) {
                    auxPrev = aux;
                    aux = aux.getNextQueryUser();
                }
                if (aux == null) {
                    q.setPrevQueryUser(auxPrev);
                    auxPrev.setNextQueryUser(q);
                } else if (auxPrev == null) {
                    user.setFirstQuery(q);
                    q.setNextQueryUser(aux);
                    aux.setPrevQueryUser(q);
                } else {
                    q.setNextQueryUser(aux);
                    q.setPrevQueryUser(auxPrev);
                    aux.setPrevQueryUser(q);
                    auxPrev.setNextQueryUser(q);
                }
            }
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
     * Remove all the queries for a resource
     *
     * @param resource info to remove
     */
    @Override
    public void removeQueriesFromResource(String resource) {
        if (numRes == 0) return;
        NodeResource currentResource = firstRes;
        NodeResource auxResource;
        NodeUsers auxUser;
        boolean found = false;
        //Search the resource
        auxResource = searchResource(resource, currentResource);
        if (auxResource != null) {
            found = true;
            currentResource = auxResource;
        }
        //In case resource is found
        if (!found) return;
        NodeQuery currentQuery = currentResource.getFirstQuery();
        while (currentQuery != null) {
            //In case the current Query is the first Query of the user
            if (currentQuery.getPrevQueryUser() == null) {
                //Method to find the user of this current Query
                NodeUsers currentUser = firstUser;
                auxUser = searchUser(currentQuery.getQuery().getUser(), currentUser);
                if (auxUser != null) {
                    currentUser = auxUser;
                }
                //Set the first request pointing to the next one of our current request, which is the one we are going to delete
                currentUser.setFirstQuery(currentQuery.getNextQueryUser());
                if (currentQuery.getNextQueryUser() != null) {
                    currentQuery.getNextQueryUser().setPrevQueryUser(null);
                }
            } else {
                currentQuery.getPrevQueryUser().setNextQueryUser(currentQuery.getNextQueryUser());
            }
            currentQuery = currentQuery.getNextQueryRes();
        }
        currentResource.setFirstQuery(null);
        currentResource.setCont(0);
    }

    /**
     * Remove all the queries for a resource  in a specific date
     *
     * @param resource info to remove
     * @param date     info to remove
     */
    @Override
    public void removeQueriesFromResourceDate(String resource, Date date) {
        if (numRes == 0) return;
        NodeResource currentResource = firstRes;
        boolean found = false;
        //Search the resource
        NodeResource auxResource = searchResource(resource, currentResource);
        if (auxResource != null) {
            found = true;
            currentResource = auxResource;
        }
        //resource found
        if (!found) return;
        NodeQuery currentQuery = currentResource.getFirstQuery();
        NodeQuery auxPrevQuery = null;
        while (currentQuery != null) {
            if (currentQuery.getQuery().getDate().equals(date)) {
                //mirar si el previo de user es null, si es así deberemos buscar usuario.
                if (currentQuery.getPrevQueryUser() == null) {
                    //buscar usuario
                    NodeUsers auxUser;
                    NodeUsers currentUser = firstUser;
                    auxUser = searchUser(currentQuery.getQuery().getUser(), currentUser);
                    if (auxUser != null) currentUser = auxUser;
                    //hemos encontrado user
                    currentUser.setFirstQuery(currentQuery.getNextQueryUser());
                } else {
                    currentQuery.getPrevQueryUser().setNextQueryUser(currentQuery.getNextQueryUser());
                }
                if (currentQuery.equals(currentResource.getFirstQuery()))
                    currentResource.setFirstQuery(currentQuery.getNextQueryRes());
                else if (auxPrevQuery != null) {
                    auxPrevQuery.setNextQueryRes(currentQuery.getNextQueryRes());
                }
                currentResource.setCont(currentResource.getCont() - 1);
            }
            auxPrevQuery = currentQuery;
            currentQuery = currentQuery.getNextQueryRes();
        }
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
        NodeResource currentResource = firstRes;
        NodeResource auxResource = searchResource(resource, currentResource);
        if (auxResource != null) {
            found = true;
            currentResource = auxResource;
        }
        //if resource exists
        if (found) {
            NodeQuery currentQuery = currentResource.getFirstQuery();
            int i = 0;
            while (currentQuery != null && i < numUsers) {
                usersResource[i] = currentQuery.getQuery().getUser();
                currentQuery = currentQuery.getNextQueryRes();
                i++;
            }
        } else usersResource[0] = "None user consulted this recourse";
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
        NodeResource auxResource = searchResource(resource, currentResource);
        if (auxResource != null) {
            found = true;
            currentResource = auxResource;
        }
        //if resource exists
        if (found) {
            NodeQuery currentQuery = currentResource.getFirstQuery();
            int i = 0;
            while (currentQuery != null && i < numUsers) {
                if (currentQuery.getQuery().getDate().equals(date)) {
                    usersResource[i] = currentQuery.getQuery().getUser();
                    i++;
                }
                currentQuery = currentQuery.getNextQueryRes();
            }
        } else usersResource[0] = "None user consulted this recourse.";
        return usersResource;
    }


    /**
     * Return the most queried resource
     */
    @Override
    public String getMostQueriedResource() {
        if (numRes == 0) return null;
        NodeResource mostResource;
        NodeResource currentResource = firstRes;
        mostResource = currentResource;
        if (currentResource.getNextRes() != null) {
            do {
                currentResource = currentResource.getNextRes();
                if (currentResource.getCont() > mostResource.getCont()) mostResource = currentResource;
            } while (currentResource.getNextRes() != null);
        }
        if (mostResource.getCont() == 0) return "";
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
        NodeUsers currentUser = firstUser;
        NodeUsers auxUser = searchUser(user, currentUser);
        if (auxUser != null) {
            currentUser = auxUser;
            found = true;
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
        return resourcesFromUser;
    }


    /**
     * Return a list with the users who queried a resource in a given date range
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
        NodeResource currentResource = firstRes;
        NodeResource auxResource = searchResource(resource, currentResource);
        if (auxResource != null) {
            found = true;
            currentResource = auxResource;
        }
        if (!found) {
            result[0] = "This resource does not exists";
            return result;
        }
        //Loop to note the users who queried the currentResource in a given data range (inRange)
        NodeQuery currentQuery = currentResource.getFirstQuery();
        int index = 0;
        int count = 0;
        while (count < currentResource.getCont()) {
            if (currentQuery.getQuery().getDate().inRange(start, end) || currentQuery.getQuery().getDate().equals(start) || currentQuery.getQuery().getDate().equals(end)) {
                if (!belong(result, currentQuery.getQuery().getUser())) {
                    result[index] = currentQuery.getQuery().getUser();
                    index++;
                }
            }
            currentQuery = currentQuery.getNextQueryRes();
            count++;
        }
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
        boolean foundUser = false, foundResource = false;
        if (firstUser == null) return false;    //in case there is no users
        NodeUsers currentUser = firstUser;
        //Method to find the user of this current Query
        NodeUsers auxUser;
        auxUser = searchUser(user, currentUser);
        if (auxUser != null) {
            currentUser = auxUser;
            foundUser = true;
        }
        if (foundUser) {
            NodeQuery currentQuery = currentUser.getFirstQuery();
            try {
                while (currentQuery != null && !foundResource) {
                    if (currentQuery.getQuery().getResource().equals(resource)) foundResource = true;
                    currentQuery = currentQuery.getNextQueryUser();
                }
            } catch (NullPointerException exception) {
                return foundResource;
            }
        }
        return foundResource;
    }


    /**
     * Method toString the DynamicSecondSetResources structure
     *
     * @return string
     */
    public String toString() {
        StringBuilder result = new StringBuilder();
        NodeQuery query;
        NodeUsers user = firstUser;
        for (int i = 0; i < numUsers; i++) {
            query = user.getFirstQuery();
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
            query = user.getFirstQuery();
            while (query != null) {
                result.append(query.toStringFile()).append("\n");
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


    /**
     * Method to find a resource with a loop through the NodeResources of the DataStructure
     * @param resource: name of the resource
     * @param currentResource: node from which to start the loop
     * @return the resource
     */
    public NodeResource searchResource(String resource, NodeResource currentResource) {
        NodeResource selectResource = null;
        boolean found = false;
        if (currentResource != null) {
            if (currentResource.getResource().equals(resource)) found = true;
        }
        if (currentResource.getNextRes() != null && !found) {
            do {
                currentResource = currentResource.getNextRes();
                if (currentResource.getResource().equals(resource)) found = true;
            } while (currentResource.getNextRes() != null && !found);
        }
        if (found) selectResource = currentResource;
        return selectResource;
    }

    /**
     * Method to find a user with a loop through the NodeUsers of the DataStructure
     *
     * @param user: name of the user
     * @param currentUser: node from which to start the loop
     * @return the user
     */
    public NodeUsers searchUser(String user, NodeUsers currentUser) {
        boolean found = false;
        NodeUsers selectUser = null;
        if (currentUser != null) {
            if (currentUser.getUser().equals(user)) found = true;
        }
        if (currentUser.getNextUser() != null && !found) {
            do {
                currentUser = currentUser.getNextUser();
                if (currentUser.getUser().equals(user)) found = true;
            } while (currentUser.getNextUser() != null && !found);
        }
        if (found) selectUser = currentUser;
        return selectUser;
    }

}
