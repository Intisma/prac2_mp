package tests;

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
import timeMeasurer.Queries;

import java.util.InputMismatchException;
import java.util.Scanner;


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
        int type = -1;
        System.out.println("\nEnter a number to choose static or dynamic memory'");
        while (type < 0 || type > 3) {
            System.out.print("""
                    \t0 --> 'STATIC MEMORY FIRST IMPLEMENTATION'  | 1 --> 'STATIC MEMORY SECOND IMPLEMENTATION'\s
                    \t2 --> 'DYNAMIC MEMORY FIRST IMPLEMENTATION' | 3 --> 'DYNAMIC MEMORY SECOND IMPLEMENTATION': \s""");
            type = readInt("\n\tINTRODUCE ONE OPTION, ", 0, 3);
        }
        key.close();
        ADTsetResources set = setGenerator.generateSet(type, resources, users, dates, 5, new Queries());
        test(set, type, users);
        System.out.println("\n\n\t************END OF THE TEST************\n");
    }


    public static void test(ADTsetResources set, int type, Users users) {
        switch (type) {
            case 0 -> System.out.println("\n\t************STARTING THE TEST WITH FIRST IMPLEMENTATION OF STATIC STRUCTURE************\n");
            case 2 -> System.out.println("\n\t************STARTING THE TEST WITH FIRST IMPLEMENTATION OF DYNAMIC STRUCTURE************\n");
            case 3 -> System.out.println("\n\t************STARTING THE TEST WITH SECOND IMPLEMENTATION OF DYNAMIC STRUCTURE************\n");
            default -> System.out.println("\n\t************STARTING THE TEST WITH SECOND IMPLEMENTATION OF STATIC STRUCTURE************\n");
        }
        System.out.println("\nActual structure:");
        System.out.println(set);

        System.out.println("\n\t--------BASIC TESTS TO VERIFY THE CORRECT OPERATION OF ALL THE METHODS--------\n");

        System.out.println("****TEST 1: Get resources from user: " + users.getUserAtIndex(1) + " ****");
        System.out.println("Name:" + users.getUserAtIndex(1) + "\nResources consulted:" + listToString(set.getResourcesFromUser(users.getUserAtIndex(1))));

        System.out.println("\n****TEST 2: Show resource with most queries: ****");
        System.out.println(set.getMostQueriedResource());

        System.out.println("\n****Adding some users with queries from the same resource...");
        System.out.println("\t1. Jessica  | t3.mp4 | 6/10/2021  12:03:00");
        Date date0 = new Date(2021, (char) 10, (char) 6, (char) 12, (char) 3, (char) 0);
        set.addQuery(new Query("t3.mp4", "Jessica", date0));
        System.out.println("\t2. Jordi | mp.pdf | 1/10/2020  4:59:32");
        Date date = new Date(2020, (char) 10, (char) 1, (char) 4, (char) 59, (char) 32);
        set.addQuery(new Query("mp.pdf", "Jordi", date));
        System.out.println("\t3. Marta | mp.pdf | 1/10/2021  4:59:32");
        set.addQuery(new Query("mp.pdf", "Marta", date));
        System.out.println("\t4. Lucas | mp.pdf | 7/10/2021  12:39:22");
        Date date1 = new Date(2021, (char) 10, (char) 7, (char) 12, (char) 39, (char) 22);
        set.addQuery(new Query("mp.pdf", "Lucas", date1));
        System.out.println("\t5. Carla | t3.mp4 | 12/01/2003  18:07:03");
        Date date2 = new Date(2003, (char) 1, (char) 12, (char) 18, (char) 7, (char) 3);
        set.addQuery(new Query("t3.mp4", "Carla", date2));
        System.out.println("\t6. Marc  | t3.mp4 | 7/10/2021  12:39:22");
        set.addQuery(new Query("t3.mp4", "Marc", date1));
        System.out.println("\t7. Jessica  | mp.pdf | 6/10/2021  12:03:00");
        Date date3 = new Date(1999, (char) 1, (char) 2, (char) 0, (char) 0, (char) 0);
        set.addQuery(new Query("mp.pdf", "Jessica", date3));

        System.out.println("\nActual structure:");
        System.out.println(set);

        System.out.println("\n****TEST 3: Shows the users who consulted a resource ('mp.pdf') on a specific date ('1/10/2021') ****");
        System.out.println("Resource: mp.pdf  Date:" + date + "\nUsers:" + listToString(set.getUsersFromResourceDate("mp.pdf", date)));

        System.out.println("\n****TEST 4: Check if a user has queried a resource");
        System.out.println("User: Marc  |  Resource: t3.mp4");
        boolean bool = set.userHasConsultedResource("t3.mp4", "Marc");
        if (bool) System.out.println("Yes, this resource has been consulted");
        else System.out.println("No, this resource has not been consulted");
        System.out.println("User: " + users.getUserAtIndex(1) + "  |  Resource: none.xls");
        bool = set.userHasConsultedResource("none.xls", users.getUserAtIndex(1));
        if (bool) System.out.println("Yes, this resource has been consulted");
        else System.out.println("No, this resource has not been consulted");

        System.out.println("\n****TEST 5: Return a list with the users who queried the resource (t3.mp4) in a given date range. ****\n\tResource: t3.mp4\n\tStart: " + date + "\n\tEnd: " + date1);
        System.out.println("USERS LIST: " + listToString(set.getUsersFromResourceDateRange("t3.mp4", date, date1)));

        System.out.println("\n****TEST 6: Deleting all the queries of 'mp.pdf' on 01/10/2021 - 4:59:32 ****");
        System.out.println(set);
        System.out.println("\t\tRemoving...\n");
        set.removeQueriesFromResourceDate("mp.pdf", date);
        System.out.println(set);

        System.out.println("\n****TEST 7: Delete all the queries of different resources: ****");
        System.out.println("\tDeleting all the queries of 't3.mp4'...");
        set.removeQueriesFromResource("t3.mp4");
        System.out.println(set);
        System.out.println("\tDeleting all the queries of 'mp.pdf'...");
        set.removeQueriesFromResource("mp.pdf");
        System.out.println(set);


        System.out.println("\n\t--------TESTS FOR USERS AND RESOURCES THAT DO NOT EXIST--------\n");

        System.out.println("****TEST 8: Get resources of user: Potato ***");
        System.out.println("Name: Potato\nResources consulted:" + listToString(set.getResourcesFromUser("Potato")));

        System.out.println("\n****TEST 9: Shows the users who consulted a resource ('null') on a specific date ('1/10/2021') ****");
        System.out.println("Resource: null  Date:" + date + "\nUsers:" + listToString(set.getUsersFromResourceDate("null", date)));

        System.out.println("\n****TEST 10: Delete all the queries of 'null' on 01/10/2021 - 4:59:32 ****");
        System.out.println(set);
        System.out.println("\t\tRemoving...\n");
        set.removeQueriesFromResourceDate("null", date);
        System.out.println(set);

        System.out.println("\n\t------------OTHER TESTS------------\n");
        System.out.println("\n****TEST 11: Delete all the queries of resource: 'null' ****");
        set.removeQueriesFromResource("null");
        System.out.println(set);


        System.out.println("\n****TEST 12: Get resources from different users... ****");
        int i;
        for (i = 0; i < 5; i++) {
            System.out.println("- " + i + ": Get resources from user: " + users.getUserAtIndex(i));
            System.out.println("Resources consulted: " + listToString(set.getResourcesFromUser(users.getUserAtIndex(i))));
        }

        System.out.println("\n\t\tACTUAL SET :");
        System.out.println(set);

        System.out.println("\n****TEST 13: Delete all the queries of ALL resources...****");
        for (i = 0; i < 5; i++) {
            System.out.println("- " + i + ": Deleting all the queries of resource: " + set.getMostQueriedResource() + "...");
            set.removeQueriesFromResource(set.getMostQueriedResource());
        }
        System.out.println("\n\t\tACTUAL SET:");
        System.out.println(set);

        System.out.println("\n****TEST 14: Find the most queried resource in a empty TAD****");
        if (set.getMostQueriedResource() != null) System.out.println(set.getMostQueriedResource());
        else System.out.println("\tEmpty TAD");

    }


    /**
     * Method to convert a list of String to a printable String
     *
     * @param data with the strings
     * @return printable String
     */
    public static String listToString(String[] data) {
        StringBuilder information = new StringBuilder("[ ");
        if (data != null) {
            for (int index = 0; index < data.length && data[index] != null; index++) {
                information.append(data[index]).append(",  ");
            }
            if (information.length() > 3) {
                information.delete(information.length() - 3, information.length() - 1);
            }
            information.append("]");
        }
        return information.toString();
    }


    /**
     * Method to read an integer controlling InputMismatchException
     *
     * @param message to show
     * @param min     minimum number
     * @param max     maximum number
     * @return the correct number
     */
    public static int readInt(String message, int min, int max) {
        int result = 0;
        boolean read = false;
        do {
            try {
                System.out.print(message + "the number has to be greater or equal than " + min + " and smaller or equal than " + max + ": ");
                result = key.nextInt();
                read = true;
            } catch (InputMismatchException e) {
                System.out.println("It has to be an Integer");
                key.nextLine();
            } finally {
                if (result > max || result < min) {
                    read = false;
                }
            }
        } while (!read);
        return result;
    }

}
