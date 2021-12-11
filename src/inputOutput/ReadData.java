package inputOutput;

import information.ADTsetResources;

import java.io.*;
import java.util.Objects;
import java.util.Scanner;

public class ReadData {
    /**
     * Empty constructor
     */
    public ReadData() {
    }

    /**
     * Read the information from a file
     * @param file name of the file that contains the info
     * @return an array of Strings with all the information
     */
    public String[] read(String file) {
        int cont=0, j=0;
        try {
            BufferedReader f = new BufferedReader(new FileReader(file));
            int numLines = (int) f.lines().count();
            f.close();
            Scanner ft = new Scanner(new File(file));
            String[] aux = new String[numLines];
            int i;
            for (i = 0; i < numLines; i++) {
                String text = ft.nextLine();
                if (text != null & !Objects.equals(text, "")) {
                    aux[j] = text;
                    j++;
                    cont++;
                }
            }
            ft.close();
            String[] info = new String[cont];
            for (i = 0; i < cont; i++) info[i] = aux[i];
            return info;
        } catch (IOException e) {
            return null;
        }
    }
}
