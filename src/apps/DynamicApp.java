package apps;

import information.DynamicSetResources;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;
public class DynamicApp {

    static Scanner key = new Scanner(System.in);

    /**
     * Main application for dynamic
     * @param args not used
     */
    public static void main (String[] args){
        int option;
        DynamicSetResources dynamic=null;
        do {
            showMenu();
            option = readInt("", 0, 8);
            switch (option) {
                case 1: dynamic = readFromFile();
                case 2: removeQueries(dynamic);
                case 3: removeQueriesByDate(dynamic);
                case 4: listUsers(dynamic);
                case 5: listUsersByDate(dynamic);
                case 6: mostResource(dynamic);
                case 7: listResources(dynamic);
                case 8: test();
                case 9: writeToFile(dynamic);
                case 0: System.out.println("\tHasta otra!");
            }
        } while (option != 0);
        key.close();
    }

    /**
     * Menu
     */
    public static void showMenu() {
        System.out.println("\n Options:");
        System.out.println("\n\t1. Read information from a file.");
        System.out.println("\t2. Remove all the queries from a resource.");
        System.out.println("\t3. Delete all de queries from a resource of a specific date");
        System.out.println("\t4. See a list of users that queried a resource.");
        System.out.println("\t5. See a list of users of a resource on a specific date.");
        System.out.println("\t6. The resource most queried by users.");
        System.out.println("\t7. See a list of all the resources queried by a specific user.");
        System.out.println("\t8. Tests.");
        System.out.println("\t0. Exit.");
        System.out.print("\n\t\t\tSelect an option,\t");
    }

    /**
     * Method control exceptions
     * @param message to show
     * @param min minimum number
     * @param max maximum number
     * @return the correct number
     */
    public static int readInt(String message, int min, int max) {
        int result = 0;
        boolean read = false;
        do {
            try {
                System.out.print(message + "the number has to be greater than " + min + " and smaller than " + max + ": ");
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

    /**
     * Read a number in String format
     * @param message to show in terminal
     * @return the read number
     */
    public static String readNum(String message) {
        String num;
        boolean read;
        do {
            System.out.print(message);
            num = key.next();
            read = ((!num.equals("")) && (num.matches("[+-]?\\d*(\\.\\d+)?")));
        } while (!read);
        return num;
    }

    /**
     * The program stops util the user press enter
     */
    public static void pause() {
        System.out.println("\n\tPress \"ENTER\" to continue...");
        try {
            if (System.in.read() == -1)
                System.out.println("\t\tYou shouldn't have written anything!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Read the info of a file and creates a DynamicSetResources
     */
    public static DynamicSetResources readFromFile(){
//      ReadData
//        System.out.print("\n\tIntroduce the file's name: ");
//        String file = teclado.next();
//      String[] info = readData.leer(file);
//        if (info != null) {
//            DynamicSetResources dynamic = new DynamicSetResources();
        //bucle creando DynamicSetResources
//            System.out.println("\n\tCreating the dynamic resources... ");
//            dynamic.addQuery(info);
//            System.out.print("\n\tIntroduce the file's name where do you want to save the info: ");
//            file = teclado.next();
//            if (gestorFicheros.escribir(gestorPrimos, nombreFichero))
//                System.out.println("\n\tAll gone fine");
//            else
//                System.out.println("\n\tError to write in file");
//        } else
//            System.out.println("\t\tError, file not found");
//
        pause();
        return null;
    }

    /**
     * Removes all the queries from a resource
     * @param info where to delete
     */
    public static void removeQueries(DynamicSetResources info){
        String resource = readNum("\nIntroduce the name of the resource: ");
        info.removeQueriesFromResource(resource);
        System.out.println("All the queries from"+resource+"deleted!");
        System.out.println(info);
        pause();
    }

    /**
     * Removes all the queries from a resource of a specific date
     * @param info where to delete
     */
    public static void removeQueriesByDate(DynamicSetResources info){
        String resource = readNum("\nIntroduce the name of the resource: ");
        String date = readNum("\nIntroduce the date: ");
        //Transformar a date
        info.removeQueriesFromResourceDate(date, resource);
        System.out.println("All the queries from"+resource+"from "+date+"were succefully deleted!");
        System.out.println(info);
        pause();
    }

    /**
     * List of users that queried a resource
     * @param info to find
     */
    public static void listUsers(DynamicSetResources info){
        String resource = readNum("\nIntroduce the name of the resource: ");
        System.out.println("The users that queried the"+resource+"were:\n"+info.getUsersFromResource(resource));
        pause();
    }

    /**
     * Shows a list of users that queried a resource on a specific date
     * @param info information
     */
    public static void listUsersByDate(DynamicSetResources info){
        String resource = readNum("\nIntroduce the name of the resource: ");
        String date = readNum("\nIntroduce the date: ");
        System.out.println("The users that queried the"+resource+" the"+date+" :\n"+info.getUsersFromResourceDate(date,resource));
        pause();
    }

    /**
     * The most queried resource
     * @param info information
     */
    public static void  mostResource(DynamicSetResources info){
        System.out.println("The most queried resource is"+info.getMostQueriedResource());
        pause();
    }

    /**
     * The resources queried by an user
     * @param info information
     */
    public static void listResources(DynamicSetResources info){
        String name = readNum("\nIntroduce the alias of the user: ");
        System.out.println("The resources queried by"+name+"were:\n"+info.getResourcesFromUser(name));
        pause();
    }

    /**
     * Write the structures in a file
     * @param info
     */
    public static void writeToFile (DynamicSetResources info){
        //Escribir info en fichero llamar writeData
        String file = readNum("\nWrite the name of the file: ");
        //Escribir
    }

    /**
     * Test for dynamic
     */
    public static void test(){

    }

}
