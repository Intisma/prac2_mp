package inputOutput;

import information.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * Class that allows to construct a set from the data stored in a file
 */
public class ReadData {

    /**
     * Read the information from a file
     *
     * @param fileName name of the file that contains the info
     * @return the constructed set with the information of the file
     */
    public static ADTsetResources read(String fileName, int type) {
        int numLinesRead = 0;
        ADTsetResources set;
        try {
            BufferedReader fileReader = new BufferedReader(new FileReader(fileName));
            int numLines = (int) fileReader.lines().count();
            fileReader.close();
            Scanner textScanner = new Scanner(new File(fileName));
            String[] readLines = new String[numLines];
            for (int index = 0; index < numLines; index++) {
                String text = textScanner.nextLine();
                if (text != null & !Objects.equals(text, "")) {
                    readLines[numLinesRead] = text;
                    numLinesRead++;
                }
            }
            textScanner.close();
            // Once the file is read we create the structure
            switch (type) {
                case 0 -> set = new StaticSetResources();
                case 2 -> set = new DynamicSetResources();
                case 3 -> set = new DynamicSecondSetResources();
                default -> set = new StaticSecondSetResources();
            }
            if (numLinesRead > 0) {
                for (int index = 0; index < numLinesRead; index++) {
                    String information = readLines[index];
                    StringTokenizer separator = new StringTokenizer(information, ",");
                    // Only valid because Java assures left to right evaluation in constructors
                    set.addQuery(new Query(separator.nextToken(), separator.nextToken(), new Date(Integer.parseInt(separator.nextToken()), (char) Integer.parseInt(separator.nextToken()),
                            (char) Integer.parseInt(separator.nextToken()), (char) Integer.parseInt(separator.nextToken()), (char) Integer.parseInt(separator.nextToken()),
                            (char) Integer.parseInt(separator.nextToken()))));
                }
            }
            return set;
        } catch (IOException e) {
            System.out.println("ERROR: FILE NOT FOUND");
            return null;
        }
    }
}
