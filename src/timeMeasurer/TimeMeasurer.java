package timeMeasurer;

import dataGenerator.RandomDateGenerator;
import dataGenerator.RandomResourceGenerator;
import dataGenerator.RandomSetGenerator;
import dataGenerator.RandomUserGenerator;
import information.ADTsetResources;
import information.Query;
import staticInformation.Dates;
import staticInformation.Queries;
import staticInformation.Resources;
import staticInformation.Users;

import java.util.Random;

public class TimeMeasurer {
    public static Time getTime(int size, boolean dynamic) {
        Random numGenerator = new Random();
        Queries queries = new Queries();
        Queries queries2 = new Queries();

        // Generate Sets
        if (size < 100 || size > ADTsetResources.size) size = ADTsetResources.size;
        RandomUserGenerator userGenerator = new RandomUserGenerator();
        Users users = userGenerator.generateRandomUsers(size / 100);
        RandomResourceGenerator resourceGenerator = new RandomResourceGenerator();
        Resources resources = resourceGenerator.generateRandomResources(size / 10);
        RandomDateGenerator dateGenerator = new RandomDateGenerator();
        Dates dates = dateGenerator.generateRandomDates(size / 50);
        RandomSetGenerator setGenerator = new RandomSetGenerator();
        ADTsetResources set = setGenerator.generateSet(dynamic, users, resources, dates, size, queries);
        ADTsetResources set2 = setGenerator.generateSet(dynamic, users, resources, dates, size, queries2);

        // Generate new users, resources and dates to measure time to add new queries
        Users newUsers = userGenerator.generateRandomUsers(1000);
        Resources newResources = resourceGenerator.generateRandomResources(1000);
        Dates newDates = dateGenerator.generateRandomDates(1000);

        long start, end;

        // Time to add 4000 queries to the data set
        start = System.currentTimeMillis();
        for (int index = 0; index < 4000; index++) {
            set.addQuery(new Query(newResources.getResourceAtIndex(numGenerator.nextInt(newResources.getNumResources())), newUsers.getUserAtIndex(numGenerator.nextInt(newUsers.getNumUsers())), newDates.getDateAtIndex(numGenerator.nextInt(newDates.getNumDates()))));
        }
        end = System.currentTimeMillis();
        long timeAddQuery = end - start;

        // Time to get the users who queried 4000 resources
        start = System.currentTimeMillis();
        for (int index = 0; index < 4000; index++) {
            int random = numGenerator.nextInt(queries.getNumQueries());
            set.getUsersFromResource(queries.getQueryAtIndex(random).getResource());
        }
        end = System.currentTimeMillis();
        long timeUsersFromResource = end - start;

        // Time to get the users who queried 4000 resources on a specified date
        start = System.currentTimeMillis();
        for (int index = 0; index < 4000; index++) {
            int random = numGenerator.nextInt(queries.getNumQueries());
            set.getUsersFromResourceDate(queries.getQueryAtIndex(random).getResource(), queries.getQueryAtIndex(random).getDate());
        }
        end = System.currentTimeMillis();
        long timeUsersFromResourceDate = end - start;

        //Time to get users from 4000 given date ranges
        start = System.currentTimeMillis();
        for (int index = 0; index < 4000; index++) {
            int random = numGenerator.nextInt(queries.getNumQueries());
            set.getUsersFromResourceDateRange(queries.getQueryAtIndex(random).getResource(), queries.getQueryAtIndex(random).getDate(), dates.getDateAtIndex(numGenerator.nextInt(dates.getNumDates())));
        }
        end = System.currentTimeMillis();
        long timeUsersFromResourceDateRange = end - start;

        // Time to get 4000 times the resource queried most times
        start = System.currentTimeMillis();
        for (int index = 0; index < 4000; index++) {
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

        // Time to check if a user has consulted a resource
        start = System.currentTimeMillis();
        for (int index = 0; index < 4000; index++) {
            int random = numGenerator.nextInt(queries.getNumQueries());
            set.userHasConsultedResource(queries.getQueryAtIndex(random).getResource(), queries.getQueryAtIndex(random).getUser());
        }
        end = System.currentTimeMillis();
        long timeUserConsultedResource = end - start;

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
            set2.getUsersFromResourceDate(queries2.getQueryAtIndex(random).getResource(), queries2.getQueryAtIndex(random).getDate());
        }
        end = System.currentTimeMillis();
        long timeRemoveResourceDate = end - start;

        return new Time(size, timeAddQuery, timeRemoveResource, timeRemoveResourceDate, timeUsersFromResource, timeUsersFromResourceDate, timeUsersFromResourceDateRange, timeMostQueriedResource, timeResourcesFromUser, timeUserConsultedResource);
    }

    /**
     * Method to study how an increasing size impacts the time
     *
     * @param repeats number of repeats
     * @param dynamic boolean indicating if the set should be dynamic or static
     * @return list of times
     */
    public static Times sizeEvolution(int maxSize, int repeats, boolean dynamic) {
        if (maxSize <= 5000 || maxSize >= ADTsetResources.size) maxSize = ADTsetResources.size - 5000;
        if (repeats < 1) repeats = 5;
        Times times = new Times(repeats);
        for (int size = 5000; size <= maxSize; size += 5000) {
            System.out.println("Measuring time for size: " + size);
            for (int rep = 0; rep < repeats; rep++) {
                times.addTime(getTime(size, dynamic));
            }
        }
        return times;
    }
}
