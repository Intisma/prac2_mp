package staticInformation;

import information.Date;

public class UserQueries {
    public static final int maxQueries = 10000;
    String user;
    private final UserQuery[] list = new UserQuery[maxQueries];
    private int numQueries = 0;

    /**
     * Constructor
     */
    public UserQueries(String user) {
        this.user = user;
    }

    /**
     * Method to add a new query
     *
     * @param resource to add
     * @param date     to add
     */
    public void addQuery(String resource, Date date) {
        if (numQueries < 1) {
            list[0] = new UserQuery(resource, date);
            numQueries++;
        } else if (numQueries < maxQueries) {
            int index = lowerBinarySearchResource(date, 0, numQueries - 1);
            if (index < numQueries) {
                for (int shiftIndex = numQueries; shiftIndex > index; shiftIndex--) {
                    list[shiftIndex] = list[shiftIndex - 1];
                }
            }
            list[index] = new UserQuery(resource, date);
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
     * Method to get the resources who queried this resource
     *
     * @return users who queried this resource
     */
    public String[] getResources() {
        String[] resources = new String[maxQueries];
        for (int index = 0; index < numQueries; index++) {
            resources[index] = list[index].getResource();
        }
        return resources;
    }

    /**
     * Method to get the number of queries
     *
     * @return integer with the number of queries
     */
    public int getNumQueries() {
        return numQueries;
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
            total.append("\tResource: ").append(list[index].getResource()).append(" | Date: ").append(list[index].getDate()).append('\n');
        }
        return total.toString();
    }
}
