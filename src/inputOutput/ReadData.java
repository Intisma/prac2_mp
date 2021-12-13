package inputOutput;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;
import java.util.StringTokenizer;

import information.*;

public class ReadData {
    /**
     * Empty constructor
     */
    public ReadData() {
    }

    /**
     * Read the information from a file
     *
     * @param file name of the file that contains the info
     * @return an array of Strings with all the information
     */
    public ADTsetResources read(String file, int type) {
        int cont = 0, j = 0, i, year;
        String information, resource, user, aux3, aux4;
        StringTokenizer aux2, aux5, aux6;
        char month, day, hour, minute, second;
        ADTsetResources info = null;
        try {
            BufferedReader f = new BufferedReader(new FileReader(file));
            int numLines = (int) f.lines().count();
            f.close();
            Scanner ft = new Scanner(new File(file));
            String[] aux = new String[numLines];

            for (i = 0; i < numLines; i++) {
                String text = ft.nextLine();
                if (text != null & !Objects.equals(text, "")) {
                    aux[j] = text;
                    j++;
                }
            }
            ft.close();
            if (j!= 0) {
                switch (type) {
                    case 1 -> info = new StaticSetResources();
                    case 2 -> info = new StaticSecondSetResources();
                    case 3 -> info = new DynamicSecondSetResouces();
                    case 4 -> info = new DynamicSetResources();
                    default -> System.out.println("Type doesn't exists!");
                }
                for (i = 0; i < j; i++) {
                    information = aux[i];
                    aux2 = new StringTokenizer(information, ";");
                    resource = aux2.nextToken();
                    user = aux2.nextToken();
                    aux3 = aux2.nextToken(" ");
                    aux4 = aux2.nextToken();
                    aux5 = new StringTokenizer(aux3, "/");
                    aux6 = new StringTokenizer(aux4, ":");
                    day = (char) Integer.parseInt(aux5.nextToken().replace(";", ""));
                    month = (char) Integer.parseInt(aux5.nextToken());
                    year = Integer.parseInt(aux5.nextToken().replace("/", ""));
                    hour = (char) Integer.parseInt(aux6.nextToken().replace(" ", ""));
                    minute = (char) Integer.parseInt(aux6.nextToken());
                    second = (char) Integer.parseInt(aux6.nextToken());
                    //System.out.println(aux3+"   "+aux4+ "  " +year+"-"+month+"-"+day+"  "+hour+":"+minute+":"+second);
                    Date date = new Date(year, month, day, hour, minute, second);
                    Query query = new Query(resource, user, date);
                    info.addQuery(query);
                }

            }
            else System.out.println("Empty file!");
            return info;
        } catch (IOException e) {
            return null;
        }
    }
}
