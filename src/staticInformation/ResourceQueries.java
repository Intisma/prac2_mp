package staticInformation;

import information.Date;

public class ResourceQueries {
    public static final int maxQueries = 10000;
    private final ResourceQuery[] list = new ResourceQuery[maxQueries];
    private int numQueries = 0;

    /**
     * Constructor
     */
    public ResourceQueries() {

    }

    /**
     * Remove all the queries that happened in a given date
     *
     * @param date to check
     * @return boolean indicating if there are no more queries
     */
    public boolean removeQueries(Date date) {
        int index = lowerBinarySearchResource(date, 0, numQueries - 1);
        int counter = 0;
        while (index < numQueries && list[index].getDate().equals(date)) {
            counter++;
            index++;
        }
        while (index < numQueries) {
            list[index - counter] = list[index];
            index++;
        }
        numQueries -= counter;
        return (numQueries == 0);
    }

    /**
     * Method to add a new query
     *
     * @param user to add
     * @param date to add
     */
    public void addQuery(String user, Date date) {
        if (numQueries < 1) {
            list[0] = new ResourceQuery(user, date);
            numQueries++;
        } else if (numQueries < maxQueries) {
            int index = lowerBinarySearchResource(date, 0, numQueries - 1);
            if (index < numQueries) {
                for (int shiftIndex = numQueries; shiftIndex > index; shiftIndex--) {
                    list[shiftIndex] = list[shiftIndex - 1];
                }
            }
            list[index] = new ResourceQuery(user, date);
            numQueries++;
        }
    }

    /**
     * Binary search to find the lowest index where a query is located or where it should be inserted.
     *
     * @param date   to find
     * @param lower  index to find
     * @param higher index to find
     * @return found index
     */
    public int lowerBinarySearchResource(Date date, int lower, int higher) {
        if (lower > higher) return lower;
        int index = lower + (higher - lower) / 2;
        if (list[index].getDate().equals(date)) return lowerBinarySearchResource(date, lower, index - 1);
        else if (list[index].getDate().moreRecentThan(date)) return lowerBinarySearchResource(date, index + 1, higher);
        return lowerBinarySearchResource(date, lower, index - 1);
    }

    /**
     * Method to get the users who queried this resource
     *
     * @return users who queried this resource
     */
    public String[] getUsers() {
        Users users = new Users();
        for (int index = 0; index < numQueries; index++) {
            users.addUser(list[index].getUser());
        }
        return users.getUsers();
    }

    /**
     * Method to get the users who queried this resource in a given date
     *
     * @return users who queried this resource
     */
    public String[] getUsersDate(Date date) {
        Users users = new Users();
        int index = lowerBinarySearchResource(date, 0, numQueries - 1);
        while (index < numQueries && list[index].getDate().equals(date)) {
            users.addUser(list[index].getUser());
            index++;
        }
        return users.getUsers();
    }

    /**
     * Method to get the number of queries
     *
     * @return integer with the number of queries
     */
    public int getNumQueries() {
        return numQueries;
    }

    public boolean isUser(String user) {
        for (int index = 0; index < numQueries; index++) {
            if (list[index].getUser().equals(user)) return true;
        }
        return false;
    }

    /**
     * Method to get the users who queried the resource in a given date range
     *
     * @param start of the range
     * @param end   of the range
     * @return list of users
     */
    public String[] getUsersDateRange(Date start, Date end) {
        Users users = new Users();
        int index = lowerBinarySearchResource(end, 0, numQueries);
        while (index < numQueries && list[index].getDate().inRange(start, end)) {
            users.addUser(list[index].getUser());
            index++;
        }
        return users.getUsers();
    }

    /**
     * Method toString
     *
     * @return String with the information
     */
    @Override
    public String toString() {
        StringBuilder total = new StringBuilder();
        for (int index = 0; index < numQueries; index++) {
            total.append("\tUser: ").append(list[index].getUser()).append(" | Date: ").append(list[index].getDate()).append('\n');
        }
        return total.toString();
    }
}