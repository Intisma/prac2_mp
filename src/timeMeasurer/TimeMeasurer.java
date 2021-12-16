package timeMeasurer;

import dataGenerator.RandomDateGenerator;
import dataGenerator.RandomResourceGenerator;
import dataGenerator.RandomSetGenerator;
import dataGenerator.RandomUserGenerator;
import information.ADTsetResources;
import information.Date;
import information.Query;
import staticInformation.Dates;
import staticInformation.Resources;
import staticInformation.Users;

import java.util.Random;

/**
 * Class in charge of measuring time of all types of resource sets.
 */
public class TimeMeasurer {
    /**
     * Class to get the times corresponding a size and type of resource set
     *
     * @param size to assign
     * @param type of resource set
     * @return Time with all the times stored
     */
    public static Time getTime(int size, int type, Resources generatedResources, Users generatedUsers, Dates generatedDates) {
        Random numGenerator = new Random();
        Queries queries = new Queries();
        Queries queries2 = new Queries();

        // Generate sets keeping track of the added queries
        RandomSetGenerator setGenerator = new RandomSetGenerator();
        ADTsetResources set = setGenerator.generateSet(type, generatedResources, generatedUsers, generatedDates, size, queries);
        ADTsetResources set2 = setGenerator.generateSet(type, generatedResources, generatedUsers, generatedDates, size, queries2);

        long start, end;

        // Time to get the users who queried 20000 resources
        start = System.currentTimeMillis();
        for (int index = 0; index < 20000; index++) {
            int random = numGenerator.nextInt(queries.getNumQueries());
            set.getUsersFromResource(queries.getQueryAtIndex(random).getResource());
        }
        end = System.currentTimeMillis();
        long timeUsersFromResource = end - start;

        // Time to get the users who queried 20000 resources on a specified date (we check the earliest date possible to have the worst case possible)
        start = System.currentTimeMillis();
        for (int index = 0; index < 20000; index++) {
            int random = numGenerator.nextInt(queries.getNumQueries());
            set.getUsersFromResourceDate(queries.getQueryAtIndex(random).getResource(), new Date(1990, (char) 1, (char) 1, (char) 0, (char) 0, (char) 0));
        }
        end = System.currentTimeMillis();
        long timeUsersFromResourceDate = end - start;

        //Time to get users from 20000 given date ranges (we check the biggest date range possible to simulate the worst case)
        start = System.currentTimeMillis();
        for (int index = 0; index < 20000; index++) {
            int random = numGenerator.nextInt(queries.getNumQueries());
            set.getUsersFromResourceDateRange(queries.getQueryAtIndex(random).getResource(), new Date(1990, (char) 1, (char) 1, (char) 0, (char) 0, (char) 0), new Date(2500, (char) 12, (char) 31, (char) 23, (char) 59, (char) 59));
        }
        end = System.currentTimeMillis();
        long timeUsersFromResourceDateRange = end - start;

        // Time to get 20000 times the resource queried most times
        start = System.currentTimeMillis();
        for (int index = 0; index < 20000; index++) {
            set.getMostQueriedResource();
        }
        end = System.currentTimeMillis();
        long timeMostQueriedResource = end - start;

        // Time to get resources queried by 4000 users
        start = System.currentTimeMillis();
        for (int index = 0; index < 4000; index++) {
            int random = numGenerator.nextInt(queries.getNumQueries());
            set.getResourcesFromUser(queries.getQueryAtIndex(random).getUser());
        }
        end = System.currentTimeMillis();
        long timeResourcesFromUser = end - start;

        // Time to check 20000 times if a user has consulted a resource
        start = System.currentTimeMillis();
        for (int index = 0; index < 20000; index++) {
            int random = numGenerator.nextInt(queries.getNumQueries());
            set.userHasConsultedResource(queries.getQueryAtIndex(random).getResource(), queries.getQueryAtIndex(numGenerator.nextInt(queries.getNumQueries())).getUser());
        }
        end = System.currentTimeMillis();
        long timeUserConsultedResource = end - start;

        // Time to add 4000 queries to the data set
        start = System.currentTimeMillis();
        for (int index = 0; index < 4000; index++) {
            set.addQuery(new Query(generatedResources.getResourceAtIndex(numGenerator.nextInt(generatedResources.getNumResources())), generatedUsers.getUserAtIndex(numGenerator.nextInt(generatedUsers.getNumUsers())), generatedDates.getDateAtIndex(numGenerator.nextInt(generatedDates.getNumDates()))));
        }
        end = System.currentTimeMillis();
        long timeAddQuery = end - start;

        //Time to remove queries of 4000 resources
        start = System.currentTimeMillis();
        for (int index = 0; index < 4000; index++) {
            int random = numGenerator.nextInt(queries.getNumQueries());
            set.removeQueriesFromResource(queries.getQueryAtIndex(random).getResource());
        }
        end = System.currentTimeMillis();
        long timeRemoveResource = end - start;

        // Time to remove queries of 4000 resources in specified dates
        start = System.currentTimeMillis();
        for (int index = 0; index < 4000; index++) {
            int random = numGenerator.nextInt(queries2.getNumQueries());
            set2.removeQueriesFromResourceDate(queries2.getQueryAtIndex(random).getResource(), queries2.getQueryAtIndex(random).getDate());
        }
        end = System.currentTimeMillis();
        long timeRemoveResourceDate = end - start;

        return new Time(size, timeAddQuery, timeRemoveResource, timeRemoveResourceDate, timeUsersFromResource, timeUsersFromResourceDate, timeUsersFromResourceDateRange, timeMostQueriedResource, timeResourcesFromUser, timeUserConsultedResource);
    }

    /**
     * Method to gather information on how an increasing size impacts the time cost of the different methods
     * performed by the resources sets
     *
     * @param repeats number of repeats
     * @param type    boolean indicating if the implementation of the set
     * @return list of times
     */
    public static Times sizeEvolution(int maxSize, int repeats, int type) {
        // Generate new users, resources and dates to measure time to add new queries
        RandomUserGenerator userGenerator = new RandomUserGenerator();
        Users generatedUsers = userGenerator.generateRandomUsers(20000);
        RandomResourceGenerator resourceGenerator = new RandomResourceGenerator();
        Resources generatedResources = resourceGenerator.generateRandomResources(20000);
        RandomDateGenerator dateGenerator = new RandomDateGenerator();
        Dates generatedDates = dateGenerator.generateRandomDates(20000);

        int addSize = 1;
        if (maxSize <= 3 || maxSize >= ADTsetResources.size) maxSize = ADTsetResources.size - 5000;
        if (repeats < 1) repeats = 5;
        Times times = new Times(repeats);
        for (int size = 2; size <= maxSize; size += addSize) {
            System.out.println("Measuring time for size: " + size);
            for (int rep = 0; rep < repeats; rep++) {
                times.addTime(getTime(size, type, generatedResources, generatedUsers, generatedDates));
            }
            addSize *= 2;
        }
        return times;
    }
}
