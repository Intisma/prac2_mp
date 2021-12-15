package apps;

import information.ADTsetResources;
import information.Date;
import information.Query;
import inputOutput.ReadData;
import inputOutput.WriteData;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Main application. It loads the queries saved in the file "appData.csv". After working with the app, all the changes
 * will be saved in the file (only when changes have been produced).
 */
public class GeneralApp {

    static Scanner key = new Scanner(System.in);

    /**
     * Main to execute
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
                    option = readInt("", 0, 11);
                    switch (option) {
                        case 0 -> end = true;
                        case 1 -> System.out.println("\n" + set);
                        case 2 -> {
                            addQueries(set);
                            changed = true;
                        }
                        case 3 -> {
                            removeQueriesFromResource(set);
                            changed = true;
                        }
                        case 4 -> {
                            removeQueriesFromResourceAndDate(set);
                            changed = true;
                        }
                        case 5 -> listUsersFromResource(set);
                        case 6 -> listUsersFromResourceAndDate(set);
                        case 7 -> listUsersFromResourceAndDateRange(set);
                        case 8 -> mostQueriedResource(set);
                        case 9 -> listResourcesFromUser(set);
                        case 10 -> userHasConsultedResource(set);
                        case 11 -> backup(set);
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
        System.out.println("\t1.  Show the set's queries.");
        System.out.println("\t2.  Add a new query to the set.");
        System.out.println("\t3.  Remove all the queries from a resource.");
        System.out.println("\t4.  Remove all the queries from a resource on a specific date");
        System.out.println("\t5.  See a list of users that queried a resource.");
        System.out.println("\t6.  See a list of users of a resource on a specific date.");
        System.out.println("\t7.  See a list of users of a resource on a given date range.");
        System.out.println("\t8.  The resource most queried by users.");
        System.out.println("\t9.  See a list of all the resources queried by a specific user.");
        System.out.println("\t10. See if a user has consulted a resource.");
        System.out.println("\t11. Make a backup of the data.");
        System.out.println("\t0.  Exit.");
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
     * Method to convert a list of String to a printable String
     *
     * @param data with the strings
     * @return printable String
     */
    public static String listToString(String[] data) {
        StringBuilder information = new StringBuilder("[ ");
        if (data != null) {
            for (int index = 0; index < data.length && data[index] != null; index++) {
                information.append(data[index]).append(",  ");
            }
            if (information.length() > 3) {
                information.delete(information.length() - 3, information.length() - 1);
            }
            information.append("]");
        }
        return information.toString();
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
     * Read a string after showing the user a message
     *
     * @param message to show in terminal
     * @return the read string
     */
    public static String readString(String message) {
        String data;
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
        String resource = readString("\n\tIntroduce the resource's name: ");
        String user = readString("\tIntroduce the user's name: ");
        set.addQuery(new Query(resource, user, getDate()));
    }

    /**
     * Removes all the queries from a resource
     *
     * @param set where to delete the resource
     */
    public static void removeQueriesFromResource(ADTsetResources set) {
        String resource = readString("\n\tIntroduce the resource's name: ");
        set.removeQueriesFromResource(resource);
        System.out.println("\tAll the queries from " + resource + " deleted!");
    }

    /**
     * Removes all the queries from a resource of a specific date
     *
     * @param set where to delete
     */
    public static void removeQueriesFromResourceAndDate(ADTsetResources set) {
        String resource = readString("\n\tIntroduce the resource's name: ");
        Date date = getDate();
        set.removeQueriesFromResourceDate(resource, date);
        System.out.println("\tAll the queries from " + resource + " at " + date + " were successfully deleted!");
    }

    /**
     * List of users that queried a resource
     *
     * @param set to find
     */
    public static void listUsersFromResource(ADTsetResources set) {
        String resource = readString("\n\tIntroduce the resource's name: ");
        System.out.println("\tThe users that queried the " + resource + " were:\n");
        System.out.println(listToString(set.getUsersFromResource(resource)));
    }

    /**
     * Shows a list of users that queried a resource on a specific date
     *
     * @param set information
     */
    public static void listUsersFromResourceAndDate(ADTsetResources set) {
        String resource = readString("\n\tIntroduce the resource's name: ");
        Date date = getDate();
        System.out.println("\tThe users that queried the " + resource + " the " + date + " were:\n");
        System.out.println(listToString(set.getUsersFromResourceDate(resource, date)));
    }

    /**
     * Shows a list with the users who queried a resource in a given date range
     *
     * @param set information
     */
    public static void listUsersFromResourceAndDateRange(ADTsetResources set) {
        String resource = readString("\n\tIntroduce the resource's name: ");
        System.out.println("\tIntroducing the start of the range ");
        Date start = getDate();
        System.out.println("\tIntroducing the end of the range ");
        Date end = getDate();
        System.out.println("\tThe users that queried the " + resource + " in the range of " + start + " to" + end + "were:\n");
        System.out.println(listToString(set.getUsersFromResourceDateRange(resource, start, end)));
    }

    /**
     * The most queried resource
     *
     * @param set information
     */
    public static void mostQueriedResource(ADTsetResources set) {
        System.out.println("\n\tThe most queried resource is  " + set.getMostQueriedResource());
    }

    /**
     * The resources queried by a user
     *
     * @param set information
     */
    public static void listResourcesFromUser(ADTsetResources set) {
        String user = readString("\n\tIntroduce the user's name: ");
        System.out.println("\tThe resources queried by " + user + " were:\n");
        System.out.println(listToString(set.getResourcesFromUser(user)));
    }

    /**
     * Shows if a given user has consulted a given resource
     *
     * @param set information
     */
    public static void userHasConsultedResource(ADTsetResources set) {
        String resource = readString("\n\tIntroduce the resource's name: ");
        String user = readString("\tIntroduce the user's name: ");
        if (set.userHasConsultedResource(resource, user))
            System.out.println("\tThe user " + user + " has consulted the resource " + resource);
        else System.out.println("\tThe user " + user + " has not consulted the resource " + resource);
    }

    /**
     * Store the data in a backup file
     *
     * @param set with the information to write in file
     */
    public static void backup(ADTsetResources set) {
        String file = readString("\n\tChoose the name of the file: ");
        file = "src/apps/" + file;
        WriteData.write(set, file);
    }
}


