package inputOutput;

import information.ADTsetResources;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class WriteData {

    /**
     * Write the data in a file
     *
     * @param set  to save in the file
     * @param file name of the file
     * @return true if the writing went correctly, false if not
     */
    public static boolean write(ADTsetResources set, String file) {
        try {
            BufferedWriter result = new BufferedWriter(new FileWriter(file));
            result.write(set.toStringFile());
            result.close();
            return true;
        } catch (IOException e) {
            return false;
        }
    }
}