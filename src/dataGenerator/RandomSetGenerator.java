package dataGenerator;

import information.*;
import staticInformation.Dates;
import staticInformation.Queries;
import staticInformation.Resources;
import staticInformation.Users;

import java.util.Random;

public class RandomSetGenerator {
    private final Random numGenerator;

    /**
     * Constructor
     */
    public RandomSetGenerator() {
        numGenerator = new Random();
    }

    public ADTsetResources generateSet(int type, Users users, Resources resources, Dates dates, int size, Queries queries) {
        if (size < 1 || size > ADTsetResources.size) size = ADTsetResources.size;
        ADTsetResources set;
        switch (type) {
            case 0 -> set = new StaticSetResources();
            case 2 -> set = new DynamicSetResources();
            case 3 -> set = new DynamicSecondSetResouces();
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
}
