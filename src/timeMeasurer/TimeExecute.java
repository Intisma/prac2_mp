package timeMeasurer;

import inputOutput.WriteTimes;

/**
 * This class is used to execute the code corresponding to the time measures. Once these measures are finished,
 * the results are stored in a file. By default, the size values are 2048 to facilitate the execution.
 */
public class TimeExecute {
    /**
     * Main to generate all the times. WARNING, the time cost of generating the data in all the repeats and perform the different
     * activities is high. It is recommended to execute one at a time.
     *
     * @param args not used
     */
    public static void main(String[] args) {
        Times times;

        // Generate times for the first static implementation
        times = TimeMeasurer.sizeEvolution(2048, 5, 0);
        WriteTimes.write(times, "TimeResultsStatic.csv");

        // Generate times for the second static implementation
        times = TimeMeasurer.sizeEvolution(2048, 5, 1);
        WriteTimes.write(times, "TimeResultsStaticSecond.csv");

        // Generate times for the first dynamic implementation
        times = TimeMeasurer.sizeEvolution(2048, 5, 1);
        WriteTimes.write(times, "TimeResultsStaticSecond.csv");

        // Generate times for the second dynamic implementation
        times = TimeMeasurer.sizeEvolution(2048, 5, 3);
        WriteTimes.write(times, "TimeResultsDynamicSecondMod.csv");
    }
}
