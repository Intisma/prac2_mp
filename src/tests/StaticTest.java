package tests;

import information.*;
import staticInformation.Queries;
import staticInformation.UserQueries;

public class StaticTest {
    public static void main(String[] args) {



        Date date = new Date(1990, (char) 1, (char) 2, (char) 1, (char) 1, (char) 1);
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
        set.addQuery(new Query("Aormigon", "Herminia", date2));
        set.addQuery(new Query("Hormigon", "Terelu", date3));
        set.addQuery(new Query("Hormigon", "Caniche", date2));
        set.addQuery(new Query("Hormigon", "Embolicada", date2));
        set.addQuery(new Query("Hormigon", "Herminia", date));
        System.out.println(set);
        if(set.userHasConsultedResource("Hormigon", "Herminia")) System.out.println("Si que lo ha consultao la tia");

        /*String[] info = set.getUsersFromResourceDateRange("Hormigon", date3, date2);

        for (int index = 0; info[index] != null; index++) System.out.println(info[index] + "  ");

        /*String[] info = set.getUsersFromResourceDateRange("Hormigon", date2, date3);
        int index=0;
        while(info[index]!=null){
            System.out.println(info[index]);
            index++;
        }*/

    }
}
