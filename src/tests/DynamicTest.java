package tests;

import information.DynamicSetResources;

public class DynamicTest {
    public static void main (String[] args){

        DynamicSetResources datos = new DynamicSetResources ();
        datos.addQuery("23/02/2021", "15:32", "Manolita", "fichero1");
        datos.addQuery("23/02/2021", "15:32", "Manolita", "fichero1");
        datos.addQuery("23/02/2021", "15:32", "Manolita", "fichero2");
        datos.addQuery("25/02/2021", "19:32", "Pepe", "compus");
        datos.addQuery("25/02/2021", "1:32", "Pepe", "fichero1");
        datos.addQuery("27/02/2021", "20:32", "Juan", "fichero1");
        datos.addQuery("28/05/2021", "9:02", "Alex", "aspectes");
        datos.addQuery("23/02/2021", "15:32", "Juan", "fichero1");
        datos.addQuery("29/02/2021", "4:32", "Alex", "compus");

        System.out.println("\nPRUEBA 1");
        System.out.println("Name: Manolita\nResources consulted:"+datos.getResourcesFromUser("Manolita"));

        System.out.println("\nPRUEBA 2");
        System.out.println("Resource: fichero1 Date: 23/02/2021\nUsers:"+datos.getUsersFromResourceDate("23/02/2021", "fichero1"));

        System.out.println("\nPRUEBA 3");
        System.out.println("Resource with most queries: "+datos.getMostQueriedResource());

        System.out.println("\nBEFORE DELETING...");
        System.out.println(datos);

        System.out.println("\nPRUEBA 4: DELETING ALL THE QUERIES OF FICHERO1 OF THE 23/01/2021");
        datos.removeQueriesFromResourceDate("23/02/2021", "fichero1");
        System.out.println(datos);

        System.out.println("\nPRUEBA 5: DELETING ALL THE QUERIES OF FICHERO1 AND COMPUS");
        datos.removeQueriesFromResource("fichero1");
        datos.removeQueriesFromResource("compus");
        System.out.println(datos);
    }
}
