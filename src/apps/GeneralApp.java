package apps;

import information.*;
import inputOutput.ReadData;
import inputOutput.WriteData;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class GeneralApp {
    static Scanner key = new Scanner(System.in);

    /**
     * Main application for dynamic
     * @param args not used
     */
    public static void main (String[] args){
        int option, type;
        boolean ok=false;
        showTypes();
        type = readInt("",0,4);
        if (type == 0) {
            System.out.println("\tSee you later!");
        }else {
            ADTsetResources info = readFromFile(type);
            do {
                showMenu();
                option = readInt("", 0, 7);
                switch (option) {
                    case 0 -> System.out.println("\tSee you later!");
                    case 1 -> removeQueries(info);
                    case 2 -> removeQueriesByDate(info);
                    case 3 -> listUsers(info);
                    case 4 -> listUsersByDate(info);
                    case 5 -> mostResource(info);
                    case 6 -> listResources(info);
                    case 7 -> writeToFile(info);
                    default -> System.out.println("This operation doesn't exists!");

                }
            } while (option != 0);

            System.out.println("Do you want to save the results in a file? (Yes/No)");
            String write =readNum("");
            do {
                if (write.equals("Yes")){
                    writeToFile(info);
                    ok=true;
                }
                else if (write.equals("No")){
                    System.out.println("OK. Bye!");
                    ok=true;
                }
                else {
                    System.out.println("I don't understand. Try again: ");
                    write = readNum("");
                }
            }while(!ok);
        }
        key.close();
    }

    /**
     * Menu
     */
    public static void showMenu() {
        System.out.println("\n Options:");
        System.out.println("\t1. Remove all the queries from a resource.");
        System.out.println("\t2. Delete all de queries from a resource of a specific date");
        System.out.println("\t3. See a list of users that queried a resource.");
        System.out.println("\t4. See a list of users of a resource on a specific date.");
        System.out.println("\t5. The resource most queried by users.");
        System.out.println("\t6. See a list of all the resources queried by a specific user.");
        System.out.println("\t0. Exit.");
        System.out.print("\n\t\t\tSelect an option,\t");
    }
    /**
     * Menu with the types implemented
     */
    public static void showTypes() {
        System.out.println("\n Which type of ADT do you want to test?");
        System.out.println("\n\t1. Static with one vector of queries.");
        System.out.println("\t2. Static with vector of resources each one with vector of users.");
        System.out.println("\t3. Dynamic with TAD implemented by us.");
        System.out.println("\t4. Dynamic with HashMaps of Java.");
        System.out.println("\t0. None of above. Exit.");
        System.out.print("\n\t\t\tSelect one,\t");
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
    public static ADTsetResources readFromFile(int type){
        ReadData r = new ReadData();
        ADTsetResources info;
        System.out.print("\n\tIntroduce the file's name with the information: ");
        String file = key.next();
        info = r.read(file,type);
        if (info != null) {
            System.out.println("\n\tAll gone fine");
        } else System.out.println("\t\tError, file not found");
        return info;
    }

    /**
     * Removes all the queries from a resource
     * @param info where to delete
     */
    public static void removeQueries(ADTsetResources info){
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
    public static void removeQueriesByDate(ADTsetResources info){
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
    public static void listUsers(ADTsetResources info){
        String resource = readNum("\nIntroduce the name of the resource: ");
        System.out.println("The users that queried the "+resource+" were:");
        String[] result = info.getUsersFromResource(resource);
        for (String s : result) {
            System.out.println(s);
        }
        pause();
    }

    /**
     * Shows a list of users that queried a resource on a specific date
     * @param info information
     */
    public static void listUsersByDate(ADTsetResources info){
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
        System.out.println("The users that queried the "+resource+" the "+date+" :");
        String[] result = info.getUsersFromResourceDate(resource,date);
        for (String s : result) {
            System.out.println(s);
        }
        pause();
    }

    /**
     * The most queried resource
     * @param info information
     */
    public static void  mostResource(ADTsetResources info){
        System.out.println("The most queried resource is  "+info.getMostQueriedResource());
        pause();
    }

    /**
     * The resources queried by an user
     * @param info information
     */
    public static void listResources(ADTsetResources info){
        String name = readNum("\nIntroduce the alias of the user: ");
        System.out.println("The resources queried by "+name+" were:");
        String[] result = info.getResourcesFromUser(name);
        for (String s : result) {
            System.out.println(s);
        }
        pause();
    }

    /**
     * Write the structures in a file
     * @param info: information to write in file
     */
    public static void writeToFile (ADTsetResources info){
        WriteData write = new WriteData();
        String file = readNum("\nWrite the name of the file: ");
        write.write(info,file);
    }
}


