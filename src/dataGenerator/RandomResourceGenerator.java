package dataGenerator;

import staticInformation.Resources;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class RandomResourceGenerator {
    private final ArrayList<String> resource;
    private final ArrayList<String> extension;
    private final Random resourceGenerator;

    /**
     * Constructor
     */
    public RandomResourceGenerator() {
        resource = new ArrayList<>();
        extension = new ArrayList<>();
        resourceGenerator = new Random();
    }

    /**
     * Method to add resources names from text file to resource ArrayList
     */
    public void setResource() throws FileNotFoundException {
        Scanner resourceReader = new Scanner(new File("./src/dataGenerator/Resources.txt"));
        while (resourceReader.hasNext()) {
            String firstName = resourceReader.nextLine();
            resource.add(firstName);
        }
    }

    /**
     * Method to add extensions names from text file to extension ArrayList
     */
    public void setExtension() throws FileNotFoundException {
        Scanner extensionReader = new Scanner(new File("./src/dataGenerator/Extensions.txt"));
        while (extensionReader.hasNext()) {
            String lastName = extensionReader.nextLine();
            extension.add(lastName);
        }
    }

    /**
     * Method to generate random names attained from the text files
     */
    public Resources generateRandomResources(int size) {
        if (size < 10 || size > Resources.maxResources) size = Resources.maxResources;
        Resources resources = new Resources();
        try {
            this.setResource();
            this.setExtension();

            for (int i = 0; i < size; i++) {
                resources.addResource(resource.get(resourceGenerator.nextInt(resource.size())) + "." + extension.get(resourceGenerator.nextInt(extension.size())));
            }
        } catch (IOException e) {
            System.out.println("ERROR: COULD NOT OPEN THE FILES, RESOURCES NOT GENERATED CORRECTLY");
        }
        return resources;
    }
}
