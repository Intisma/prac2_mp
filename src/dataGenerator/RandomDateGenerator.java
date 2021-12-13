package dataGenerator;

import information.Date;
import staticInformation.Dates;

import java.util.Random;

/**
 * Class to generate random dates
 */
public class RandomDateGenerator {
    private final Random dateGenerator;

    /**
     * Constructor
     */
    public RandomDateGenerator() {
        dateGenerator = new Random();
    }

    /**
     * Method to generate random dates
     *
     * @return a class containing the generated dates
     */
    public Dates generateRandomDates(int size) {
        if (size < 10 || size > Dates.maxDates) size = Dates.maxDates;
        Dates dates = new Dates();
        char month;
        int maxDay;
        for (int index = 0; index < size; index++) {
            month = (char) dateGenerator.nextInt(1, 12);
            switch (month) {
                case 1, 3, 5, 7, 8, 10, 12 -> maxDay = 31;
                case 2 -> maxDay = 28;
                default -> maxDay = 30;
            }
            dates.addDate(new Date(dateGenerator.nextInt(1990, 2021), month, (char) dateGenerator.nextInt(1, maxDay), (char) dateGenerator.nextInt(0, 23), (char) dateGenerator.nextInt(0, 59), (char) dateGenerator.nextInt(0, 59)));
        }
        return dates;
    }
}
