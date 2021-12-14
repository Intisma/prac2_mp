package apps;

import information.ADTsetResources;
import information.Date;
import information.Query;
import inputOutput.ReadData;
import inputOutput.WriteData;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class GeneralApp {

    static Scanner key = new Scanner(System.in);

    /**
     * Main application. It loads the queries saved in the file "appData.csv". After working with the app, all the changes
     * will be saved in the file (only when changes have been produced).
     *
     * @param args not used
     */
    public static void main(String[] args) {
        int option, type;
        boolean end = false, changed = false;
        showTypes();
        type = readInt("", 0, 4);
        if (type == 4) {
            System.out.println("\n\tSee you later!");
        } else {
            ADTsetResources set = readFromFile(type);
            if (set != null) {
                do {
                    showMenu();
                    option = readInt("", 0, 9);
                    switch (option) {
                        case 0 -> end = true;
                        case 1 -> System.out.println("\n" + set);
                        case 2 -> {
                            addQueries(set);
                            changed = true;
                        }
                        case 3 -> {
                            removeQueries(set);
                            changed = true;
                        }
                        case 4 -> {
                            removeQueriesByDate(set);
                            changed = true;
                        }
                        case 5 -> listUsers(set);
                        case 6 -> listUsersByDate(set);
                        case 7 -> mostResource(set);
                        case 8 -> listResources(set);
                        case 9 -> writeToFile(set);
                        default -> System.out.println("This operation does not exist!");

                    }
                    if (!end) {
                        pause();
                    }
                } while (option != 0);
                if (changed) {
                    if (!WriteData.write(set, "src/apps/appData.csv")) {
                        System.out.println("\n\tERROR: Data has not been saved");
                    }
                }

                System.out.println("\n\tSee you later!");
            } else System.out.println("\n\tERROR: The file of the data was not found");
        }
        key.close();
    }

    /**
     * Menu
     */
    public static void showMenu() {
        System.out.println("\n Options:");
        System.out.println("\t1. Show the set's queries.");
        System.out.println("\t2. Add a new query to the set.");
        System.out.println("\t3. Remove all the queries from a resource.");
        System.out.println("\t4. Delete all de queries from a resource of a specific date");
        System.out.println("\t5. See a list of users that queried a resource.");
        System.out.println("\t6. See a list of users of a resource on a specific date.");
        System.out.println("\t7. The resource most queried by users.");
        System.out.println("\t8. See a list of all the resources queried by a specific user.");
        System.out.println("\t0. Exit.");
        System.out.print("\n\t\t\tSelect an option,");
    }

    /**
     * Menu with the types implemented
     */
    public static void showTypes() {
        System.out.println("\n Which type of implementation do you wish to use?");
        System.out.println("\n\t0. Static implementation with an ordered vector of queries.");
        System.out.println("\t1. Static implementation with a vector of resources, each one with it's own vector of users.");
        System.out.println("\t2. Dynamic implementation with HashMaps of Java.");
        System.out.println("\t3. Dynamic implementation with multilist");
        System.out.println("\t4. None of above. Exit.");
        System.out.print("\n\t\t\tSelect one,\t");
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

    /**
     * Read a string
     *
     * @param message to show in terminal
     * @return the read string
     */
    public static String readString(String message) {
        String data;
        boolean read;
        System.out.print(message);
        do {
            data = key.nextLine();
        } while (data.equals(""));
        return data;
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
     * Read the information stored in the file of the app and generates the type of set chosen by the user
     *
     * @param type of implementation
     * @return the constructed set
     */
    public static ADTsetResources readFromFile(int type) {
        ADTsetResources set;
        set = ReadData.read("src/apps/appData.csv", type);
        return set;
    }

    /**
     * Method to get a Date from the user
     *
     * @return constructed date
     */
    public static Date getDate() {
        int year = readInt("\tIntroduce the year: ", 1990, 2500);
        char month = (char) readInt("\tIntroduce the month: ", 1, 12);
        // Control that the introduced day is correct
        int maxDay;
        switch (month) {
            case 1, 3, 5, 7, 8, 10, 12 -> maxDay = 31;
            case 2 -> maxDay = 28;
            default -> maxDay = 30;
        }
        char day = (char) readInt("\tIntroduce the day: ", 1, maxDay);
        char hour = (char) readInt("\tIntroduce the hour: ", 0, 23);
        char minute = (char) readInt("\tIntroduce the minute: ", 0, 59);
        char second = (char) readInt("\tIntroduce the second: ", 0, 59);
        return new Date(year, month, day, hour, minute, second);
    }

    /**
     * Read the information corresponding to a query by keyboard and introduces it to the set
     *
     * @param set to add the query to
     */
    public static void addQueries(ADTsetResources set) {
        String resource = readString("\n\tIntroduce the resource name: ");
        String user = readString("\tIntroduce the user's name: ");
        set.addQuery(new Query(resource, user, getDate()));
    }

    /**
     * Removes all the queries from a resource
     *
     * @param set where to delete the resource
     */
    public static void removeQueries(ADTsetResources set) {
        String resource = readString("\nIntroduce the name of the resource: ");
        set.removeQueriesFromResource(resource);
        System.out.println("All the queries from " + resource + " deleted!");
        System.out.println(set);
        pause();
    }

    /**
     * Removes all the queries from a resource of a specific date
     *
     * @param info where to delete
     */
    public static void removeQueriesByDate(ADTsetResources info) {
        int year;
        char month, day, hour, minute, second;
        String resource = readString("\nIntroduce the name of the resource: ");
        year = Integer.parseInt(readString("\nIntroduce the year: "));
        month = (char) Integer.parseInt(readString("\nIntroduce the month: "));
        day = (char) Integer.parseInt(readString("\nIntroduce the day: "));
        hour = (char) Integer.parseInt(readString("\nIntroduce the hour: "));
        minute = (char) Integer.parseInt(readString("\nIntroduce the minute: "));
        second = (char) Integer.parseInt(readString("\nIntroduce the second: "));
        Date date = new Date(year, month, day, hour, minute, second);
        info.removeQueriesFromResourceDate(resource, date);
        System.out.println("All the queries from " + resource + " from " + date + " were successfully deleted!");
        System.out.println(info);
        pause();
    }

    /**
     * List of users that queried a resource
     *
     * @param info to find
     */
    public static void listUsers(ADTsetResources info) {
        String resource = readString("\nIntroduce the name of the resource: ");
        System.out.println("The users that queried the " + resource + " were:");
        String[] result = info.getUsersFromResource(resource);
        for (String s : result) {
            System.out.println(s);
        }
        pause();
    }

    /**
     * Shows a list of users that queried a resource on a specific date
     *
     * @param info information
     */
    public static void listUsersByDate(ADTsetResources info) {
        int year;
        char month, day, hour, minute, second;
        String resource = readString("\nIntroduce the name of the resource: ");
        year = Integer.parseInt(readString("\nIntroduce the year: "));
        month = (char) Integer.parseInt(readString("\nIntroduce the month: "));
        day = (char) Integer.parseInt(readString("\nIntroduce the day: "));
        hour = (char) Integer.parseInt(readString("\nIntroduce the hour: "));
        minute = (char) Integer.parseInt(readString("\nIntroduce the minute: "));
        second = (char) Integer.parseInt(readString("\nIntroduce the second: "));
        Date date = new Date(year, month, day, hour, minute, second);
        System.out.println("The users that queried the " + resource + " the " + date + " :");
        String[] result = info.getUsersFromResourceDate(resource, date);
        for (String s : result) {
            System.out.println(s);
        }
        pause();
    }

    /**
     * The most queried resource
     *
     * @param info information
     */
    public static void mostResource(ADTsetResources info) {
        System.out.println("The most queried resource is  " + info.getMostQueriedResource());
        pause();
    }

    /**
     * The resources queried by a user
     *
     * @param info information
     */
    public static void listResources(ADTsetResources info) {
        String name = readString("\nIntroduce the alias of the user: ");
        System.out.println("The resources queried by " + name + " were:");
        String[] result = info.getResourcesFromUser(name);
        for (String s : result) {
            System.out.println(s);
        }
        pause();
    }

    /**
     * Write the structures in a file
     *
     * @param info: information to write in file
     */
    public static void writeToFile(ADTsetResources info) {
        WriteData write = new WriteData();
        String file = readString("\nWrite the name of the file: ");
        write.write(info, file);
    }
}


