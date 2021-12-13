package information;

import staticInformation.ResourceAndQueries;
import staticInformation.UserQueries;

public class StaticSecondSetResources implements ADTsetResources {
    private final int size = 200000;
    private final ResourceAndQueries[] resources = new ResourceAndQueries[size / 10];
    private int numResources = 0;

    /**
     * Constructor
     */
    public StaticSecondSetResources() {

    }


    /**
     * Binary search to find the lowest index where a resource is located or where it should be inserted.
     *
     * @param resource to find
     * @param lower    index to find
     * @param higher   index to find
     * @return found index
     */
    public int lowerBinarySearchResource(String resource, int lower, int higher) {
        if (lower > higher) return lower;
        int index = lower + (higher - lower) / 2;
        int compare = resources[index].getResource().compareTo(resource);
        if (compare == 0) return lowerBinarySearchResource(resource, lower, index - 1);
        if (compare < 0) return lowerBinarySearchResource(resource, index + 1, higher);
        return lowerBinarySearchResource(resource, lower, index - 1);
    }

    /**
     * Add query to the data structure
     */
    @Override
    public void addQuery(Query query) {
        int index;
        if (numResources == 0) {
            resources[0] = new ResourceAndQueries(query);
            numResources++;
        } else if (numResources < size) {
            index = lowerBinarySearchResource(query.getResource(), 0, numResources - 1);
            if (index < numResources) {
                if (resources[index].getResource().equals(query.getResource()))
                    resources[index].addQuery(query);
                else {
                    for (int shiftIndex = numResources; shiftIndex > index; shiftIndex--) {
                        resources[shiftIndex] = resources[shiftIndex - 1];
                    }
                    resources[index] = new ResourceAndQueries(query);
                    numResources++;
                }
            } else {
                resources[index] = new ResourceAndQueries(query);
                numResources++;
            }
        }
    }

    /**
     * Remove all the queries of a resource
     */
    @Override
    public void removeQueriesFromResource(String resource) {
        int index;
        if (numResources > 0) {
            index = lowerBinarySearchResource(resource, 0, numResources - 1);
            if ((index < numResources) && (resources[index].getResource().equals(resource))) {
                for (int shiftIndex = index; shiftIndex < numResources - 1; shiftIndex++) {
                    resources[shiftIndex] = resources[shiftIndex + 1];
                }
                numResources--;
            }
        }
    }

    /**
     * Remove all the queries of a resource  in a specific date
     */
    @Override
    public void removeQueriesFromResourceDate(String resource, Date date) {
        int index;
        if (numResources > 0) {
            index = lowerBinarySearchResource(resource, 0, numResources - 1);
            if ((index < numResources) && (resources[index].getResource().equals(resource))) {
                if (resources[index].removeQueries(date)) {
                    for (int shiftIndex = index; shiftIndex < numResources - 1; shiftIndex++) {
                        resources[shiftIndex] = resources[shiftIndex + 1];
                    }
                }
            }
        }
    }

    /**
     * Return a list with the users who queried a certain resource
     */
    @Override
    public String[] getUsersFromResource(String resource) {
        int index;
        if (numResources > 0) {
            index = lowerBinarySearchResource(resource, 0, numResources - 1);
            if ((index < numResources) && (resources[index].getResource().equals(resource))) {
                return resources[index].getUsers();
            }
        }
        return null;
    }

    /**
     * Return a list with the users who queried a certain resource in a specific date
     */
    @Override
    public String[] getUsersFromResourceDate(String resource, Date date) {
        int index;
        if (numResources > 0) {
            index = lowerBinarySearchResource(resource, 0, numResources - 1);
            if ((index < numResources) && (resources[index].getResource().equals(resource))) {
                return resources[index].getUsersDate(date);
            }
        }
        return null;
    }

    /**
     * Return the most queried resource
     */
    @Override
    public String getMostQueriedResource() {
        if (numResources > 0) {
            int maxQueries = resources[0].getQueries().getNumQueries();
            String maxResource = resources[0].getResource();
            for (int index = 0; index < numResources; index++) {
                if (maxQueries < resources[index].getQueries().getNumQueries()) {
                    maxQueries = resources[index].getQueries().getNumQueries();
                    maxResource = resources[index].getResource();
                }
            }
            return maxResource;
        }
        return null;

    }

    /**
     * Return a list with the resources queried by a user
     */
    @Override
    public String[] getResourcesFromUser(String user) {
        UserQueries userQueries = new UserQueries(user);
        for (int index = 0; index < numResources; index++) {
            resources[index].getResourcesFromUser(userQueries);
        }
        return userQueries.getResources();
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
        if (start.moreRecentThan(end)) {
            Date aux = start;
            start = end;
            end = aux;
        }
        int index;
        if (numResources > 0) {
            index = lowerBinarySearchResource(resource, 0, numResources - 1);
            if ((index < numResources) && (resources[index].getResource().equals(resource))) {
                return resources[index].getUsersDateRange(start, end);
            }
        }
        return null;
    }


    /**
     * toString method to print the data
     *
     * @return String with the data
     */
    @Override
    public String toString() {
        StringBuilder total = new StringBuilder();
        for (int index = 0; index < numResources; index++) {
            total.append(resources[index].toString());
        }
        return total.toString();
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
        int index = lowerBinarySearchResource(resource, 0, numResources - 1);
        if (index < numResources && resources[index].getResource().equals(resource)) {
            return resources[index].isUser(user);
        }
        return false;
    }

    /**
     * Return a String to write in the file
     *
     * @return String with the information
     */
    @Override
    public String toStringFile() {
        return "ismael";
    }
}
