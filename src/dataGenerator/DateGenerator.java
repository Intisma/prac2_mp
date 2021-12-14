package dataGenerator;

import inputOutput.WriteData;

/**
 * Class to generate a large file of queries
 */
public class DateGenerator {
    public static void main(String[] args) {
        RandomSetGenerator setGenerator = new RandomSetGenerator();
        WriteData.write(setGenerator.generateSet(1, 200000), "src/dataGenerator/generatedData.csv");
    }
}
