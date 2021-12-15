package timeMeasurer;

import inputOutput.WriteTimes;

/**
 * Class used to execute the code corresponding to the time measure
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
        times = TimeMeasurer.sizeEvolution(100000, 20, 0);
        WriteTimes.write(times, "TimeResultsStatic.csv");

        // Generate times for the second static implementation
        times = TimeMeasurer.sizeEvolution(100000, 20, 1);
        WriteTimes.write(times, "TimeResultsStaticSecond.csv");

        // Generate times for the first dynamic implementation
        /*times = TimeMeasurer.sizeEvolution(100000, 10, 2);
        WriteTimes.write(times, "TimeResultsDynamic.csv");

        // Generate times for the second dynamic implementation
        times = TimeMeasurer.sizeEvolution(100000, 10, 3);
        WriteTimes.write(times, "TimeResultsDynamicSecond.csv");*/
    }
}
