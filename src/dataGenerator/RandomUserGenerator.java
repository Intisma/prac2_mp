package dataGenerator;

import information.Users;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class RandomUserGenerator {
    private final ArrayList<String> firstNames;
    private final ArrayList<String> lastNames;
    Scanner firstReader;
    Scanner lastReader;
    Random nameGenerator;

    /**
     * Constructor
     */
    public RandomUserGenerator() {
        firstNames = new ArrayList<>();
        lastNames = new ArrayList<>();


        nameGenerator = new Random();
    }

    /**
     * Method to add first names from text file to firstNames ArrayList
     */
    public void setFirstNames() throws FileNotFoundException {
        firstReader = new Scanner(new File("./src/dataGenerator/FirstNames.txt"));
        while (firstReader.hasNext()) {
            String firstName = firstReader.nextLine();
            firstNames.add(firstName);
        }
    }

    /**
     * Method to add last names from text file to lastNames ArrayList
     */
    public void setLastNames() throws FileNotFoundException {
        lastReader = new Scanner(new File("./src/dataGenerator/LastNames.txt"));
        while (lastReader.hasNext()) {
            String lastName = lastReader.nextLine();
            lastNames.add(lastName);
        }
    }

    /**
     * Method to generate random names attained from the text files
     */
    public Users generateRandomUsers(int size) {
        if (size < 1 || size > Users.maxUsers) size = Users.maxUsers;
        Users users = new Users();
        try {
            this.setFirstNames();
            this.setLastNames();
            for (int i = 0; i < size; i++) {
                users.addUser(firstNames.get(nameGenerator.nextInt(firstNames.size())) + " " + lastNames.get(nameGenerator.nextInt(lastNames.size())));
            }
        } catch (IOException e) {
            System.out.println("ERROR: COULD NOT OPEN THE FILES, USERS NOT GENERATED CORRECTLY");
        }

        return users;
    }
}