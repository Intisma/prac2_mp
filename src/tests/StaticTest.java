package tests;

import information.*;

public class StaticTest {
    public static void main(String[] args) {
        Date date = new Date(1990, (char) 1, (char) 2, (char) 1, (char) 1, (char) 1);
        Date date2 = new Date(1992, (char) 1, (char) 2, (char) 1, (char) 1, (char) 1);
        Date date3 = new Date(1994, (char) 1, (char) 2, (char) 1, (char) 1, (char) 1);
        Date date4 = new Date(1999, (char) 1, (char) 2, (char) 1, (char) 1, (char) 1);
        Date dateProba = new Date(1992, (char) 1, (char) 3, (char) 1, (char) 1, (char) 1);
        ADTsetResources set = new StaticSetResources();
        set.addQuery(new Query("Casa", "Herminia", date));
        set.addQuery(new Query("Casa", "Terelu", date2));
        set.addQuery(new Query("Casa", "Caniche", date3));
        set.addQuery(new Query("Aormigon", "Herminia", date2));
        set.addQuery(new Query("Hormigon", "Terelu", date2));
        set.addQuery(new Query("Hormigon", "Caniche", date2));
        set.addQuery(new Query("Hormigon", "Embolicada", date2));
        set.addQuery(new Query("Hormigon", "Herminia", date));
        String[] info = set.getUsersFromResourceDateRange("Hormigon", date2, date3);
        int index=0;
        while(info[index]!=null){
            System.out.println(info[index]);
            index++;
        }

    }
}
