package tests;

import dataGenerator.RandomDateGenerator;
import dataGenerator.RandomResourceGenerator;
import dataGenerator.RandomSetGenerator;
import dataGenerator.RandomUserGenerator;
import information.ADTsetResources;
import information.Query;
import staticInformation.Dates;
import staticInformation.Resources;
import staticInformation.Users;
import timeMeasurer.Queries;

import java.util.Random;

public class StaticTest {
    public static void main(String[] args) {
        Random numGenerator = new Random();
        // Generate sets keeping track of the added queries
        RandomSetGenerator setGenerator = new RandomSetGenerator();
        ADTsetResources set = setGenerator.generateSet(0, 5000, new Queries());

        // Generate new users, resources and dates to measure time to add new queries
        RandomUserGenerator userGenerator = new RandomUserGenerator();
        Users newUsers = userGenerator.generateRandomUsers(1000);
        RandomResourceGenerator resourceGenerator = new RandomResourceGenerator();
        Resources newResources = resourceGenerator.generateRandomResources(1000);
        RandomDateGenerator dateGenerator = new RandomDateGenerator();
        Dates newDates = dateGenerator.generateRandomDates(1000);

        for (int index = 0; index < 4000; index++) {
            set.addQuery(new Query(newResources.getResourceAtIndex(numGenerator.nextInt(newResources.getNumResources())), newUsers.getUserAtIndex(numGenerator.nextInt(newUsers.getNumUsers())), newDates.getDateAtIndex(numGenerator.nextInt(newDates.getNumDates()))));
        }

        System.out.println(set);

        set.userHasConsultedResource("year.cache", "pepe");


        /*String[] frase = new String[2];
        frase[0] = "Holis";
        System.out.println(listToString(frase));
        /*ADTsetResources set = ReadData.read("src/apps/appData.csv", 0);
        WriteData writer = new WriteData();
        writer.write(set, "src/apps/appOut.csv");

        /*Date date = new Date(1990, (char) 1, (char) 2, (char) 1, (char) 1, (char) 1);
        Date date2 = new Date(1992, (char) 1, (char) 2, (char) 1, (char) 1, (char) 1);
        Date date3 = new Date(1994, (char) 1, (char) 2, (char) 1, (char) 1, (char) 1);
        Date date4 = new Date(1999, (char) 1, (char) 2, (char) 1, (char) 1, (char) 1);
        Date dateProba = new Date(1992, (char) 1, (char) 3, (char) 1, (char) 1, (char) 1);

        Queries queries = new Queries();
        queries.addQuery(new Query("Casa", "Herminia", date));
        queries.addQuery(new Query("Casa", "Herminia", date));
        queries.addQuery(new Query("Casalona", "Herminia", date));
        queries.addQuery(new Query("Casapera", "Herminia", date2));
        queries.addQuery(new Query("Casaverde", "Herminia", date3));
        queries.addQuery(new Query("Casapera", "Herminia", date));
        System.out.println(queries);

        ADTsetResources set = new StaticSecondSetResources();
        set.addQuery(new Query("Casa", "Herminia", date));
        set.addQuery(new Query("Casa", "Terelu", date2));
        set.addQuery(new Query("Casa", "Caniche", date3));
        set.addQuery(new Query("Aormigon", "Herminia", date));
        set.addQuery(new Query("Aormigon", "Herminia", date4));
        set.addQuery(new Query("Hormigon", "Terelu", date3));
        set.addQuery(new Query("Hormigon", "Caniche", date2));
        set.addQuery(new Query("Hormigon", "Embolicada", date2));
        set.addQuery(new Query("Hormigon", "Herminia", date4));
        System.out.println(set);
        String[] resources = set.getResourcesFromUser("Herminia");
        for (int index = 0; resources[index] != null; index++) {
            System.out.println(resources[index]);
        }
        /*if(set.userHasConsultedResource("Aormigon", "Terelu")) System.out.println("Si que lo ha consultao la tia");
        else System.out.println("La tia no lo ha consultado");*/

        /*String[] info = set.getUsersFromResourceDateRange("Hormigon", date3, date2);

        for (int index = 0; info[index] != null; index++) System.out.println(info[index] + "  ");

        /*String[] info = set.getUsersFromResourceDateRange("Hormigon", date2, date3);
        int index=0;
        while(info[index]!=null){
            System.out.println(info[index]);
            index++;
        }*/

    }

    /**
     * Method to convert a list of String to a printable String
     *
     * @param data with the strings
     * @return printable String
     */
    public static String listToString(String[] data) {
        StringBuilder information = new StringBuilder("[ ");
        for (int index = 0; index < data.length && data[index] != null; index++) {
            information.append(data[index]).append(",  ");
        }
        if (information.length() > 3) {
            information.delete(information.length() - 3, information.length() - 1);
        }
        information.append("]");
        return information.toString();
    }
}
