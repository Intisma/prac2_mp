package timeMeasurer;

import dataGenerator.RandomDateGenerator;
import dataGenerator.RandomResourceGenerator;
import dataGenerator.RandomSetGenerator;
import dataGenerator.RandomUserGenerator;
import information.ADTsetResources;
import information.Dates;
import information.Resources;
import information.Users;

public class TimeMeasurer {
    public static void time(int size){
        if(size<100 || size> ADTsetResources.size) size = ADTsetResources.size;
        RandomUserGenerator userGenerator = new RandomUserGenerator();
        Users users = userGenerator.generateRandomUsers(size/100);
        RandomResourceGenerator resourceGenerator = new RandomResourceGenerator();
        Resources resources = resourceGenerator.generateRandomResources(size/100);
        RandomDateGenerator dateGenerator = new RandomDateGenerator();
        Dates dates = dateGenerator.generateRandomDates(size/50);
        RandomSetGenerator setGenerator = new RandomSetGenerator();
        long start = System.currentTimeMillis();
        ADTsetResources set = setGenerator.generateSet(false, users, resources, dates, size);
        long end = System.currentTimeMillis();
    }
}
