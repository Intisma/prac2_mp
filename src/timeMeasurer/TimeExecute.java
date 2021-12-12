package timeMeasurer;

import inputOutput.WriteTimes;

public class TimeExecute {
    public static void main(String[] args){
        Times times = TimeMeasurer.sizeEvolution(-1, 10, false);
        WriteTimes.write(times, "TimeResults.csv");
    }
}
