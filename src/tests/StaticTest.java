package tests;

import information.Date;
import information.Query;
import information.StaticSetResources;

public class StaticTest {
    public static void main(String[] args) {
        char day = 1, month = 10, hour = 4, minute = 59, second = 32;
        Date date = new Date(2000, month, day, hour, minute, second);
        Date date2 = new Date(2000, month, day, day, minute, second);
        StaticSetResources set = new StaticSetResources();
        set.addQuery(new Query("pdf", "ismael", date));
        set.addQuery(new Query("doc", "ismael", date2));
        set.addQuery(new Query("archive", "nerea", date));
        set.addQuery(new Query("book", "ismael", date));
        set.addQuery(new Query("links", "ismael", date));
        set.addQuery(new Query("links", "nerea", date));
        set.addQuery(new Query("links", "nerea", date));
        set.addQuery(new Query("bookstore", "ismael", date));
        set.addQuery(new Query("note", "ismael", date));
        set.addQuery(new Query("message", "nerea", date2));
        System.out.println(set);
        System.out.println(set.getResourcesFromUser("nerea"));

    }
}
