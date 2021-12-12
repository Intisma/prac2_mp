package inputOutput;

import information.ADTsetResources;
import timeMeasurer.Times;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class WriteTimes {

    /**
     * Write the time data in a result file
     * @param information to save in the file
     * @param file name of the file
     * @return boolean to know if the writing has gone correctly
     */
    public static boolean write(Times information, String file) {
        try {
            file = "src/generatedInformation/" +file;
            BufferedWriter result = new BufferedWriter(new FileWriter(file));
            result.write("size,add,removeResource,removeResourceDate,usersFromResource,usersFromResourceDate,mostQueried,resourceFromUser\n");
            result.write(information.toString());
            result.close();
            return true;
        } catch (IOException e) {
            return false;
        }
    }
}
