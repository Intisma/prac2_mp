package apps;
import information.*;

public class DynamicApp {
    public static void main (String[] args){

        DynamicSetResources datos = new DynamicSetResources ();
        datos.addQuery("23/02/2021", "15:32", "Manolita", "fichero1");
        datos.addQuery("23/02/2021", "15:32", "Manolita", "fichero1");
        datos.addQuery("23/02/2021", "15:32", "Manolita", "fichero2");
        datos.addQuery("25/02/2021", "19:32", "Pepe", "compus");
        datos.addQuery("27/02/2021", "20:32", "Juan", "fichero1");

        datos.addQuery("28/05/2021", "9:02", "Alex", "aspectes");
        datos.addQuery("23/02/2021", "15:32", "Juan", "fichero1");

        System.out.println("\nPROBAR\n");
        System.out.println(datos.getResourcesFromUser("Manolita"));
        System.out.println("\nPROBAR\n");
        System.out.println(datos.getUsersFromResourceDate("23/02/2021", "fichero1"));

        System.out.println(datos);
        datos.removeQueriesFromResource("fichero1");
        datos.removeQueriesFromResource("compus");
        System.out.println(datos);



    }
}
