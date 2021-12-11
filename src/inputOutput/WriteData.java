package inputOutput;

import information.ADTsetResources;

import java.io.*;

public class WriteData {

    /**
     * Empty constructor
     */
    public WriteData() {
    }

    /**
     * Write the data in a result file
     * @param information to save in the file
     * @param file name of the file
     * @return boolean to know if the writing gone correctly
     */
    public boolean write(ADTsetResources information, String file) {
        try {
            BufferedWriter result = new BufferedWriter(new FileWriter(file));
            result.write(information.toString());
            result.close();
            return true;
        } catch (IOException e) {
            return false;
        }
    }
}