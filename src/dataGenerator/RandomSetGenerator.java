package dataGenerator;

import information.*;

import java.util.Random;

public class RandomSetGenerator {
    private final Random numGenerator;

    /**
     * Constructor
     */
    public RandomSetGenerator() {
        numGenerator = new Random();
    }

    public ADTsetResources generateSet(boolean dynamic){
        ADTsetResources set;
        if(dynamic) set = new StaticSetResources();             //ADD DYNAMIC WHEN MERGED
        else set = new StaticSetResources();

        RandomDateGenerator dateGenerator = new RandomDateGenerator();
        Dates dates = dateGenerator.generateRandomDates();
        RandomUserGenerator userGenerator = new RandomUserGenerator();
        Users users = userGenerator.generateRandomUsers();
        RandomResourceGenerator resourceGenerator = new RandomResourceGenerator();
        Resources resources = resourceGenerator.generateRandomResources();

        for(int index=0; index < ADTsetResources.size; index++){
            set.addQuery(new Query(resources.getResourceAtIndex(numGenerator.nextInt(resources.getNumResources())), users.getUserAtIndex(numGenerator.nextInt(users.getNumUsers())), dates.getDateAtIndex(numGenerator.nextInt(dates.getNumDates()))));
        }
        return set;
    }
}
