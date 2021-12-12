package tests;

import java.util.Scanner;

import dataGenerator.RandomDateGenerator;
import dataGenerator.RandomResourceGenerator;
import dataGenerator.RandomSetGenerator;
import dataGenerator.RandomUserGenerator;
import information.*;


public class GeneralTest {

    static Scanner key = new Scanner(System.in);

    public static void main(String[] args) {

        RandomUserGenerator userGenerator = new RandomUserGenerator();
        Users users = userGenerator.generateRandomUsers(4);
        RandomResourceGenerator resourceGenerator = new RandomResourceGenerator();
        Resources resources = resourceGenerator.generateRandomResources(8);
        RandomDateGenerator dateGenerator = new RandomDateGenerator();
        Dates dates = dateGenerator.generateRandomDates(12);
        RandomSetGenerator setGenerator = new RandomSetGenerator();

        //Selection kind of structure (dynamic or static)
        boolean dynamic;
        int result = -1;
        System.out.println("\nEnter a number to choose static or dynamic memory'");
        while (result != 1 && result != 2) {
            System.out.println("\n\t1 --> 'STATIC MEMORY' | 2 --> 'DYNAMIC MEMORY':  ");
            result = key.nextInt();
        }
        key.close();
        dynamic = result != 1;
        ADTsetResources set = setGenerator.generateSet(dynamic, users, resources, dates, 5);
        test(set, dynamic, users, resources, dates);
        System.out.println("\n\n\t************END OF THE TEST************\n");
    }


    public static void test (ADTsetResources set, boolean dynamic, Users users, Resources resources, Dates dates) {
        if (dynamic) System.out.println("\n\t************STARTING THE TEST WITH DYNAMIC STRUCTURE************");
        else System.out.println("\n\t************STARTING THE TEST WITH STATIC STRUCTURE************\n");
        System.out.println("\nActual structure:");
        System.out.println(set);

        System.out.println("\n\t--------BASIC TESTS TO VERIFY THE CORRECT OPERATION OF ALL THE METHODS--------\n");

        System.out.println("****TEST 1: Get resources from user: " + users.getUserAtIndex(1) + " ****");
        System.out.println("Name:" +users.getUserAtIndex(1) + "\nResources consulted:"+ printStaticString(set.getResourcesFromUser(users.getUserAtIndex(1))));

        System.out.println("\n****TEST 2: Show resource with most queries: ****");
        System.out.println(set.getMostQueriedResource());

        System.out.println("\n****Adding some users with queries from the same resource...");
        System.out.println("\t1. Jordi | mp.pdf | 1/10/2021  4:59:32");
        Date date = new Date(2021, (char)10, (char)1, (char)4, (char)59, (char)32);
        set.addQuery(new Query ("mp.pdf", "Jordi", date));
        System.out.println("\t2. Marta | mp.pdf | 1/10/2021  4:59:32");
        set.addQuery(new Query ("mp.pdf", "Marta", date));
        System.out.println("\t3. Lucas | mp.pdf | 7/10/2021  12:39:22");
        Date date1 = new Date(2021, (char)10, (char)7, (char)12, (char)39, (char)22);
        set.addQuery(new Query ("mp.pdf", "Lucas", date1));
        System.out.println("\t4. Carla | t3.mp4 | 12/01/2003  18:07:03");
        Date date2 = new Date(2003, (char)1, (char)12, (char)18, (char)7, (char)3);
        set.addQuery(new Query ("t3.mp4", "Carla", date2));
        System.out.println("\t5. Marc  | t3.mp4 | 7/10/2021  12:39:22");
        set.addQuery(new Query ("t3.mp4", "Marc", date1));

        System.out.println("\n****TEST 3: Shows the users who consulted a resource ('mp.pdf') on a specific date ('1/10/2021') ****");
        System.out.println("Resource: mp.pdf  Date:" + date + "\nUsers:"+ printStaticString(set.getUsersFromResourceDate("mp.pdf", date)));

        System.out.println("\n****TEST 4: Deleting all the queries of 'mp.pdf' on 01/10/2021 - 4:59:32 ****");
        System.out.println(set);
        System.out.println("\t\tRemoving...\n");
        set.removeQueriesFromResourceDate("mp.pdf", date);
        System.out.println(set);

        System.out.println("\n****TEST 5: Delete all the queries of different resources: ****");
        System.out.println("\tDeleting all the queries of 't3.mp4'...");
        set.removeQueriesFromResource("t3.mp4");
        System.out.println(set);
        System.out.println("\tDeleting all the queries of 'mp.pdf'...");
        set.removeQueriesFromResource("mp.pdf");
        System.out.println(set);


        System.out.println("\n\t--------TESTS FOR USERS AND RESOURCES THAT DO NOT EXIST--------\n");

        System.out.println("****TEST 6: Get resources of user: Potato ***");
        System.out.println("Name: Potato\nResources consulted:"+ printStaticString(set.getResourcesFromUser("Potato")));

        System.out.println("\n****TEST 7: Shows the users who consulted a resource ('null') on a specific date ('1/10/2021') ****");
        System.out.println("Resource: null  Date:" + date + "\nUsers:"+ printStaticString(set.getUsersFromResourceDate("null", date)));

        System.out.println("\n****TEST 8: Delete all the queries of 'null' on 01/10/2021 - 4:59:32 ****");
        System.out.println(set);
        System.out.println("\t\tRemoving...\n");
        set.removeQueriesFromResourceDate("null", date);
        System.out.println(set);

        System.out.println("\n\t------------OTHER TESTS------------\n");
        System.out.println("\n****TEST 9: Delete all the queries of resource: 'null' ****");
        set.removeQueriesFromResource("null");
        System.out.println(set);


        System.out.println("\n****TEST 10: Get resources from different ussers... ****");
        int i;
        for (i=0; i<5; i++) {
            System.out.println("- " +i+ ": Get resources from user: " + users.getUserAtIndex(i));
            System.out.println("Resources consulted: "+ printStaticString(set.getResourcesFromUser(users.getUserAtIndex(i))));
        }

        System.out.println("\n\t\tACTUAL SET :");
        System.out.println(set);

        System.out.println("\n****TEST 11: Delete all the queries of ALL resources...****");
        for (i=0; i<5; i++) {
            System.out.println("- " +i+ ": Deleting all the queries of resource: " + set.getMostQueriedResource() + "...");
            set.removeQueriesFromResource(set.getMostQueriedResource());
        }
        System.out.println("\n\t\tACTUAL SET:");
        System.out.println(set);

        System.out.println("****TEST 12: Find the most queried resource in a empty TAD****");
        if (set.getMostQueriedResource()!=null) System.out.println(set.getMostQueriedResource());
        else System.out.println("\tEmpty TAD");


    }



    public static String printStaticString (String[] data) {
        StringBuilder information = new StringBuilder("[ ");
        for(int index=0; index < data.length && data[index]!=null; index++){
            information.append(data[index]).append("  ");
        }
        information.append("]");
        return information.toString();
    }

}
