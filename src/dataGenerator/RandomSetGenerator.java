package dataGenerator;

import information.*;
import staticInformation.Dates;
import staticInformation.Resources;
import staticInformation.Users;
import timeMeasurer.Queries;

import java.util.Random;

/**
 * Class to generate random sets
 */
public class RandomSetGenerator {
    private final Random numGenerator;

    /**
     * Constructor
     */
    public RandomSetGenerator() {
        numGenerator = new Random();
    }

    /**
     * Set generator
     *
     * @param type      of set
     * @param users     list to use
     * @param resources list to use
     * @param dates     list to use
     * @param size      of the set
     * @param queries   list to keep track of all the queries we add to the set
     * @return ADTsetResources generated
     */
    public ADTsetResources generateSet(int type, Users users, Resources resources, Dates dates, int size, Queries queries) {
        if (size < 1 || size > ADTsetResources.size) size = ADTsetResources.size;
        ADTsetResources set;
        switch (type) {
            case 0 -> set = new StaticSetResources();
            case 2 -> set = new DynamicSetResources();
            case 3 -> set = new DynamicSecondSetResources();
            default -> set = new StaticSecondSetResources();
        }

        while (queries.getNumQueries() < size) {
            queries.addQuery(new Query(resources.getResourceAtIndex(numGenerator.nextInt(resources.getNumResources())), users.getUserAtIndex(numGenerator.nextInt(users.getNumUsers())), dates.getDateAtIndex(numGenerator.nextInt(dates.getNumDates()))));
        }
        for (int index = 0; index < size; index++) {
            set.addQuery(queries.getQueryAtIndex(index));
        }
        return set;
    }

    /**
     * Set generator that generates its own set of user, resources and dates
     *
     * @param type    of implementation
     * @param size    of the set
     * @param queries to keep record of all the queries added to the set
     * @return the constructed set
     */
    public ADTsetResources generateSet(int type, int size, Queries queries) {
        // Generate Sets
        int sizeComponents = size;
        if(sizeComponents < 1000) sizeComponents = 1000;
        if (size < 1 || size > ADTsetResources.size) size = ADTsetResources.size;
        RandomUserGenerator userGenerator = new RandomUserGenerator();
        Users users = userGenerator.generateRandomUsers(sizeComponents / 100);
        RandomResourceGenerator resourceGenerator = new RandomResourceGenerator();
        Resources resources = resourceGenerator.generateRandomResources(sizeComponents / 10);
        RandomDateGenerator dateGenerator = new RandomDateGenerator();
        Dates dates = dateGenerator.generateRandomDates(sizeComponents / 50);
        return this.generateSet(type, users, resources, dates, size, queries);
    }

    /**
     * Set generator that generates its own set of user, resources and dates. It does not need a Queries for those
     * cases in which we do not wish to keep track of the added queries
     *
     * @param type of implementation
     * @param size of the set
     * @return the constructed set
     */
    public ADTsetResources generateSet(int type, int size) {
        // Generate Sets
        Queries queries = new Queries();
        if (size < 100 || size > ADTsetResources.size) size = ADTsetResources.size;
        RandomUserGenerator userGenerator = new RandomUserGenerator();
        Users users = userGenerator.generateRandomUsers(size / 100);
        RandomResourceGenerator resourceGenerator = new RandomResourceGenerator();
        Resources resources = resourceGenerator.generateRandomResources(size / 10);
        RandomDateGenerator dateGenerator = new RandomDateGenerator();
        Dates dates = dateGenerator.generateRandomDates(size / 50);
        return this.generateSet(type, users, resources, dates, size, queries);
    }
}
