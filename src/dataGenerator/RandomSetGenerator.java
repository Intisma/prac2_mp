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

    public ADTsetResources generateSet(boolean dynamic, Users users, Resources resources, Dates dates, int size){
        if(size<1 || size>ADTsetResources.size) size = ADTsetResources.size;
        ADTsetResources set;
        if(dynamic) set = new DynamicSetResources();
        else set = new StaticSecondSetResources();

        for(int index=0; index < size; index++){
            set.addQuery(new Query(resources.getResourceAtIndex(numGenerator.nextInt(resources.getNumResources())), users.getUserAtIndex(numGenerator.nextInt(users.getNumUsers())), dates.getDateAtIndex(numGenerator.nextInt(dates.getNumDates()))));
        }
        return set;
    }
}
