package tests;

import information.Date;
import information.DynamicSetResources;
import information.Query;

import java.util.Arrays;

public class DynamicTest {
    public static void main (String[] args){

        char day = 1, month = 10, hour = 4, minute = 59, second = 32;
        Date date = new Date(2000, month, day, hour, minute, second);
        DynamicSetResources set = new DynamicSetResources();
        set.addQuery(new Query("pdf", "ismael", date));

        day = 23; month = 2; hour = 15; minute = 32; second = 21;
        Date date1 = new Date(2021, month, day, hour, minute, second);
        set.addQuery(new Query("fichero1", "Manolita", date1));

        day = 23; month = 2; hour = 15; minute = 32; second = 21;
        Date date2 = new Date(2021, month, day, hour, minute, second);
        set.addQuery(new Query("fichero1", "Manolita", date2));

        day = 23; month = 2; hour = 15; minute = 32; second = 21;
        Date date3 = new Date(2021, month, day, hour, minute, second);
        set.addQuery(new Query("fichero2", "Manolita", date3));

        day = 25; month = 2; hour = 19; minute = 32; second = 43;
        Date date4 = new Date(2021, month, day, hour, minute, second);
        set.addQuery(new Query("compus", "Pepe", date4));

        day = 25; month = 2; hour = 1; minute = 32; second = 21;
        Date date5 = new Date(2021, month, day, hour, minute, second);
        set.addQuery(new Query("fichero1", "Pepe", date5));

        day = 27; month = 2; hour = 20; minute = 32; second = 10;
        Date date6 = new Date(2021, month, day, hour, minute, second);
        set.addQuery(new Query("fichero1", "Juan", date6));

        day = 28; month = 5; hour = 9; minute = 2; second = 31;
        Date date7 = new Date(2021, month, day, hour, minute, second);
        set.addQuery(new Query("aspectes", "Alex", date7));

        day = 23; month = 2; hour = 15; minute = 32; second = 51;
        Date date8 = new Date(2021, month, day, hour, minute, second);
        set.addQuery(new Query("fichero1", "Juan", date8));

        day = 29; month = 2; hour = 4; minute = 32; second = 1;
        Date date9 = new Date(2021, month, day, hour, minute, second);
        set.addQuery(new Query("compus", "Alex", date9));


        System.out.println("\nPRUEBA 1");
        System.out.println("Name: Manolita\nResources consulted:"+ Arrays.toString(Arrays.stream(set.getResourcesFromUser("Manolita")).toArray()));

        System.out.println("\nPRUEBA 2");
        System.out.println("Resource: fichero1 Date: 23/02/2021\nUsers:"+ Arrays.toString(set.getUsersFromResourceDate("fichero1", date1)));

        System.out.println("\nPRUEBA 3");
        System.out.println("Resource with most queries: "+set.getMostQueriedResource());

        System.out.println("\nBEFORE DELETING...");
        System.out.println(set);

        System.out.println("\nPRUEBA 4: DELETING ALL THE QUERIES OF FICHERO1 OF THE 23/01/2021");
        day = 23; month = 1; hour = 3; minute = 58; second = 32;
        Date date10 = new Date(2021, month, day, hour, minute, second);
        set.removeQueriesFromResourceDate("fichero1", date10);
        System.out.println(set);

        System.out.println("\nPRUEBA 5: DELETING ALL THE QUERIES OF FICHERO1 AND COMPUS");
        set.removeQueriesFromResource("fichero1");
        set.removeQueriesFromResource("compus");
        System.out.println(set);
    }
}
