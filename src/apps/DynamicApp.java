package apps;

import information.Date;
import information.DynamicSetResources;
import inputOutput.WriteData;
import inputOutput.ReadData;
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
            option = readInt("", 0, 9);
            switch (option) {
                case 1 -> dynamic = readFromFile();
                case 2 -> removeQueries(dynamic);
                case 3 -> removeQueriesByDate(dynamic);
                case 4 -> listUsers(dynamic);
                case 5 -> listUsersByDate(dynamic);
                case 6 -> mostResource(dynamic);
                case 7 -> listResources(dynamic);
                case 8 -> test();
                case 9 -> writeToFile(dynamic);
                case 0 -> System.out.println("\tHasta otra!");
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
            read = (!num.equals(""));
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
      ReadData r = new ReadData();
        System.out.print("\n\tIntroduce the file's name: ");
        String file = key.next();
        DynamicSetResources info = (DynamicSetResources) r.read(file, 3);
        if (info != null) {
            System.out.println("\n\tAll gone fine");
        } else System.out.println("\t\tError, file not found");
        pause();
        return info;
    }

    /**
     * Removes all the queries from a resource
     * @param info where to delete
     */
    public static void removeQueries(DynamicSetResources info){
        String resource = readNum("\nIntroduce the name of the resource: ");
        info.removeQueriesFromResource(resource);
        System.out.println("All the queries from "+resource+" deleted!");
        System.out.println(info);
        pause();
    }

    /**
     * Removes all the queries from a resource of a specific date
     * @param info where to delete
     */
    public static void removeQueriesByDate(DynamicSetResources info){
        int year;
        char month, day, hour, minute, second;
        String resource = readNum("\nIntroduce the name of the resource: ");
        year = Integer.parseInt(readNum("\nIntroduce the year: "));
        month = (char) Integer.parseInt(readNum("\nIntroduce the month: "));
        day = (char) Integer.parseInt(readNum("\nIntroduce the day: "));
        hour = (char) Integer.parseInt(readNum("\nIntroduce the hour: "));
        minute = (char) Integer.parseInt(readNum("\nIntroduce the minute: "));
        second = (char) Integer.parseInt(readNum("\nIntroduce the second: "));
        Date date = new Date(year,month,day,hour,minute,second);
        info.removeQueriesFromResourceDate(resource, date);
        System.out.println("All the queries from "+resource+" from "+date+" were successfully deleted!");
        System.out.println(info);
        pause();
    }

    /**
     * List of users that queried a resource
     * @param info to find
     */
    public static void listUsers(DynamicSetResources info){
        String resource = readNum("\nIntroduce the name of the resource: ");
        System.out.println("The users that queried the "+resource+" were:\n"+info.getUsersFromResource(resource));
        pause();
    }

    /**
     * Shows a list of users that queried a resource on a specific date
     * @param info information
     */
    public static void listUsersByDate(DynamicSetResources info){
        int year;
        char month, day, hour, minute, second;
        String resource = readNum("\nIntroduce the name of the resource: ");
        year = Integer.parseInt(readNum("\nIntroduce the year: "));
        month = (char) Integer.parseInt(readNum("\nIntroduce the month: "));
        day = (char) Integer.parseInt(readNum("\nIntroduce the day: "));
        hour = (char) Integer.parseInt(readNum("\nIntroduce the hour: "));
        minute = (char) Integer.parseInt(readNum("\nIntroduce the minute: "));
        second = (char) Integer.parseInt(readNum("\nIntroduce the second: "));
        Date date = new Date(year,month,day,hour,minute,second);
        System.out.println("The users that queried the "+resource+" the "+date+" :\n"+info.getUsersFromResourceDate(resource,date));
        pause();
    }

    /**
     * The most queried resource
     * @param info information
     */
    public static void  mostResource(DynamicSetResources info){
        System.out.println("The most queried resource is  "+info.getMostQueriedResource());
        pause();
    }

    /**
     * The resources queried by an user
     * @param info information
     */
    public static void listResources(DynamicSetResources info){
        String name = readNum("\nIntroduce the alias of the user: ");
        System.out.println("The resources queried by "+name+" were:\n"+info.getResourcesFromUser(name).toString());
        pause();
    }

    /**
     * Write the structures in a file
     * @param info
     */
    public static void writeToFile (DynamicSetResources info){
        WriteData write = new WriteData();
        String file = readNum("\nWrite the name of the file: ");
        write.write(info,file);
    }

    /**
     * Test for dynamic
     */
    public static void test(){

    }

}
